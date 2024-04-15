package Util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioUtil {
    public static boolean validaCpf(String cpf){
        Pattern pattern = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
        Matcher matcher = pattern.matcher(cpf);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    public static boolean validaCep(String cep){
        Pattern pattern = Pattern.compile("\\d{5}-\\d{3}");
        Matcher matcher = pattern.matcher(cep);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    public static boolean validaIdade(Calendar dataNasc){
        Calendar hoje = Calendar.getInstance();
        int maiorDeIdade = hoje.get(Calendar.YEAR) - 18;
        if(dataNasc.get(Calendar.YEAR) >= maiorDeIdade ){
            return false;
        }
        return true;
    }

    public static boolean validaNome(String nome){
        Pattern pattern = Pattern.compile("[A-Z][a-z].* [A-Z][a-z].*");
        Matcher matcher = pattern.matcher(nome);
        if(matcher.find()){
            return true;
        }
        return false;
    }
}
