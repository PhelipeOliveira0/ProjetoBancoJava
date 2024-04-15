package Dao;

import Exceptions.CepInvalidoException;

import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarContaDao {

    private Connection conn;

    public BuscarContaDao(Connection connection){
        this.conn = connection;
    }
    public ContaCorrente buscarContaCorrente(String cpf){
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT cpf, senha, saldo, ranking, tem_cartao FROM contacorrente WHERE cpf = ?";
        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()){
                String cpfQ = rs.getString(1);
                String senhaQ = rs.getString(2);
                double saldoQ = rs.getDouble(3);
                String rankingQ = rs.getString(4);
                boolean tem_cartao = rs.getBoolean(5);
                Endereco endereco = new Endereco("4",",5","5",56,"54","54");


                if(rankingQ.equals("comum")){
                    ContaCorrenteComum conta = new ContaCorrenteComum(cpfQ, "53", endereco,senhaQ);
                    conta.setTemCartao(tem_cartao);
                    conta.setSaldo(saldoQ);
                    if(tem_cartao){
                        String sqlCartao = "SELECT creditoLimite, debito, limiteTransacao, transacoesHoje, seguro FROM cartao WHERE cliente_cpf ";
                        PreparedStatement preparedStatement = conn.prepareStatement(sqlCartao);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()){
                            double creditoLimiteQ = resultSet.getDouble(1);
                            double debitoQ = resultSet.getDouble(2);
                            int limiteQ = resultSet.getInt(3);
                            int transacaoHjQ = resultSet.getInt(4);
                            boolean seguro = resultSet.getBoolean(5);
                            CartaoCredito cartao = new CartaoCredito(creditoLimiteQ, limiteQ);

                            cartao.setTransacoesHoje(transacaoHjQ);
                            cartao.setSeguro(seguro);
                            cartao.setDebito(debitoQ);

                            conta.setCartaoCredito(cartao);

                        }
                    }
                    return conta;
                }

                if(rankingQ.equals("premium")){
                    ContaCorrentePremium conta = new ContaCorrentePremium(cpfQ, "hr", endereco, senhaQ);
                    conta.setSaldo(saldoQ);
                    if(tem_cartao){
                        String sqlCartao = "SELECT creditoLimite, debito, limiteTransacao, transacoesHoje, seguro FROM cartao WHERE cliente_cpf ";
                        PreparedStatement preparedStatement = conn.prepareStatement(sqlCartao);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()){
                            double creditoLimiteQ = resultSet.getDouble(1);
                            double debitoQ = resultSet.getDouble(2);
                            int limiteQ = resultSet.getInt(3);
                            int transacaoHjQ = resultSet.getInt(4);
                            boolean seguro = resultSet.getBoolean(5);
                            CartaoCredito cartao = new CartaoCredito(creditoLimiteQ, limiteQ);

                            cartao.setTransacoesHoje(transacaoHjQ);
                            cartao.setSeguro(seguro);
                            cartao.setDebito(debitoQ);

                            conta.setCartaoCredito(cartao);

                        }
                    }


                    return conta;
                }

                if(rankingQ.equals("super")){
                    ContaCorrenteSuper conta = new ContaCorrenteSuper(cpfQ, "4", endereco, senhaQ);
                    conta.setSaldo(saldoQ);
                    if(tem_cartao){
                        String sqlCartao = "SELECT creditoLimite, debito, limiteTransacao, transacoesHoje, seguro FROM cartao WHERE cliente_cpf ";
                        PreparedStatement preparedStatement = conn.prepareStatement(sqlCartao);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()){
                            double creditoLimiteQ = resultSet.getDouble(1);
                            double debitoQ = resultSet.getDouble(2);
                            int limiteQ = resultSet.getInt(3);
                            int transacaoHjQ = resultSet.getInt(4);
                            boolean seguro = resultSet.getBoolean(5);
                            CartaoCredito cartao = new CartaoCredito(creditoLimiteQ, limiteQ);

                            cartao.setTransacoesHoje(transacaoHjQ);
                            cartao.setSeguro(seguro);
                            cartao.setDebito(debitoQ);

                            conta.setCartaoCredito(cartao);

                        }
                    }

                    return conta;
                }
                ps.close();
                rs.close();
                conn.close();
            }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

        return null;

    }
    public ContaPoupanca buscarContaPoupanca(String cpf){
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT cpf, senha, saldo, ranking FROM contapoupanca WHERE cpf = ?";
        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()){
                String cpfQ = rs.getString(1);
                String senhaQ = rs.getString(2);
                double saldoQ = rs.getDouble(3);
                String rankingQ = rs.getString(4);
                Endereco endereco = new Endereco("4",",5","5",56,"54","54");
                System.out.println(senhaQ);

                if(rankingQ.equals("comum")){
                    ContaPoupancaComum conta = new ContaPoupancaComum(cpfQ, "53", endereco,senhaQ);
                    conta.setSaldo(saldoQ);
                    return conta;
                }

                if(rankingQ.equals("premium")){
                    ContaPoupancaPremium conta = new ContaPoupancaPremium(cpfQ, "hr", endereco, senhaQ);
                    conta.setSaldo(saldoQ);
                    return conta;
                }

                if(rankingQ.equals("super")){
                    ContaPoupancaSuper conta = new ContaPoupancaSuper(cpfQ, "4", endereco, senhaQ);
                    conta.setSaldo(saldoQ);
                    return conta;
                }
                ps.close();
                rs.close();
                conn.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;

    }

    public CartaoCredito buscarCartao(String cpf){
        String sql = "SELECT creditoLimite, debito, limiteTransacao, transacoesHoje, seguro FROM cartao WHERE cliente_cpf = ?";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()){
                double creditoLimite = rs.getDouble(1);
                double debito = rs.getDouble(2);
                int limiteTransacao = rs.getInt(3);
                int transacoesHoje = rs.getInt(4);
                boolean seguro = rs.getBoolean(5);

                CartaoCredito cartao = new CartaoCredito(creditoLimite, limiteTransacao);
                cartao.setDebito(debito);
                cartao.setTransacoesHoje(transacoesHoje);
                cartao.setSeguro(seguro);
                return cartao;
            }
            conn.close();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

}
