package dev.foro_hub.foroHub.infra.error;

import org.springframework.http.HttpStatus;

public record Answer(Boolean success, Integer code, HttpStatus status, String message) {
}
