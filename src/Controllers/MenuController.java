package Controllers;

import Dao.BuscarContaDao;
import Dao.ConnectionFactory;
import Dao.SalvarContaDao;
import Exceptions.*;
import Models.*;
import Util.UsuarioUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    private static ConnectionFactory connection;

    public MenuController(){
        this.connection = new ConnectionFactory();
    }
    public void menuInicial() throws CepInvalidoException, CpfInvalidoException, SQLException {

        int opcaoMenu = 0;
        Scanner input = new Scanner(System.in);
        while (opcaoMenu != 3){
            System.out.println("""
                    O que deseja fazer ?
                    1- Cadastrar
                    2- Logar
                    3- Sair
                    """);

            try {
                opcaoMenu = input.nextInt();

            }catch (InputMismatchException e){
                opcaoMenu = input.nextInt();
                this.menuInicial();
            }

            switch (opcaoMenu){

                case 1:
                    opcaoMenu = 3;
                    this.menuCadastrar();
                    break;

                case 2:
                    opcaoMenu = 3;
                    this.menuLogar();
                    break;

            }
        }

    }

    public void menuCadastrar() throws CepInvalidoException, CpfInvalidoException, SQLException {
        Scanner input = new Scanner(System.in);


        System.out.println("Qual é o seu nome?");
        String nome = input.nextLine();

        try {
            if(!UsuarioUtil.validaNome(nome)){
                throw new NomeInvalidoException();
            }
        }catch (NomeInvalidoException e){
            System.out.println(e.getMessage());
            this.menuInicial();
        }

        System.out.println("Qual é o seu cpf?");
        String cpf = input.nextLine();

        try {
            if(!UsuarioUtil.validaCpf(cpf)){
                throw new CpfInvalidoException();
            }
        }catch (CpfInvalidoException e){
            System.out.println(e.getMessage());
            this.menuInicial();
        }

        System.out.println("Qual vai ser a sua senha?");
        String senha = input.nextLine();

        Calendar nascimento = Calendar.getInstance();

        int ano = 1;
        System.out.println("Qual é o ano do seu nascimento");
        try {
            ano = input.nextInt();
        }catch (InputMismatchException e){
            this.menuInicial();
        }

        nascimento.set(Calendar.YEAR, ano);

        int mes = 0;
        System.out.println("Qual é o mes do seu nascimento");
        try {
            mes = input.nextInt();
        }catch (InputMismatchException e){
            this.menuInicial();
        }
        nascimento.set(Calendar.MONTH, mes);

        int dia = 0;
        System.out.println("Dia do seu nascimento");
        try {
            dia = input.nextInt();
        }catch (InputMismatchException e){
            this.menuInicial();
        }
        nascimento.set(Calendar.DAY_OF_MONTH, dia);

        try {
            if(!UsuarioUtil.validaIdade(nascimento)){
                throw new IdadeInvalidaException();
            }
        }catch (IdadeInvalidaException e){
            System.out.println(e.getMessage());
            this.menuInicial();
        }

        System.out.println("Qual é o seu estado?");
        String estado = input.nextLine();
        estado = input.nextLine();

        System.out.println("Qual é a sua cidade?");
        String cidade = input.nextLine();

        System.out.println("Qual é a sua rua?");
        String rua = input.nextLine();

        System.out.println("Qual é o seu numero?");
        int numero = input.nextInt();

        System.out.println("Qual é o seu cep?");
        String cep = input.nextLine();
        cep = input.nextLine();

        try {
            if(!UsuarioUtil.validaCep(cep)){
                throw new CepInvalidoException();
            }
        }catch (CepInvalidoException e){
            System.out.println(e.getMessage());
            this.menuInicial();
        }

        boolean opComp = false;
        char opcaoComp = 'u';

        while (!opComp){

            System.out.println("Possui complemento? s/n");
            opcaoComp = input.nextLine().charAt(0);

            if(opcaoComp == 's'){
                opComp = true;
            }

            if(opcaoComp == 'S'){
                opComp = true;
            }

            if(opcaoComp == 'n'){
                opComp = true;
            }

            if(opcaoComp == 'N'){
                opComp = true;
            }
        }

        String complemento = "";

        if(opcaoComp == 's'){
            System.out.println("Qual é o complemento");
            complemento = input.nextLine();
        }

        if(opcaoComp == 'S'){
            System.out.println("Qual é o complemento");
            complemento = input.nextLine();
        }

        Endereco endereco = new Endereco(estado, cidade, rua, numero, cep, complemento);

        boolean confirmacaoTipoConta = false;
        String tipoConta = "";


        while (!confirmacaoTipoConta){

            System.out.println("""
                Qual tipo de conta você gostaria?
                1- Corrente
                2- Poupança
                """);
            int tipo = 0;
            try {
                tipo = input.nextInt();
            }catch (InputMismatchException e){
                this.menuInicial();
            }


            if(tipo == 1){
                tipoConta = "Corrente";
                confirmacaoTipoConta = true;
            }

            if(tipo == 2){
                tipoConta = "Poupanca";
                confirmacaoTipoConta = true;
            }

        }

        boolean confirmacaoRanking = false;
        String ranking = "";

        while (!confirmacaoRanking){

            System.out.println("""
                    Qual vai ser o nivel da sua conta?
                    1- Conta comum = Conta corrente possui mil reais de limite, taxa de manutenção mensal $12,00/ a conta poupança tem o rendimento de 0,5%
                    2- Conta super = Conta corrente possui 5 mil reais de limite taxa de manutenção mensal $8,00/ a conta poupança tem o rendimento de 0,7%
                    3- Conta premium = Conta corrente possui 10 mil reais de limite e possui seguro do cartão de graça/ a conta poupança tem o rendimento de 0,9%
                    """);
            int tipo = 0;
            try {
                tipo = input.nextInt();
            }catch (InputMismatchException e){
                this.menuInicial();
            }


            if(tipo == 1){
                ranking = "comum";
                confirmacaoRanking = true;
            }

            if(tipo == 2){
                ranking = "super";
                confirmacaoRanking = true;
            }

            if(tipo == 3){
                ranking = "premium";
                confirmacaoRanking = true;
            }

        }

        ContaCorrenteMenuController contaCorrenteMenuController = new ContaCorrenteMenuController();
        if(tipoConta == "Corrente"){

            if(ranking == "comum"){

                double taxa = 12;
                Connection conn = connection.recuperarConexao();

                SalvarContaDao salvar = new SalvarContaDao(conn);
                salvar.salvarContaCorrente(cpf,nome,senha,estado,cidade,rua,complemento,numero,cep,taxa,"comum", 1000);


                ContaCorrenteComum conta = new ContaCorrenteComum(cpf,nome,endereco,senha);
                contaCorrenteMenuController.menuPrincipal(conta);
            }

            if(ranking == "super"){

                int taxa = 8;
                Connection conn = connection.recuperarConexao();

                SalvarContaDao salvar = new SalvarContaDao(conn);
                salvar.salvarContaCorrente(cpf,nome,senha,estado,cidade,rua,complemento,numero,cep,taxa,"super", 5000);

                ContaCorrenteSuper conta = new ContaCorrenteSuper(cpf,nome,endereco,senha);
                contaCorrenteMenuController.menuPrincipal(conta);
            }

            if(ranking == "premium") {

                int taxa = 8;
                Connection conn = connection.recuperarConexao();

                SalvarContaDao salvar = new SalvarContaDao(conn);
                salvar.salvarContaCorrente(cpf, nome, senha, estado, cidade, rua, complemento, numero, cep, taxa, "super", 10000);

                ContaCorrentePremium conta = new ContaCorrentePremium(cpf, nome, endereco, senha);
                contaCorrenteMenuController.menuPrincipal(conta);
            }
        }

        if(tipoConta == "Poupanca"){

            if(ranking == "comum"){

                Connection conn = connection.recuperarConexao();
                double rendimento = 0.05;
                ContaPoupancaMenuController contaPoupancaMenuController = new ContaPoupancaMenuController();

                SalvarContaDao salvar = new SalvarContaDao(conn);
                salvar.salvarContaPoupanca(nome, senha, estado, cidade, rua, complemento, numero, cep, rendimento, "comum", cpf);

                ContaPoupancaComum conta = new ContaPoupancaComum(cpf,nome,endereco,senha);
                contaPoupancaMenuController.menuPrincipalPoupanca(conta);
            }

            if(ranking == "super"){

                Connection conn = connection.recuperarConexao();
                double rendimento = 0.07;

                ContaPoupancaMenuController contaPoupancaMenuController = new ContaPoupancaMenuController();

                SalvarContaDao salvar = new SalvarContaDao(conn);
                salvar.salvarContaPoupanca(nome, senha, estado, cidade, rua, complemento, numero, cep, rendimento, "super", cpf);

                ContaPoupancaSuper conta = new ContaPoupancaSuper(cpf,nome,endereco,senha);
                contaPoupancaMenuController.menuPrincipalPoupanca(conta);
            }

            if(ranking == "premium"){

                Connection conn = connection.recuperarConexao();
                double rendimento = 0.09;

                ContaPoupancaMenuController contaPoupancaMenuController = new ContaPoupancaMenuController();

                SalvarContaDao salvar = new SalvarContaDao(conn);
                salvar.salvarContaPoupanca(nome, senha, estado, cidade, rua, complemento, numero, cep, rendimento, "premium", cpf);

                ContaPoupancaPremium conta = new ContaPoupancaPremium(cpf,nome,endereco,senha);
                contaPoupancaMenuController.menuPrincipalPoupanca(conta);
            }
        }
        input.close();
    }

    public void menuLogar() throws SQLException, CepInvalidoException, CpfInvalidoException {
        Scanner input = new Scanner(System.in);
        ContaCorrenteMenuController contaCorrenteMenuController = new ContaCorrenteMenuController();

        System.out.println("Qual é o seu CPF?");
        String cpf = input.nextLine();

        try {
            if(!UsuarioUtil.validaCpf(cpf)){
                throw new CpfInvalidoException();
            }
        }catch (CpfInvalidoException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Qual é a senha");
        String senha = input.nextLine();

        System.out.println( """
                Qual é o tipo de conta?
                1- Poupança
                2- Corrente
                """);

        int opTipo = input.nextInt();
        String tipo = "";

        ContaPoupancaMenuController contaPoupancaMenuController = new ContaPoupancaMenuController();

        if(opTipo == 1){
            Connection conn = connection.recuperarConexao();
            BuscarContaDao buscar = new BuscarContaDao(conn);
            ContaPoupanca conta = buscar.buscarContaPoupanca(cpf);
            if(conta.getSenha().equals(senha)){
                contaPoupancaMenuController.menuPrincipalPoupanca(conta);
            }else{
                this.menuInicial();
            }
        }

        if(opTipo == 2){
            Connection conn = connection.recuperarConexao();
            BuscarContaDao buscar = new BuscarContaDao(conn);
            ContaCorrente conta = buscar.buscarContaCorrente(cpf);
            if(conta.getSenha().equals(senha)){
                contaCorrenteMenuController.menuPrincipal(conta);
            }else{
                this.menuInicial();
            }

        }



    }

}





























