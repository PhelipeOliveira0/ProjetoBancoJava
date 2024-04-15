package Models;

import Util.ContaUtil;

public class CartaoCredito {

    protected double creditoLimite;
    protected double debito;
    protected int limiteTransacao;
    protected int transacoesHoje = 0;
    protected boolean seguro = false;

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public void setTransacoesHoje(int transacoesHoje) {
        this.transacoesHoje = transacoesHoje;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public CartaoCredito(double creditoLimite, int limiteTransacao){
        this.creditoLimite = creditoLimite;
        this.limiteTransacao = limiteTransacao;

    }

    public void resetarTransacao(){
        this.transacoesHoje = 0;
    }

    public double getDebito() {
        return this.debito;
    }

    public String mostrarDebito(){
        return ContaUtil.formatoReal(this.debito);
    }

    public String getCreditoLimite() {
        return ContaUtil.formatoReal(this.creditoLimite);
    }

    public boolean pagarComCredito(double valor){
        if(this.transacoesHoje < this.limiteTransacao){
            if(this.creditoLimite >= this.debito + valor){
                this.debito += valor;
                this.transacoesHoje++;
                return true;
            }
        }

        return false;
    }

    public boolean pagarDebito(ContaCorrente conta){
        int tarifaPartir = 0;

        double limiteParaTarifa = this.creditoLimite * 0.8;
        boolean pagarTarifa = false;
        if(this.debito >= limiteParaTarifa){
            pagarTarifa = true;
        }


        double tarifa = this.debito * 0.05;
        double valorApagar = this.debito + tarifa;
        if(this.debito <= conta.getSaldo()){
            if(pagarTarifa == true){
                if(valorApagar <= conta.getSaldo()){
                    this.debito = 0;
                    conta.sacar(valorApagar);
                    return true;
                }
                return false;
            }

            conta.sacar(debito);
            this.debito = 0;
            return true;
        }
        return false;
    }

    public boolean contratarSeguro(ContaCorrente conta){
        if(seguro == false){
            if(conta.limiteCredito == 10000){
                this.seguro = true;
                return true;
            }else{
                if(this.pagarComCredito(50)){
                    this.seguro = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean solicitarSeguro(){
        if(this.seguro == true){
            if(this.debito <= 5000){
                this.debito = 0;
                return true;
            }else{
                this.debito -= 5000;
                return true;
            }
        }
        return false;
    }

    public boolean cancelarSeguro(){
        if(this.seguro == true){
            this.seguro = false;
            return true;
        }
        return false;
    }

}
