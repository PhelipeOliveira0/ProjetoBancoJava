package Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalvarContaDao {

    private Connection conn;

    public SalvarContaDao(Connection connection){
        this.conn = connection;
    }

    public void salvarContaCorrente(String cpf, String nome, String senha, String estado, String cidade, String rua, String complemento, int numero, String cep, double taxa
    , String ranking, double limiteCredito){
        try {
            String sql = "INSERT INTO contacorrente(cpf, nome, senha, estado, cidade, rua, complemento, numero, cep, saldo, taxa, ranking, tipo, tem_cartao, limite_credito) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, senha);
            preparedStatement.setString(4, estado);
            preparedStatement.setString(5, cidade);
            preparedStatement.setString(6, rua);
            preparedStatement.setString(7, complemento);
            preparedStatement.setInt(8, numero);
            preparedStatement.setString(9, cep);
            preparedStatement.setDouble(10, 100);
            preparedStatement.setDouble(11 , taxa);
            preparedStatement.setString(12, ranking);
            preparedStatement.setString(13, "corrente");
            preparedStatement.setBoolean(14, false);
            preparedStatement.setDouble(15, limiteCredito);
            preparedStatement.execute();

            preparedStatement.close();
            conn.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void salvarContaPoupanca(String nome, String senha, String estado, String cidade, String rua, String complemento, int numero, String cep, double rendimento,
                                    String ranking, String cpf){
        try {
            String sql = "INSERT INTO contapoupanca (nome, senha, estado, cidade, rua, complemento, numero, cep, saldo, rendimento, ranking, tipo, cpf) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement preparedStatement = conn.prepareStatement(sql);


            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3, estado);
            preparedStatement.setString(4, cidade);
            preparedStatement.setString(5, rua);
            preparedStatement.setString(6, complemento);
            preparedStatement.setInt(7, numero);
            preparedStatement.setString(8, cep);
            preparedStatement.setDouble(9, 100);
            preparedStatement.setDouble(10, rendimento);
            preparedStatement.setString(11, ranking);
            preparedStatement.setString(12,"poupanca");
            preparedStatement.setString(13, cpf);
            preparedStatement.execute();

            preparedStatement.close();
            conn.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void cadastrarCartao(double creditoLimite, double debito, int limiteTransacao, boolean seguro, String cliente_cpf){

        try {
            String sql = "INSERT INTO cartao(creditoLimite, debito, limiteTransacao, transacoesHoje, seguro, cliente_cpf)VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setDouble(1, creditoLimite);
            preparedStatement.setDouble(2, debito);
            preparedStatement.setInt(3, limiteTransacao);
            preparedStatement.setInt(4, 0);
            preparedStatement.setBoolean(5, seguro);
            preparedStatement.setString(6, cliente_cpf);
            preparedStatement.execute();

            preparedStatement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

}
