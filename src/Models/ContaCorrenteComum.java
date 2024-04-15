package Models;

import Exceptions.CpfInvalidoException;

import java.util.Calendar;

public class ContaCorrenteComum extends ContaCorrente{

    public ContaCorrenteComum(String cpf, String nome, Endereco endereco, String senha) {
        super(cpf,nome,endereco,senha);
        this.limiteCredito = 1000;
        this.taxa = 12;
        this.ranking = "comum";
        this.tipo = "corrente";
    }
}
