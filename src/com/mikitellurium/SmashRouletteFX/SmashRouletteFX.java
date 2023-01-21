package com.mikitellurium.SmashRouletteFX;

import javafx.application.Application;
import javafx.stage.Stage;

public class SmashRouletteFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainWindow main = new MainWindow(primaryStage);
    }
}
