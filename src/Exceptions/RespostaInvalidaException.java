package Exceptions;

import java.util.InputMismatchException;

public class RespostaInvalidaException extends InputMismatchException {


    RespostaInvalidaException(){
            super("Resposta invalida");
        }

    RespostaInvalidaException(String message){
        super(message);
    }

}
