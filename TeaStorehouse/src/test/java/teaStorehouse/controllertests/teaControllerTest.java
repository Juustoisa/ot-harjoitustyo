/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaStorehouse.controllertests;

import teastorehouse.db.Db;
import teastorehouse.controllers.TeaController;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
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
    String[] userinput = new String[7];

    @Before
    public void setUp() {
        dbTest = mock(Db.class);
        tC = new TeaController(dbTest);
        Arrays.fill(userinput, "");
    }
    
    @Test
    public void noTeaNameResultsInFail(){
        userinput[2]="Green Tea";
        userinput[3]="5.5";
        assertFalse(tC.addTea(userinput));
    }
    
    @Test
    public void noTeaTypeResultsInFail(){
        userinput[1]="Lipton Lemon";
        userinput[3]="0.5";
        assertFalse(tC.addTea(userinput));
    }
    
    @Test
    public void noTeaScoreResultsInFail(){
        userinput[1]="Lipton Lemon";
        userinput[2]="Black tea";
        assertFalse(tC.addTea(userinput));
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
