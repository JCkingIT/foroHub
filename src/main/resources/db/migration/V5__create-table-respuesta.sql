CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mesaje VARCHAR(500) NOT NULL,
    status TINYINT,
    usuarioId BIGINT NOT NULL,
    topicoId BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT `fk_respuesta_usuario` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `fk_respuesta_topico` FOREIGN KEY (`topicoId`) REFERENCES `topico` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)