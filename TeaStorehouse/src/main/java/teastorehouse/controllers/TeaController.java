package teastorehouse.controllers;

import teastorehouse.db.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                            rs.getDouble("score"), rs.getDouble("price"),  rs.getDouble("amount"), rs.getDouble("usage")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return teasInDb;
    }

    // [0.id, 1.name, 2.teatype, 3.score, 4.price, 5.amount, 6.usage]
    public Boolean addTea(String[] variables) {

        if (variables[1].isEmpty() || variables[2].isEmpty() || variables[3].isEmpty())  {
            System.out.println("Mandatory information missing");
            return false;
        }
        if (variables[4].isEmpty()) {
            variables[4] = "0";
        }
        if (variables[5].isEmpty()) {
            variables[5] = "0";
        }
        if (variables[6].isEmpty()) {
            variables[6] = "0";
        }
        return db.addTea(variables);
    }
}
