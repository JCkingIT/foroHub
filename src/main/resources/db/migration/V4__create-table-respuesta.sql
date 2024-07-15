CREATE TABLE respuesta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(500) NOT NULL,
    status TINYINT,
    solucion TINYINT,
    usuario_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT `fk_respuesta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `fk_respuesta_topico` FOREIGN KEY (`topico_id`) REFERENCES `topico` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)