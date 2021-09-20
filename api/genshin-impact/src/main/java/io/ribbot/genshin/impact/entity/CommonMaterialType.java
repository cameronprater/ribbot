package io.ribbot.genshin.impact.entity;

public enum CommonMaterialType {
    SLIME("Slime"),
    MASK("Mask"),
    SCROLL("Scroll"),
    ARROWHEAD("Arrowhead"),
    FATUI_INSIGNIA("Fatui Insignia"),
    TREASURE_HOARDER_INSIGNIA("Treasure Hoarder Insignia"),
    NECTAR("Nectar"),
    HANDGUARD("Handguard"),
    SPECTRAL("Spectral");

    private final String name;

    CommonMaterialType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
