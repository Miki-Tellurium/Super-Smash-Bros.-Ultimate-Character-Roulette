/* Migration from Swing to JavaFX
 * WIP
 */

package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.SmashRoulette;
import com.mikitellurium.SmashRoulette.util.Characters;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainWindow {

    Pane rootPane = new Pane();
    Scene mainScene = new Scene(rootPane);

    final BackgroundImage baseRefreshImage = new BackgroundImage(new Image("/resources/base refresh icon.png"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(28, 28, false, false, false, false));
    final Background baseRefreshIcon = new Background(baseRefreshImage);
    final BackgroundImage hoverRefreshImage = new BackgroundImage(new Image("/resources/hover refresh icon.png"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(28, 28, false, false, false, false));
    final Background hoverRefreshIcon = new Background(hoverRefreshImage);
    final BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/background.jpg", 500, 300, true, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    final Background mainStageBackground = new Background(backgroundImage);

    final String fontArial = "Arial";
    //This button roll a random character
    Button randomButton = new Button();
    //This button refresh the random button
    Button refreshButton = new Button();
    //This button open the second window
    Button characterListButton = new Button();
    //Display the credits
    Text credit = new Text();
    //Display the version
    Text version = new Text();

    //Display the name of the character
    Text characterName = new Text();
    //Display the render of the character
    ImageView characterRender = new ImageView();
    //Display the series symbol
    ImageView seriesSymbol = new ImageView();

    public MainWindow(Stage mainStage) throws IOException {
        //Main window
        mainStage.setTitle("Super Smash Bros. Ultimate Characters Roulette");
        mainStage.getIcons().add(new Image("/resources/smash logo.png"));
        mainStage.setResizable(false);
        mainStage.setWidth(500);
        mainStage.setHeight(300);
        //The window always appear at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainStage.setX(screenSize.getWidth()/2 - mainStage.getWidth()/2);
        mainStage.setY(screenSize.getHeight()/2 - mainStage.getHeight()/2);

        randomButton.setText("Random");
        randomButton.setPrefSize(100, 30);
        randomButton.setLayoutX(mainStage.getWidth()/2 - randomButton.getPrefWidth()/2);
        randomButton.setLayoutY(40);
        randomButton.setFont(Font.font(fontArial, FontWeight.BOLD, 14));
        randomButton.getStyleClass().add("normal-buttons");
        randomButton.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
        randomButton.setOnMouseEntered(e -> changeButtonColorWhenEnter(randomButton));
        randomButton.setOnMouseExited(e -> changeButtonColorWhenExit(randomButton));
        randomButton.setOnAction(e -> randomButtonAction());

        refreshButton.setPrefSize(30, 30);
        refreshButton.setLayoutX(randomButton.getLayoutX() + randomButton.getPrefWidth() + 5);
        refreshButton.setLayoutY(randomButton.getLayoutY());
        refreshButton.setBackground(baseRefreshIcon);
        refreshButton.getStyleClass().add("refresh-button");
        refreshButton.setDisable(true);
        refreshButton.setOnMouseEntered(e -> changeIconWhenEnter(refreshButton));
        refreshButton.setOnMouseExited(e -> changeIconWhenExit(refreshButton));
        refreshButton.setOnAction(e -> refreshButtonAction());

        characterListButton.setText("Characters\n  checklist");
        characterListButton.setPrefSize(80, 40);
        characterListButton.setLayoutX(mainStage.getWidth() - characterListButton.getPrefWidth() - 20);
        characterListButton.setLayoutY(mainStage.getHeight() - characterListButton.getPrefHeight()*2 - 3);
        characterListButton.setFont(Font.font(fontArial, FontWeight.BOLD, 11));
        characterListButton.setTextAlignment(TextAlignment.JUSTIFY);
        characterListButton.getStyleClass().add("normal-buttons");
        characterListButton.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
        characterListButton.setOnMouseEntered(e -> changeButtonColorWhenEnter(characterListButton));
        characterListButton.setOnMouseExited(e -> changeButtonColorWhenExit(characterListButton));
        ChecklistWindow checklistWindow = new ChecklistWindow(mainStage);
        characterListButton.setOnAction(e -> checklistWindow.show());

        credit.setText("by Miki_Tellurium");
        credit.setFont(Font.font(fontArial, FontWeight.BOLD, 12));
        credit.setLayoutX(mainStage.getWidth() - credit.getText().length()*7);  //multiplying by 7 moves the text right enough
        credit.setLayoutY(12);

        version.setText("v" + SmashRoulette.VERSION);
        version.setFont(Font.font(fontArial, FontWeight.BOLD, 12));
        version.setLayoutX(2);
        version.setLayoutY(mainStage.getHeight() - 42);

        characterName.setFont(Font.font(fontArial, FontWeight.BOLD, 32));
        characterName.setLayoutY(randomButton.getLayoutY() + randomButton.getPrefHeight()*2.5);

        characterRender.setViewOrder(1.0);
        characterRender.setFitWidth(300);
        characterRender.setFitHeight(300);
        characterRender.setLayoutX(-30);
        characterRender.setLayoutY(-5);

        seriesSymbol.setViewOrder(1.0);
        seriesSymbol.setFitWidth(170);
        seriesSymbol.setFitHeight(170);
        seriesSymbol.setLayoutX(randomButton.getLayoutX() + randomButton.getPrefWidth() + 20);
        seriesSymbol.setLayoutY(25);

        rootPane.setBackground(mainStageBackground);
        rootPane.getChildren().addAll(randomButton, refreshButton, characterListButton, credit, version);
        mainScene.getStylesheets().add("/resources/style.css");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /* Functionality of the random button */
    private void randomButtonAction() {
        randomButton.setDisable(true);
        refreshButton.setDisable(false);
        displayCharacter(Characters.rollRandomCharacter());
    }

    /* Functionality of the refresh button */
    private void refreshButtonAction() {
        rootPane.getChildren().removeAll(characterName, characterRender, seriesSymbol);
        randomButton.setDisable(false);
        refreshButton.setDisable(true);
    }

    /* Displays the character name, render and series symbol on the screen */
    private void displayCharacter(Characters.Character character) {
        if (character == null) {
            characterName.setText("All characters done!");
            characterName.setLayoutX(250 - characterName.getLayoutBounds().getWidth() / 2);
            rootPane.getChildren().add(characterName);
        } else {
            characterName.setText(character.name());
            characterName.setLayoutX(250 - characterName.getLayoutBounds().getCenterX());
            characterRender.setImage(character.getRender());
            seriesSymbol.setImage(character.getSeriesSymbol());
            rootPane.getChildren().addAll(characterName, characterRender, seriesSymbol);
        }
    }

    /* Changes button look when mouse pointer enter buttons */
    private void changeButtonColorWhenEnter(Button button) {
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #7DFFFF)");
    }

    /* Changes button look when mouse pointer exit buttons */
    private void changeButtonColorWhenExit(Button button) {
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
    }

    /* Changes button look when mouse pointer enter button */
    private void changeIconWhenEnter(Button button) {
        button.setBackground(hoverRefreshIcon);
    }

    /* Changes button look when mouse pointer exit button */
    private void changeIconWhenExit(Button button) {
        button.setBackground(baseRefreshIcon);
    }

}