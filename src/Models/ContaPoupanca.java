package Models;

import Util.ContaUtil;

public class ContaPoupanca extends Usuario {

    protected double saldo = 100;
    protected double rendimento;
    protected String ranking;
    protected String tipo;

    public String getRanking() {
        return ranking;
    }

    public String getTipo() {
        return tipo;
    }

    public ContaPoupanca(String cpf, String nome, Endereco endereco, String senha){
        super(cpf, nome, endereco, senha);
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public String mostrarSaldo(){
       return ContaUtil.formatoReal(this.saldo);
    }

    public boolean depositar(double valor){
        if(valor >= 0){
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public boolean sacar(double valor){
        if(this.saldo >= valor){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public void rendimentoConta(){
        this.saldo += this.saldo * rendimento;
    }

}
