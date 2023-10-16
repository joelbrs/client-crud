package br.com.joelbrs.ClientCRUD.services.exceptions;

public class ResourceDoesNotExistsException extends RuntimeException {
    public ResourceDoesNotExistsException(String msg) {
        super(msg);
    }
}
