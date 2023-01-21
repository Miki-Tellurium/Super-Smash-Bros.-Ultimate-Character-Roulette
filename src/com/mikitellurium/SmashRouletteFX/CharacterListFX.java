package com.mikitellurium.SmashRouletteFX;

import com.mikitellurium.smashcharacterreoulette.SmashRoulette;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class CharacterListFX {
    private static final String[] characters = {
            "Mario",
            "Donkey Kong",
            "Link",
            "Samus",
            "Dark Samus",
            "Yoshi",
            "Kirby",
            "Fox",
            "Pikachu",
            "Luigi",
            "Ness",
            "Captain Falcon",
            "Jigglypuff",
            "Peach",
            "Daisy",
            "Bowser",
            "Ice Climbers",
            "Sheik",
            "Zelda",
            "Dr. Mario",
            "Pichu",
            "Falco",
            "Marth",
            "Lucina",
            "Young Link",
            "Ganondorf",
            "Mewtwo",
            "Roy",
            "Chrom",
            "Mr. Game & Watch",
            "Meta Knight",
            "Pit",
            "Dark Pit",
            "Zero Suit Samus",
            "Wario",
            "Snake",
            "Ike",
            "Pokémon Trainer",
            "Diddy Kong",
            "Lucas",
            "Sonic",
            "King Dedede",
            "Olimar",
            "Lucario",
            "R.O.B.",
            "Toon Link",
            "Wolf",
            "Villager",
            "Mega Man",
            "Wii Fit Trainer",
            "Rosalina & Luma",
            "Little Mac",
            "Greninja",
            "Palutena",
            "Pac-Man",
            "Robin",
            "Shulk",
            "Bowser Jr.",
            "Duck Hunt",
            "Ryu",
            "Ken",
            "Cloud",
            "Corrin",
            "Bayonetta",
            "Inkling",
            "Ridley",
            "Simon",
            "Richter",
            "King K. Rool",
            "Isabelle",
            "Incineroar",
            "Piranha Plant",
            "Joker",
            "Hero",
            "Banjo & Kazooie",
            "Terry",
            "Byleth",
            "Min Min",
            "Steve",
            "Sephiroth",
            "Pyra & Mythra",
            "Kazuya",
            "Sora",
            "Mii Brawler",
            "Mii Swordfighter",
            "Mii Gunner"
    };

    private static final Map<String,String> seriesSymbols = new HashMap<>();
    static {
        seriesSymbols.put("Mario", "MarioSymbol");
        seriesSymbols.put("Donkey Kong", "DKSymbol");
        seriesSymbols.put("Link", "ZeldaSymbol");
        seriesSymbols.put("Samus", "MetroidSymbol");
        seriesSymbols.put("Dark Samus", "MetroidSymbol");
        seriesSymbols.put("Yoshi", "YoshiSymbol");
        seriesSymbols.put("Kirby", "KirbySymbol");
        seriesSymbols.put("Fox", "StarFoxSymbol");
        seriesSymbols.put("Pikachu", "PokemonSymbol");
        seriesSymbols.put("Luigi", "MarioSymbol");
        seriesSymbols.put("Ness", "EarthboundSymbol");
        seriesSymbols.put("Captain Falcon", "FZeroSymbol");
        seriesSymbols.put("Jigglypuff", "PokemonSymbol");
        seriesSymbols.put("Peach", "MarioSymbol");
        seriesSymbols.put("Daisy", "MarioSymbol");
        seriesSymbols.put("Bowser", "MarioSymbol");
        seriesSymbols.put("Ice Climbers", "IceClimberSymbol");
        seriesSymbols.put("Sheik", "ZeldaSymbol");
        seriesSymbols.put("Zelda", "ZeldaSymbol");
        seriesSymbols.put("Dr. Mario", "MarioSymbol");
        seriesSymbols.put("Pichu", "PokemonSymbol");
        seriesSymbols.put("Falco", "StarFoxSymbol");
        seriesSymbols.put("Marth", "FireEmblemSymbol");
        seriesSymbols.put("Lucina", "FireEmblemSymbol");
        seriesSymbols.put("Young Link", "ZeldaSymbol");
        seriesSymbols.put("Ganondorf", "ZeldaSymbol");
        seriesSymbols.put("Mewtwo", "PokemonSymbol");
        seriesSymbols.put("Roy", "FireEmblemSymbol");
        seriesSymbols.put("Chrom", "FireEmblemSymbol");
        seriesSymbols.put("Mr. Game & Watch", "Game&WatchSymbol");
        seriesSymbols.put("Meta Knight", "KirbySymbol");
        seriesSymbols.put("Pit", "KidIcarusSymbol");
        seriesSymbols.put("Dark Pit", "KidIcarusSymbol");
        seriesSymbols.put("Zero Suit Samus", "MetroidSymbol");
        seriesSymbols.put("Wario", "WarioSymbol");
        seriesSymbols.put("Snake", "MetalGearSymbol");
        seriesSymbols.put("Ike", "FireEmblemSymbol");
        seriesSymbols.put("Pokémon Trainer", "PokemonSymbol");
        seriesSymbols.put("Diddy Kong", "DKSymbol");
        seriesSymbols.put("Lucas", "EarthboundSymbol");
        seriesSymbols.put("Sonic", "SonicSymbol");
        seriesSymbols.put("King Dedede", "KirbySymbol");
        seriesSymbols.put("Olimar", "PikminSymbol");
        seriesSymbols.put("Lucario", "PokemonSymbol");
        seriesSymbols.put("R.O.B.", "GyromiteSymbol");
        seriesSymbols.put("Toon Link", "ZeldaSymbol");
        seriesSymbols.put("Wolf", "StarFoxSymbol");
        seriesSymbols.put("Villager", "AnimalCrossingSymbol");
        seriesSymbols.put("Mega Man", "MegaManSymbol");
        seriesSymbols.put("Wii Fit Trainer", "WiiFitSymbol");
        seriesSymbols.put("Rosalina & Luma", "MarioSymbol");
        seriesSymbols.put("Little Mac", "PunchOutSymbol");
        seriesSymbols.put("Greninja", "PokemonSymbol");
        seriesSymbols.put("Palutena", "KidIcarusSymbol");
        seriesSymbols.put("Pac-Man", "PacManSymbol");
        seriesSymbols.put("Robin", "FireEmblemSymbol");
        seriesSymbols.put("Shulk", "XenobladeSymbol");
        seriesSymbols.put("Bowser Jr.", "MarioSymbol");
        seriesSymbols.put("Duck Hunt", "DuckHuntSymbol");
        seriesSymbols.put("Ryu", "StreetFighterSymbol");
        seriesSymbols.put("Ken", "StreetFighterSymbol");
        seriesSymbols.put("Cloud", "FinalFantasySymbol");
        seriesSymbols.put("Corrin", "FireEmblemSymbol");
        seriesSymbols.put("Bayonetta", "BayonettaSymbol");
        seriesSymbols.put("Inkling", "SplatoonSymbol");
        seriesSymbols.put("Ridley", "MetroidSymbol");
        seriesSymbols.put("Simon", "CastlevaniaSymbol");
        seriesSymbols.put("Richter", "CastlevaniaSymbol");
        seriesSymbols.put("King K. Rool", "DKSymbol");
        seriesSymbols.put("Isabelle", "AnimalCrossingSymbol");
        seriesSymbols.put("Incineroar", "PokemonSymbol");
        seriesSymbols.put("Piranha Plant", "MarioSymbol");
        seriesSymbols.put("Joker", "PersonaSymbol");
        seriesSymbols.put("Hero", "DragonQuestSymbol");
        seriesSymbols.put("Banjo & Kazooie", "BanjoKazooieSymbol");
        seriesSymbols.put("Terry", "FatalFurySymbol");
        seriesSymbols.put("Byleth", "FireEmblemSymbol");
        seriesSymbols.put("Min Min", "ARMSSymbol");
        seriesSymbols.put("Steve", "MinecraftSymbol");
        seriesSymbols.put("Sephiroth", "FinalFantasySymbol");
        seriesSymbols.put("Pyra & Mythra", "XenobladeSymbol");
        seriesSymbols.put("Kazuya", "TekkenSymbol");
        seriesSymbols.put("Sora", "KingdomHeartsSymbol");
        seriesSymbols.put("Mii Brawler", "SmashBrosSymbol");
        seriesSymbols.put("Mii Swordfighter", "SmashBrosSymbol");
        seriesSymbols.put("Mii Gunner", "SmashBrosSymbol");
    }
    //Generate a random integer and return the name of the corresponding character
    public static String rollRandomCharacter() {
        int random;
        random = new Random().nextInt(86);
        return getCharacter(random);
    }
    //Return the character name corresponding to the provided integer
    public static String getCharacter(int a) {
        return characters[a];
    }

    //Return the series symbol corresponding the provided character name
    public static ImageIcon getSeriesSymbol(String name) {
        String symbol = seriesSymbols.get(name);
        return new ImageIcon(Objects.requireNonNull(SmashRoulette.class.getResource("/resources/symbols/" + symbol + ".png")));
    }
}
