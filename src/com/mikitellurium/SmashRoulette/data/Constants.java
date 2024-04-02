package com.mikitellurium.SmashRoulette.data;

import com.mikitellurium.SmashRoulette.util.ColorConverter;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

public class Constants {
    public static final String VERSION = "2.2.0";

    public static final String FONT_ARIAL = "Arial";
    public static final String CSS_STYLE = "/resources/style.css";

    public static final Image WINDOW_ICON = new Image("/resources/smash logo.png");

    /* Properties */
    public static final String PROPERTIES_FILE = "SmashRoulette.properties";
    public static final String PROPERTIES_COMMENTS = "Smash Roulette settings\nThis file contains the application saved data.";

    public static final Color DEFAULT_HIGHLIGHT_COLOR = Color.rgb(255, 0, 75);
    public static final String DEFAULT_HIGHLIGHT_COLOR_STRING = ColorConverter.colorToString(DEFAULT_HIGHLIGHT_COLOR);
    public static final String HIGHLIGHT_COLOR_KEY = "highlight_color";

    /* MainWindow */
    public static final BackgroundImage MAIN_WINDOW_BACKGROUND_IMAGE = new BackgroundImage(new Image("/resources/background.jpg", 500, 300, true, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    public static final BackgroundImage BASE_REFRESH_BUTTON_IMAGE = new BackgroundImage(new Image("/resources/base refresh icon.png"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(28, 28, false, false, false, false));
    public static final BackgroundImage HOVER_REFRESH_BUTTON_IMAGE = new BackgroundImage(new Image("/resources/hover refresh icon.png"),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(28, 28, false, false, false, false));

    /* ChecklistWindow */
    public static final BackgroundImage CHECKLIST_WINDOW_BACKGROUND_IMAGE = new BackgroundImage(new Image("/resources/mural.jpg",
            1100, 250, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(1100, 250, false, false, false, false));



}
