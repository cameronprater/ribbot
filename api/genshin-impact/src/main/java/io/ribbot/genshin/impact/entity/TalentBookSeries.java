package io.ribbot.genshin.impact.entity;

import java.time.DayOfWeek;

public class TalentBookSeries {
    private final Name name;
    private final DayOfWeek weekdayOne;
    private final DayOfWeek weekdayTwo;

    public TalentBookSeries(Name name, DayOfWeek weekdayOne, DayOfWeek weekdayTwo) {
        this.name = name;
        this.weekdayOne = weekdayOne;
        this.weekdayTwo = weekdayTwo;
    }

    public Name getName() {
        return name;
    }

    public DayOfWeek getWeekdayOne() {
        return weekdayOne;
    }

    public DayOfWeek getWeekdayTwo() {
        return weekdayTwo;
    }

    public enum Name {
        FREEDOM("Freedom"),
        RESISTANCE("Resistance"),
        BALLAD("Ballad"),
        PROSPERITY("Prosperity"),
        DILIGENCE("Diligence"),
        GOLD("Gold"),
        TRANSIENCE("Transience"),
        ELEGANCE("Elegance"),
        LIGHT("Light");

        private final String value;

        Name(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
