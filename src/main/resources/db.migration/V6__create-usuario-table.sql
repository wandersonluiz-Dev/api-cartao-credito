CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cliente_id BIGINT UNIQUE,
    CONSTRAINT fk_usuario_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES clientes(id)
);