package com.mikitellurium.SmashRouletteFX;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SmashRouletteFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainWindow main = new MainWindow(primaryStage);
    }
}
