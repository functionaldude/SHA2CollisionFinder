package net.thewalkingthread.collisionfinder;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnector{
    private final String url = "jdbc:mysql://192.168.99.100:32777/hash?rewriteBatchedStatements=true";
    private static final String user = "root";
    private static final String passwd = "root";
    private Connection connect;

    private PreparedStatement batchstmt;

    public SQLConnector() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Exception at referencing MySQL Driver");
            System.out.println(e.getMessage());
        }
        resetCache();
    }
    public Connection getConn(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException ex) {
                System.out.println("Error at creating SQL connection");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return connect;
    }
    public void closeConn(){
        try {
            connect.close();
            connect = null;
        } catch (SQLException ex) {
            System.out.println("SQLException(close): " + ex.getMessage());
        }
    }
    public void addHash(String input, String hash) throws SQLException{
        batchstmt.setString(1, input);
        batchstmt.setString(2, hash);
        batchstmt.addBatch();
    }
    public void flushCache(){
        try {
            batchstmt.executeBatch();
            batchstmt.clearBatch();
            batchstmt.close();
        } catch (SQLException e) {}
    }
    public void resetCache() throws SQLException{
        batchstmt = getConn().prepareStatement("INSERT INTO hash (input, hash) VALUES (?, ?)");
    }
}
