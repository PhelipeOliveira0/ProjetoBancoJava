package Exceptions;

public class SeguroCartaoException extends Exception{

    public SeguroCartaoException(){
        super("Não foi possivel solicitar o seguro");
    }

    public SeguroCartaoException(String message){
        super(message);
    }

}
