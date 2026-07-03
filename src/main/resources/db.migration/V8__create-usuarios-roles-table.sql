CREATE TABLE usuarios_roles (
                                usuario_id BIGINT NOT NULL,
                                role_id BIGINT NOT NULL,

                                PRIMARY KEY (usuario_id, role_id),

                                CONSTRAINT fk_usuario
                                    FOREIGN KEY (usuario_id)
                                        REFERENCES usuarios(id),

                                CONSTRAINT fk_role
                                    FOREIGN KEY (role_id)
                                        REFERENCES roles(id)
);