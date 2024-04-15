package Models;

import Exceptions.CpfInvalidoException;

import java.util.Calendar;

public class ContaPoupancaPremium extends ContaPoupanca{

    public ContaPoupancaPremium(String cpf, String nome, Endereco endereco, String senha) {
        super(cpf, nome, endereco,senha);
        this.rendimento = 0.09;
        this.ranking = "premium";
        this.tipo = "poupanca";
    }

}
