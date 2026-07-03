    CREATE TABLE cartao (
        id BIGSERIAL PRIMARY KEY,
        numero VARCHAR(255) NOT NULL,
        validade DATE NOT NULL,
        cvv CHAR(3) NOT NULL,
        limite_total NUMERIC(19,2) NOT NULL,
        limite_disponivel NUMERIC(19,2) NOT NULL,
        cliente_id BIGINT NOT NULL,

    );