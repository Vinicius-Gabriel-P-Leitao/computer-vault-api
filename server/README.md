# Introdução

Este projeto utiliza imagens Docker e Docker Compose para executar um aplicativo com as seguintes tecnologias:

* **JWT:** Autenticação e autorização seguras.
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
NAME_ADMIN=User
PASSWORD_ADMIN=User
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