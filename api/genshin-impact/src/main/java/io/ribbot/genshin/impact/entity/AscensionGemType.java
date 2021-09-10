package io.ribbot.genshin.impact.entity;

public enum AscensionGemType {
    BRILLIANT_DIAMOND("Brilliant Diamond"),
    AGNIDUS_AGATE("Agnidus Agate"),
    VARUNADA_LAZURITE("Varunda Lazurite"),
    VAJRADA_AMETHYST("Vajrada Amethyst"),
    VAYUDA_TURQUOISE("Vayuda Turquoise"),
    SHIVADA_JADE("Shivada Jade"),
    PRITHIVA_TOPAZ("Prithiva Topaz");

    private final String name;

    AscensionGemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
