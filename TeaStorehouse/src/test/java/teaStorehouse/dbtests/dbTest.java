package teaStorehouse.dbtests;

import java.util.Arrays;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import teastorehouse.db.Db;


public class dbTest {

    Db dbTest;
    // [0.id, 1.name, 2.teatype, 3.score, 4.price, 5.amount, 6.usage]
    String[] emptyUserInput = new String[7];

    @Before
    public void setUp() {
        dbTest = new Db(true);
        Arrays.fill(emptyUserInput, "");
    }

    @Test
    public void invalidDbQueryReturnsFalse() {
        try {
            dbTest.addTeaToDb(emptyUserInput);
            fail("Exception didnt occur on invalid query.");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }

    }

    @After
    public void tearDown() {
    }

}
