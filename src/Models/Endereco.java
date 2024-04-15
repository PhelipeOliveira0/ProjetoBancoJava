package Models;

import Exceptions.CepInvalidoException;
import Util.UsuarioUtil;

public class Endereco {
    protected String estado;
    protected String cidade;
    protected String rua;
    protected String complemento;
    protected int numero;
    protected String cep;

    public Endereco(String estado, String cidade, String rua, int numero, String cep, String complemento){
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }
    public String getCidade() {
        return cidade;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getEstado() {
        return estado;
    }
    public String getRua() {
        return rua;
    }
    public String getCep() {
        return cep;
    }

    public void setComplemento(String complemento){
        this.complemento = complemento;
    }
}
