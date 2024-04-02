/* Migration from Swing to JavaFX
 * WIP
 */

package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.SmashRoulette;
import com.mikitellurium.SmashRoulette.data.Character;
import com.mikitellurium.SmashRoulette.data.Constants;
import com.mikitellurium.SmashRoulette.element.CharacterBox;
import com.mikitellurium.SmashRoulette.util.Util;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class MainWindow {

    private final Pane rootPane = new Pane();

    private final ChecklistWindow checklistWindow;

    private final Background baseRefreshIcon = new Background(Constants.BASE_REFRESH_BUTTON_IMAGE);
    private final Background hoverRefreshIcon = new Background(Constants.HOVER_REFRESH_BUTTON_IMAGE);

    private final Button rollButton = Util.make(new Button(), (button) -> {
        button.setText("Random");
        button.setPrefSize(100, 30);
        button.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 14));
        button.getStyleClass().add("normal-buttons");
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
    });
    private final Button refreshButton = Util.make(new Button(), (button) -> {
        button.setPrefSize(30, 30);
        button.setBackground(baseRefreshIcon);
        button.getStyleClass().add("refresh-button");
        button.setDisable(true);
    });
    private final Button characterListButton = Util.make(new Button(), (button) -> {
        button.setText("Characters\n  checklist");
        button.setPrefSize(80, 40);
        button.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 11));
        button.setTextAlignment(TextAlignment.JUSTIFY);
        button.getStyleClass().add("normal-buttons");
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
    });

    private final Text characterName = Util.make(new Text(), (text) -> {
        text.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 32));
    });
    private final ImageView characterRender = Util.make(new ImageView(), (imageView) -> {
        imageView.setViewOrder(1.0);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setLayoutX(-30);
        imageView.setLayoutY(-5);
    });
    private final ImageView seriesSymbol = Util.make(new ImageView(), (imageView) -> {
        imageView.setViewOrder(1.0);
        imageView.setFitWidth(170);
        imageView.setFitHeight(170);
    });

    public MainWindow(Stage mainStage) {
        mainStage.setTitle("Super Smash Bros. Ultimate Characters Roulette");
        mainStage.getIcons().add(Constants.WINDOW_ICON);
        mainStage.setResizable(false);
        mainStage.setWidth(500);
        mainStage.setHeight(300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainStage.setX(screenSize.getWidth()/2 - mainStage.getWidth()/2);
        mainStage.setY(screenSize.getHeight()/2 - mainStage.getHeight()/2);

        rollButton.setLayoutX(mainStage.getWidth() / 2 - rollButton.getPrefWidth() / 2);
        rollButton.setLayoutY(40);
        rollButton.setOnMouseEntered(e -> changeButtonColorWhenEnter(rollButton));
        rollButton.setOnMouseExited(e -> changeButtonColorWhenExit(rollButton));
        rollButton.setOnAction(e -> randomButtonAction());

        refreshButton.setLayoutX(rollButton.getLayoutX() + rollButton.getPrefWidth() + 5);
        refreshButton.setLayoutY(rollButton.getLayoutY());
        refreshButton.setOnMouseEntered(e -> refreshButton.setBackground(hoverRefreshIcon));
        refreshButton.setOnMouseExited(e -> refreshButton.setBackground(baseRefreshIcon));
        refreshButton.setOnAction(e -> refreshButtonAction());

        characterListButton.setLayoutX(mainStage.getWidth() - characterListButton.getPrefWidth() - 20);
        characterListButton.setLayoutY(mainStage.getHeight() - characterListButton.getPrefHeight()*2 - 3);
        characterListButton.setOnMouseEntered(e -> changeButtonColorWhenEnter(characterListButton));
        characterListButton.setOnMouseExited(e -> changeButtonColorWhenExit(characterListButton));

        final Text credits = Util.make(new Text(), (text) -> {
            text.setText("by Miki_Tellurium");
            text.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 12));
            text.setLayoutX(mainStage.getWidth() - text.getText().length() * 7);  // Multiplying by 7 moves the text right enough
            text.setLayoutY(12);
        });
        final Text version = Util.make(new Text(), (text) -> {
            text.setText("v" + Constants.VERSION);
            text.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 12));
            text.setLayoutX(2);
            text.setLayoutY(mainStage.getHeight() - 42);
        });

        characterName.setLayoutY(rollButton.getLayoutY() + rollButton.getPrefHeight() * 2.5);

        seriesSymbol.setLayoutX(rollButton.getLayoutX() + rollButton.getPrefWidth() + 20);
        seriesSymbol.setLayoutY(25);

        this.checklistWindow = new ChecklistWindow(mainStage);
        characterListButton.setOnAction(e -> checklistWindow.show());

        rootPane.setBackground(new Background(Constants.MAIN_WINDOW_BACKGROUND_IMAGE));
        rootPane.getChildren().addAll(rollButton, refreshButton, characterListButton, credits, version);
        Scene mainScene = new Scene(rootPane);
        mainScene.getStylesheets().add(Constants.CSS_STYLE);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /* Functionality of the random button */
    private void randomButtonAction() {
        rollButton.setDisable(true);
        refreshButton.setDisable(false);
        List<CharacterBox> checkBoxes = this.checklistWindow.getBoxManager().getUncheckedBoxes();
        if (checkBoxes.isEmpty()) {
            this.displayAllDone();
        } else {
            Random random = new Random();
            Character character = checkBoxes.get(random.nextInt(checkBoxes.size())).getCharacter();
            this.displayCharacter(character);
        }
    }

    private void refreshButtonAction() {
        rootPane.getChildren().removeAll(characterName, characterRender, seriesSymbol);
        rollButton.setDisable(false);
        refreshButton.setDisable(true);
    }

    private void displayCharacter(Character character) {
        characterName.setText(character.getName());
        characterName.setLayoutX(250 - characterName.getLayoutBounds().getCenterX());
        characterRender.setImage(character.getRender());
        seriesSymbol.setImage(character.getSeriesSymbol());
        rootPane.getChildren().addAll(characterName, characterRender, seriesSymbol);
    }

    private void displayAllDone() {
        characterName.setText("All characters done!");
        characterName.setLayoutX(250 - characterName.getLayoutBounds().getWidth() / 2);
        rootPane.getChildren().add(characterName);
    }

    private void changeButtonColorWhenEnter(Button button) {
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #7DFFFF)");
    }

    private void changeButtonColorWhenExit(Button button) {
        button.setStyle("-fx-background-color: linear-gradient(#FFFFFF, #C8FFFF)");
    }

}