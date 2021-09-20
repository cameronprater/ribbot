package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.LocalSpecialty;

import java.util.List;

public class Nation {
    private final Name name;
    private final List<LocalSpecialty> specialties;

    public Nation(Name name, List<LocalSpecialty> specialties) {
        this.name = name;
        this.specialties = specialties;
    }

    public Name getName() {
        return name;
    }

    public List<LocalSpecialty> getSpecialties() {
        return specialties;
    }

    public enum Name {
        MONDSTADT("Mondstadt"),
        LIYUE("Liyue"),
        INAZUMA("Inazuma"),
        SUMERU("Sumeru"),
        FONTAINE("Fontaine"),
        NATLAN("Natlan"),
        SNEZHNAYA("Snezhnaya"),
        KHAENRIAH("Khaenri'ah");

        private final String value;

        Name(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
