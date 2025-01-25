package com.simplebank.data;

import com.simplebank.domain.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já possui cadastro", HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFound(EntityNotFoundException exception){
      return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity threatForbiddenException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Falha no sistema", HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDTO);
    }

    @ExceptionHandler(HttpServerErrorException.GatewayTimeout.class)
    public ResponseEntity threatTimeoutException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Sistema indisponível", HttpStatus.GATEWAY_TIMEOUT);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

}
