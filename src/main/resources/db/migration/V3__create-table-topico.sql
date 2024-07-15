CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion DATE NOT NULL,
    status TINYINT,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT `fk_topico_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `fk_topico_curso` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)