package teastorehouse.controllers;

import teastorehouse.db.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.lang3.math.NumberUtils;

public class TeaController {

    Db db;

    /**
     * Controller class middleware working between UI and Tea table in database.
     *
     * @param db needs a database object as a parameter
     */
    public TeaController(Db db) {
        this.db = db;
    }

    /**
     * Method to return all teas from database.
     *
     * @return a list containing teas as formatted strings.
     */
    public ArrayList<String> getAll() {
        ArrayList<String> teasInDb = new ArrayList<>();
        try {
            ResultSet rs = db.getAllTeas();
            if (!rs.isBeforeFirst()) {
                teasInDb.add("No teas in database yet");
                return teasInDb;
            } else {
                teasInDb.add(String.format("%-10s %-70s %-26s %-20s %-20s %-20s %-1s", "id", "name", "teatype", "score", "price", "amount", "usage"));
                while (rs.next()) {
                    teasInDb.add(String.format("%-10s %-70s %-26s %-20s %-20s %10s %22s", rs.getInt("id"), rs.getString("name"), rs.getString("teatype"),
                            rs.getDouble("score"), rs.getDouble("price"), rs.getDouble("amount"), rs.getDouble("usage")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return teasInDb;
    }

    /**
     * Method to add tea to database.
     *
     * @param userInput expects array with these values in index #: 1.name -
     * string, 2.teatype - string, 3.score - double, 4.price - double, 5.amount
     * - double, 6.usage - double.
     *
     * @return True after a successful database insertion. False after failed
     * validation or failed database insertion.
     *
     */
    public Boolean addTea(String[] userInput) {
        // [0.id, 1.name, 2.teatype, 3.score, 4.price, 5.amount, 6.usage]
        if (userInput[1].isEmpty() || userInput[2].isEmpty()) {
            return false;
        }
        if (userInput[3].isEmpty() || !NumberUtils.isParsable(userInput[3])) {
            return false;
        }
        if (!validateEntryNumberOrEmpty(userInput[4]) || !validateEntryNumberOrEmpty(userInput[5])
                || !validateEntryNumberOrEmpty(userInput[6])) {
            return false;
        }
        return db.addTeaToDb(userInput);
    }

    public Boolean checkIfIdExists(String id) {
        if (!NumberUtils.isParsable(id)) {
            return false;
        }
        return db.checkIfIdExists(id);
    }

    public Integer getLatestId() {
        return db.getLatestId();
    }

    public Boolean deleteTea(String id) {
        if (!NumberUtils.isParsable(id)) {
            return false;
        }
        return db.deleteTea(Integer.parseInt(id));
    }

    /**
     * Validation method which checks if given string is parsable into number.
     *
     * @param string single string from various userinputs.
     * @return True if string is empty or not empty & parsable. False if not
     * empty & not parsable.
     */
    private boolean validateEntryNumberOrEmpty(String string) {
        if (!string.isEmpty() && !NumberUtils.isParsable(string)) {
            return false;
        }
        return true;
    }

}
