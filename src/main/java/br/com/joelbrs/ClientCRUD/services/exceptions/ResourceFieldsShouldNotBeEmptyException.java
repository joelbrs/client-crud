package br.com.joelbrs.ClientCRUD.services.exceptions;

public class ResourceFieldsShouldNotBeEmptyException extends RuntimeException{
    public ResourceFieldsShouldNotBeEmptyException(String msg) {
        super(msg);
    }
}
