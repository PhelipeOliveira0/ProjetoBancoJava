package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MudarSaldoDao {

    private Connection conn;

    public MudarSaldoDao(Connection connection){
        this.conn = connection;
    }

    public void mudarValorCorrente(String cpf, double valor){
        String sql = "UPDATE contacorrente SET saldo = ? WHERE cpf = ?";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setString(2, cpf);
            ps.execute();
            ps.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void mudarValorPoupanca(String cpf, double valor){
        String sql = "UPDATE contapoupanca SET saldo = ? WHERE cpf = ?";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setString(2, cpf);
            ps.execute();
            ps.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void mudarValorCartao(String cpf, double valor){
        String sql = "UPDATE cartao SET debito = ? WHERE cliente_cpf = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, valor);
            ps.setString(2, cpf);
            ps.execute();
            ps.close();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
