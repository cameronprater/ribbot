package io.ribbot.genshin.impact.entity;

public class ConstellationLevel {
    private final String name;
    private final int level;
    private final String effect;

    public ConstellationLevel(String name, int level, String effect) {
        this.name = name;
        this.level = level;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getEffect() {
        return effect;
    }
}
