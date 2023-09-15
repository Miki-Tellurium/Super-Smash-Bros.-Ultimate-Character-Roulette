package com.mikitellurium.SmashRoulette.util;

import com.mikitellurium.SmashRoulette.window.ChecklistWindow;
import javafx.scene.image.Image;

import java.util.Random;

public class CharacterList {

    private static final Character[] characters = new Character[]{
        new Character("Mario", "MarioSymbol"),
        new Character("Donkey Kong", "DKSymbol"),
        new Character("Link", "ZeldaSymbol"),
        new Character("Samus", "MetroidSymbol"),
        new Character("Dark Samus", "MetroidSymbol"),
        new Character("Yoshi", "YoshiSymbol"),
        new Character("Kirby", "KirbySymbol"),
        new Character("Fox", "StarFoxSymbol"),
        new Character("Pikachu", "PokemonSymbol"),
        new Character("Luigi", "MarioSymbol"),
        new Character("Ness", "EarthboundSymbol"),
        new Character("Captain Falcon", "FZeroSymbol"),
        new Character("Jigglypuff", "PokemonSymbol"),
        new Character("Peach", "MarioSymbol"),
        new Character("Daisy", "MarioSymbol"),
        new Character("Bowser", "MarioSymbol"),
        new Character("Ice Climbers", "IceClimberSymbol"),
        new Character("Sheik", "ZeldaSymbol"),
        new Character("Zelda", "ZeldaSymbol"),
        new Character("Dr. Mario", "MarioSymbol"),
        new Character("Pichu", "PokemonSymbol"),
        new Character("Falco", "StarFoxSymbol"),
        new Character("Marth", "FireEmblemSymbol"),
        new Character("Lucina", "FireEmblemSymbol"),
        new Character("Young Link", "ZeldaSymbol"),
        new Character("Ganondorf", "ZeldaSymbol"),
        new Character("Mewtwo", "PokemonSymbol"),
        new Character("Roy", "FireEmblemSymbol"),
        new Character("Chrom", "FireEmblemSymbol"),
        new Character("Mr. Game & Watch", "Game&WatchSymbol"),
        new Character("Meta Knight", "KirbySymbol"),
        new Character("Pit", "KidIcarusSymbol"),
        new Character("Dark Pit", "KidIcarusSymbol"),
        new Character("Zero Suit Samus", "MetroidSymbol"),
        new Character("Wario", "WarioSymbol"),
        new Character("Snake", "MetalGearSymbol"),
        new Character("Ike", "FireEmblemSymbol"),
        new Character("Pokémon Trainer", "PokemonSymbol"),
        new Character("Diddy Kong", "DKSymbol"),
        new Character("Lucas", "EarthboundSymbol"),
        new Character("Sonic", "SonicSymbol"),
        new Character("King Dedede", "KirbySymbol"),
        new Character("Olimar", "PikminSymbol"),
        new Character("Lucario", "PokemonSymbol"),
        new Character("R.O.B.", "GyromiteSymbol"),
        new Character("Toon Link", "ZeldaSymbol"),
        new Character("Wolf", "StarFoxSymbol"),
        new Character("Villager", "AnimalCrossingSymbol"),
        new Character("Mega Man", "MegaManSymbol"),
        new Character("Wii Fit Trainer", "WiiFitSymbol"),
        new Character("Rosalina & Luma", "MarioSymbol"),
        new Character("Little Mac", "PunchOutSymbol"),
        new Character("Greninja", "PokemonSymbol"),
        new Character("Palutena", "KidIcarusSymbol"),
        new Character("Pac-Man", "PacManSymbol"),
        new Character("Robin", "FireEmblemSymbol"),
        new Character("Shulk", "XenobladeSymbol"),
        new Character("Bowser Jr.", "MarioSymbol"),
        new Character("Duck Hunt", "DuckHuntSymbol"),
        new Character("Ryu", "StreetFighterSymbol"),
        new Character("Ken", "StreetFighterSymbol"),
        new Character("Cloud", "FinalFantasySymbol"),
        new Character("Corrin", "FireEmblemSymbol"),
        new Character("Bayonetta", "BayonettaSymbol"),
        new Character("Inkling", "SplatoonSymbol"),
        new Character("Ridley", "MetroidSymbol"),
        new Character("Simon", "CastlevaniaSymbol"),
        new Character("Richter", "CastlevaniaSymbol"),
        new Character("King K. Rool", "DKSymbol"),
        new Character("Isabelle", "AnimalCrossingSymbol"),
        new Character("Incineroar", "PokemonSymbol"),
        new Character("Piranha Plant", "MarioSymbol"),
        new Character("Joker", "PersonaSymbol"),
        new Character("Hero", "DragonQuestSymbol"),
        new Character("Banjo & Kazooie", "BanjoKazooieSymbol"),
        new Character("Terry", "FatalFurySymbol"),
        new Character("Byleth", "FireEmblemSymbol"),
        new Character("Min Min", "ARMSSymbol"),
        new Character("Steve", "MinecraftSymbol"),
        new Character("Sephiroth", "FinalFantasySymbol"),
        new Character("Pyra & Mythra", "XenobladeSymbol"),
        new Character("Kazuya", "TekkenSymbol"),
        new Character("Sora", "KingdomHeartsSymbol"),
        new Character("Mii Brawler", "SmashBrosSymbol"),
        new Character("Mii Swordfighter", "SmashBrosSymbol"),
        new Character("Mii Gunner", "SmashBrosSymbol")
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

//    /* Returns an Image object representing the render of the character provided */
//    public static Image getCharacterRender(String name) {
//        return new Image("/resources/renders/" + name + ".png");
//    }
//
//    /* Returns an Image object representing the series symbol corresponding the provided character name */
//    public static Image getSeriesSymbol(String name) {
//        String symbol = seriesSymbols.get(name);
//        return new Image("/resources/symbols/" + symbol + ".png");
//    }

    public record Character(String name, String symbol) {

        public Image getRender() {
            return new Image("/resources/renders/" + name + ".png");
        }

        public Image getSeriesSymbol() {
            return new Image("/resources/symbols/" + symbol + ".png");
        }

    }

}