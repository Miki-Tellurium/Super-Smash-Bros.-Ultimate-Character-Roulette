package com.mikitellurium.smashcharacterreoulette;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CharacterList {
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

    private static final Map<String,String> symbols = new HashMap<>();
    static {
        symbols.put("Mario", "MarioSymbol");
        symbols.put("Donkey Kong", "DKSymbol");
        symbols.put("Link", "ZeldaSymbol");
        symbols.put("Samus", "MetroidSymbol");
        symbols.put("Dark Samus", "MetroidSymbol");
        symbols.put("Yoshi", "YoshiSymbol");
        symbols.put("Kirby", "KirbySymbol");
        symbols.put("Fox", "StarFoxSymbol");
        symbols.put("Pikachu", "PokemonSymbol");
        symbols.put("Luigi", "MarioSymbol");
        symbols.put("Ness", "EarthboundSymbol");
        symbols.put("Captain Falcon", "FZeroSymbol");
        symbols.put("Jigglypuff", "PokemonSymbol");
        symbols.put("Peach", "MarioSymbol");
        symbols.put("Daisy", "MarioSymbol");
        symbols.put("Bowser", "MarioSymbol");
        symbols.put("Ice Climbers", "IceClimberSymbol");
        symbols.put("Sheik", "ZeldaSymbol");
        symbols.put("Zelda", "ZeldaSymbol");
        symbols.put("Dr. Mario", "MarioSymbol");
        symbols.put("Pichu", "PokemonSymbol");
        symbols.put("Falco", "StarFoxSymbol");
        symbols.put("Marth", "FireEmblemSymbol");
        symbols.put("Lucina", "FireEmblemSymbol");
        symbols.put("Young Link", "ZeldaSymbol");
        symbols.put("Ganondorf", "ZeldaSymbol");
        symbols.put("Mewtwo", "PokemonSymbol");
        symbols.put("Roy", "FireEmblemSymbol");
        symbols.put("Chrom", "FireEmblemSymbol");
        symbols.put("Mr. Game & Watch", "Game&WatchSymbol");
        symbols.put("Meta Knight", "KirbySymbol");
        symbols.put("Pit", "KidIcarusSymbol");
        symbols.put("Dark Pit", "KidIcarusSymbol");
        symbols.put("Zero Suit Samus", "MetroidSymbol");
        symbols.put("Wario", "WarioSymbol");
        symbols.put("Snake", "MetalGearSymbol");
        symbols.put("Ike", "FireEmblemSymbol");
        symbols.put("Pokémon Trainer", "PokemonSymbol");
        symbols.put("Diddy Kong", "DKSymbol");
        symbols.put("Lucas", "EarthboundSymbol");
        symbols.put("Sonic", "SonicSymbol");
        symbols.put("King Dedede", "KirbySymbol");
        symbols.put("Olimar", "PikminSymbol");
        symbols.put("Lucario", "PokemonSymbol");
        symbols.put("R.O.B.", "GyromiteSymbol");
        symbols.put("Toon Link", "ZeldaSymbol");
        symbols.put("Wolf", "StarFoxSymbol");
        symbols.put("Villager", "AnimalCrossingSymbol");
        symbols.put("Mega Man", "MegaManSymbol");
        symbols.put("Wii Fit Trainer", "WiiFitSymbol");
        symbols.put("Rosalina & Luma", "MarioSymbol");
        symbols.put("Little Mac", "PunchOutSymbol");
        symbols.put("Greninja", "PokemonSymbol");
        symbols.put("Palutena", "KidIcarusSymbol");
        symbols.put("Pac-Man", "PacManSymbol");
        symbols.put("Robin", "FireEmblemSymbol");
        symbols.put("Shulk", "XenobladeSymbol");
        symbols.put("Bowser Jr.", "MarioSymbol");
        symbols.put("Duck Hunt", "DuckHuntSymbol");
        symbols.put("Ryu", "StreetFighterSymbol");
        symbols.put("Ken", "StreetFighterSymbol");
        symbols.put("Cloud", "FinalFantasySymbol");
        symbols.put("Corrin", "FireEmblemSymbol");
        symbols.put("Bayonetta", "BayonettaSymbol");
        symbols.put("Inkling", "SplatoonSymbol");
        symbols.put("Ridley", "MetroidSymbol");
        symbols.put("Simon", "CastlevaniaSymbol");
        symbols.put("Richter", "CastlevaniaSymbol");
        symbols.put("King K. Rool", "DKSymbol");
        symbols.put("Isabelle", "AnimalCrossingSymbol");
        symbols.put("Incineroar", "PokemonSymbol");
        symbols.put("Piranha Plant", "MarioSymbol");
        symbols.put("Joker", "PersonaSymbol");
        symbols.put("Hero", "DragonQuestSymbol");
        symbols.put("Banjo & Kazooie", "BanjoKazooieSymbol");
        symbols.put("Terry", "FatalFurySymbol");
        symbols.put("Byleth", "FireEmblemSymbol");
        symbols.put("Min Min", "ARMSSymbol");
        symbols.put("Steve", "MinecraftSymbol");
        symbols.put("Sephiroth", "FinalFantasySymbol");
        symbols.put("Pyra & Mythra", "XenobladeSymbol");
        symbols.put("Kazuya", "TekkenSymbol");
        symbols.put("Sora", "KingdomHeartsSymbol");
        symbols.put("Mii Brawler", "SmashBrosSymbol");
        symbols.put("Mii Swordfighter", "SmashBrosSymbol");
        symbols.put("Mii Gunner", "SmashBrosSymbol");
    }

    /* Return the character name corresponding to the provided integer */
    public static String getCharacter(int a) {
        return characters[a];
    }

    /* Return the series symbol corresponding the provided character name */
    public static ImageIcon getSeriesSymbol(String name) {
        String symbol = symbols.get(name);
        return new ImageIcon(Objects.requireNonNull(SmashRoulette.class.getResource("/resources/symbols/" + symbol + ".png")));
    }

}
