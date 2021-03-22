package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ClientDAO;
import dao.ProductDAO;
//import bll.StudentBLL;
import model.Client;
import model.Product;
/**
 * Clasa principala unde se afla metoda main
 */
public class Start {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    /**
     * In aceasta clasa se foloseste doar o metoda statica care arunca o exceptie de tip SQLException, in aceasta metoda se instantiaza un obiect de tip Parsare prin care se apeleaza functia citire din clasa Parsare, dand ca paramentru un string dat de la tastatura (din cmd).
     * Aceste doua instructiuni sunt puse intr-un bloc try catch pentru a prinde exceptia posibila de tip SQLException
     */
    public static void main(String[] args) throws SQLException {

        try {
            Parsare p=new Parsare();
            //p.citire("text.txt");
              p.citire(args[0]);
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
        }

    }

}
