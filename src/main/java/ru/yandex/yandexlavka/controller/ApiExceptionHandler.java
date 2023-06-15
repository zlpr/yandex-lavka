package ru.yandex.yandexlavka.controller;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yandex.yandexlavka.exception.BadRequestException;
import ru.yandex.yandexlavka.exception.NotFoundException;
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String EMPTY_BODY = "{}";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.error(ex.getMessage(),ex);
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> createResponseEntity(Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return ResponseEntity.status(statusCode).body(EMPTY_BODY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintValidationException(ConstraintViolationException e) {
        log.warn(e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMPTY_BODY);
    }

    @ExceptionHandler({ RequestNotPermitted.class })
    public ResponseEntity<?> requestNotPermitted(RequestNotPermitted e) {
        log.warn(e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(EMPTY_BODY);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.warn(e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EMPTY_BODY);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleNotFoundException(BadRequestException e) {
        log.warn(e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMPTY_BODY);
    }


}
