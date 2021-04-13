package TeaStorehouse.controllers;

import TeaStorehouse.Db.Db;
import java.sql.ResultSet;
import java.sql.SQLException;

public class teaController {

    Db db;

    public teaController(Db db) {
        this.db = db;
    }

    public void getAll() {

        try {
            ResultSet rs = db.getAllTeas();
            if (!rs.isBeforeFirst()) {
                System.out.println("No teas in database yet");
            } else { 
                System.out.println("Teas in storehouse:\n");
                System.out.println(("id") + "\t"
                        + ("name") + "\t"
                        + ("teatype") + "\t"
                        + ("score") + "\t"
                        + "price" + "\t"
                        + ("amount") + "\t"
                        + ("usage") + "\t");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + "\t"
                            + rs.getString("name") + "\t"
                            + rs.getString("teatype") + "\t"
                            + rs.getDouble("score") + "\t"
                            + rs.getDouble("price") + "\t"
                            + rs.getDouble("amount") + "\t"
                            + rs.getDouble("usage") + "\t");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // [0.id, 1.name, 2.teatype, 3.score, 4.price, 5.amount, 6.usage]
    public Boolean addTea(String[] variables) {

        if (variables[1].isEmpty()) {
            System.out.println("Every tea requires a name");
            return false;
        }
        if (variables[2].isEmpty()) {
            System.out.println("Every tea requires a teatype");
            return false;
        }
        if (variables[3].isEmpty()) {
            System.out.println("Every tea requires a score");
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
