package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.Rarity;

import java.time.DayOfWeek;

public class TalentBook extends Material {
    private final DayOfWeek weekdayOne;
    private final DayOfWeek weekdayTwo;
    private final DayOfWeek weekdayThree;

    public TalentBook(String name, Rarity rarity, DayOfWeek weekdayOne, DayOfWeek weekdayTwo, DayOfWeek weekdayThree) {
        super(name, rarity);
        this.weekdayOne = weekdayOne;
        this.weekdayTwo = weekdayTwo;
        this.weekdayThree = weekdayThree;
    }

    public DayOfWeek getWeekdayOne() {
        return weekdayOne;
    }

    public DayOfWeek getWeekdayTwo() {
        return weekdayTwo;
    }

    public DayOfWeek getWeekdayThree() {
        return weekdayThree;
    }
}
