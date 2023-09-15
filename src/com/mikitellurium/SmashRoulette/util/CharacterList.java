package com.mikitellurium.SmashRoulette.util;

import com.mikitellurium.SmashRoulette.window.ChecklistWindow;
import javafx.scene.image.Image;

import java.util.Random;

public class CharacterList {

    private static final Character[] characters = new Character[]{
        new Character("Mario", SeriesSymbols.SUPER_MARIO),
        new Character("Donkey Kong", SeriesSymbols.DONKEY_KONG),
        new Character("Link", SeriesSymbols.ZELDA),
        new Character("Samus", SeriesSymbols.METROID),
        new Character("Dark Samus", SeriesSymbols.METROID),
        new Character("Yoshi", SeriesSymbols.YOSHI),
        new Character("Kirby", SeriesSymbols.KIRBY),
        new Character("Fox", SeriesSymbols.STAR_FOX),
        new Character("Pikachu", SeriesSymbols.POKEMON),
        new Character("Luigi", SeriesSymbols.SUPER_MARIO),
        new Character("Ness", SeriesSymbols.EARTHBOUND),
        new Character("Captain Falcon", SeriesSymbols.F_ZERO),
        new Character("Jigglypuff", SeriesSymbols.POKEMON),
        new Character("Peach", SeriesSymbols.SUPER_MARIO),
        new Character("Daisy", SeriesSymbols.SUPER_MARIO),
        new Character("Bowser", SeriesSymbols.SUPER_MARIO),
        new Character("Ice Climbers", SeriesSymbols.ICE_CLIMBERS),
        new Character("Sheik", SeriesSymbols.ZELDA),
        new Character("Zelda", SeriesSymbols.ZELDA),
        new Character("Dr. Mario", SeriesSymbols.SUPER_MARIO),
        new Character("Pichu", SeriesSymbols.POKEMON),
        new Character("Falco", SeriesSymbols.STAR_FOX),
        new Character("Marth", SeriesSymbols.FIRE_EMBLEM),
        new Character("Lucina", SeriesSymbols.FIRE_EMBLEM),
        new Character("Young Link", SeriesSymbols.ZELDA),
        new Character("Ganondorf", SeriesSymbols.ZELDA),
        new Character("Mewtwo", SeriesSymbols.POKEMON),
        new Character("Roy", SeriesSymbols.FIRE_EMBLEM),
        new Character("Chrom", SeriesSymbols.FIRE_EMBLEM),
        new Character("Mr. Game & Watch", SeriesSymbols.GAME_AND_WATCH),
        new Character("Meta Knight", SeriesSymbols.KIRBY),
        new Character("Pit", SeriesSymbols.KID_ICARUS),
        new Character("Dark Pit", SeriesSymbols.KID_ICARUS),
        new Character("Zero Suit Samus", SeriesSymbols.METROID),
        new Character("Wario", SeriesSymbols.WARIO),
        new Character("Snake", SeriesSymbols.METAL_GEAR),
        new Character("Ike", SeriesSymbols.FIRE_EMBLEM),
        new Character("Pokémon Trainer", SeriesSymbols.POKEMON),
        new Character("Diddy Kong", SeriesSymbols.DONKEY_KONG),
        new Character("Lucas", SeriesSymbols.EARTHBOUND),
        new Character("Sonic", SeriesSymbols.SONIC),
        new Character("King Dedede", SeriesSymbols.KIRBY),
        new Character("Olimar", SeriesSymbols.PIKMIN),
        new Character("Lucario", SeriesSymbols.POKEMON),
        new Character("R.O.B.", SeriesSymbols.GYROMITE),
        new Character("Toon Link", SeriesSymbols.ZELDA),
        new Character("Wolf", SeriesSymbols.STAR_FOX),
        new Character("Villager", SeriesSymbols.ANIMAL_CROSSING),
        new Character("Mega Man", SeriesSymbols.MEGA_MAN),
        new Character("Wii Fit Trainer", SeriesSymbols.WII_FIT),
        new Character("Rosalina & Luma", SeriesSymbols.SUPER_MARIO),
        new Character("Little Mac", SeriesSymbols.PUNCH_OUT),
        new Character("Greninja", SeriesSymbols.POKEMON),
        new Character("Palutena", SeriesSymbols.KID_ICARUS),
        new Character("Pac-Man", SeriesSymbols.PAC_MAN),
        new Character("Robin", SeriesSymbols.FIRE_EMBLEM),
        new Character("Shulk", SeriesSymbols.XENOBLADE_CHRONICLES),
        new Character("Bowser Jr.", SeriesSymbols.SUPER_MARIO),
        new Character("Duck Hunt", SeriesSymbols.DUCK_HUNT),
        new Character("Ryu", SeriesSymbols.STREET_FIGHTER),
        new Character("Ken", SeriesSymbols.STREET_FIGHTER),
        new Character("Cloud", SeriesSymbols.FINAL_FANTASY),
        new Character("Corrin", SeriesSymbols.FIRE_EMBLEM),
        new Character("Bayonetta", SeriesSymbols.BAYONETTA),
        new Character("Inkling", SeriesSymbols.SPLATOON),
        new Character("Ridley", SeriesSymbols.METROID),
        new Character("Simon", SeriesSymbols.CASTLEVANIA),
        new Character("Richter", SeriesSymbols.CASTLEVANIA),
        new Character("King K. Rool", SeriesSymbols.DONKEY_KONG),
        new Character("Isabelle", SeriesSymbols.ANIMAL_CROSSING),
        new Character("Incineroar", SeriesSymbols.POKEMON),
        new Character("Piranha Plant", SeriesSymbols.SUPER_MARIO),
        new Character("Joker", SeriesSymbols.PERSONA),
        new Character("Hero", SeriesSymbols.DRAGON_QUEST),
        new Character("Banjo & Kazooie", SeriesSymbols.BANJO_KAZOOIE),
        new Character("Terry", SeriesSymbols.FATAL_FURY),
        new Character("Byleth", SeriesSymbols.FIRE_EMBLEM),
        new Character("Min Min", SeriesSymbols.ARMS),
        new Character("Steve", SeriesSymbols.MINECRAFT),
        new Character("Sephiroth", SeriesSymbols.FINAL_FANTASY),
        new Character("Pyra & Mythra", SeriesSymbols.XENOBLADE_CHRONICLES),
        new Character("Kazuya", SeriesSymbols.TEKKEN),
        new Character("Sora", SeriesSymbols.KINGDOM_HEARTS),
        new Character("Mii Brawler", SeriesSymbols.SUPER_SMASH_BROS),
        new Character("Mii Swordfighter", SeriesSymbols.SUPER_SMASH_BROS),
        new Character("Mii Gunner", SeriesSymbols.SUPER_SMASH_BROS)
    };

    /* Returns the character name corresponding to the provided integer */
    public static Character getCharacter(int a) {
        return characters[a];
    }

    /* Generates a random integer and returns the name of the corresponding character */
    public static Character rollRandomCharacter() {
        if (ChecklistWindow.areAllBoxChecked()) {
            return null;
        }

        Random random = new Random();
        int i;
        do {
            i = random.nextInt(86);
        } while (ChecklistWindow.isBoxChecked(i));
        return getCharacter(i);
    }

    public record Character(String name, String symbol) {

        public Image getRender() {
            return new Image("/resources/renders/" + name + ".png");
        }

        public Image getSeriesSymbol() {
            return new Image("/resources/symbols/" + symbol + ".png");
        }

    }

}