package Controllers;

import Dao.ConnectionFactory;
import Dao.MudarSaldoDao;
import Exceptions.ValorInvalidoException;
import Models.ContaCorrente;
import Models.ContaPoupanca;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaPoupancaMenuController{

    private static ConnectionFactory connection;

    public ContaPoupancaMenuController(){
        this.connection = new ConnectionFactory();
    }

    public void menuPrincipalPoupanca(ContaPoupanca conta){



        Scanner input = new Scanner(System.in);
        int optionMenu = 0;

        while (optionMenu != 5){
            System.out.println("""
                    O que deseja fazer?
                    1- Ver saldo
                    2- Depositar
                    3- Sacar
                    4- Pegar o rendimento
                    5- Sair
                    """);
            try {
                optionMenu = input.nextInt();
            }catch (InputMismatchException e){
                optionMenu = 5;
                this.menuPrincipalPoupanca(conta);
            }

            switch (optionMenu){
                case 1:
                    optionMenu = 5;
                    this.verSaldo(conta);
                    this.menuPrincipalPoupanca(conta);
                    break;

                case 2:
                    optionMenu = 5;
                    this.depositar(conta);
                    this.menuPrincipalPoupanca(conta);
                    break;

                case 3:
                    optionMenu = 5;
                    this.sacar(conta);
                    this.menuPrincipalPoupanca(conta);
                    break;

                case 4:
                    optionMenu = 5;
                    this.pegarRendimento(conta);
                    this.menuPrincipalPoupanca(conta);
                    break;
            }
        }

    }

    public void verSaldo(ContaPoupanca conta){
        System.out.println(conta.mostrarSaldo());
    }

    public void depositar(ContaPoupanca conta){
        Scanner input = new Scanner(System.in);
        double valor = 0;
        System.out.println("Quanto gostaria de depositar");
        try {
            valor = input.nextDouble();
        }catch (InputMismatchException e){
            this.menuPrincipalPoupanca(conta);
        }


        try {
            if(!conta.depositar(valor)){
                throw new ValorInvalidoException();
            }
        }catch (ValorInvalidoException e){
            System.out.println(System.in);
        }
        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorPoupanca(conta.getCpf(),conta.getSaldo());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void sacar(ContaPoupanca conta){
        Scanner input = new Scanner(System.in);

        System.out.println("Quanto gostaria de sacar");
        double valor = 0;
        try {
            valor = input.nextDouble();
        }catch (InputMismatchException e){
            this.menuPrincipalPoupanca(conta);
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
            alter.mudarValorPoupanca(conta.getCpf(),conta.getSaldo());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void pegarRendimento(ContaPoupanca conta){
        conta.rendimentoConta();
        try {
            Connection conn = connection.recuperarConexao();
            MudarSaldoDao alter = new MudarSaldoDao(conn);
            alter.mudarValorPoupanca(conta.getCpf(),conta.getSaldo());
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("O rendimento foi computado na sua conta");
    }

}






