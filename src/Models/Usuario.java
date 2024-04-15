package Models;

import Exceptions.CpfInvalidoException;
import Util.UsuarioUtil;


public class Usuario {
    protected String cpf;
    protected String nome;

    protected Endereco endereco;
    protected String senha;

    public Usuario(String cpf, String nome, Endereco endereco, String senha){
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
