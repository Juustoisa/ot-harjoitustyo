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

    public Db(Boolean test) {
        try {
            if (test) {
                this.dbConnection = DriverManager.getConnection("jdbc:sqlite::memory:");
            } else {
                System.out.println("Connecting to database...");
                this.dbConnection = DriverManager.getConnection("jdbc:sqlite:TeaStorehouse.db;");
            }

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
            if (!test) {
                System.out.println("Database connected");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Method to return all teas from database as ResultSet object.
     *
     * @return ResultSet containing all database rows in teas table.
     */
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

    /**
     * Method to return all notes from database as ResultSet object.
     *
     * @return ResultSet containing all database rows in notes table.
     */
    public ResultSet getAllNotes() {
        String sql = "SELECT * FROM notes INNER JOIN teas ON teas.id = notes.tea_id";
        ResultSet result = null;

        try {
            stmt = dbConnection.createStatement();
            result = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    /**
     *
     * Method to check if given id exists in database.
     *
     * @return true if id exists, false if id doesn't exist.
     */
    public Boolean checkIfIdExists(String id) {
        String sql = "SELECT id FROM teas WHERE id = ?";
        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public Boolean addTeaToDb(String[] variables) {
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
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Boolean addNoteToDb(String[] variables) {
        // [0.id, 1.tea_id String, 2.note String]
        String sql = "INSERT INTO notes(tea_id, note) VALUES(?,?)";
        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(variables[1]));
            pstmt.setString(2, variables[2]);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Boolean deleteNote(int id) {
        try {
            String sql = "SELECT id FROM notes WHERE id = ?";

            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            sql = "DELETE FROM notes WHERE id = ?";

            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public Integer getLatestId() {

        Integer latestId = 0;
        try {
            String sql = "SELECT * FROM teas ORDER BY id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            latestId = rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return latestId;
    }

    public Boolean deleteTea(int id) {
        try {
            String sql = "SELECT id FROM teas WHERE id = ?";
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next() == false) {
                return false;
            }
            sql = "DELETE FROM teas WHERE id = ?";
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            sql = "DELETE FROM notes WHERE tea_id = ?";
            pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

}
