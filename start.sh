#!/bin/bash
set -e

echo "==== INICIALIZANDO AMBIENTE DE DESENVOLVIMENTO ===="

# Configurações
KEYCLOAK_URL="http://localhost:8080"
REALM="app-quarkus-realm"
MAX_RETRIES=60
DELAY=5

# Funções de verificação
check_keycloak() {
  docker inspect -f '{{.State.Health.Status}}' keycloak 2>/dev/null | grep -q "healthy"
}

check_realm() {
  curl -s -o /dev/null -w "%{http_code}" "$KEYCLOAK_URL/realms/$REALM" | grep -q "200"
}

# 1. Limpar containers antigos
echo "🧹 Limpando ambiente anterior..."
docker-compose down -v > /dev/null 2>&1 || true

# 2. Verificar se o arquivo de importação existe
mkdir -p keycloak/import
if [ ! -f keycloak/import/realm-export.json ]; then
  echo "❌ Arquivo 'keycloak/import/realm-export.json' não encontrado!"
  exit 1
fi

# 3. Subir o Keycloak
echo "🚀 Subindo Keycloak com Docker Compose..."
docker-compose up -d

# 4. Aguardar Keycloak ficar healthy
echo "⏳ Aguardando Keycloak iniciar..."
count=0
while [ $count -lt $MAX_RETRIES ]; do
  if check_keycloak; then
    echo "✅ Keycloak está pronto!"
    break
  fi
  count=$((count+1))
  echo "⌛ Tentativa $count/$MAX_RETRIES - Aguardando $DELAY segundos..."
  sleep $DELAY
done

if [ $count -eq $MAX_RETRIES ]; then
  echo "❌ Keycloak não ficou 'healthy' após $(($MAX_RETRIES * $DELAY))s"
  docker-compose logs keycloak
  exit 1
fi

# 5. Aguardar realm importado
echo "⏳ Aguardando realm '$REALM' ser importado..."
count=0
while [ $count -lt $MAX_RETRIES ]; do
  if check_realm; then
    echo "✅ Realm '$REALM' disponível!"
    break
  fi
  count=$((count+1))
  echo "⌛ Tentativa $count/$MAX_RETRIES - Aguardando $DELAY segundos..."
  sleep $DELAY
done

if [ $count -eq $MAX_RETRIES ]; then
  echo "❌ Realm '$REALM' não ficou disponível após $(($MAX_RETRIES * $DELAY))s"
  docker-compose logs keycloak
  exit 1
fi

# 6. Verificar OIDC
echo "🔐 Verificando configuração OIDC..."
oidc_status=$(curl -s -o /dev/null -w "%{http_code}" "$KEYCLOAK_URL/realms/$REALM/.well-known/openid-configuration")
if [[ "$oidc_status" == "200" ]]; then
  echo "✅ Configuração OIDC disponível!"
else
  echo "❌ Configuração OIDC não está disponível (status: $oidc_status)"
  exit 1
fi

# 7. Rodar Quarkus
echo -e "\n🎉 Ambiente pronto! Iniciando Quarkus...\n"
echo "🌐 Acesse Keycloak em: $KEYCLOAK_URL/admin (admin/admin)"
echo "🔑 Realm: $REALM"
echo -e "\n📝 Para ver os logs: docker-compose logs -f keycloak\n"

./mvnw quarkus:dev -Ddebug
