package com.mikitellurium.SmashRoulette.data;

import com.mikitellurium.SmashRoulette.element.CharacterBox;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public enum Character {
    MARIO("Mario", SeriesSymbols.SUPER_MARIO),
    DONKEY_KONG("Donkey Kong", SeriesSymbols.DONKEY_KONG),
    LINK("Link", SeriesSymbols.ZELDA),
    SAMUS("Samus", SeriesSymbols.METROID),
    DARK_SAMUS("Dark Samus", SeriesSymbols.METROID),
    YOSHI("Yoshi", SeriesSymbols.YOSHI),
    KIRBY("Kirby", SeriesSymbols.KIRBY),
    FOX("Fox", SeriesSymbols.STAR_FOX),
    PIKACHU("Pikachu", SeriesSymbols.POKEMON),
    LUIGI("Luigi", SeriesSymbols.SUPER_MARIO),
    NESS("Ness", SeriesSymbols.EARTHBOUND),
    CAPTAIN_FALCON("Captain Falcon", SeriesSymbols.F_ZERO),
    JIGGLYPUFF("Jigglypuff", SeriesSymbols.POKEMON),
    PEACH("Peach", SeriesSymbols.SUPER_MARIO),
    DAISY("Daisy", SeriesSymbols.SUPER_MARIO),
    BOWSER("Bowser", SeriesSymbols.SUPER_MARIO),
    ICE_CLIMBERS("Ice Climbers", SeriesSymbols.ICE_CLIMBERS),
    SHEIK("Sheik", SeriesSymbols.ZELDA),
    ZELDA("Zelda", SeriesSymbols.ZELDA),
    DR_MARIO("Dr. Mario", SeriesSymbols.SUPER_MARIO),
    PICHU("Pichu", SeriesSymbols.POKEMON),
    FALCO("Falco", SeriesSymbols.STAR_FOX),
    MARTH("Marth", SeriesSymbols.FIRE_EMBLEM),
    LUCINA("Lucina", SeriesSymbols.FIRE_EMBLEM),
    YOUNG_LINK("Young Link", SeriesSymbols.ZELDA),
    GANONDORF("Ganondorf", SeriesSymbols.ZELDA),
    MEWTWO("Mewtwo", SeriesSymbols.POKEMON),
    ROY("Roy", SeriesSymbols.FIRE_EMBLEM),
    CHROM("Chrom", SeriesSymbols.FIRE_EMBLEM),
    MR_GAME_AND_WATCH("Mr. Game & Watch", SeriesSymbols.GAME_AND_WATCH),
    META_KNIGHT("Meta Knight", SeriesSymbols.KIRBY),
    PIT("Pit", SeriesSymbols.KID_ICARUS),
    DARK_PIT("Dark Pit", SeriesSymbols.KID_ICARUS),
    ZERO_SUIT_SAMUS("Zero Suit Samus", SeriesSymbols.METROID),
    WARIO("Wario", SeriesSymbols.WARIO),
    SNAKE("Snake", SeriesSymbols.METAL_GEAR),
    IKE("Ike", SeriesSymbols.FIRE_EMBLEM),
    POKEMON_TRAINER("Pokémon Trainer", SeriesSymbols.POKEMON),
    DIDDY_KONG("Diddy Kong", SeriesSymbols.DONKEY_KONG),
    LUCAS("Lucas", SeriesSymbols.EARTHBOUND),
    SONIC("Sonic", SeriesSymbols.SONIC),
    KING_DEDEDE("King Dedede", SeriesSymbols.KIRBY),
    OLIMAR("Olimar", SeriesSymbols.PIKMIN),
    LUCARIO("Lucario", SeriesSymbols.POKEMON),
    R_O_B("R.O.B.", SeriesSymbols.GYROMITE),
    TOON_LINK("Toon Link", SeriesSymbols.ZELDA),
    WOLF("Wolf", SeriesSymbols.STAR_FOX),
    VILLAGER("Villager", SeriesSymbols.ANIMAL_CROSSING),
    MEGA_MAN("Mega Man", SeriesSymbols.MEGA_MAN),
    WII_FIT_TRAINER("Wii Fit Trainer", SeriesSymbols.WII_FIT),
    ROSALINA_AND_LUMA("Rosalina & Luma", SeriesSymbols.SUPER_MARIO),
    LITTLE_MAC("Little Mac", SeriesSymbols.PUNCH_OUT),
    GRENINJA("Greninja", SeriesSymbols.POKEMON),
    PALUTENA("Palutena", SeriesSymbols.KID_ICARUS),
    PAC_MAN("Pac-Man", SeriesSymbols.PAC_MAN),
    ROBIN("Robin", SeriesSymbols.FIRE_EMBLEM),
    SHULK("Shulk", SeriesSymbols.XENOBLADE_CHRONICLES),
    BOWSER_JR("Bowser Jr.", SeriesSymbols.SUPER_MARIO),
    DUCK_HUNT("Duck Hunt", SeriesSymbols.DUCK_HUNT),
    RYU("Ryu", SeriesSymbols.STREET_FIGHTER),
    KEN("Ken", SeriesSymbols.STREET_FIGHTER),
    CLOUD("Cloud", SeriesSymbols.FINAL_FANTASY),
    CORRIN("Corrin", SeriesSymbols.FIRE_EMBLEM),
    BAYONETTA("Bayonetta", SeriesSymbols.BAYONETTA),
    INKLING("Inkling", SeriesSymbols.SPLATOON),
    RIDLEY("Ridley", SeriesSymbols.METROID),
    SIMON("Simon", SeriesSymbols.CASTLEVANIA),
    RICHTER("Richter", SeriesSymbols.CASTLEVANIA),
    KING_K_ROOL("King K. Rool", SeriesSymbols.DONKEY_KONG),
    ISABELLE("Isabelle", SeriesSymbols.ANIMAL_CROSSING),
    INCINEROAR("Incineroar", SeriesSymbols.POKEMON),
    PIRANHA_PLANT("Piranha Plant", SeriesSymbols.SUPER_MARIO),
    JOKER("Joker", SeriesSymbols.PERSONA),
    HERO("Hero", SeriesSymbols.DRAGON_QUEST),
    BANJO_AND_KAZOOIE("Banjo & Kazooie", SeriesSymbols.BANJO_KAZOOIE),
    TERRY("Terry", SeriesSymbols.FATAL_FURY),
    BYLETH("Byleth", SeriesSymbols.FIRE_EMBLEM),
    MIN_MIN("Min Min", SeriesSymbols.ARMS),
    STEVE("Steve", SeriesSymbols.MINECRAFT),
    SEPHIROTH("Sephiroth", SeriesSymbols.FINAL_FANTASY),
    PYRA_AND_MYTHRA("Pyra & Mythra", SeriesSymbols.XENOBLADE_CHRONICLES),
    KAZUYA("Kazuya", SeriesSymbols.TEKKEN),
    SORA("Sora", SeriesSymbols.KINGDOM_HEARTS),
    MII_BRAWLER("Mii Brawler", SeriesSymbols.SUPER_SMASH_BROS),
    MII_SWORDFIGHTER("Mii Swordfighter", SeriesSymbols.SUPER_SMASH_BROS),
    MII_GUNNER("Mii Gunner", SeriesSymbols.SUPER_SMASH_BROS);

    private final String name;
    private final String symbol;

    Character(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Image getRender() {
        return new Image("/resources/renders/" + name + ".png");
    }

    public Image getSeriesSymbol() {
        return new Image("/resources/symbols/" + symbol + ".png");
    }

}