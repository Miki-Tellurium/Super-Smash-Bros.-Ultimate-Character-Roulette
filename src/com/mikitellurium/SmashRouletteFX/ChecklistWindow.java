package com.mikitellurium.SmashRouletteFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class ChecklistWindow {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Stage stage = new Stage();
    GridPane checkBoxesPane = new GridPane();
    Pane mainPane = new Pane(checkBoxesPane);
    Scene scene = new Scene(mainPane);

    final BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/mural.jpg", 1100, 250, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1100, 250, false, false, false, false));
    final Background background = new Background(backgroundImage);

    TextField searchField = new TextField();
    Button hint = new Button();
    Tooltip hintTooltip = new Tooltip();

    public  ChecklistWindow(Stage mainStage) {
        stage.initOwner(mainStage);
        stage.setTitle("Characters Checklist");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(1100);
        stage.setHeight(250);
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
        hintTooltip.setText("Search a character to highlight it on the list");
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

        scene.getStylesheets().add("/resources/buttonStyle.css");
        stage.setScene(scene);
        stage.show();
    }
}
