package br.com.joelbrs.ClientCRUD.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
