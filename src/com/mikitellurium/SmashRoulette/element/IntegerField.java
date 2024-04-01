package com.mikitellurium.SmashRoulette.element;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class IntegerField extends TextField {

    private SimpleIntegerProperty value;
    private final int minValue;
    private final int maxValue;

    public IntegerField(int minValue, int maxValue) {

        if (minValue > maxValue) {
            throw new IllegalArgumentException(
                    "IntegerField minimum value " + minValue + "is greater than maximum value " + maxValue);
        }

        this.minValue = minValue;
        this.maxValue = maxValue;
        value = new SimpleIntegerProperty(minValue);
        setText(minValue + "");

        // Make sure the value stays in the required range
        // and update the field text to match the value
        value.addListener((observableValue, oldValue, newValue) -> {

            if (newValue == null) {
                this.setText("");
            } else {
                if (newValue.intValue() < minValue) {
                    value.setValue(this.maxValue);
                    return;
                }
                if (newValue.intValue() > maxValue) {
                    value.setValue(this.maxValue);
                    return;
                }
                if (newValue.intValue() == 0 && (textProperty().get() == null || textProperty().get().equals(""))) {
                    // no action required, text property is already blank, no need to set it to 0
                } else {
                    this.setText(newValue.toString());
                }
            }
        });

        final String digits = "0123456789";

        // Allow only numerical input
        this.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!digits.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });

        this.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == null || newValue.equals("")) {
                this.setValue(0);
                return;
            }
            // Read the value typed by the user
            final int typedValue = Integer.parseInt(newValue);

            if (this.minValue > typedValue || typedValue > this.maxValue) {
                textProperty().setValue(oldValue);
            }

            value.set(Integer.parseInt(textProperty().get()));
        });
    }

    public IntegerField(int minValue, int maxValue, int initialValue) {
        this(minValue, maxValue);
        value = new SimpleIntegerProperty(initialValue);
        setText(initialValue + "");
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        if (value < minValue) {
            throw new IllegalArgumentException(
                    "IntegerField value is lower than minimum value " + minValue);
        } else if (value > maxValue) {
            throw new IllegalArgumentException(
                    "IntegerField value is greater than maximum value " + maxValue);
        }
        this.value.set(value);
    }

    public int getValue() {
        return value.get();
    }

 }

