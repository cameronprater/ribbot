package io.ribbot.genshin.impact.entity;

public enum WeaponType {
    SWORD("Sword"),
    CLAYMORE("Claymore"),
    POLEARM("Polearm"),
    CATALYST("Catalyst"),
    BOW("Bow");

    private final String name;

    WeaponType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
