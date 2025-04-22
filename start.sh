#!/bin/bash

set -e

echo ""
echo "🧪 Gerando build do Quarkus..."
./mvnw clean package -DskipTests

echo ""
echo "🔄 Subindo Keycloak e MySQL..."
docker-compose up -d keycloak mysql

echo ""
echo "⏳ Aguardando Keycloak ficar disponível..."

until curl -s --head --request GET http://localhost:8080/realms/app-quarkus-realm/.well-known/openid-configuration | grep "200 OK" > /dev/null; do
  echo "🔁 Keycloak ainda iniciando..."
  sleep 2
done

echo ""
echo "✅ Keycloak está online. Subindo Quarkus app..."
docker-compose up --build quarkus-app
