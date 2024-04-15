import Controllers.MenuController;
import Exceptions.CepInvalidoException;
import Exceptions.CpfInvalidoException;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws CepInvalidoException, CpfInvalidoException, SQLException {
        MenuController menu = new MenuController();
        menu.menuInicial();

    }
}