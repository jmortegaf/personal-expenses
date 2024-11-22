package com.jmortegaf.personal_expenses.errorhandlers;

import com.jmortegaf.personal_expenses.dto.ErrorData;
import com.jmortegaf.personal_expenses.exceptions.InvalidAccountDataException;
import com.jmortegaf.personal_expenses.exceptions.InvalidExpenseException;
import com.jmortegaf.personal_expenses.exceptions.InvalidUserDataException;
import com.jmortegaf.personal_expenses.exceptions.UserAuthenticationErrorException;
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
                .body(new ErrorData("Not Found","The requested endpoint doesn't exist").getBody());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error","Bad Request",
                        "message",e.getFieldErrors().stream()
                                .map(error->error.getField()+" "+error.getDefaultMessage()).findFirst()));
    }

    @ExceptionHandler(InvalidAccountDataException.class)
    public ResponseEntity<?> invalidAccountDataHandler(InvalidAccountDataException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorData("Bad Request",e.getMessage()).getBody());
    }

    @ExceptionHandler(InvalidExpenseException.class)
    public ResponseEntity<?> invalidExpenseHandler(InvalidExpenseException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorData("Bad Request",e.getMessage()).getBody());
    }

    @ExceptionHandler(UserAuthenticationErrorException.class)
    public ResponseEntity<?> userAuthenticationErrorHandler(UserAuthenticationErrorException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorData("Unauthorized",e.getMessage()).getBody());
    }
}
