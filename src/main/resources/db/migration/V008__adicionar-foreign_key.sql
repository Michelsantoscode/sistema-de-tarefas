ALTER TABLE tarefa
    ADD COLUMN usuario_id BIGINT,
ADD CONSTRAINT fk_tarefa_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuario(id);