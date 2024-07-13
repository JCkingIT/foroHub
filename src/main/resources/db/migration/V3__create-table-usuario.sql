CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    clave VARCHAR(100) NOT NULL,
    status TINYINT,
    perfil_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT `fk_usuario_perfil` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)