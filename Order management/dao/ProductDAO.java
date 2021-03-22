package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Product;

/**
 * Clasa face ,cu ajutorul pachetului connection, operatiile de insert delete si update din tabelul Product din baza de date
 */
public class ProductDAO {

    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (name,cant,pret)" + " VALUES (?,?,?)";
    private static final String deleteStatementString = "DELETE FROM product WHERE name=?";
    private final static String findStatementString = "SELECT * FROM product where name = ?";
    private static final String updateStatementString = "UPDATE product SET cant=? WHERE name=?";
    /**
     * Implementarea operatiei de findById care are rolul de a cauta daca exista numele dat ca parametru in tabelul Product din baza de date
     */
    public static Product findById(String nume) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, nume);
            rs = findStatement.executeQuery();
            if(rs.next()){
            String name = rs.getString("name");
            int cant = rs.getInt("cant");
            double pret = rs.getDouble("pret");
            toReturn = new Product( nume,cant,pret);}
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Implementarea operatiei de insert  din tabelul Product din baza de date
     */
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getCant());
            insertStatement.setDouble(3, product.getPret());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    /**
     * Implementarea operatiei de delete din tabelul Product din baza de date
     */
    public static int delete(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int deleteId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, product.getName());
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }
    /**
     * Implementarea operatiei de update  din tabelul Product din baza de date
     */
    public static int update(Product product,int cant) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updateId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(2, product.getName());
            updateStatement.setInt(1, product.getCant()+cant);
            //updateStatement.setDouble(3, product.getPret()+pret);
            updateStatement.executeUpdate();
            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updateId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updateId;
    }
}