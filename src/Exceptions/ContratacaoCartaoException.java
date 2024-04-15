package Exceptions;


public class ContratacaoCartaoException extends Exception{

    public ContratacaoCartaoException(){
        super("Não é possivel contratar o cartão");
    }

    public ContratacaoCartaoException(String message){
        super(message);
    }

}
