package com.juaracoding.fantastic4_rest_api.handler;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
    Kode Exception
 -> 99
    VALIDATION      = 01
    DATABASE        = 02
    AUTH            = 03
    MEDIA / FILE    = 04
    EXTERNAL API    = 05
    OTHER           = 99
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    List<Map<String,Object>> errors = new ArrayList<>();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        errors.clear();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            Map<String,Object> mapError = new HashMap<>();
            mapError.put("field", error.getField());
            mapError.put("message", error.getDefaultMessage());
//            mapError.put("rejected-value", error.getRejectedValue());
            errors.add(mapError);
        }
        return new ResponseHandler().handleResponse("Data Tidak Valid !!",HttpStatus.BAD_REQUEST,errors,"X0101",request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request){
        return new ResponseHandler().handleResponse("Format Salah, Tidak Dapat Diproses !!",HttpStatus.BAD_REQUEST,null,"X0102",request);
    }
    
    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<Object> unexpectedRollbackException(UnexpectedRollbackException ex, HttpServletRequest request){
        return new ResponseHandler().handleResponse("Rollback untuk Transaksi Telah dilakukan",HttpStatus.BAD_REQUEST,null,"X0201",request);
    }
}