package teastorehouse.controllers;

import teastorehouse.db.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.lang3.math.NumberUtils;

public class NoteController {

    Db db;
    /**
     * Controller class middleware working between UI and Note table in database.
     * 
     * @param db needs a database object as a parameter
     */
    public NoteController(Db db) {
        this.db = db;
    }
    /**
     * Method to return all notes from database.
     * 
     * @return a list containing notes as formatted strings. 
     */
    public ArrayList<String> getAll() {
        ArrayList<String> notesInDb = new ArrayList<>();
        try {
            ResultSet rs = db.getAllNotes();
            if (!rs.isBeforeFirst()) {
                notesInDb.add("No notes in database yet");
                return notesInDb;
            } else {
                notesInDb.add(String.format("%-10s %-10s %-70s", "id", "tea", "note"));
                while (rs.next()) {
                    notesInDb.add(String.format("%-10s %-10s %-70s", rs.getInt("id"), rs.getString("name"), rs.getString("note")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return notesInDb;
    }
    /**
     * Method to add note to database.
     * 
     * @param userInput expects array with these values in index #:
     *  1.tea_id  - string, 2.note - string
     * 
     * @return True after a successful database insertion. 
     *         False after failed validation or failed database insertion.
     * 
     */
    public Boolean addNote(String[] userInput) {
        if (!NumberUtils.isParsable(userInput[1]) || userInput[1].isEmpty() || userInput[2].isEmpty()) {
            return false;
        }
        
        
        return db.addNoteToDb(userInput);
    }
    
    public Boolean checkIfIdExists(String id) {
        if (!NumberUtils.isParsable(id)) {
            return false;
        }
        return db.checkIfIdExists(id);
    }
    
    public Boolean deleteNote(String id) {
        if (!NumberUtils.isParsable(id)) {
            return false;
        }
        return db.deleteNote(Integer.parseInt(id));
    }
    
    /**
     * Validation method which checks if given string is parsable into number.
     * @param string single string from various userinputs.
     * @return True if string is empty or not empty & parsable. 
     *         False if not empty & not parsable.
     */
    private boolean validateEntryNumberOrEmpty(String string) {
        if (!string.isEmpty() && !NumberUtils.isParsable(string)) {
            return false;
        }
        return true;
    }

}
