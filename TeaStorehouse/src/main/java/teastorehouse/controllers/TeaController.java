package teastorehouse.controllers;

import teastorehouse.db.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.lang3.math.NumberUtils;

public class TeaController {

    Db db;

    public TeaController(Db db) {
        this.db = db;
    }

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

    // [0.id, 1.name, 2.teatype, 3.score, 4.price, 5.amount, 6.usage]
    public Boolean addTea(String[] userInput) {
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

    private boolean validateEntryNumberOrEmpty(String string) {
        if (!string.isEmpty() && !NumberUtils.isParsable(string)) {
            return false;
        }
        return true;
    }

}
