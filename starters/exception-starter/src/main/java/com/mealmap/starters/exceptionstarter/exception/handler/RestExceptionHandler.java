package com.mealmap.starters.exceptionstarter.exception.handler;

import com.mealmap.starters.exceptionstarter.core.message.ErrorCauseMessages;
import com.mealmap.starters.exceptionstarter.exception.BadRequestException;
import com.mealmap.starters.exceptionstarter.exception.ConflictException;
import com.mealmap.starters.exceptionstarter.exception.ForbiddenException;
import com.mealmap.starters.exceptionstarter.exception.ResourceNotFoundException;
import com.mealmap.starters.exceptionstarter.exception.UnauthorizedException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
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
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequestException(BadRequestException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(
                        BAD_REQUEST,
                        e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ProblemDetail> handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(ProblemDetail.forStatusAndDetail(
                        UNAUTHORIZED,
                        e.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ProblemDetail> handleForbiddenException(ForbiddenException e) {
        return ResponseEntity
                .status(FORBIDDEN)
                .body(ProblemDetail.forStatusAndDetail(
                        FORBIDDEN,
                        e.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                ex.getMessage()
        );
        problemDetail.setTitle("Access Denied");
        problemDetail.setProperty("errorCode", "AUTHZ_DENIED");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                ex.getMessage()
        );
        problemDetail.setTitle("Access Denied");
        problemDetail.setProperty("errorCode", "ACCESS_DENIED");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problemDetail);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundException(Exception e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(
                        NOT_FOUND,
                        e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(
                        NOT_FOUND,
                        e.getMessage()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ProblemDetail> handleConflictException(ConflictException e) {
        return ResponseEntity
                .status(CONFLICT)
                .body(ProblemDetail.forStatusAndDetail(
                        CONFLICT,
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
