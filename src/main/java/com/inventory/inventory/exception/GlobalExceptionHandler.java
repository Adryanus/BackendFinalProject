package com.inventory.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> manejarErroresValidacion(
                        MethodArgumentNotValidException ex) {

                Map<String, String> errores = new HashMap<>();

                ex.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> errores.put(
                                                error.getField(),
                                                error.getDefaultMessage()));

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(errores);
        }

        @ExceptionHandler(RecursoNoEncontradoException.class)
        public ResponseEntity<Map<String, String>> manejarRecursoNoEncontrado(
                        RecursoNoEncontradoException ex) {

                Map<String, String> error = new HashMap<>();

                error.put("error", ex.getMessage());

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(error);
        }

        @ExceptionHandler(InvalidDataAccessApiUsageException.class)
public ResponseEntity<Map<String, String>>
manejarErrorOrdenamiento(
        InvalidDataAccessApiUsageException ex) {

    Map<String, String> error = new HashMap<>();

    error.put(
            "error",
            "Campo de ordenamiento inválido.");

    error.put(
            "camposPermitidos",
            "id, nombre, precio, stock");

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error);
}

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, String>> manejarErrorGeneral(
                        Exception ex) {

                Map<String, String> error = new HashMap<>();

                error.put(
                                "error",
                                "Ha ocurrido un error interno en la aplicación");

                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error);
        }

}
