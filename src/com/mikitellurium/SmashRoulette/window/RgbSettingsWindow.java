package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.util.IntegerField;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;
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

    Button confirmButton = new Button("Ok");
    Shape colorSquare = new Rectangle(30, 30);
    ChangeListener<Number> colorListener = (observableValue, number, t1) -> colorSquare.setFill(updateSquareColor());

    public RgbSettingsWindow() {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Highlight Color Setting");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(400);
        stage.setHeight(210);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth()/2 - stage.getWidth()/2);
        stage.setY(screenSize.getHeight()/2 - stage.getHeight()/2);

        chooseAColor.setFont(Font.font(MainWindow.ARIAL, FontWeight.SEMI_BOLD, 20));
        chooseAColor.setLayoutX(10);
        chooseAColor.setLayoutY(15);

        red.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 14));
        red.setTextFill(Color.RED);
        GridPane.setConstraints(red, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        green.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 14));
        green.setTextFill(Color.GREEN);
        GridPane.setConstraints(green, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        blue.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 14));
        blue.setTextFill(Color.BLUE);
        GridPane.setConstraints(blue, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        redTextField.setPrefSize(50, 10);
        redTextField.setAlignment(Pos.CENTER);
        redTextField.valueProperty().bindBidirectional(redSlider.valueProperty());
        GridPane.setConstraints(redTextField, 1, 1);
        greenTextField.setPrefSize(50, 10);
        greenTextField.setAlignment(Pos.CENTER);
        greenTextField.valueProperty().bindBidirectional(greenSlider.valueProperty());
        GridPane.setConstraints(greenTextField, 1, 2);
        blueTextField.setPrefSize(50, 10);
        blueTextField.setAlignment(Pos.CENTER);
        blueTextField.valueProperty().bindBidirectional(blueSlider.valueProperty());
        GridPane.setConstraints(blueTextField, 1, 3);

        redSlider.setPrefWidth(240);
        redSlider.setMin(0);
        redSlider.setMax(255);
        redSlider.setBlockIncrement(1);
        redSlider.setMajorTickUnit(1);
        redSlider.setMinorTickCount(0);
        redSlider.setSnapToTicks(true);
        redSlider.setValue(ChecklistWindow.getHighlightColor().getRed()*255);
        redSlider.valueProperty().addListener(colorListener);
        GridPane.setConstraints(redSlider, 2, 1);
        greenSlider.setPrefWidth(240);
        greenSlider.setMin(0);
        greenSlider.setMax(255);
        greenSlider.setBlockIncrement(1);
        greenSlider.setMajorTickUnit(1);
        greenSlider.setMinorTickCount(0);
        greenSlider.setSnapToTicks(true);
        greenSlider.setValue(ChecklistWindow.getHighlightColor().getGreen()*255);
        greenSlider.valueProperty().addListener(colorListener);
        GridPane.setConstraints(greenSlider, 2, 2);
        blueSlider.setPrefWidth(240);
        blueSlider.setMin(0);
        blueSlider.setMax(255);
        blueSlider.setBlockIncrement(1);
        blueSlider.setMajorTickUnit(1);
        blueSlider.setMinorTickCount(0);
        blueSlider.setSnapToTicks(true);
        blueSlider.setValue(ChecklistWindow.getHighlightColor().getBlue()*255);
        blueSlider.valueProperty().addListener(colorListener);
        GridPane.setConstraints(blueSlider, 2, 3);

        confirmButton.setFont(Font.font(MainWindow.ARIAL, FontWeight.BOLD, 16));
        confirmButton.setPrefSize(50, 10);
        confirmButton.setOnAction(e -> confirmButtonAction());
        GridPane.setConstraints(confirmButton, 1, 4);
        colorSquare.setFill(ChecklistWindow.getHighlightColor());
        GridPane.setConstraints(colorSquare, 2, 4, 1, 1, HPos.LEFT, VPos.BASELINE);

        gridLayout.setPadding(new Insets(0, 5, 0, 5));
        gridLayout.setHgap(15);
        gridLayout.setVgap(10);
        gridLayout.getChildren().addAll(
                chooseAColor, red, green, blue, redTextField, greenTextField, blueTextField, redSlider, greenSlider,
                blueSlider, confirmButton, colorSquare);
        gridLayout.setLayoutX(0);
        gridLayout.setLayoutY(20);

        mainPane.getChildren().addAll(chooseAColor, gridLayout);
        stage.setScene(scene);
    }

    /* Shows the window */
    public void show() {
        stage.showAndWait();
    }

    /* Confirm button action */
    private void confirmButtonAction() {
        ChecklistWindow.setHighlightColor(updateSquareColor());
        ChecklistWindow.updateCheckboxTextColor(updateSquareColor());
        stage.close();
    }

    /* Update the color indicator */
    public Color updateSquareColor() {
        int red = redTextField.getValue();
        int green = greenTextField.getValue();
        int blue = blueTextField.getValue();
        return Color.rgb(red, green, blue);
    }

}
