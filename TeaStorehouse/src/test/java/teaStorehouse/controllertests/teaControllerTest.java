/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaStorehouse.controllertests;

import java.util.ArrayList;
import teastorehouse.db.Db;
import teastorehouse.controllers.TeaController;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author tata
 */
public class teaControllerTest {

    Db dbTest;
    TeaController tC;
    // [0.id, 1.name, 2.teatype, 3.score, 4.price, 5.amount, 6.usage]
    String[] emptyUserInput = new String[7];

    @Before
    public void setUp() {
        dbTest = new Db(true);
        tC = new TeaController(dbTest);
        Arrays.fill(emptyUserInput, "");
    }

    @Test
    public void noTeaNameResultsInFail() {
        emptyUserInput[2] = "Green Tea";
        emptyUserInput[3] = "5.5";
        assertFalse(tC.addTea(emptyUserInput));
    }

    @Test
    public void noTeaTypeResultsInFail() {
        emptyUserInput[1] = "Lipton Lemon";
        emptyUserInput[3] = "0.5";
        assertFalse(tC.addTea(emptyUserInput));
    }

    @Test
    public void noTeaScoreResultsInFail() {
        emptyUserInput[1] = "Lipton Lemon";
        emptyUserInput[2] = "Black tea";
        assertFalse(tC.addTea(emptyUserInput));
    }
    
    @Test
    public void newDbIsEmpty(){
        ArrayList<String> TeaList = tC.getAll();
        assertTrue(TeaList.get(0).contains("No teas in database yet")); 
    }

    
    @Test
    public void validTeaGetsSavedToDb(){
        emptyUserInput[1]="Lipton Lemon";
        emptyUserInput[2]="Black tea";
        emptyUserInput[3]="9";
        emptyUserInput[4]="0";
        emptyUserInput[5]="0";
        emptyUserInput[6]="0";
        assertTrue(tC.addTea(emptyUserInput));
        ArrayList<String> TeaList = tC.getAll();
        assertTrue(TeaList.get(1).contains("Lipton Lemon"));
        assertTrue(TeaList.get(1).contains("Black tea"));
    }
     

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
