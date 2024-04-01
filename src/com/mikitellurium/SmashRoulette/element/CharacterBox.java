package com.mikitellurium.SmashRoulette.element;

import com.mikitellurium.SmashRoulette.data.Character;
import javafx.scene.control.CheckBox;

public class CharacterBox extends CheckBox {

    private final Character character;

    public CharacterBox(Character character, String text) {
        super(text);
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

}
