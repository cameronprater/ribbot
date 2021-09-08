package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.TalentBook;

import java.util.List;

public class Domain {
    private final String name;
    private final Nation nation;
    private final List<TalentBook> talentBooks;

    public Domain(String name, Nation nation, List<TalentBook> talentBooks) {
        this.name = name;
        this.nation = nation;
        this.talentBooks = talentBooks;
    }

    public String getName() {
        return name;
    }

    public Nation getNation() {
        return nation;
    }

    public List<TalentBook> getTalentBooks() {
        return talentBooks;
    }
}
