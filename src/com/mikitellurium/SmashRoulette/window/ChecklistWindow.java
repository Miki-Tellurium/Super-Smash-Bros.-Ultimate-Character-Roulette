package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.util.CharacterList;
import com.mikitellurium.SmashRoulette.util.ColorConverter;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class ChecklistWindow {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    Stage stage = new Stage();
    GridPane checkBoxesPane = new GridPane();
    Pane mainPane = new Pane();
    Scene scene = new Scene(mainPane);

    final BackgroundImage backgroundImage = new BackgroundImage(new Image("/resources/mural.jpg",
            1100, 250, true, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            new BackgroundSize(1100, 250, false, false, false, false));
    final Background background = new Background(backgroundImage);

    MenuItem checkAllBoxes = new MenuItem("Check All");
    MenuItem uncheckAllBoxes = new MenuItem("Uncheck All");
    SeparatorMenuItem separator = new SeparatorMenuItem();
    MenuItem highlightColorSetting = new MenuItem("Highlight Color");
    Menu options = new Menu("Options");
    MenuBar menuBar = new MenuBar();

    final Color defaultColor = Color.rgb(255, 0, 75);
    final String defaultColorString = ColorConverter.colorToString(defaultColor.getRed() * 255, defaultColor.getGreen() * 255,
            defaultColor.getBlue() * 255);

    static TextField searchField = new TextField();
    static Color highlightColor;
    @SuppressWarnings("rawtypes")
    ChangeListener searchFieldListener = (observableValue, o, t1) -> updateCheckboxTextColor(highlightColor);
    Button hint = new Button();
    Tooltip hintTooltip = new Tooltip();
    static Text currentStatus = new Text();

    private static final ArrayList<CheckBox> boxes = new ArrayList<>();
    static final private String charactersProperties = "SmashRoulette.properties";
    final static Properties properties = new Properties();

    @SuppressWarnings("unchecked")
    public ChecklistWindow(Stage parentStage) throws IOException {
        stage.initOwner(parentStage);
        stage.setTitle("Characters Checklist");
        stage.getIcons().add(new Image("/resources/smash logo.png"));
        stage.setResizable(false);
        stage.setWidth(1000);
        stage.setHeight(280);
        stage.setX(screenSize.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(screenSize.getHeight() / 2 - stage.getHeight() / 2 - 50);

        searchField.setPromptText("Search...");
        searchField.setPrefSize(120, 10);
        searchField.setLayoutX(5);
        searchField.setLayoutY(25);
        searchField.textProperty().addListener(searchFieldListener);

        hint.setText("?");
        hint.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        hint.setPrefSize(10, 10);
        hint.setPadding(new Insets(2, 7, 1, 7));
        hint.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 5);
        hint.setLayoutY(25);
        hint.getStyleClass().add("hint");
        hintTooltip.setText("Type a character name to highlight it on the list");
        hintTooltip.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        hintTooltip.setStyle("-fx-background-color: linear-gradient(to left, #24CFFE, #007BFD); -fx-text-fill: black;");
        hintTooltip.setMaxHeight(10);
        hintTooltip.setShowDelay(Duration.ZERO);
        hintTooltip.setHideDelay(Duration.ZERO);
        hint.setTooltip(hintTooltip);

        currentStatus.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        currentStatus.setLayoutX(245);
        currentStatus.setLayoutY(47);

        checkAllBoxes.setOnAction(e -> showWarningMessage(WarningWindow.TEXT_CHECK_ALL));
        uncheckAllBoxes.setOnAction(e -> showWarningMessage(WarningWindow.TEXT_UNCHECK_ALL));
        highlightColorSetting.setOnAction(e -> new RgbSettingsWindow().show());

        options.getItems().addAll(checkAllBoxes, uncheckAllBoxes, highlightColorSetting);
        options.getItems().add(2, separator);
        menuBar.getMenus().add(options);
        menuBar.setUseSystemMenuBar(true);
        menuBar.setPrefSize(stage.getWidth(), 20);
        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);

        checkBoxesPane.setPrefSize(stage.getWidth(), stage.getHeight() - 25);
        checkBoxesPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        checkBoxesPane.setLayoutX(0);
        checkBoxesPane.setLayoutY(50);
        checkBoxesPane.setPadding(new Insets(4));
        checkBoxesPane.setVgap(2);
        checkBoxesPane.setHgap(15);
        makeCheckBoxes();
        initializeCheckBoxes();
        currentStatus.setText(updateCurrentStatusText(getBoxesChecked(boxes)));
        mainPane.getChildren().add(checkBoxesPane);
        mainPane.getChildren().addAll(menuBar, searchField, hint, currentStatus);
        mainPane.setBackground(background);

        scene.getStylesheets().add("/resources/style.css");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            try {
                saveCheckBoxData(boxes);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /* Show this window on the screen */
    public void show() {
        stage.show();
    }

    /* Make the check-boxes and add them to an array */
    private void makeCheckBoxes() {
        int column = 0;
        int row = 0;
        for (int i = 0; i < 86; i++) {
            CheckBox checkBox = new CheckBox(CharacterList.getCharacter(i).name());
            GridPane.setConstraints(checkBox, column, row);
            checkBox.setFont(Font.font(FontUIResource.DIALOG, FontWeight.EXTRA_BOLD, 12));
            checkBox.setTextFill(Color.BLACK);
            checkBox.setOnAction(this::fireCheckbox);
            checkBoxesPane.getChildren().add(checkBox);
            boxes.add(checkBox);
            if (column < 7) {
                column++;
            } else {
                column = 0;
                row++;
            }
        }
    }

    /* Returns true if the specified box is checked */
    public static boolean isBoxChecked(int box) {
        return boxes.get(box).isSelected();
    }

    /* Returns true if all check-boxes are checked */
    public static boolean areAllBoxChecked() {
        for (CheckBox box : boxes) {
            if (!box.isSelected()) {
                return false;
            }
        }
        return true;
    }

    /* Checks if the properties file already exist */
    private void initializeCheckBoxes() throws IOException {
        File file = new File(charactersProperties);
        if (file.exists()) {
            readCheckBoxData(boxes);
        } else {
            highlightColor = defaultColor;
            makeCheckBoxData(boxes);
        }
    }

    /* Reads the properties file */
    @SuppressWarnings("SameParameterValue")
    private void readCheckBoxData(ArrayList<CheckBox> arrayList) throws IOException {
        InputStream inputProperties = new FileInputStream(charactersProperties);
        properties.load(inputProperties);
        for (CheckBox box : arrayList) {
            box.setSelected(Boolean.parseBoolean(properties.getProperty(box.getText())));
        }
        setHighlightColor(ColorConverter.stringToColor(properties.getProperty("highlight-color")));
    }

    /* Generates a new properties file to program data */
    @SuppressWarnings("SameParameterValue")
    private void makeCheckBoxData(ArrayList<CheckBox> arrayList) throws IOException {
        OutputStream outputProperties = new FileOutputStream(charactersProperties);
        for (CheckBox box : arrayList) {
            properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
        }
        properties.setProperty("highlight-color", defaultColorString);
        properties.store(outputProperties, "This file register the application data");
    }

    /* Save the current data to the properties file */
    private void saveCheckBoxData(ArrayList<CheckBox> arrayList) throws IOException {
        OutputStream outputProperties = new FileOutputStream(charactersProperties);
        for (CheckBox box : arrayList) {
            properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
        }
        properties.setProperty("highlight-color", ColorConverter.colorToString(highlightColor.getRed() * 255,
                highlightColor.getGreen() * 255, highlightColor.getBlue() * 255));
        properties.store(outputProperties, "This file register the application data");
    }

    /* Returns how many box are checked */
    @SuppressWarnings("SameParameterValue")
    private static int getBoxesChecked(ArrayList<CheckBox> arrayList) {
        int numberOfBoxes = 0;
        for (CheckBox box : arrayList) {
            if (box.isSelected()) {
                numberOfBoxes++;
            }
        }
        return numberOfBoxes;
    }

    /* Show a warning window */
    private void showWarningMessage(String warningMessage) {
        WarningWindow warning = new WarningWindow(warningMessage);
        warning.show();
    }

    /* Returns true if substring is contained in string */
    private static boolean textContains(String string, String substring) {
        if (string == null) {
            return false;
        }
        if (substring == null | Objects.equals(substring, "")) {
            return false;
        }
        char[] fullString = string.toLowerCase().toCharArray();
        char[] sub = substring.toLowerCase().toCharArray();
        int counter = 0;
        if (sub.length == 0) {
            return true;
        }
        for (char c : fullString) {
            if (c == sub[counter]) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == sub.length) {
                return true;
            }
        }
        return false;
    }

    /* Returns a string that states the current number of characters selected */
    private static String updateCurrentStatusText(int numberOfBox) {
        String text;
        if (numberOfBox == 0) {
            text = "No character selected";
            return text;
        } else if(numberOfBox == 1) {
            text = " character selected";
            return numberOfBox + text;
        } else if (numberOfBox == 86) {
            text = "Every character selected";
            return text;
        } else {
            text = " characters selected";
            return numberOfBox + text;
        }
    }

    /* Set all the check-boxes to selected */
    public static void checkAll() {
        for (CheckBox box : boxes) {
            box.setSelected(true);
            try {
                OutputStream outputProperties = new FileOutputStream(charactersProperties);
                properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
                properties.store(outputProperties, "This file register the application data");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        currentStatus.setText(updateCurrentStatusText(getBoxesChecked(boxes)));
    }

    /* Set all the check-boxes to unselected */
    public static void uncheckAll() {
        for (CheckBox box : boxes) {
            box.setSelected(false);
            try {
                OutputStream outputProperties = new FileOutputStream(charactersProperties);
                properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
                properties.store(outputProperties, "This file register the application data");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        currentStatus.setText(updateCurrentStatusText(getBoxesChecked(boxes)));
    }

    public static void setHighlightColor(Color color) {
        highlightColor = color;
    }

    public static Color getHighlightColor() {
        return highlightColor;
    }

    public static void updateCheckboxTextColor(Color color) {
        //Highlight the characters corresponding with the typed text
        String name = searchField.getText();
        for (CheckBox box : boxes) {
            if (textContains(box.getText(), name)) {
                box.setTextFill(color);
            } else {
                box.setTextFill(Color.BLACK);
            }
        }
    }

    /* Updates the checkbox state in the SmashRoulette.properties file */
    private void fireCheckbox(ActionEvent e) {
        if (e.getSource() instanceof CheckBox box) {
            try {
                OutputStream outputProperties = new FileOutputStream(charactersProperties);
                properties.setProperty(box.getText(), String.valueOf(box.isSelected()));
                properties.store(outputProperties, "This file register the application data");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        //Updates the current number of characters selected
        currentStatus.setText(updateCurrentStatusText(getBoxesChecked(boxes)));
    }
}