# Computer Vault

Este projeto consiste em uma API utilizando Spring Boot para gerenciamento de estoque de computadores. O banco de dados utilizado é o PostgreSQL, e todas as dependências são gerenciadas e executadas através do Docker.

## Sumário
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Instalação

Para instalar e executar este projeto localmente, siga os passos abaixo:

1. Clone o repositório:

    ```bash
    git clone https://github.com/Viniciusggabriel/computer-vault.git
    cd computer-vault/server
    ```

2. Configure o Docker e o Docker Compose:

    Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.

3. Construa e inicie os containers:

    ```bash
    docker-compose up --build
    ```

    Isso iniciará o PostgreSQL e o servidor Spring Boot.

## Configuração

Certifique-se de que as variáveis de ambiente necessárias estão definidas corretamente. Elas podem ser configuradas no arquivo `application.properties` ou através do Docker Compose.

Exemplo de configuração do `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banco
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
Uso
Após iniciar os containers, a API estará disponível em http://localhost:8080.

Endpoints
Login
Antes de utilizar qualquer outro endpoint, é necessário realizar o login:

http
Copiar código
POST /login
Content-Type: application/json

{
    "username": "User",
    "password": "User"
}
Este endpoint irá gerar um token do tipo Bearer que deve ser usado nos headers das requisições subsequentes.

Listar todos os computadores
http
Copiar código
GET /v1/computer?page=0&size=20
Authorization: Bearer {token}
Adicionar um novo computador
http
Copiar código
POST /v1/computer
Content-Type: application/json
Authorization: Bearer {token}

{
    "dados-gerais": {
        "quem-adicionou": "User",
        "condicoes": "NOVO",
        "unidade-de-negocio": "HOSPITAL",
        "departamento": "COLETA",
        "numero-patrimonio": "221103",
        "local": "POSTO"
    },
    "hardware": {
        "marca-computador": "DELL",
        "tipo-computador": "DESKTOP",
        "nome": "Nome6",
        "ip": "192.168.1.6",
        "processador": "i5-8500",
        "memoria-ram": 32,
        "frequencia-ram": 2400,
        "tipo-ram": "DDR3",
        "modelo-ram": "DIMM",
        "quantidade-instalada": 2,
        "HD": 256,
        "SSD": 512
    },
    "software": {
        "sistema-operacional": "W11"
    }
}
Atualizar um computador
http
Copiar código
PATCH /v1/computer/{id}
Content-Type: application/json
Authorization: Bearer {token}

{
    "dados-gerais": {
        "quem-adicionou": "User",
        "condicoes": "NOVO",
        "unidade-de-negocio": "HOSPITAL",
        "departamento": "COLETA",
        "numero-patrimonio": "221103",
        "local": "POSTO"
    },
    "hardware": {
        "marca-computador": "DELL",
        "tipo-computador": "DESKTOP",
        "nome": "Nome6",
        "ip": "192.168.1.6",
        "processador": "i5-8500",
        "memoria-ram": 32,
        "frequencia-ram": 2400,
        "tipo-ram": "DDR3",
        "modelo-ram": "DIMM",
        "quantidade-instalada": 2,
        "HD": 256,
        "SSD": 512
    },
    "software": {
        "sistema-operacional": "W11"
    }
}
Deletar um computador
http
Copiar código
DELETE /v1/computer/{id}
Authorization: Bearer {token}
