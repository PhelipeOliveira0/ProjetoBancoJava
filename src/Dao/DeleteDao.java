package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDao {


    private Connection conn;

    public DeleteDao(Connection connection){
        this.conn = connection;
    }

    public void deletarCartao(String cpf){
        String sql = "DELETE FROM cartao WHERE cliente_cpf = ?";

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
