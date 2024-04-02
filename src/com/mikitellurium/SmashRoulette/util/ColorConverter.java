package com.mikitellurium.SmashRoulette.util;

import com.mikitellurium.SmashRoulette.data.Constants;
import com.mikitellurium.SmashRoulette.window.ChecklistWindow;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class ColorConverter {

    /* Convert color value to a printable string */
    public static String colorToString(Color color) {
        return colorToString(color.getRed(), color.getGreen(), color.getBlue());
    }

    /* Convert rgb color value to a printable string */
    public static String colorToString(double red, double green, double blue) {
        return (int)(red * 255) + "/" + (int)(green * 255) + "/" + (int)(blue * 255);
    }

    /* Convert a printable string to a color */
    public static Color stringToColor(String colorString) {
        if (colorString.contains(",")) { // Handle old color property
            colorString = colorString.replace(',', '/');
        }

        try {
            int[] rgb = Arrays.stream(colorString.split("/"))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return Color.rgb(rgb[0], rgb[1], rgb[2]);
        } catch (NumberFormatException e) {
            return Constants.DEFAULT_HIGHLIGHT_COLOR;
        }
    }

}