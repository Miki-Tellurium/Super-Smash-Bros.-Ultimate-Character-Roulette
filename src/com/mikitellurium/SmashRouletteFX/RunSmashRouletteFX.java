package com.mikitellurium.SmashRouletteFX;

import javafx.application.Application;
import javafx.stage.Stage;

public class RunSmashRouletteFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SmashRouletteFX main = new SmashRouletteFX(primaryStage);
    }
}
