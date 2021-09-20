package io.ribbot.genshin.impact.entity;

import java.time.DayOfWeek;

public class TalentBookSeries {
    private final Name name;
    private final Domain domain;
    private final DayOfWeek weekdayOne;
    private final DayOfWeek weekdayTwo;

    public TalentBookSeries(Name name, Domain domain, DayOfWeek weekdayOne, DayOfWeek weekdayTwo) {
        this.name = name;
        this.domain = domain;
        this.weekdayOne = weekdayOne;
        this.weekdayTwo = weekdayTwo;
    }

    public Name getName() {
        return name;
    }

    public Domain getDomain() {
        return domain;
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
