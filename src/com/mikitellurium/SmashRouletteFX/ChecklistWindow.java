package com.mikitellurium.SmashRouletteFX;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class ChecklistWindow {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Stage stage = new Stage();
    GridPane checkBoxesPane = new GridPane();
    Pane mainPane = new Pane();
    Scene scene = new Scene(mainPane);

    final BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/mural.jpg", 1100, 250, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1100, 250, false, false, false, false));
    final Background background = new Background(backgroundImage);

    MenuItem checkAllBoxes = new MenuItem("Check All");
    MenuItem uncheckAllBoxes = new MenuItem("Uncheck All");
    MenuItem higlightColorOptions = new MenuItem("Higlight Color");
    Menu options = new Menu("Options");
    MenuBar menuBar = new MenuBar();

    TextField searchField = new TextField();
    ChangeListener searchFieldListener = (observableValue, o, t1) -> {
        //Highlight the characters corresponding with the typed text
        String name = searchField.getText();
        for (CheckBox box : boxes) {
            if (textContains(box.getText(), name)) {
                box.setTextFill(Color.rgb(255, 0, 75));
            } else {
                box.setTextFill(Color.BLACK);
            }
        }
    };
    Button hint = new Button();
    Tooltip hintTooltip = new Tooltip();
    Text currentStatus = new Text();

    private static final ArrayList<CheckBox> boxes = new ArrayList<>();
    final private String charactersProperties = "characters.properties";
    final Properties properties = new Properties();

    public ChecklistWindow(Stage mainStage) throws IOException {
        stage.initOwner(mainStage);
        stage.setTitle("Characters Checklist");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(1000);
        stage.setHeight(280);
        stage.setX(screenSize.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(screenSize.getHeight() / 2 - stage.getHeight() / 2);

        searchField.setPromptText("Search...");
        searchField.setPrefSize(120, 10);
        searchField.setLayoutX(5);
        searchField.setLayoutY(25);
        searchField.textProperty().addListener(searchFieldListener);

        hint.setText("?");
        hint.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        hint.setPrefSize(10, 10);
        hint.setPadding(new Insets(2, 7, 1, 7));
        hint.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 5);
        hint.setLayoutY(25);
        hint.getStyleClass().add("hint");
        hintTooltip.setText("Type a character name to highlight it on the list");
        hintTooltip.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        hintTooltip.setStyle("-fx-background-color: linear-gradient(to left, #24CFFE, #007BFD); -fx-text-fill: black;");
        hintTooltip.setMaxHeight(10);
        hintTooltip.setShowDelay(Duration.ZERO);
        hintTooltip.setHideDelay(Duration.ZERO);
        hint.setTooltip(hintTooltip);

        currentStatus.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        currentStatus.setLayoutX(245);
        currentStatus.setLayoutY(47);

        options.getItems().addAll(checkAllBoxes, uncheckAllBoxes, higlightColorOptions);
        menuBar.getMenus().add(options);
        menuBar.setUseSystemMenuBar(true);
        menuBar.setPrefSize(stage.getWidth(), 20);
        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);

        checkBoxesPane.setPrefSize(stage.getWidth(), stage.getHeight() - 25);
        checkBoxesPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        checkBoxesPane.setLayoutX(0);
        checkBoxesPane.setLayoutY(50);
        checkBoxesPane.setPadding(new Insets(4));
        checkBoxesPane.setVgap(2);
        checkBoxesPane.setHgap(15);
        makeCheckBoxes();
        initializeCheckBoxes();
        currentStatus.setText(updateCurrentStatusText(getBoxesChecked(boxes)));
        mainPane.getChildren().add(checkBoxesPane);
        mainPane.getChildren().addAll(menuBar, searchField, hint, currentStatus);
        mainPane.setBackground(background);

        scene.getStylesheets().add("/resources/style.css");
        stage.setScene(scene);
    }
    /* Make the check-boxes and add them to an array */
    private void makeCheckBoxes() {
        int column = 0;
        int row = 0;
        for (int character = 0; character < 86; character++) {
            CheckBox checkBox = new CheckBox(CharacterListFX.getCharacterName(character));
            GridPane.setConstraints(checkBox, column, row);
            checkBox.setFont(Font.font(FontUIResource.DIALOG, FontWeight.EXTRA_BOLD, 12));
            checkBox.setTextFill(Color.BLACK);
            checkBox.setOnAction(this::fireCheckbox);
            checkBoxesPane.getChildren().add(checkBox);
            boxes.add(checkBox);
            if (column < 7) {
                column++;
            } else {
                column = 0;
                row++;
            }
        }
    }

    /* Show this window on the screen */
    public void show() {
        stage.show();
    }

    /* Returns true if the specified box is checked */
    public static boolean isBoxChecked(int box) {
        return boxes.get(box).isSelected();
    }

    /* Returns true if all check-boxes are checked */
    public static boolean areAllBoxChecked() {
        for (CheckBox box : boxes) {
            if (!box.isSelected()) {
                return false;
            }
        }
        return true;
    }

    /* Checks if the properties file already exist */
    private void initializeCheckBoxes() throws IOException {
        File file = new File(charactersProperties);
        if (file.exists()) {
            readCheckBoxState(boxes);
        } else {
            saveCheckBoxState(boxes);
        }
    }

    /* Reads the properties file to set the state of all checkboxes */
    private void readCheckBoxState(ArrayList<CheckBox> arrayList) throws IOException {
        InputStream inputProperties = new FileInputStream(charactersProperties);
        properties.load(inputProperties);
        for (CheckBox box : arrayList) {
            box.setSelected(Boolean.parseBoolean(properties.getProperty(box.getText())));
        }
    }

    /* Generates a new properties file to save character progress */
    private void saveCheckBoxState(ArrayList<CheckBox> arrayList) throws IOException {
        OutputStream outputProperties = new FileOutputStream(charactersProperties);
        for (CheckBox box : arrayList) {
            properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
        }
        properties.store(outputProperties, "This file register the checkboxes state");
    }

    /* Returns how many box are checked */
    private int getBoxesChecked(ArrayList<CheckBox> arrayList) {
        int numberOfBoxes = 0;
        for (CheckBox box : arrayList) {
            if (box.isSelected()) {
                numberOfBoxes++;
            }
        }
        return numberOfBoxes;
    }

    /* Returns true if substring is contained in string */
    private boolean textContains(String string, String substring) {
        if (string == null) {
            return false;
        }
        if (substring == null | Objects.equals(substring, "")) {
            return false;
        }
        char[] fullString = string.toLowerCase().toCharArray();
        char[] sub = substring.toLowerCase().toCharArray();
        int counter = 0;
        if (sub.length == 0) {
            return true;
        }
        for (char c : fullString) {
            if (c == sub[counter]) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == sub.length) {
                return true;
            }
        }
        return false;
    }

    /* Returns a string that states the current number of characters selected */
    private String updateCurrentStatusText(int numberOfBox) {
        String text;
        if (numberOfBox == 0) {
            text = "No character selected";
            return text;
        } else if(numberOfBox == 1) {
            text = " character selected";
            return numberOfBox + text;
        } else if (numberOfBox == 86) {
            text = "Every character selected";
            return text;
        } else {
            text = " characters selected";
            return numberOfBox + text;
        }
    }

    /* Updates the checkbox state in the characters.properties file */
    private void fireCheckbox(ActionEvent e) {
        if (e.getSource() instanceof CheckBox box) {
            try {
                OutputStream outputProperties = new FileOutputStream(charactersProperties);
                System.out.println("True");
                properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
                properties.store(outputProperties, "This file register the checkboxes state");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        //Updates the current number of characters selected
        currentStatus.setText(updateCurrentStatusText(getBoxesChecked(boxes)));
    }
}