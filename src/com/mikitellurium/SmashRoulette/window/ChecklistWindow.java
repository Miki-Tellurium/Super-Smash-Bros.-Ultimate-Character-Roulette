package com.mikitellurium.SmashRoulette.window;

import com.mikitellurium.SmashRoulette.data.Character;
import com.mikitellurium.SmashRoulette.data.Constants;
import com.mikitellurium.SmashRoulette.element.CharacterBox;
import com.mikitellurium.SmashRoulette.element.CharacterBoxManager;
import com.mikitellurium.SmashRoulette.util.ColorConverter;
import com.mikitellurium.SmashRoulette.util.Util;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class ChecklistWindow {

    private final Stage stage = new Stage();
    private final GridPane checkBoxesPane = Util.make(new GridPane(), (pane) -> {
        pane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        pane.setLayoutX(0);
        pane.setLayoutY(50);
        pane.setPadding(new Insets(4));
        pane.setVgap(2);
        pane.setHgap(15);
    });

    private final CharacterBoxManager manager = new CharacterBoxManager();

    private final Properties PROPERTIES = new Properties();
    private Color highlightColor;

    private final TextField searchField = Util.make(new TextField(), (textField) -> {
        textField.setPromptText("Search...");
        textField.setPrefSize(120, 10);
        textField.setLayoutX(5);
        textField.setLayoutY(25);
    });
    private final Text currentStatus = Util.make(new Text(), (text) -> {
        text.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 24));
        text.setLayoutX(245);
        text.setLayoutY(47);
    });

    public ChecklistWindow(Stage parentStage) {
        stage.initOwner(parentStage);
        stage.setTitle("Characters Checklist");
        stage.getIcons().add(Constants.WINDOW_ICON);
        stage.setResizable(false);
        stage.setWidth(1000);
        stage.setHeight(280);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(screenSize.getHeight() / 2 - stage.getHeight() / 2 - 50);

        searchField.textProperty().addListener((observableValue, oldValue, newValue) -> this.highlightCharacters());

        final Button hint = Util.make(new Button(), (button) -> {
            button.setText("?");
            button.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 16));
            button.setPrefSize(10, 10);
            button.setPadding(new Insets(2, 7, 1, 7));
            button.getStyleClass().add("hint");
            button.setLayoutX(searchField.getLayoutX() + searchField.getPrefWidth() + 5);
            button.setLayoutY(25);
        });
        final Tooltip hintTooltip = Util.make(new Tooltip(), (tooltip) -> {
            tooltip.setText("Type a character name to highlight it on the list");
            tooltip.setFont(Font.font(Constants.FONT_ARIAL, FontWeight.BOLD, 12));
            tooltip.setStyle("-fx-background-color: linear-gradient(to left, #24CFFE, #007BFD); -fx-text-fill: black;");
            tooltip.setMaxHeight(10);
            tooltip.setShowDelay(Duration.ZERO);
            tooltip.setHideDelay(Duration.ZERO);
        });
        hint.setTooltip(hintTooltip);

        final MenuItem checkAllBoxes = new MenuItem("Check All");
        checkAllBoxes.setOnAction(e -> showWarningMessage(WarningWindow.Action.CHECK_ALL));
        final MenuItem uncheckAllBoxes = new MenuItem("Uncheck All");
        uncheckAllBoxes.setOnAction(e -> showWarningMessage(WarningWindow.Action.UNCHECK_ALL));
        final MenuItem highlightColorSetting = new MenuItem("Highlight Color");
        highlightColorSetting.setOnAction(e -> new RgbSettingsWindow(this).show());

        final Menu options = new Menu("Options");
        options.getItems().addAll(checkAllBoxes, uncheckAllBoxes, highlightColorSetting);
        final SeparatorMenuItem separator = new SeparatorMenuItem();
        options.getItems().add(2, separator);

        final MenuBar menuBar = Util.make(new MenuBar(), (bar) -> {
            bar.getMenus().add(options);
            bar.setUseSystemMenuBar(true);
            bar.setPrefSize(stage.getWidth(), 20);
            bar.setLayoutX(0);
            bar.setLayoutY(0);
        });

        checkBoxesPane.setPrefSize(stage.getWidth(), stage.getHeight() - 25);

        this.buildCheckBoxes();

        currentStatus.setText(this.updateCurrentStatusText());
        final Pane mainPane = new Pane();
        mainPane.getChildren().add(checkBoxesPane);
        mainPane.getChildren().addAll(menuBar, searchField, hint, currentStatus);
        mainPane.setBackground(new Background(Constants.CHECKLIST_WINDOW_BACKGROUND_IMAGE));

        final Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(Constants.CSS_STYLE);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> this.saveProperties());
    }

    protected void show() {
        stage.show();
    }

    private void buildCheckBoxes() {
        int column = 0;
        int row = 0;
        for (Character character : Character.values()) {
            CharacterBox box = new CharacterBox(character, character.getName());
            GridPane.setConstraints(box, column, row);
            box.setFont(Font.font(FontUIResource.DIALOG, FontWeight.EXTRA_BOLD, 12));
            box.setTextFill(Color.BLACK);
            box.setOnAction(this::updateBoxSavedProperty);
            checkBoxesPane.getChildren().add(box);
            this.manager.add(box);
            if (column < 7) {
                column++;
            } else {
                column = 0;
                row++;
            }
        }
        this.initCheckBoxes();
    }

    public CharacterBoxManager getBoxManager() {
        return this.manager;
    }

    private void initCheckBoxes() {
        File file = new File(Constants.PROPERTIES_FILE);
        if (file.exists()) {
            this.loadSavedProperties();
        } else {
            highlightColor = Constants.DEFAULT_HIGHLIGHT_COLOR;
            this.buildSavedProperties();
        }
    }

    private void loadSavedProperties() {
        try {
            InputStream inputProperties = new FileInputStream(Constants.PROPERTIES_FILE);
            PROPERTIES.load(inputProperties);
            this.manager.forEachBox((box) -> box.setSelected(Boolean.parseBoolean(PROPERTIES.getProperty(box.getText()))));

            if (PROPERTIES.containsKey("highlight-color")) { // Check for the old key
                this.highlightColor = ColorConverter.stringToColor(PROPERTIES.getProperty("highlight-color"));
                PROPERTIES.remove("highlight-color");
            } else {
                this.highlightColor = ColorConverter.stringToColor(
                        (String) PROPERTIES.getOrDefault(Constants.HIGHLIGHT_COLOR_KEY, Constants.DEFAULT_HIGHLIGHT_COLOR_STRING));
            }
        } catch (IOException e) {
            System.out.println("Exception thrown while loading saved properties.");
            e.printStackTrace();
        }
    }

    private void buildSavedProperties() {
        try {
            OutputStream outputProperties = new FileOutputStream(Constants.PROPERTIES_FILE);
            this.manager.forEachBox((box) -> PROPERTIES.setProperty(box.getText(), String.valueOf(box.isSelected())));
            PROPERTIES.setProperty(Constants.HIGHLIGHT_COLOR_KEY, Constants.DEFAULT_HIGHLIGHT_COLOR_STRING);
            PROPERTIES.store(outputProperties, Constants.PROPERTIES_COMMENTS);
        } catch (IOException e) {
            System.out.println("Exception thrown while building new saved properties.");
            e.printStackTrace();
        }
    }

    private void saveProperties() {
        try {
            OutputStream outputProperties = new FileOutputStream(Constants.PROPERTIES_FILE);
            this.manager.forEachBox((box) -> PROPERTIES.setProperty(box.getText(), String.valueOf(box.isSelected())));
            PROPERTIES.setProperty(Constants.HIGHLIGHT_COLOR_KEY, ColorConverter.colorToString(highlightColor));
            PROPERTIES.store(outputProperties, Constants.PROPERTIES_COMMENTS);
        } catch (IOException e) {
            System.out.println("Exception thrown while saving properties.");
            e.printStackTrace();
        }
    }

    public Color getHighlightColor() {
        return highlightColor;
    }

    protected void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }

    protected void highlightCharacters() {
        String name = searchField.getText();
        this.manager.forEachBox((box) -> {
            if (textContains(box.getText(), name)) {
                box.setTextFill(this.highlightColor);
            } else {
                box.setTextFill(Color.BLACK);
            }
        });
    }

    /* Returns true if substring is contained in string */
    private boolean textContains(String string, String substring) {
        if (string == null) {
            return false;
        }
        if (substring == null || Objects.equals(substring, "")) {
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

    private String updateCurrentStatusText() {
        int n = this.manager.getCheckedBoxes().size();
        String text;
        if (n == 0) {
            text = "No character selected";
        } else if(n == 1) {
            text = n + " character selected";
        } else if (n == 86) {
            text = "All characters selected";
        } else {
            text = n + " characters selected";
        }
        return text;
    }

    protected void checkAll() {
        this.manager.forEachBox((box) -> {
            box.setSelected(true);
            try {
                OutputStream outputProperties = new FileOutputStream(Constants.PROPERTIES_FILE);
                PROPERTIES.setProperty(box.getText(), String.valueOf(box.isSelected()));
                PROPERTIES.store(outputProperties, Constants.PROPERTIES_COMMENTS);
            } catch (IOException e) {
                System.out.println("Exception thrown while checking box: " + box.getText());
                e.printStackTrace();
            }
        });
        currentStatus.setText(this.updateCurrentStatusText());
    }

    protected void uncheckAll() {
        this.manager.forEachBox((box) -> {
            box.setSelected(false);
            try {
                OutputStream outputProperties = new FileOutputStream(Constants.PROPERTIES_FILE);
                PROPERTIES.setProperty(box.getText(), String.valueOf(box.isSelected()));
                PROPERTIES.store(outputProperties, Constants.PROPERTIES_COMMENTS);
            } catch (IOException e) {
                System.out.println("Exception thrown while unchecking box: " + box.getText());
                e.printStackTrace();
            }
        });
        currentStatus.setText(this.updateCurrentStatusText());
    }

    private void updateBoxSavedProperty(ActionEvent event) {
        if (event.getSource() instanceof CharacterBox box) {
            try {
                OutputStream outputProperties = new FileOutputStream(Constants.PROPERTIES_FILE);
                PROPERTIES.setProperty(box.getText(), String.valueOf(box.isSelected()));
                PROPERTIES.store(outputProperties, Constants.PROPERTIES_COMMENTS);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        currentStatus.setText(this.updateCurrentStatusText());
    }

    private void showWarningMessage(WarningWindow.Action action) {
        WarningWindow warning = new WarningWindow(this, action);
        warning.show();
    }

}