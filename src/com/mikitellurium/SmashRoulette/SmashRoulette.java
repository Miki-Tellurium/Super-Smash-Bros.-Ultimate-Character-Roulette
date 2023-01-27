package com.mikitellurium.SmashRoulette;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SmashRoulette extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    @SuppressWarnings("unused")
    public void start(Stage primaryStage) throws IOException {
        MainWindow main = new MainWindow(primaryStage);
    }
}
