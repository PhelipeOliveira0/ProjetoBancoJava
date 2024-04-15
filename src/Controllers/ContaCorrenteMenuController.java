package Controllers;

import Dao.ConnectionFactory;
import Dao.MudarSaldoDao;
import Exceptions.ValorInvalidoException;
import Models.ContaCorrente;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaCorrenteMenuController {

    private static ConnectionFactory connection;

    public ContaCorrenteMenuController(){
        this.connection = new ConnectionFactory();
    }

    public void menuPrincipal(ContaCorrente conta){
        int opcaoMenu = 9;
        Scanner input = new Scanner(System.in);
        CartaoDeCreditoMenuController cartaoDeCreditoMenuController = new CartaoDeCreditoMenuController();

        while (opcaoMenu != 6){
            System.out.println("""
                    O que deseja fazer?
                    1- ver saldo
                    2- depositar
                    3- sacar
                    4- transferencia pix
                    5- cartão de credito
                    6- sair
                    """);


            try {
                opcaoMenu = input.nextInt();

            }catch (InputMismatchException e){
                opcaoMenu = 6;
                menuPrincipal(conta);
            }


            switch (opcaoMenu){
                case 1:
                    opcaoMenu = 6;
                    this.mostrarSaldo(conta);
                    this.menuPrincipal(conta);
                    break;

                case 2:
                    opcaoMenu = 6;
                    this.depositar(conta);
                    this.menuPrincipal(conta);
                    break;

                case 3:
                    opcaoMenu = 6;
                    this.sacar(conta);
                    this.menuPrincipal(conta);
                    break;

                case 4:
                    opcaoMenu = 6;
                    this.transferenciaPix(conta);
                    this.menuPrincipal(conta);
                    break;

                case 5:
                    cartaoDeCreditoMenuController.menuPrincipalCartao(conta);
                    break;
            }

        }

    }

    public void mostrarSaldo(ContaCorrente conta){
        System.out.println(conta.getSaldo());
    }

    public void depositar(ContaCorrente conta){
        Scanner input = new Scanner(System.in);

        System.out.println("Qual vai ser o valor?");
        double valor = 0;
        try {
           valor = input.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("Resposta invalida");
            this.menuPrincipal(conta);
        }

        try {
            if(!conta.depositar(valor)){
                throw new ValorInvalidoException();
            }
        }catch (ValorInvalidoException e){
            System.out.println(e.getMessage());
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

    public void sacar(ContaCorrente conta){
        Scanner input = new Scanner(System.in);

        System.out.println("Qual vai ser o valor a sacar?");
        double valor = 0;
        try {
            valor = input.nextDouble();
        }catch (InputMismatchException e){
            System.out.println("Resposta invalida");
            this.menuPrincipal(conta);
        }


        try {
            if(!conta.sacar(valor)){
                throw new ValorInvalidoException();
            }
        }catch (ValorInvalidoException e){
            System.out.println(e.getMessage());
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

    public void transferenciaPix(ContaCorrente conta){

        Scanner input = new Scanner(System.in);

        System.out.println("Qual é o cpf da conta?");
        String cpfOutraConta = input.nextLine();

        System.out.println("Qual vai ser o valor a ser transferido?");
        double valor = 0;
        try {
            valor = input.nextDouble();
        }catch (InputMismatchException e){
            this.menuPrincipal(conta);
        }

        int opcaoConta = 0;

        System.out.println("""
                Qual é o tipo da conta?
                1- corrente
                2- poupanca
                """);

        try {
            opcaoConta = input.nextInt();
        }catch (InputMismatchException e){
            this.menuPrincipal(conta);
        }

        if(opcaoConta == 1){
            try {
                Connection conn = connection.recuperarConexao();
                MudarSaldoDao alter = new MudarSaldoDao(conn);
                alter.mudarValorCorrente(cpfOutraConta,valor);
                conn.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        if(opcaoConta == 2){
            try {
                Connection conn = connection.recuperarConexao();
                MudarSaldoDao alter = new MudarSaldoDao(conn);
                alter.mudarValorPoupanca(cpfOutraConta,valor);
                conn.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        try {
            if(!conta.sacar(valor)){
                throw new ValorInvalidoException();
            }
        }catch (ValorInvalidoException e){
            System.out.println(e.getMessage());
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

}














