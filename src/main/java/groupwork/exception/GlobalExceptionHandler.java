package groupwork.exception;

import groupwork.dto.ResponseError;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//503?
    public ResponseError handle(ConnectionDataBaseException e){
        return new ResponseError((e.getMessage()) + ": " + e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(InvalidInputServiceException e) {
        return new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle(RuntimeException e){
        return new ResponseError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
