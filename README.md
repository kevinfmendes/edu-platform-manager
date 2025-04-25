# Plataforma Educacional - Backend API

## 📝 Descrição do Projeto
API REST desenvolvida em Quarkus para gerenciamento de uma plataforma educacional, com controle de acesso baseado em roles (ADMIN, COORDENADOR, PROFESSOR, ALUNO), utilizando Keycloak como provedor de identidade, Docker Compose e Bash para orquestrar ambiente.

## 🚀 Funcionalidades Principais
* **Administração de Usuários** 
* **Gestão de Cursos e Disciplinas** 
* **Montagem da Matriz Curricular** 
* **Visualização da Matriz Curricular**
* **Autenticação e Autorização via Keycloak**

## 🛠️ Tecnologias Utilizadas
* **Java 17+** com framework **Quarkus**
* **Keycloak** para autenticação e autorização
* **Docker** e **Docker Compose** para orquestração
* **JPA/Hibernate** para persistência de dados

## ⚙️ Configuração do Ambiente

### Pré-requisitos
* Docker e Docker Compose instalados

## 🏗️ Inicialização Automatizada com `start.sh`
O script `start.sh` foi desenvolvido para simplificar ao máximo a configuração do ambiente de desenvolvimento:

1. **Configuração Completa com um Comando**:
```bash
./start.sh
```

2. **O que o script faz**:
   * Limpa containers antigos (se existirem)
   * Verifica a existência do arquivo de configuração do Keycloak
   * Sobe o container do Keycloak com Docker Compose
   * Aguarda até que o Keycloak esteja saudável e pronto para receber requisições
   * Verifica se o realm foi importado corretamente
   * Confirma que a configuração OIDC está disponível
   * Inicia a aplicação Quarkus em modo desenvolvimento

3. **Benefícios**:
   * Elimina a necessidade de configuração manual do Keycloak
   * Garante que todos os serviços estejam prontos antes de iniciar a aplicação
   * Fornece feedback claro sobre o status de cada etapa
   * Configura automaticamente usuários de teste (admin_test, aluno_test)

## 🔐 Credenciais de Teste
O Keycloak é configurado automaticamente com os seguintes usuários:

| Usuário | Senha | Role |
|---------|-------|------|
| admin_test | admin | ADMIN |
| aluno_test | aluno | ALUNO |

## 🌐 Endpoints da API
A API está documentada no Swagger UI disponível em:

```
http://localhost:8081/q/swagger-ui
```

## Considederações:
Devido ao pouco tempo livre que tive para desenvolver o projeto, optei por focar principalmente na orquestração e montagem do ambiente usando bash para subir e configurar o servidor Keycloak, além de organizar o esquema de login e registro de novos usuários 'dentro' da aplicação, além das operações principais e relacionamentos entre as entidades. Vários pontos de melhoria podem ser identificados como melhorar auth, código mais limpo, testes unitários e obviamente o desenvolvimento do front com Angular, porém devido ao prazo mais curto e ter que conciliar minhas obrigações atuais com o projeto, optei pela entrega parcial.  
