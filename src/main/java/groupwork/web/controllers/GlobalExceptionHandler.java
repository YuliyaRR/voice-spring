package groupwork.web.controllers;

import groupwork.dto.ResponseError;
import groupwork.exception.ConnectionDataBaseException;
import groupwork.exception.InvalidInputServiceException;
import groupwork.exception.NotFoundDataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(NotFoundDataBaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handle(NotFoundDataBaseException e){
        return new ResponseError((e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConnectionDataBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle(ConnectionDataBaseException e){
        return new ResponseError((e.getMessage()) + ": " + e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(InvalidInputServiceException e) {
        return new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle(Exception e){
        return new ResponseError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
