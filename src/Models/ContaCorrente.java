package Models;

import Util.ContaUtil;

public class ContaCorrente extends Usuario {

    protected double saldo = 100;
    protected double taxa;
    protected String ranking;
    protected String tipo;
    protected boolean temCartao = false;
    protected double limiteCredito;
    protected CartaoCredito cartaoCredito = null;


    public ContaCorrente(String cpf, String nome, Endereco endereco, String senha) {
        super(cpf, nome, endereco, senha);
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public void setTemCartao(boolean temCartao) {
        this.temCartao = temCartao;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getRanking() {
        return ranking;
    }

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double getLimiteCredito() {
        return this.limiteCredito;
    }

    public String mostrarSaldo(){
        return ContaUtil.formatoReal(this.saldo);
    }

    public CartaoCredito getCartaoCredito(){
        return this.cartaoCredito;
    }

    public boolean sacar(double valor){
        if(this.saldo >= valor){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor){
        if(valor > 0){
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public boolean transferenciaPix(ContaCorrente conta, double valor){
        if(this.saldo >= valor){
            conta.depositar(valor);
            this.sacar(valor);
            return true;
        }
        return false;
    }

    public boolean contratarCartao(int limiteTransacao){
        if(this.temCartao == false){
            System.out.println(this.getLimiteCredito());
            this.cartaoCredito = new CartaoCredito(this.limiteCredito, limiteTransacao);
            this.temCartao = true;
            return true;
        }
        return false;
    }

    public boolean cancelarCartao(){
        if(this.cartaoCredito != null){
            if(this.cartaoCredito.pagarDebito(this)){
                this.temCartao = false;
                this.cartaoCredito = null;
                return true;
            }
        }
        return false;
    }

    public void cobrarTaxa(){
        this.sacar(taxa);
    }
}
