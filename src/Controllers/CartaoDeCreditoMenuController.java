package Controllers;

import Dao.*;
import Exceptions.CancelarCartaoException;
import Exceptions.ContratacaoCartaoException;
import Exceptions.SeguroCartaoException;
import Exceptions.ValorInvalidoException;
import Models.CartaoCredito;
import Models.ContaCorrente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CartaoDeCreditoMenuController {

    private static ConnectionFactory connection;
    public CartaoDeCreditoMenuController(){
        this.connection = new ConnectionFactory();
    }

    public void menuPrincipalCartao(ContaCorrente conta){

        Scanner input = new Scanner(System.in);
        int opcaoMenu = 11;

        while (opcaoMenu != 10){

            System.out.println("""
                    O que deseja fazer?
                    1- Contratar cartão
                    2- Cancelar cartão
                    3- Ver debito
                    4- Ver limite
                    5- Pagar com cartão
                    6- Pagar debito
                    7- Contratar seguro
                    8- Solicitar seguro
                    9- Cancelar segura
                    10- sair
                    """);

            try {
                opcaoMenu = input.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Resposta invalida");
                opcaoMenu = 10;
                this.menuPrincipalCartao(conta);
            }


            switch (opcaoMenu){
                case 1:
                    opcaoMenu = 10;
                    this.contratarCartao(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 2:
                    opcaoMenu = 10;
                    this.cancelarCartao(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 3:
                    opcaoMenu = 10;
                    this.mostrarSaldo(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 4:
                    opcaoMenu = 10;
                    this.mostrarLimite(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 5:
                    opcaoMenu = 10;
                    this.pagarComCartao(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 6:
                    opcaoMenu = 10;
                    this.pagarDebido(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 7:
                    opcaoMenu = 10;
                    this.contratarSeguro(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 8:
                    opcaoMenu = 10;
                    this.solicitarSeguro(conta);
                    this.menuPrincipalCartao(conta);
                    break;

                case 9:
                    opcaoMenu = 10;
                    this.cancelarSeguro(conta);
                    this.menuPrincipalCartao(conta);
                    break;
            }

        }

    }

    public void contratarCartao(ContaCorrente conta){

        Scanner input = new Scanner(System.in);

        System.out.println("Qual vai ser o limite de transação por dia?");

        int limiteTransacao = 0;
        try {
            limiteTransacao =  input.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Resposta invalida");
            this.menuPrincipalCartao(conta);
        }

        try {
            if(!conta.contratarCartao(limiteTransacao)){
                throw new ContratacaoCartaoException();
            }
        }catch (ContratacaoCartaoException e){
            System.out.println(e.getMessage());
        }

        try {

            Connection conn = connection.recuperarConexao();
            SalvarContaDao salvar = new SalvarContaDao(conn);
            salvar.cadastrarCartao(conta.getLimiteCredito(), 0, limiteTransacao,false,conta.getCpf());
            conn.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try {
            Connection conn = connection.recuperarConexao();
            AlteracoesContaCartaoDao alter = new AlteracoesContaCartaoDao(conn);
            alter.possuiCartaoTrue(conta.getCpf());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }



    }

    public void cancelarCartao(ContaCorrente conta){

        try {
            if(!conta.cancelarCartao()){
                throw new CancelarCartaoException();
            }
        }catch (CancelarCartaoException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = connection.recuperarConexao();
            AlteracoesContaCartaoDao alter = new AlteracoesContaCartaoDao(conn);
            alter.possuiCartaoFalse(conta.getCpf());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try {
            Connection conn = connection.recuperarConexao();
            DeleteDao delete = new DeleteDao(conn);
            delete.deletarCartao(conta.getCpf());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void mostrarSaldo(ContaCorrente conta){
        System.out.println(conta.getCartaoCredito().mostrarDebito());
    }

    public void mostrarLimite(ContaCorrente conta){
        System.out.println(conta.getCartaoCredito().getCreditoLimite());
    }

    public void pagarComCartao(ContaCorrente conta){
        Scanner input = new Scanner(System.in);

        System.out.println("Quanto você quer pagar no cartão");
        double valor = 0;
        try {
            valor = input.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("Resposta invalida");
            this.menuPrincipalCartao(conta);
        }


        try {
            if(!conta.getCartaoCredito().pagarComCredito(valor)){
                throw new ValorInvalidoException();
            }
        }catch (ValorInvalidoException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorCartao(conta.getCpf(),conta.getCartaoCredito().getDebito());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void pagarDebido(ContaCorrente conta){
        try {
            if(!conta.getCartaoCredito().pagarDebito(conta)){
                throw new ValorInvalidoException();
            }
        }catch (ValorInvalidoException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorCartao(conta.getCpf(),conta.getCartaoCredito().getDebito());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorCorrente(conta.getCpf(),conta.getSaldo());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void contratarSeguro(ContaCorrente conta){

        try {
            if(!conta.getCartaoCredito().contratarSeguro(conta)){
                throw new ValorInvalidoException("Não foi possivel contratar o seguro");
            }
        }catch (ValorInvalidoException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = connection.recuperarConexao();
            AlteracoesContaCartaoDao alter = new AlteracoesContaCartaoDao(conn);
            alter.seguroTrue(conta.getCpf());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorCartao(conta.getCpf(),conta.getCartaoCredito().getDebito());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void solicitarSeguro(ContaCorrente conta){
        try {
            if(!conta.getCartaoCredito().solicitarSeguro()){
                throw new SeguroCartaoException();
            }
        }catch (SeguroCartaoException e){
            System.out.println(e.getMessage());
        }
        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorCartao(conta.getCpf(),conta.getCartaoCredito().getDebito());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorCartao(conta.getCpf(),conta.getCartaoCredito().getDebito());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void cancelarSeguro(ContaCorrente conta){
        try {
            if(!conta.getCartaoCredito().cancelarSeguro()){
                throw new SeguroCartaoException("Não foi possivel cancelar o seguro");
            }
        }catch (SeguroCartaoException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = connection.recuperarConexao();
            AlteracoesContaCartaoDao alter = new AlteracoesContaCartaoDao(conn);
            alter.seguroFalse(conta.getCpf());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}























