CREATE TABLE pagamento (
                           id BIGSERIAL PRIMARY KEY,
                           valor_pago NUMERIC(19,2) NOT NULL,
                           data_pagamento TIMESTAMP NOT NULL,
                           forma_pagamento VARCHAR(30) NOT NULL,
                           fatura_id BIGINT NOT NULL
);