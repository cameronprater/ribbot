package io.ribbot.genshin.impact.entity;

public class Talent {
    private final String name;
    private final TalentType type;
    private final String info;

    public Talent(String name, TalentType type, String info) {
        this.name = name;
        this.type = type;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public TalentType getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}
