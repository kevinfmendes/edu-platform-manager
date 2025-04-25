#!/bin/bash
set -e

echo "==== INICIALIZANDO AMBIENTE DE DESENVOLVIMENTO ===="

# Configurações
KEYCLOAK_URL="http://127.0.0.1:8080"
REALM="app-quarkus-realm"
MAX_RETRIES=60
DELAY=5
KEYCLOAK_CONTAINER=keycloak
REALM_FILE=keycloak/import/realm-export.json

# Verifica se o arquivo JSON existe
echo "📁 Verificando arquivo de importação..."
if [ ! -f "$REALM_FILE" ]; then
  echo "❌ Arquivo '$REALM_FILE' não encontrado!"
  exit 1
fi
head -n 10 "$REALM_FILE"

# 1. Limpar containers antigos
echo "🧹 Parando containers antigos..."
docker-compose down -v > /dev/null 2>&1 || true

# 2. Subir o Keycloak
echo "🚀 Subindo Keycloak com Docker Compose..."
docker-compose up -d

# 3. Aguardar Keycloak estar disponível via HTTP
echo "⏳ Aguardando Keycloak iniciar (HTTP 200)..."
count=0
while [ $count -lt $MAX_RETRIES ]; do
  status=$(curl -s -o /dev/null -w "%{http_code}" "$KEYCLOAK_URL")
  if [[ "$status" == "200" ]]; then
    echo "✅ Keycloak está acessível em $KEYCLOAK_URL"
    break
  fi
  count=$((count+1))
  echo "⌛ Tentativa $count/$MAX_RETRIES - Aguardando $DELAY segundos..."
  sleep $DELAY
done

if [ $count -eq $MAX_RETRIES ]; then
  echo "❌ Keycloak não respondeu em $((MAX_RETRIES * DELAY))s"
  docker-compose logs $KEYCLOAK_CONTAINER
  exit 1
fi

# 4. Importar realm via kcadm.sh
echo "🔐 Logando no Keycloak via kcadm.sh..."
docker exec -i $KEYCLOAK_CONTAINER /opt/keycloak/bin/kcadm.sh config credentials \
  --server http://localhost:8080 \
  --realm master \
  --user admin \
  --password admin

echo "📦 Importando realm '$REALM' via kcadm.sh..."
docker exec -i $KEYCLOAK_CONTAINER /opt/keycloak/bin/kcadm.sh create realms -f /opt/keycloak/data/import/realm-export.json

echo "✅ Realm '$REALM' importado com sucesso!"

# 5. Verificar OIDC metadata
echo "🔐 Verificando OIDC metadata..."
oidc_status=$(curl -s -o /dev/null -w "%{http_code}" "$KEYCLOAK_URL/realms/$REALM/.well-known/openid-configuration")
if [[ "$oidc_status" == "200" ]]; then
  echo "✅ Configuração OIDC disponível!"
else
  echo "❌ Configuração OIDC não disponível (status: $oidc_status)"
  exit 1
fi

# 6. Iniciar Quarkus
echo -e "\n🎉 Ambiente pronto! Iniciando Quarkus...\n"
echo "🌐 Keycloak: $KEYCLOAK_URL/admin (admin / admin)"
echo "🔑 Realm: $REALM"
echo -e "\n📝 Para logs: docker-compose logs -f keycloak\n"

./mvnw quarkus:dev -Ddebug
