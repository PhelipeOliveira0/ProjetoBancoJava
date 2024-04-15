package Exceptions;

public class CancelarCartaoException extends Exception{

    public CancelarCartaoException(){
        super("Não foi possivel cancelar o cartão");
    }

    public CancelarCartaoException(String message){
        super(message);
    }

}
