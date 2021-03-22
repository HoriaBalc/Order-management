package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.OrderItem;

/**
 * Clasa face ,cu ajutorul pachetului connection, operatiile de insert delete si update din tabelul OrderItem din baza de date
 */
public class OrderItemDAO {

    protected static final Logger LOGGER = Logger.getLogger(OrderItemDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orderitem (id,nameC,nameP,cant)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM orderitem where id = ?";
    private static final String deleteStatementString = "DELETE FROM orderitem WHERE name=?";

    /**
     * Implementarea operatiei de findByName care are rolul de a cauta daca exista id-ul dat ca parametru in tabelul OrderItem din baza de date
     */
    public static OrderItem findById(int id) {
        OrderItem toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id);
            rs = findStatement.executeQuery();
            if(rs.next()) {

                String nameC = rs.getString("nameC");
                String nameP = rs.getString("nameP");
                int cant = rs.getInt("cant");
                toReturn = new OrderItem(id, nameC, nameP, cant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderItemDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * Implementarea operatiei de findByName care are rolul de a cauta daca exista numele dat ca parametru in tabelul OrderItem din baza de date.
     */
    public static OrderItem findByName(String name) {
        OrderItem toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, name);
            rs = findStatement.executeQuery();
            if(rs.next()) {
                int id=rs.getInt("id");
                String nameC = rs.getString("nameC");
                String nameP = rs.getString("nameP");
                int cant = rs.getInt("cant");
                toReturn = new OrderItem(id, nameC, nameP, cant);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderItemDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    /**
     * Implementarea operatiei de insert  din tabelul OrderItem din baza de date
     */
    public static int insert(OrderItem orderItem) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orderItem.getId());
            insertStatement.setString(2, orderItem.getNameC());
            insertStatement.setString(3, orderItem.getNameP());
            insertStatement.setInt(4, orderItem.getCant());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    /**
     * Implementarea operatiei de delete  din tabelul OrderItem din baza de date
     */
    public static int delete(OrderItem orderItem) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        int deleteId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, orderItem.getId());
            deleteStatement.setString(2, orderItem.getNameC());
            deleteStatement.setString(3, orderItem.getNameP());
            deleteStatement.setInt(4, orderItem.getCant());
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }
}