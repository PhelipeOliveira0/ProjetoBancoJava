package Models;

import Exceptions.CpfInvalidoException;

import java.util.Calendar;

public class ContaCorrentePremium extends ContaCorrente{


    public ContaCorrentePremium(String cpf, String nome, Endereco endereco, String senha) {
        super(cpf,nome,endereco,senha);
        this.limiteCredito = 10000;
        this.taxa = 0;
        this.ranking = "premium";
        this.tipo = "corrente";
    }

}
