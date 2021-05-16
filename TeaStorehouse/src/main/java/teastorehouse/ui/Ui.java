package teastorehouse.ui;

import java.util.ArrayList;
import teastorehouse.db.Db;
import teastorehouse.controllers.TeaController;
import teastorehouse.utils.IO;
import teastorehouse.utils.Validators;
import java.util.Arrays;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.math.NumberUtils;
import teastorehouse.controllers.NoteController;

public class Ui extends Application {

    IO io = new IO();
    Db db = new Db(false);
    TeaController teaController = new TeaController(db);
    NoteController noteController = new NoteController(db);
    private Validators validators = new Validators();
    private Scene listTeasScene;
    private Scene listNotesScene;
    private Scene addNewNoteScene;
    private Scene startMenuScene;
    private VBox teaList = new VBox(10);
    private VBox noteList = new VBox(10);
    private Scene addNewTeaScene;
    private ArrayList<String> teasInDb;
    private ArrayList<String> notesInDb;
    private String noteTargetId;

    /**
     * Method to update teaList inside a ScrollPane Uses TeaController class
     * method getAll() to receive list of teas and appends them as text.
     */
    public void getTeaList() {
        teaList.getChildren().clear();
        teasInDb = teaController.getAll();
        teasInDb.forEach(tea -> {
            teaList.getChildren().add(new Text(tea));
        });
    }

    public void getNoteList() {
        noteList.getChildren().clear();
        notesInDb = noteController.getAll();
        notesInDb.forEach(note -> {
            noteList.getChildren().add(new Text(note));
        });
    }

    /**
     *
     * @param appStage
     * @throws Exception
     */
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
        listNotesButton.setOnAction(click -> {
            getNoteList();
            appStage.setScene(listNotesScene);
            appStage.show();
        });

        Button addNewTeaButton = new Button("Add a new tea");
        addNewTeaButton.setOnAction(click -> {
            appStage.setScene(addNewTeaScene);
            appStage.show();
        });

//        Button addNewNoteButton = new Button("Add a new note");
//        addNewNoteButton.setOnAction(click -> {
//            appStage.setScene(addNewNoteScene);
//            appStage.show();
//        });
        mainMenuButtonGroup.getChildren().addAll(listTeasButton, listNotesButton, addNewTeaButton);
        mainMenuButtonGroup.setAlignment(Pos.CENTER);
        mainMenuButtonGroup.setPadding(new Insets(10));
        mainMenuButtonGroup.setSpacing(10);
        menuPane.setBottom(mainMenuButtonGroup);

        startMenuScene = new Scene(menuPane, 900, 650);

        //List teas view
        BorderPane teaListViewPane = new BorderPane();
        teaListViewPane.setMinSize(600, 400);
        teaListViewPane.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane teaListPane = new ScrollPane(teaList);
        teaListPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        teaListPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        teaListPane.setPrefViewportHeight(300);
        teaListPane.setStyle("-fx-background-color:transparent;");

        Label teaViewLabel = new Label("Teas in Storehouse");
        teaViewLabel.setFont(new Font("Arial", 16));
        VBox labelAndPane = new VBox();
        labelAndPane.setSpacing(20);
        labelAndPane.getChildren().addAll(teaViewLabel, teaListPane);
        labelAndPane.setAlignment(Pos.CENTER);
        teaListViewPane.setTop(labelAndPane);
        HBox teaViewButtonGroup = new HBox();
        teaViewButtonGroup.setSpacing(10);
        teaViewButtonGroup.setPadding(new Insets(10));
        teaViewButtonGroup.setAlignment(Pos.CENTER);
        Button returnFromTeaListButton = new Button("Return to main menu");
        returnFromTeaListButton.setOnAction(click -> {
            appStage.setScene(startMenuScene);
            appStage.show();
        });

        Button addANewNoteButton = new Button("Add a note for tea");
        addANewNoteButton.setOnAction(click -> {
            TextInputDialog whichTeaInput = new TextInputDialog();
            whichTeaInput.setHeaderText("Give tea id number");
            whichTeaInput.setContentText("Give id of the tea you \nwant to save the note for:");
            whichTeaInput.showAndWait();
            if (teaController.checkIfIdExists(whichTeaInput.getEditor().getText())) {
                noteTargetId = whichTeaInput.getEditor().getText();
                appStage.setScene(addNewNoteScene);
                appStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid tea id");
                alert.show();
            }
        });
        Button deleteTeaButton = new Button("Delete a tea");
        deleteTeaButton.setOnAction(click -> {
            TextInputDialog whichTeaToDelete = new TextInputDialog();
            whichTeaToDelete.setHeaderText("Give tea id number");
            whichTeaToDelete.setContentText("Give id of the tea \nyou want to delete:");
            whichTeaToDelete.showAndWait();
            if (teaController.deleteTea(whichTeaToDelete.getEditor().getText())) {
                getTeaList();
                appStage.setScene(listTeasScene);
                appStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid note id");
                alert.show();
            }
        });

        teaViewButtonGroup.getChildren().addAll(returnFromTeaListButton, addANewNoteButton, deleteTeaButton);
        teaListViewPane.setBottom(teaViewButtonGroup);
        listTeasScene = new Scene(teaListViewPane, 900, 650);

        //List notes view
        BorderPane notesViewPane = new BorderPane();
        notesViewPane.setMinSize(600, 400);
        notesViewPane.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane noteListPane = new ScrollPane(noteList);

        noteListPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        noteListPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        noteListPane.setPrefViewportHeight(300);
        noteListPane.setStyle("-fx-background-color:transparent;");

        Label notesViewLabel = new Label("Notes in Storehouse");
        notesViewLabel.setFont(new Font("Arial", 16));
        VBox notesLabelAndPane = new VBox();
        notesLabelAndPane.setSpacing(20);
        notesLabelAndPane.getChildren().addAll(notesViewLabel, noteListPane);
        notesLabelAndPane.setAlignment(Pos.CENTER);
        notesViewPane.setTop(notesLabelAndPane);
        HBox notesViewButtonGroup = new HBox();
        notesViewButtonGroup.setSpacing(10);
        notesViewButtonGroup.setPadding(new Insets(10));
        notesViewButtonGroup.setAlignment(Pos.CENTER);
        Button returnFromNoteListButton = new Button("Return to main menu");
        returnFromNoteListButton.setOnAction(click -> {
            appStage.setScene(startMenuScene);
            appStage.show();
        });
        
        Button deleteNoteButton = new Button("Delete a note");
        deleteNoteButton.setOnAction(click -> {
            TextInputDialog whichNoteInput = new TextInputDialog();
            whichNoteInput.setHeaderText("Give note id number");
            whichNoteInput.setContentText("Give id of the note \nyou want to delete:");
            whichNoteInput.showAndWait();
            if (noteController.deleteNote(whichNoteInput.getEditor().getText())) {
                getNoteList();
                appStage.setScene(listNotesScene);
                appStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid note id");
                alert.show();
            }
        });
        notesViewButtonGroup.getChildren().addAll(returnFromNoteListButton, deleteNoteButton);
        notesViewPane.setBottom(notesViewButtonGroup);
        listNotesScene = new Scene(notesViewPane, 900, 650);

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

            Alert alert = new Alert(Alert.AlertType.ERROR);

            String[] teaToBeAdded = new String[7];
            Arrays.fill(teaToBeAdded, "0");

            if (teaNameField.getText().isEmpty()) {
                alert.setTitle("Please enter tea name");
                alert.setContentText("Tea name is mandatory");
                alert.show();
                return;
            } else {
                teaToBeAdded[1] = teaNameField.getText();
            }
            if (teaTypeField.getText().isEmpty()) {
                alert.setTitle("Please enter tea type");
                alert.setContentText("Tea type is mandatory");
                alert.show();
                return;
            } else {
                teaToBeAdded[2] = teaTypeField.getText();
            }
            if (teaScoreField.getText().isEmpty() || !NumberUtils.isParsable(teaScoreField.getText())) {
                alert.setTitle("Please enter tea score");
                alert.setContentText("Tea score is mandatory and must be a number.");
                alert.show();
                return;
            }
            teaToBeAdded[3] = teaScoreField.getText();

            if (!validators.validateEntryNumberOrEmpty(teaPriceField.getText())) {
                alert.setTitle("Invalid tea price");
                alert.setContentText("Tea price needs to be empty or a number.");
                alert.show();
                return;
            } else if (!teaPriceField.getText().isEmpty()) {
                teaToBeAdded[4] = teaPriceField.getText();
            }

            if (!validators.validateEntryNumberOrEmpty(teaAmountField.getText())) {
                alert.setTitle("Invalid tea amount");
                alert.setContentText("Tea amount needs to be empty or a number.");
                alert.show();
                return;
            } else if (!teaAmountField.getText().isEmpty()) {
                teaToBeAdded[5] = teaAmountField.getText();
            }

            if (!validators.validateEntryNumberOrEmpty(teaUsageField.getText())) {

                alert.setTitle("Invalid tea usage");
                alert.setContentText("Tea usage needs to be empty or a number.");
                alert.show();
                return;
            } else if (!teaUsageField.getText().isEmpty()) {
                teaToBeAdded[6] = teaUsageField.getText();
            }

            if (teaController.addTea(teaToBeAdded)) {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "Tea Added");
                alert.setTitle("Tea Added to Storehouse");
                alert.setContentText("Tea successfully added!\nWould you like to add a note to new the\nnew tea?");

                Optional<ButtonType> selection = alert.showAndWait();
                if (selection.get() == ButtonType.OK) {
                    noteTargetId = "" + teaController.getLatestId();
                    appStage.setScene(addNewNoteScene);
                } else {
                    appStage.setScene(startMenuScene);
                }
            } else {
                alert.setTitle("Adding tea failed.");
                alert.setContentText("Adding tea to database failed.");
                alert.show();
            }

        });

        addNewTeaScene = new Scene(addTeaPane, 900, 650);

        // Add a new note View
        GridPane addNotePane = new GridPane();
        addNotePane.setHgap(10);
        addNotePane.setVgap(10);
        addNotePane.setAlignment(Pos.CENTER);
        TextArea noteText = new TextArea();
        noteText.setText("Write your note here.");
        noteText.setWrapText(true);
        addNotePane.add(noteText, 0, 0, 2, 2);
        Button returnFromAddNoteButton = new Button("Return to main menu");
        returnFromAddNoteButton.setOnAction(click -> {
            appStage.setScene(startMenuScene);
            appStage.show();
        });
        addNotePane.add(returnFromAddNoteButton, 0, 2, 1, 1);
        Button submitNewNoteButton = new Button("Add note");
        submitNewNoteButton.setOnAction(click -> {
            String[] inputForNote = new String[3];
            inputForNote[1] = noteTargetId;
            inputForNote[2] = noteText.getText();
            noteController.addNote(inputForNote);
            appStage.setScene(startMenuScene);
            appStage.show();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Note Added");
            alert.setTitle("Note successfully added!");
            alert.show();

        });
        addNotePane.add(submitNewNoteButton, 2, 2, 1, 1);

        addNewNoteScene = new Scene(addNotePane, 900, 650);

        // initialize stage with menu view
        appStage.setTitle("Tea Storehouse");
        appStage.setResizable(false);
        appStage.setScene(startMenuScene);
        appStage.show();
        appStage.setOnCloseRequest(event -> {
            System.out.println("shutting down");
            exit();
        });

    }

    public static void main() {
        launch();
    }
}
