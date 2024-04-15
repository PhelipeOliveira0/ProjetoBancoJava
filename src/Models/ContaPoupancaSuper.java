package Models;

import Exceptions.CpfInvalidoException;

import java.util.Calendar;

public class ContaPoupancaSuper extends ContaPoupanca{

    public ContaPoupancaSuper(String cpf, String nome, Endereco endereco, String senha) {
        super(cpf, nome, endereco,senha);
        this.rendimento = 0.07;
        this.ranking = "super";
        this.tipo = "poupanca";
    }

}
