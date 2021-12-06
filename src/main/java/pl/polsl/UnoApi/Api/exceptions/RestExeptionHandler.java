package pl.polsl.UnoApi.Api.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.polsl.UnoApi.Api.exceptions.ObjectExistException;

import javax.persistence.EntityExistsException;

@Log4j2
@ControllerAdvice
public class RestExeptionHandler {

    @ExceptionHandler(value = { ObjectExistException.class })
    protected ResponseEntity<Object> getObjectExistException(RuntimeException ex, WebRequest request) {
        log.info(ex.getMessage());
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(value = { ObjectValidException.class })
    protected ResponseEntity<Object> getObjectValidException(RuntimeException ex, WebRequest request) {
        log.info(ex.getMessage());
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
