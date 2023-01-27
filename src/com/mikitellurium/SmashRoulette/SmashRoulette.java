/* Super Smash Bros. Ultimate Character Roulette
 * Author: Miki_Tellurium
 * Version: 2.0.0
 *
 * I made this simple program to complete my World of Light Challenge
 * and keep track of what character I already used.
 * The program use a SmashRoulette.properties file to save the character
 * progress, if you move the .jar to a different directory remember to also
 * move the .properties file.
 * This code is probably not so good and a bit chaotic, but feel free
 * to use the program or modify if you want to.
 *
 */

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
