# Introdução

Este projeto utiliza imagens Docker e Docker Compose para executar um aplicativo com as seguintes tecnologias:

* **Swagger:** Documentação completa da API.
* **JWT:** Autenticação e autorização seguras.
* **OAuth2:** Fluxo de autenticação flexível.
* **Postgresql:** Banco de dados relacional confiável.
* **Lombok:** Simplifica a codificação Java.
* **JPA:** Camada de persistência abrangente.
* **Hibernate:** Implementação JPA robusta.

## Pré-requisitos

* Docker instalado: [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)
* Podman instalado (opcional): [https://podman.io/docs/installation](https://podman.io/docs/installation)

### Passo a Passo

1**Configure o arquivo `.env`:**

```
POSTGRES_DB=banco
POSTGRES_USER=user
POSTGRES_PASSWORD=password
PGADMIN_DEFAULT_EMAIL=example@email.com
PGADMIN_DEFAULT_PASSWORD=password
POSTGRES_DB_URL=jdbc:postgresql://postgres:5432/banco
```

2**Execute o aplicativo:**

**A. Com Docker Compose:**

```bash
docker-compose up -d
```

**B. Com Docker (sem Compose):**

1. Construa as imagens:
```bash
docker-build -t app-image .
docker-build -t postgres-image ./postgres
```

2. Execute os containers:
```bash
docker run -d --name postgres -p 5432:5432 postgres-image
docker run -d --name app -p 8080:8080 --link postgres:postgres app-image
```

**C. Execute o script de inicialização:**
Execute o arquvip 

```java
ServerApplication.java
```
Após executar dessa forma, será necessário ter o docker instalado no seu ambiente, pois é necessário para subir o banco de dados e pgadmin

4. **Acesse o Swagger:**

O Swagger estará disponível em `http://localhost:8080/swagger-ui.html`.

**Observações:**

* Este guia assume que você já possui o código do seu aplicativo em um diretório local.
* As variáveis de ambiente no arquivo `.env` podem ser personalizadas de acordo com suas necessidades.
* Para mais informações sobre as tecnologias utilizadas, consulte a documentação oficial de cada uma.