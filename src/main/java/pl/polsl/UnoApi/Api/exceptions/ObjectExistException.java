package pl.polsl.UnoApi.Api.exceptions;

public class ObjectExistException extends RuntimeException{
    public ObjectExistException(String message){
        super(message);
    }
}
