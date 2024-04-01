package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.util.Util;
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

public class WarningWindow {

    private final ChecklistWindow parent;
    private final Action action;

    private final Stage stage = new Stage();

    public WarningWindow(ChecklistWindow parent, Action action) {
        this.parent = parent;
        this.action = action;

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(action.getMessage());
        final Image icon = new Image("/resources/smash logo.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setWidth(300);
        stage.setHeight(150);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(screenSize.getHeight() / 2 - stage.getHeight() / 2);

        final Text warningText = Util.make(new Text(), (text) -> {
            text.setText(this.getWarningText());
            text.setFont(Font.font(MainWindow.ARIAL, FontWeight.NORMAL, 20));
            text.setLayoutX(stage.getWidth() / 2 - text.getLayoutBounds().getCenterX());
            text.setLayoutY(30);
        });
        final Button confirmButton = Util.make(new Button(), (button) -> {
            button.setText(getWarningButtonText());
            button.setFont(Font.font(MainWindow.ARIAL, FontWeight.BOLD, 16));
            button.setPrefWidth(90);
            button.setLayoutX(45);
            button.setLayoutY(stage.getHeight() - 80);
            button.setOnAction(e -> this.confirmButtonAction());
        });

        final Button cancelButton = Util.make(new Button("Cancel"), (button) -> {
            button.setFont(Font.font(MainWindow.ARIAL, FontWeight.BOLD, 16));
            button.setLayoutX(170);
            button.setLayoutY(stage.getHeight() - 80);
            button.setOnAction(e -> stage.close());
        });

        final Pane layout = new Pane();
        final Scene scene = new Scene(layout);
        scene.getStylesheets().add("/resources/style.css");
        layout.getChildren().addAll(warningText, confirmButton, cancelButton);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }

    private void confirmButtonAction() {
        if (action == Action.CHECK_ALL) {
            this.parent.checkAll();
        } else if (action == Action.UNCHECK_ALL) {
            this.parent.uncheckAll();;
        }
        stage.close();
    }

    private String getWarningText() {
        if (action == Action.CHECK_ALL) {
            return "Are you sure you want to\n  check all characters?";
        } else if (action == Action.UNCHECK_ALL) {
            return "Are you sure you want to\n uncheck all characters?";
        }
        return "";
    }

    private String getWarningButtonText() {
        if (action == Action.CHECK_ALL) {
            return "Check";
        } else if (action == Action.UNCHECK_ALL) {
            return "Uncheck";
        }
        return "";
    }

    public enum Action {
        CHECK_ALL("Check All"),
        UNCHECK_ALL("Uncheck All");

        private final String message;

        Action(String title) {
            this.message = title;
        }

        public String getMessage() {
            return message;
        }
    }

}
