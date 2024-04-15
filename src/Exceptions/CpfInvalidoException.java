package Exceptions;

public class CpfInvalidoException extends Exception{

    public CpfInvalidoException(){
        super("CPF invalido");
    }

    public CpfInvalidoException(String message){
        super(message);
    }

}
