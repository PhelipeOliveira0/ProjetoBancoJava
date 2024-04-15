package Exceptions;

public class IdadeInvalidaException extends Exception{

    public IdadeInvalidaException(){
        super("Idade invalida");
    }

    public IdadeInvalidaException(String message){
        super(message);
    }

}
