package io.ribbot.genshin.impact.entity;

public enum TalentType {
    NORMAL_ATTACK("Normal Attack"),
    ELEMENTAL_SKILL("Elemental Skill"),
    ELEMENTAL_BURST("Elemental Burst"),
    ALTERNATE_SPRINT("Alternate Sprint"),
    FIRST_ASCENSION_PASSIVE("1st Ascension Passive"),
    FOURTH_ASCENSION_PASSIVE("4th Ascension Passive"),
    UTILITY_PASSIVE("Utility Passive");

    private final String name;

    TalentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
