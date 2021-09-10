package io.ribbot.genshin.impact.entity;

public enum TalentBookType {
    TEACHINGS("Teachings"),
    GUIDE("Guide"),
    PHILOSOPHIES("Philosophies");

    private final String name;

    TalentBookType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
