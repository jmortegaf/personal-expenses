package com.jmortegaf.personal_expenses.errorhandlers;

import com.jmortegaf.personal_expenses.exceptions.InvalidUserDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<?> invalidUserDataHandler(InvalidUserDataException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error","Bad Request",
                        "message",e.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> noHandlerFoundHandler(NoHandlerFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Not Found", "message",
                        "The requested endpoint doesnt exist"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error","Bad Request",
                        "message",e.getFieldErrors().stream()
                                .map(error->error.getField()+" "+error.getDefaultMessage()).findFirst()));
    }
}
