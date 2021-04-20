package teastorehouse.ui;

import java.util.ArrayList;
import teastorehouse.db.Db;
import teastorehouse.controllers.TeaController;
import teastorehouse.utils.IO;
import java.util.Arrays;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ui extends Application {

    IO io = new IO();
    Db db = new Db();
    TeaController tC = new TeaController(db);

    private Scene listTeasScene;
    private Scene listNotesScene;
    private Scene addNewTeaScene;
    private Scene addNewNoteScene;
    private Scene startMenuScene;
    private VBox teaList = new VBox(10);

    public void getTeaList() {
        teaList.getChildren().clear();
        ArrayList<String> teasInDb = tC.getAll();
        teasInDb.forEach(tea -> {
            teaList.getChildren().add(new Text(tea));
        });
    }

    @Override
    public void start(Stage appStage) throws Exception {

        BorderPane menuPane = new BorderPane();
        menuPane.setMinSize(600, 400);
        menuPane.setPadding(new Insets(10, 10, 10, 10));
        Label welcomeText = new Label("Welcome to your tea storehouse");
        welcomeText.setFont(new Font("Arial", 18));
        menuPane.setCenter(welcomeText);
        HBox mainMenuButtonGroup = new HBox();
        mainMenuButtonGroup.setPadding(new Insets(10));
        Button listTeasButton = new Button("Browse teas");
        listTeasButton.setOnAction(click -> {
            getTeaList();
            appStage.setScene(listTeasScene);
            appStage.show();
        });
        Button listNotesButton = new Button("Browse tea notes");
        Button addNewTeaButton = new Button("Add a new tea");
        addNewTeaButton.setOnAction(click -> {
            getTeaList();
            appStage.setScene(addNewTeaScene);
            appStage.show();
        });
        Button addNewNoteButton = new Button("Add a new note");
        mainMenuButtonGroup.getChildren().addAll(listTeasButton, listNotesButton, addNewTeaButton, addNewNoteButton);
        mainMenuButtonGroup.setAlignment(Pos.CENTER);
        mainMenuButtonGroup.setPadding(new Insets(10));
        mainMenuButtonGroup.setSpacing(10);
        menuPane.setBottom(mainMenuButtonGroup);

        startMenuScene = new Scene(menuPane, 900, 650);

        //List teas view
        BorderPane listViewPane = new BorderPane();
        listViewPane.setMinSize(600, 400);
        listViewPane.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane listPane = new ScrollPane(teaList);
        listPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        listPane.setPrefViewportHeight(300);
        listPane.setStyle("-fx-background-color:transparent;");

        Label teaViewLabel = new Label("Teas in Storehouse");
        teaViewLabel.setFont(new Font("Arial", 16));
        VBox labelAndPane = new VBox();
        labelAndPane.setSpacing(20);
        labelAndPane.getChildren().addAll(teaViewLabel, listPane);
        labelAndPane.setAlignment(Pos.CENTER);
        listViewPane.setTop(labelAndPane);
        HBox teaViewButtonGroup = new HBox();
        teaViewButtonGroup.setPadding(new Insets(10));
        teaViewButtonGroup.setAlignment(Pos.CENTER);
        Button returnFromTeaListButton = new Button("Return to main menu");
        returnFromTeaListButton.setOnAction(click -> {
            appStage.setScene(startMenuScene);
            appStage.show();
        });
        teaViewButtonGroup.getChildren().addAll(returnFromTeaListButton);
        listViewPane.setBottom(teaViewButtonGroup);
        listTeasScene = new Scene(listViewPane, 900, 650);

        // Add a new tea View
        GridPane addTeaPane = new GridPane();
        addTeaPane.setAlignment(Pos.CENTER);
        
        Button returnFromAddTeaButton = new Button("Return to main menu");
        returnFromAddTeaButton.setOnAction(click -> {
            appStage.setScene(startMenuScene);
            appStage.show();
        });
        addTeaPane.add(returnFromAddTeaButton, 2, 0);
        GridPane addTeaPaneLeft = new GridPane();
        GridPane addTeaPaneRight = new GridPane();
        addTeaPane.add(addTeaPaneLeft, 0, 1);
        addTeaPane.add(addTeaPaneRight, 2, 1);

        addTeaPaneLeft.setPadding(new Insets(10));
        addTeaPaneLeft.setVgap(8);
        addTeaPaneLeft.setHgap(8);

        addTeaPaneRight.setPadding(new Insets(10));
        addTeaPaneRight.setVgap(8);
        addTeaPaneRight.setHgap(8);
        Label addTeaLabel = new Label("Add a new tea");
        addTeaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        addTeaPane.add(addTeaLabel, 0, 0);

        // Add Name Label
        Label teaNameLabel = new Label("Tea name : ");
        addTeaPaneLeft.add(teaNameLabel, 0, 1);
        TextField teaNameField = new TextField();
        teaNameField.setPrefHeight(40);
        addTeaPaneLeft.add(teaNameField, 1, 1);

        Label teaTypeLabel = new Label("Tea Type : ");
        addTeaPaneLeft.add(teaTypeLabel, 0, 2);
        TextField teaTypeField = new TextField();
        teaTypeField.setPrefHeight(40);
        addTeaPaneLeft.add(teaTypeField, 1, 2);

        Label teaScoreLabel = new Label("Score : ");
        addTeaPaneLeft.add(teaScoreLabel, 0, 3);
        TextField teaScoreField = new TextField();
        teaScoreField.setPrefHeight(40);
        addTeaPaneLeft.add(teaScoreField, 1, 3);

        Label teaPriceLabel = new Label("Price : ");
        addTeaPaneRight.add(teaPriceLabel, 0, 1);
        TextField teaPriceField = new TextField();
        teaPriceField.setPrefHeight(40);
        addTeaPaneRight.add(teaPriceField, 1, 1);

        Label teaAmountLabel = new Label("Amount : ");
        addTeaPaneRight.add(teaAmountLabel, 0, 2);
        TextField teaAmountField = new TextField();
        teaAmountField.setPrefHeight(40);
        addTeaPaneRight.add(teaAmountField, 1, 2);

        Label teaUsageLabel = new Label("Usage : ");
        addTeaPaneRight.add(teaUsageLabel, 0, 3);
        TextField teaUsageField = new TextField();
        teaUsageField.setPrefHeight(40);
        addTeaPaneRight.add(teaUsageField, 1, 3);

        Button addTeaButton = new Button("Add tea to Storehouse");
        addTeaPaneRight.add(addTeaButton, 3, 3);

        addTeaButton.setOnAction(action -> {
            if (teaNameField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please enter tea name");
                alert.setContentText("Tea name is mandatory");
                alert.show();
                return;
            }
            if (teaTypeField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please enter tea type");
                alert.setContentText("Tea type is mandatory");
                alert.show();
                return;
            }
            if (teaScoreField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Please enter tea score");
                alert.setContentText("Tea score is mandatory");
                alert.show();
                return;
            }
            String[] teaToBeAdded = new String[7];
            teaToBeAdded[1] = teaNameField.getText();
            teaToBeAdded[2] = teaTypeField.getText();
            teaToBeAdded[3] = teaScoreField.getText();
            teaToBeAdded[4] = teaPriceField.getText();
            teaToBeAdded[5] = teaAmountField.getText();
            teaToBeAdded[6] = teaUsageField.getText();
            
            tC.addTea(teaToBeAdded);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tea Added to Storehouse");
                alert.setContentText("Tea successfully added!");
                alert.show();
        });

        addNewTeaScene = new Scene(addTeaPane, 900, 650);

        // initialize stage with menu view
        appStage.setTitle("Tea Storehouse");
        appStage.setResizable(false);
        appStage.setScene(startMenuScene);
        appStage.show();
        appStage.setOnCloseRequest(event -> {
            System.out.println("shutting down");
            exit();
        });

//        io.print("Welcome to your Tea Storehouse");
//        Boolean commandLoop = true;
//        while(commandLoop){
//            io.printMainMenu();
//            String input = io.readString("Choose a command: ");
//            commandLoop=runCommand(input);
//        }
//        io.print("Goodbye");
//        exit();
//    }
//    
//        public Boolean runCommand(String command) {
//        switch (command) {
//            case "1":
//                tC.getAll();
//                return true;
//            case "2":
//                io.print("Feature not usable yet.");
//                return false;
//            case "3":
//                String[] placeholder = new String[7];
//                Arrays.fill(placeholder, "");
//                placeholder[1] = "Sample tea";
//                placeholder[2] = "Black";
//                placeholder[3] = "3.5";
//                tC.addTea(placeholder);
//                io.print("placeholder tea added to database");
//                return true;
//            case "4":
//                io.print("Feature not usable yet.");
//                return false;
//            case "0":
//                return false;    
//            default:
//                io.print("Invalid choice");
//                return true;
//        }
    }

    public static void main() {
        launch();
    }
}
