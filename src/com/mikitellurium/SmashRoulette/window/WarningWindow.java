package com.mikitellurium.SmashRoulette.window;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class WarningWindow {

    Stage stage = new Stage();
    Pane layout = new Pane();
    Scene scene = new Scene(layout);

    Text warningText = new Text();
    Button confirmButton = new Button();
    Button cancelButton = new Button("Cancel");

    private final Image icon = new Image("/resources/smash logo.png");

    private final String fontName = "Arial";
    public static final String TEXT_CHECK_ALL = "CheckAll";
    public static final String TEXT_UNCHECK_ALL = "UncheckAll";

    public WarningWindow(String warningMessage) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(warningMessage);
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(150);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth()/2 - stage.getWidth()/2);
        stage.setY(screenSize.getHeight()/2 - stage.getHeight()/2);


        warningText.setText(getWarningText(warningMessage));
        warningText.setFont(Font.font(fontName, FontWeight.NORMAL, 20));
        warningText.setLayoutX(stage.getWidth()/2 - warningText.getLayoutBounds().getCenterX());
        warningText.setLayoutY(30);

        confirmButton.setText(getWarningButtonText(warningMessage));
        confirmButton.setFont(Font.font(fontName, FontWeight.BOLD, 16));
        confirmButton.setPrefWidth(90);
        confirmButton.setLayoutX(45);
        confirmButton.setLayoutY(stage.getHeight() - 80);
        confirmButton.setOnAction(e -> confirmButtonAction(warningMessage));

        cancelButton.setFont(Font.font(fontName, FontWeight.BOLD, 16));
        cancelButton.setLayoutX(170);
        cancelButton.setLayoutY(stage.getHeight() - 80);
        cancelButton.setOnAction(e -> stage.close());

        scene.getStylesheets().add("/resources/style.css");
        layout.getChildren().addAll(warningText, confirmButton, cancelButton);
        stage.setScene(scene);
    }

    /* Shows the window */
    public void show() {
        stage.showAndWait();
    }

    /* Confirm button functionality */
    public void confirmButtonAction(String warning) {
        if (Objects.equals(warning, TEXT_CHECK_ALL)) {
            ChecklistWindow.checkAll();
        } else if (Objects.equals(warning, TEXT_UNCHECK_ALL)) {
            ChecklistWindow.uncheckAll();
        }
        stage.close();
    }

    /* Returns a warning based on the warning message to be displayed */
    private String getWarningText(String warning) {
        if (Objects.equals(warning, TEXT_CHECK_ALL)) {
            return "Are you sure you want to\n  check all characters?";
        } else if (Objects.equals(warning, TEXT_UNCHECK_ALL)) {
            return "Are you sure you want to\n uncheck all characters?";
        }
        return "";
    }

    /* Returns the text that should be displayed on the confirm button */
    private String getWarningButtonText(String warning) {
        if (Objects.equals(warning, TEXT_CHECK_ALL)) {
            return "Check";
        } else if (Objects.equals(warning, TEXT_UNCHECK_ALL)) {
            return "Uncheck";
        }
        return "";
    }

}
