package io.ribbot.genshin.impact.entity;

import java.util.List;

public class EnemyType {
    private final Name name;
    private final List<CommonMaterialType> drops;

    public EnemyType(Name name, List<CommonMaterialType> drops) {
        this.name = name;
        this.drops = drops;
    }

    public Name getName() {
        return name;
    }

    public List<CommonMaterialType> getDrops() {
        return drops;
    }

    public enum Name {
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

        private final String value;

        Name(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
