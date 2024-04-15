package Exceptions;

public class CepInvalidoException extends Exception{

    public CepInvalidoException(){
        super("CEP invalido");
    }

    public CepInvalidoException(String message){
        super(message);
    }

}
