package Models;

import Exceptions.CpfInvalidoException;

import java.util.Calendar;

public class ContaCorrenteSuper extends ContaCorrente {

    public ContaCorrenteSuper(String cpf, String nome, Endereco endereco, String senha){
        super(cpf,nome,endereco,senha);
        this.taxa = 8;
        this.limiteCredito = 5000;
        this.ranking = "super";
        this.tipo = "corrente";
    }

}
