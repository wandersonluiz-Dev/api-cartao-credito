CREATE TABLE fatura (
                        id BIGSERIAL PRIMARY KEY,
                        mes_referencia VARCHAR(20) NOT NULL,
                        valor_total NUMERIC(19,2) NOT NULL,
                        valor_minimo NUMERIC(19,2) NOT NULL,
                        vencimento DATE NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        cartao_id BIGINT NOT NULL
);