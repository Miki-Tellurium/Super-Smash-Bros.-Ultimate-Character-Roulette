package com.mikitellurium.SmashRouletteFX;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class RgbSettingsWindow {

    Stage stage = new Stage();
    Pane mainPane = new Pane();
    GridPane gridLayout = new GridPane();
    Scene scene = new Scene(mainPane);

    Text chooseAColor = new Text("Choose a color:");
    Label red = new Label("Red");
    Label green = new Label("Green");
    Label blue = new Label("Blue");
    IntegerField redTextField = new IntegerField(0, 255);
    IntegerField greenTextField = new IntegerField(0, 255);
    IntegerField blueTextField = new IntegerField(0, 255);
    Slider redSlider = new Slider();
    Slider greenSlider = new Slider();
    Slider blueSlider = new Slider();

    public RgbSettingsWindow() {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Highlight Color Setting");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(400);
        stage.setHeight(250);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth()/2 - stage.getWidth()/2);
        stage.setY(screenSize.getHeight()/2 - stage.getHeight()/2);

        chooseAColor.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        chooseAColor.setLayoutX(0);
        chooseAColor.setLayoutY(15);

        red.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        red.setTextFill(Color.RED);
        GridPane.setConstraints(red, 0, 1);
        green.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        green.setTextFill(Color.GREEN);
        GridPane.setConstraints(green, 0, 2);
        blue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        blue.setTextFill(Color.BLUE);
        GridPane.setConstraints(blue, 0, 3);

        redTextField.setPrefSize(50, 10);
        //redTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(NumberFormat.getCompactNumberInstance())));
        redTextField.valueProperty().bindBidirectional(redSlider.valueProperty());
        GridPane.setConstraints(redTextField, 1, 1);
        greenTextField.setPrefSize(50, 10);
        //greenTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(NumberFormat.getCompactNumberInstance())));
        greenTextField.valueProperty().bindBidirectional(greenSlider.valueProperty());
        GridPane.setConstraints(greenTextField, 1, 2);
        blueTextField.setPrefSize(50, 10);
        ///blueTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(NumberFormat.getCompactNumberInstance())));
        blueTextField.valueProperty().bindBidirectional(blueSlider.valueProperty());
        GridPane.setConstraints(blueTextField, 1, 3);

        redSlider.setPrefWidth(240);
        redSlider.setMin(0);
        redSlider.setMax(255);
        redSlider.setBlockIncrement(1);
        redSlider.setMajorTickUnit(1);
        redSlider.setMinorTickCount(0);
        redSlider.setSnapToTicks(true);
        redSlider.setValue(50);
        GridPane.setConstraints(redSlider, 2, 1);
        greenSlider.setPrefWidth(240);
        greenSlider.setMin(0);
        greenSlider.setMax(255);
        greenSlider.setBlockIncrement(1);
        greenSlider.setMajorTickUnit(1);
        greenSlider.setMinorTickCount(0);
        greenSlider.setSnapToTicks(true);
        greenSlider.setValue(110);
        GridPane.setConstraints(greenSlider, 2, 2);
        blueSlider.setPrefWidth(240);
        blueSlider.setMin(0);
        blueSlider.setMax(255);
        blueSlider.setBlockIncrement(1);
        blueSlider.setMajorTickUnit(1);
        blueSlider.setMinorTickCount(0);
        blueSlider.setSnapToTicks(true);
        blueSlider.setValue(220);
        GridPane.setConstraints(blueSlider, 2, 3);

        gridLayout.setHgap(20);
        gridLayout.setVgap(10);
        gridLayout.getChildren().addAll(chooseAColor, red, green, blue, redTextField, greenTextField, blueTextField, redSlider, greenSlider, blueSlider);
        gridLayout.setLayoutX(0);
        gridLayout.setLayoutY(20);

        mainPane.getChildren().addAll(chooseAColor, gridLayout);
        stage.setScene(scene);
    }

    /* Shows the window */
    public void show() {
        stage.showAndWait();
    }
}
