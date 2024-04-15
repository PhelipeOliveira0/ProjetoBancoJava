package Models;

import Exceptions.CpfInvalidoException;

import java.util.Calendar;

public class ContaPoupancaComum extends ContaPoupanca{

    public ContaPoupancaComum(String cpf, String nome, Endereco endereco, String senha) {
        super(cpf, nome, endereco,senha);
        this.rendimento = 0.05;
        this.ranking = "comum";
        this.tipo = "poupanca";
    }

}
