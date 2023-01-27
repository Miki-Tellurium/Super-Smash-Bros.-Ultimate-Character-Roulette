package com.mikitellurium.SmashRoulette;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class ColorConverter {

    /* Convert rgb color value to a printable string */
    public static String colorToString(double red, double green, double blue) {
        return (int)red + "," + (int)green + "," + (int)blue;
    }

    /* Convert a printable string to a color and return it */
    public static Color stringToColor(String colorString) {
        var rgb = Arrays.stream(colorString.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
        return Color.rgb(rgb[0], rgb[1], rgb[2]);
    }
}