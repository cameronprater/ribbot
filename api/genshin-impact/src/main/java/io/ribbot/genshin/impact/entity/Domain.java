package io.ribbot.genshin.impact.entity;

import java.util.List;

public class Domain {
    private final String name;
    private final Nation nation;
    private final List<TalentBookSeries> talentBookSeries;

    public Domain(String name, Nation nation, List<TalentBookSeries> talentBookSeries) {
        this.name = name;
        this.nation = nation;
        this.talentBookSeries = talentBookSeries;
    }

    public String getName() {
        return name;
    }

    public Nation getNation() {
        return nation;
    }

    public List<TalentBookSeries> getTalentBooks() {
        return talentBookSeries;
    }
}
