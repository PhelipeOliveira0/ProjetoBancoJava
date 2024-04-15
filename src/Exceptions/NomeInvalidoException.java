package Exceptions;

public class NomeInvalidoException extends Exception{

    public NomeInvalidoException(){
        super("Nome invalido");
    }

    public NomeInvalidoException(String message){
        super(message);
    }

}
