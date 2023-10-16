package br.com.joelbrs.ClientCRUD.resources.exceptions;

import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceAlreadyExistsException;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceDoesNotExistsException;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceFieldsShouldNotBeEmptyException;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        String err = "Resource Not Found!";

        return ResponseEntity.status(status.value()).body(new StandardError(Instant.now(), status.value(), err, e.getMessage(), path));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardError> resourceAlreadyExists(ResourceAlreadyExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getRequestURI();
        String err = "Resource Already Exists!";

        return ResponseEntity.status(status.value()).body(new StandardError(Instant.now(), status.value(), err, e.getMessage(), path));
    }

    @ExceptionHandler(ResourceDoesNotExistsException.class)
    public ResponseEntity<StandardError> resourceDoesNotExists(ResourceDoesNotExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        String err = "Resource Doesn't Exists!";

        return ResponseEntity.status(status.value()).body(new StandardError(Instant.now(), status.value(), err, e.getMessage(), path));
    }
    @ExceptionHandler(ResourceFieldsShouldNotBeEmptyException.class)
    public ResponseEntity<StandardError> resourceFieldsShouldNotBeEmpty(ResourceFieldsShouldNotBeEmptyException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getRequestURI();
        String err = "Resource Field(s) Shouldn't Be Empty!";

        return ResponseEntity.status(status.value()).body(new StandardError(Instant.now(), status.value(), err, e.getMessage(), path));
    }
}
