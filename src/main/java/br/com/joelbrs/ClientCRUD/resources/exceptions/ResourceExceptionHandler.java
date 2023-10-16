package br.com.joelbrs.ClientCRUD.resources.exceptions;

import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
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
        String msg = "Resource Not Found!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        String err = "Not Found";

        return ResponseEntity.status(status.value()).body(new StandardError(Instant.now(), status.value(), err, msg, path));
    }
}
