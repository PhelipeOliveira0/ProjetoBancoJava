package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlteracoesContaCartaoDao {

    private Connection conn;

    public AlteracoesContaCartaoDao(Connection connection){
        this.conn = connection;
    }

    public void seguroTrue(String cpf){
        String sql = "UPDATE cartao SET seguro = 1 WHERE cliente_cpf = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void seguroFalse(String cpf){
        String sql = "UPDATE cartao SET seguro = 0 WHERE cliente_cpf = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void possuiCartaoTrue(String cpf){
        String sql = "UPDATE contacorrente SET tem_cartao = 1 WHERE cpf = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void possuiCartaoFalse(String cpf){
        String sql = "UPDATE contacorrente SET tem_cartao = 0 WHERE cpf = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
