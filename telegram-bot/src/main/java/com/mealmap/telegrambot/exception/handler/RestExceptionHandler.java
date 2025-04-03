package com.mealmap.telegrambot.exception.handler;

import com.mealmap.telegrambot.core.message.ErrorCauseMessages;
import com.mealmap.telegrambot.exception.ForbiddenException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class})
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadableException(Exception e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(
                        BAD_REQUEST,
                        e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNoValidException(MethodArgumentNotValidException e) {
        Map<String, List<String>> errorMap = new HashMap<>();

        getValidationErrors(errorMap, e);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(
                        BAD_REQUEST,
                        errorMap.toString()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, List<String>> errorMap = new HashMap<>();

        getValidationErrors(errorMap, e);

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(
                        BAD_REQUEST,
                        errorMap.toString()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ProblemDetail> handleForbiddenException(ForbiddenException e) {
        return ResponseEntity
                .status(FORBIDDEN)
                .body(ProblemDetail.forStatusAndDetail(
                        FORBIDDEN,
                        e.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundException(Exception e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(
                        NOT_FOUND,
                        e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleOtherException(Exception e) {
        log.error("Internal error", e);

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(ProblemDetail.forStatusAndDetail(
                        INTERNAL_SERVER_ERROR,
                        ErrorCauseMessages.INTERNAL_SERVER_ERROR));
    }

    private void getValidationErrors(Map<String, List<String>> errorMap, Exception e) {
        BiConsumer<String, String> addError = (field, message) ->
                errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);

        if (e instanceof MethodArgumentNotValidException validationEx) {
            validationEx.getBindingResult()
                    .getFieldErrors().forEach(error ->
                            addError.accept(error.getField(), error.getDefaultMessage()));
        } else if (e instanceof ConstraintViolationException constraintEx) {
            constraintEx.getConstraintViolations()
                    .forEach(violation ->
                            addError.accept(violation.getPropertyPath().toString(), violation.getMessage()));
        }
    }
}
