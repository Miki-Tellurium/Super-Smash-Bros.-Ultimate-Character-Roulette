/* Migration from Swing to JavaFX
 * WIP
 */

package com.mikitellurium.SmashRouletteFX;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;

public class SmashRouletteFX {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final BackgroundImage baseRefreshImage = new BackgroundImage(new Image("/resources/base refresh icon.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(28, 28, false, false, false, false));
    final Background baseRefreshIcon = new Background(baseRefreshImage);
    final BackgroundImage hoverRefreshImage = new BackgroundImage(new Image("/resources/hover refresh icon.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(28, 28, false, false, false, false));
    final Background hoverRefreshIcon = new Background(hoverRefreshImage);
    final BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/main background.jpg", 500, 300, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    final Background mainStageBackground = new Background(backgroundImage);

    public SmashRouletteFX(Stage mainStage) {
        //Main window
        mainStage.setTitle("Super Smash Bros. Ultimate Characters Roulette");
        mainStage.getIcons().add(new Image("/resources/smash logo.png"));
        mainStage.setResizable(false);
        mainStage.setWidth(500);
        mainStage.setHeight(300);
        //The window always appear at the center of the screen
        mainStage.setX(screenSize.getWidth()/2 - mainStage.getWidth()/2);
        mainStage.setY(screenSize.getHeight()/2 - mainStage.getHeight()/2);
        //This button roll a random character
        Button randomButton = new Button("Random");
        randomButton.setPrefSize(100, 30);
        randomButton.setLayoutX(mainStage.getWidth()/2 - randomButton.getPrefWidth()/2);
        randomButton.setLayoutY(70);
        randomButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        randomButton.getStyleClass().add("normal-buttons");
        randomButton.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
        randomButton.setOnMouseEntered(e -> changeButtonColorWhenEnter(randomButton));
        randomButton.setOnMouseExited(e -> changeButtonColorWhenExit(randomButton));
        //This button refresh the random button
        Button refreshButton = new Button();
        refreshButton.setPrefSize(30, 30);
        refreshButton.setLayoutX(randomButton.getLayoutX() + randomButton.getPrefWidth() + 5);
        refreshButton.setLayoutY(randomButton.getLayoutY());
        refreshButton.setBackground(baseRefreshIcon);
        refreshButton.getStyleClass().add("refresh-button");
        refreshButton.setOnMouseEntered(e -> changeIconWhenEnter(refreshButton));
        refreshButton.setOnMouseExited(e -> changeIconWhenExit(refreshButton));
        //This button open the second window
        Button characterListButton = new Button("Characters\n  checklist");
        characterListButton.setPrefSize(80, 40);
        characterListButton.setLayoutX(mainStage.getWidth() - characterListButton.getPrefWidth() - 20);
        characterListButton.setLayoutY(mainStage.getHeight() - characterListButton.getPrefHeight()*2 - 3);
        characterListButton.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        characterListButton.setTextAlignment(TextAlignment.JUSTIFY);
        characterListButton.getStyleClass().add("normal-buttons");
        characterListButton.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
        characterListButton.setOnMouseEntered(e -> changeButtonColorWhenEnter(characterListButton));
        characterListButton.setOnMouseExited(e -> changeButtonColorWhenExit(characterListButton));
        //Display the credits
        Text credit = new Text("by Miki_Tellurium");
        credit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        credit.setLayoutX(mainStage.getWidth() -  credit.getText().length()*7);  //multiplying by 7 moves the text right enough
        credit.setLayoutY(12);

        Pane rootPane = new Pane();
        rootPane.setBackground(mainStageBackground);
        rootPane.getChildren().addAll(randomButton, refreshButton, characterListButton, credit);

        Scene mainScene = new Scene(rootPane);
        mainScene.getStylesheets().add("/resources/buttonStyle.css");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    private void changeButtonColorWhenEnter(Button button) {
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #7DFFFF)");
    }

    private void changeButtonColorWhenExit(Button button) {
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
    }

    private void changeIconWhenEnter(Button button) {
        button.setBackground(hoverRefreshIcon);
    }

    private void changeIconWhenExit(Button button) {
        button.setBackground(baseRefreshIcon);
    }
}
