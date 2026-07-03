CREATE TABLE compra (
                        id BIGSERIAL PRIMARY KEY,
                        descricao VARCHAR(255) NOT NULL,
                        valor NUMERIC(19,2) NOT NULL,
                        data_compra TIMESTAMP NOT NULL,
                        parcelas INTEGER NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        cartao_id BIGINT NOT NULL
);