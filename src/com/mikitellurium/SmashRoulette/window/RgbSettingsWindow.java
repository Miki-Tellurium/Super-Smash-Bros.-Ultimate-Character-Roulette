package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.element.IntegerField;
import com.mikitellurium.SmashRoulette.util.Util;
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

    private final ChecklistWindow parent;

    private final Stage stage = new Stage();

    private final IntegerField redTextField = Util.make(new IntegerField(0, 255), (intField) -> {
        intField.setPrefSize(50, 10);
        intField.setAlignment(Pos.CENTER);
        GridPane.setConstraints(intField, 1, 1);
    });
    private final IntegerField greenTextField = Util.make(new IntegerField(0, 255), (intField) -> {
        intField.setPrefSize(50, 10);
        intField.setAlignment(Pos.CENTER);
        GridPane.setConstraints(intField, 1, 2);
    });
    private final IntegerField blueTextField = Util.make(new IntegerField(0, 255), (intField) -> {
        intField.setPrefSize(50, 10);
        intField.setAlignment(Pos.CENTER);
        GridPane.setConstraints(intField, 1, 3);
    });

    private final Shape colorSquare = Util.make(new Rectangle(30, 30), (shape) -> {
        GridPane.setConstraints(shape, 2, 4, 1, 1, HPos.LEFT, VPos.BASELINE);
    });
    private final ChangeListener<Number> colorListener = (observableValue, oldValue, newValue) -> colorSquare.setFill(this.getCompoundColor());

    public RgbSettingsWindow(ChecklistWindow parent) {
        this.parent = parent;

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Highlight Color Setting");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(400);
        stage.setHeight(210);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(screenSize.getHeight() / 2 - stage.getHeight() / 2);

        final Text header = Util.make(new Text("Choose a color:"), (text) -> {
            text.setFont(Font.font(MainWindow.ARIAL, FontWeight.SEMI_BOLD, 20));
            text.setLayoutX(10);
            text.setLayoutY(15);
        });

        final Label red = Util.make(new Label("Red"), (label) -> {
            label.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 14));
            label.setTextFill(Color.RED);
            GridPane.setConstraints(label, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        });
        final Label green = Util.make(new Label("Green"), (label) -> {
            label.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 14));
            label.setTextFill(Color.GREEN);
            GridPane.setConstraints(label, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        });
        final Label blue = Util.make(new Label("Blue"), (label) -> {
            label.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 14));
            label.setTextFill(Color.BLUE);
            GridPane.setConstraints(label, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        });

        final Slider redSlider = Util.make(new Slider(), (slider) -> {
            slider.setPrefWidth(240);
            slider.setMin(0);
            slider.setMax(255);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setSnapToTicks(true);
            slider.setValue(parent.getHighlightColor().getRed() * 255);
            slider.valueProperty().addListener(colorListener);
            GridPane.setConstraints(slider, 2, 1);
        });
        final Slider greenSlider = Util.make(new Slider(), (slider) -> {
            slider.setPrefWidth(240);
            slider.setMin(0);
            slider.setMax(255);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setSnapToTicks(true);
            slider.setValue(parent.getHighlightColor().getGreen() * 255);
            slider.valueProperty().addListener(colorListener);
            GridPane.setConstraints(slider, 2, 2);
        });
        final Slider blueSlider = Util.make(new Slider(), (slider) -> {
            slider.setPrefWidth(240);
            slider.setMin(0);
            slider.setMax(255);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setSnapToTicks(true);
            slider.setValue(parent.getHighlightColor().getBlue() * 255);
            slider.valueProperty().addListener(colorListener);
            GridPane.setConstraints(slider, 2, 3);
        });
        redTextField.valueProperty().bindBidirectional(redSlider.valueProperty());
        greenTextField.valueProperty().bindBidirectional(greenSlider.valueProperty());
        blueTextField.valueProperty().bindBidirectional(blueSlider.valueProperty());

        colorSquare.setFill(parent.getHighlightColor());

        final Button confirmButton = Util.make(new Button("Ok"), (button) -> {
            button.setFont(Font.font(MainWindow.ARIAL, FontWeight.BOLD, 16));
            button.setPrefSize(50, 10);
            button.setOnAction(e -> this.confirmButtonAction());
            GridPane.setConstraints(button, 1, 4);
        });

        final GridPane gridPane = Util.make(new GridPane(), (pane) -> {
            pane.setPadding(new Insets(0, 5, 0, 5));
            pane.setHgap(15);
            pane.setVgap(10);
            pane.setLayoutX(0);
            pane.setLayoutY(20);
        });
        gridPane.getChildren().addAll(header, red, green, blue, redTextField, greenTextField, blueTextField, redSlider,
                greenSlider, blueSlider, confirmButton, colorSquare);

        final Pane mainPane = new Pane();
        mainPane.getChildren().addAll(header, gridPane);
        final Scene scene = new Scene(mainPane);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }

    private void confirmButtonAction() {
        parent.setHighlightColor(this.getCompoundColor());
        parent.highlightCharacters();
        stage.close();
    }

    private Color getCompoundColor() {
        int red = redTextField.getValue();
        int green = greenTextField.getValue();
        int blue = blueTextField.getValue();
        return Color.rgb(red, green, blue);
    }

}
