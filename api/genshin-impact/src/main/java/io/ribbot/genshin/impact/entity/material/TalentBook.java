package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.Rarity;

import java.time.DayOfWeek;

public class TalentBook extends Material {
    private final DayOfWeek weekdayOne;
    private final DayOfWeek weekdayTwo;

    public TalentBook(String name, Rarity rarity, DayOfWeek weekdayOne, DayOfWeek weekdayTwo) {
        super(name, rarity);
        this.weekdayOne = weekdayOne;
        this.weekdayTwo = weekdayTwo;
    }

    public DayOfWeek getWeekdayOne() {
        return weekdayOne;
    }

    public DayOfWeek getWeekdayTwo() {
        return weekdayTwo;
    }
}
