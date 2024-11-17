package com.alibou.CustomerService.Handler;

import com.alibou.CustomerService.Models.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import java.util.HashMap;

public class ExeptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponce>  handleMethodArgumentNotValidException(MethodArgumentNotValidException exp)
    {
        var errors = new HashMap<String,String>();

        exp.getBindingResult().getAllErrors()
                .forEach(error ->{
                        var fieldName = ((FieldError) error).getField();
        var errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponce(errors));
    }

}
