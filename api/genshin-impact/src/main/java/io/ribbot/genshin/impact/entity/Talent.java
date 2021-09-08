package io.ribbot.genshin.impact.entity;

import java.util.List;

public class Talent {
    private final String name;
    private final TalentType talentType;
    private final String info;
    private final List<TalentLevel> levels;

    public Talent(String name, TalentType talentType, String info, List<TalentLevel> levels) {
        this.name = name;
        this.talentType = talentType;
        this.info = info;
        this.levels = levels;
    }

    public String getName() {
        return name;
    }

    public TalentType getTalentType() {
        return talentType;
    }

    public String getInfo() {
        return info;
    }

    public List<TalentLevel> getLevels() {
        return levels;
    }
}
