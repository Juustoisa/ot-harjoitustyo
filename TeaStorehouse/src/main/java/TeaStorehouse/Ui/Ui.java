package TeaStorehouse.Ui;

import TeaStorehouse.Db.Db;
import TeaStorehouse.controllers.teaController;
import TeaStorehouse.utils.IO;
import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.stage.Stage;

public class Ui extends Application {
    
    IO io = new IO();
    Db db = new Db();
    teaController tC = new teaController(db);
            
    @Override
    public void start(Stage arg0) throws Exception {
        io.print("Welcome to your Tea Storehouse");
        Boolean commandLoop = true;
        while(commandLoop){
            io.printMainMenu();
            String input = io.readString("Choose a command: ");
            commandLoop=runCommand(input);
        }
        io.print("Goodbye");
        exit();
    }
    
        public Boolean runCommand(String command) {
        switch (command) {
            case "1":
                tC.getAll();
                return true;
            case "2":
                io.print("Feature not usable yet.");
                return false;
            case "3":
                String[] placeholder = new String[7];
                Arrays.fill(placeholder, "");
                placeholder[1] = "Sample tea";
                placeholder[2] = "Black";
                placeholder[3] = "3.5";
                tC.addTea(placeholder);
                io.print("placeholder tea added to database");
                return true;
            case "4":
                io.print("Feature not usable yet.");
                return false;
            case "0":
                return false;    
            default:
                io.print("Invalid choice");
                return true;
        }
    }
    
    public static void main(){
        launch();
    }
}
