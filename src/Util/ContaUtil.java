package Util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ContaUtil {

    public static String formatoReal(double valor){
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(valor);
    }

}
