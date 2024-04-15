package Exceptions;

public class ValorInvalidoException extends Exception{

    public ValorInvalidoException(){
        super("Saldo invalido");
    }

    public ValorInvalidoException(String message){
        super(message);
    }

}
