package io.ribbot.genshin.impact.entity;

public enum EnemyType {
    SLIME("Slime"),
    LARGE_SLIME("Large Slime"),
    WHOPPERFLOWER("Whopperflower"),
    SPECTER("Specter"),
    HILICHURL("Hilichurl"),
    HILICHURL_GUARD("Hilichurl Guard"),
    HILICHURL_GRENADIER("Hilichurl Grenadier"),
    HILICHURL_SHOOTER("Hilichurl Shooter"),
    SAMACHURL("Samachurl"),
    FATUI_VANGUARD("Fatui Vanguard"),
    FATUI_BRACER("Fatui Bracer"),
    FATUI_LEGIONNAIRE("Fatui Legionnaire"),
    TREASURE_HOARDER("Treasure Hoarder"),
    TREASURE_HOARDER_POTIONEER("Treasure Hoarder Potioneer"),
    NOBUSHI("Nobushi"),
    KAIRAGI("Kairagi");

    private final String name;

    EnemyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
