CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mesaje VARCHAR(500) NOT NULL,
    status TINYINT,
    usuarioId BIGINT NOT NULL,
    cursoId BIGINT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT `fk_topico_usuario` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `fk_topico_curso` FOREIGN KEY (`cursoId`) REFERENCES `curso` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)