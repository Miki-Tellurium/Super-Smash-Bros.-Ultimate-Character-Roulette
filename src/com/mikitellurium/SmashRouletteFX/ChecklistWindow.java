package com.mikitellurium.SmashRouletteFX;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ChecklistWindow {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Stage stage = new Stage();
    GridPane checkBoxesPane = new GridPane();
    Pane mainPane = new Pane();
    Scene scene = new Scene(mainPane);

    final BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/mural.jpg", 1100, 250, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1100, 250, false, false, false, false));
    final Background background = new Background(backgroundImage);

    TextField searchField = new TextField();
    Button hint = new Button();
    Tooltip hintTooltip = new Tooltip();

    protected ArrayList<CheckBox> boxes = new ArrayList<>();
    final private String charactersProperties = "characters.properties";
    final Properties properties = new Properties();

    public  ChecklistWindow(Stage mainStage) throws IOException {
        stage.initOwner(mainStage);
        stage.setTitle("Characters Checklist");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(1000);
        stage.setHeight(260);
        stage.setX(screenSize.getWidth()/2 - stage.getWidth()/2);
        stage.setY(screenSize.getHeight()/2 - stage.getHeight()/2);

        searchField.setPromptText("Search...");
        searchField.setPrefSize(120, 10);
        searchField.setLayoutX(5);
        searchField.setLayoutY(0);

        hint.setText("?");
        hint.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        hint.setPrefSize(10, 10);
        hint.setPadding(new Insets(2, 7, 1, 7));
        hint.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 5);
        hint.setLayoutY(0);
        hint.getStyleClass().add("hint");
        hintTooltip.setText("Type a character name to highlight it on the list");
        hintTooltip.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        hintTooltip.setStyle("-fx-background-color: linear-gradient(to left, #24CFFE, #007BFD); -fx-text-fill: black;");
        hintTooltip.setMaxHeight(10);
        hintTooltip.setShowDelay(Duration.ZERO);
        hintTooltip.setHideDelay(Duration.ZERO);
        hintTooltip.setX(100);
        hintTooltip.setY(100);
        hint.setTooltip(hintTooltip);

        mainPane.setBackground(background);
        mainPane.getChildren().addAll(searchField, hint);

        checkBoxesPane.setPrefSize(stage.getWidth(), stage.getHeight() - 25);
        checkBoxesPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        checkBoxesPane.setLayoutX(0);
        checkBoxesPane.setLayoutY(25);
        checkBoxesPane.setPadding(new Insets(7));
        checkBoxesPane.setVgap(2);
        checkBoxesPane.setHgap(15);
        makeCheckBoxes();
        initializeCheckBoxes();
        mainPane.getChildren().add(checkBoxesPane);

        scene.getStylesheets().add("/resources/style.css");
        stage.setScene(scene);
        stage.show();
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
    /* Check if the properties file already exist */
    private void initializeCheckBoxes() throws IOException {
        File file = new File(charactersProperties);
        if (file.exists()) {
            readCheckBoxState(boxes);
        } else {
            saveCheckBoxState(boxes);
        }
    }
    /* Read the properties file to set the state of all checkboxes */
    private void readCheckBoxState(ArrayList<CheckBox> arrayList) throws IOException {
        InputStream inputProp = new FileInputStream(charactersProperties);
        properties.load(inputProp);
        for (CheckBox box : arrayList) {
            box.setSelected(Boolean.parseBoolean(properties.getProperty(box.getText())));
        }
    }
    /* Generate a new properties file to save character progress */
    private void saveCheckBoxState(ArrayList<CheckBox> arrayList) throws IOException {
        OutputStream outputProp = new FileOutputStream(charactersProperties);
        for (CheckBox box : arrayList) {
            properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
        }
        properties.store(outputProp, "This file register the checkboxes state");
    }
}
