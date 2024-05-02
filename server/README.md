Gerar a chave privada

- openssl genrsa > app.key

Gerar achave publica

- openssl rsa -in app.key -pubout -out app.pub

POSTGRES_DB=banco
POSTGRES_USER=user
POSTGRES_PASSWORD=password
PGADMIN_DEFAULT_EMAIL=example@email.com
PGADMIN_DEFAULT_PASSWORD=password
POSTGRES_DB_URL=jdbc:postgresql://postgres:5432/banco