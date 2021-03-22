package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Aceasta clasa are rolul de a face conexiunea cu baza de date.Aici se initializeaza variabilele string Driver, Dburl, User, si Pass
 */
public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/bazadate";
    private static final String USER = "root";
    private static final String PASS = "Ferary99";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
/**
 * constructorul clasei ConnectionFactory
 * */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/**
 * Metoda createConnection creeaza conexiunea la baza de date cu ajutorul variabilelor mentionate mai sus.
 * */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }
/**
 * 		Metoda getConnection este un getter
 * */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
/**
 * Metoda close are rolul de a  inchide connection-ul
 * */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
/**
 *  Metoda close care o suprascrie pe precedent, aceasta din urma inchide statementul.
 * */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
    /**
     *  Metoda close care o suprascrie pe precedent, aceasta din urma inchide resultSet.
     * */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
