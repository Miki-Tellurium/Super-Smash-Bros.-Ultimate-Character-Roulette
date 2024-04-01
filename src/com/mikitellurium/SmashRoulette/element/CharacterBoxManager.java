package com.mikitellurium.SmashRoulette.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class CharacterBoxManager {

    private final List<CharacterBox> boxes = new ArrayList<>();

    public void add(CharacterBox box) {
        boxes.add(box);
    }

    public void addAll(Collection<CharacterBox> boxes) {
        this.boxes.addAll(boxes);
    }

    public List<CharacterBox> getBoxes() {
        return this.boxes;
    }

    public List<CharacterBox> getCheckedBoxes() {
        return this.boxes.stream().filter(CharacterBox::isSelected).toList();
    }

    public List<CharacterBox> getUncheckedBoxes() {
        return this.boxes.stream().filter((box) -> !box.isSelected()).toList();
    }

    public boolean isBoxChecked(int index) {
        return this.boxes.get(index).isSelected();
    }

    public boolean areAllBoxChecked() {
        return this.getUncheckedBoxes().isEmpty();
    }

    public void forEachBox(Consumer<CharacterBox> consumer) {
        for (CharacterBox box : this.boxes) {
            consumer.accept(box);
        }
    }

}
