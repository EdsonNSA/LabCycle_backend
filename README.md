# LabCycle Backend

API Spring Boot do LabCycle.

## Requisitos

- Java 17 ou superior (JDK completo, incluindo `javac`)
- MySQL

## Configuração local

Defina as variáveis de ambiente antes de iniciar a API:

```bash
export DB_URL='jdbc:mysql://localhost:3306/labcycle_db?createDatabaseIfNotExist=true'
export DB_USERNAME='root'
export DB_PASSWORD='sua-senha-do-mysql'
export API_SECURITY_TOKEN_SECRET='uma-chave-longa-e-aleatoria'
export CORS_ALLOWED_ORIGINS='http://localhost:3000,https://labcycle.netlify.app'
```

A chave JWT e a senha do banco não devem ser versionadas no repositório.

## Executar

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
mvnw.cmd spring-boot:run
```

Para expor a API local pela internet durante o desenvolvimento:

```bash
ngrok http 8080
```

## Testes

```bash
./mvnw test
```

Os testes usam H2 em memória e não dependem de um MySQL local.

## Endpoints de autenticação

- `POST /auth/login`
- `POST /auth/register`

O cadastro público cria usuários com o papel `USER`. A criação de administradores deve ser feita por um fluxo administrativo controlado.
