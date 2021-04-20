package teastorehouse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Db {

    Connection dbConnection;
    Statement stmt;

    public Db() {
        System.out.println("Connecting to database...");
        try {
            this.dbConnection = DriverManager.getConnection("jdbc:sqlite:TeaStorehouse.db");
            stmt = dbConnection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS teas (\n"
                    + "  id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "  name TEXT, \n"
                    + "  teatype TEXT, \n"
                    + "  score REAL, \n"
                    + "  price REAL, \n"
                    + "  amount REAL, \n"
                    + "  usage REAL);");
            stmt.execute("CREATE TABLE IF NOT EXISTS notes (\n"
                    + "  id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "  tea_id INTEGER, \n"
                    + "  note TEXT, \n"
                    + "  FOREIGN KEY(tea_id) REFERENCES teas(id) ON DELETE CASCADE);");
            System.out.println("Database connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ResultSet getAllTeas() {
        String sql = "SELECT * FROM teas";
        ResultSet result = null;

        try {
            stmt = dbConnection.createStatement();
            result = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
    public Boolean addTea(String[] variables) {
        // [0.id, 1.name String, 2.teatype String, 3.score Double, 4.price Double, 5.amount Double, 6.usage Double]
        String sql = "INSERT INTO teas(name, teatype, score, price, amount, usage) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, variables[1]);
            pstmt.setString(2, variables[2]);
            pstmt.setDouble(3, Double.parseDouble(variables[3]));
            pstmt.setDouble(4, Double.parseDouble(variables[4]));
            pstmt.setDouble(5, Double.parseDouble(variables[5]));
            pstmt.setDouble(6, Double.parseDouble(variables[6]));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
