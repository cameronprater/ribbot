package io.ribbot.genshin.impact.entity;

import io.smallrye.common.constraint.Nullable;

import java.util.List;

public class Character {
    private final String name;
    private final Rarity rarity;
    private final Element element;
    private final WeaponType weaponType;
    private final Sex sex;
    private final Nation nation;
    private final List<Talent> talents;
    private final List<Constellation> constellations;
    private final List<CharacterAscension> ascensions;

    public Character(String name, Rarity rarity, Element element, WeaponType weaponType, Sex sex,
            @Nullable Nation nation, List<Talent> talents, List<Constellation> constellations,
            List<CharacterAscension> ascensions) {
        this.name = name;
        this.rarity = rarity;
        this.element = element;
        this.weaponType = weaponType;
        this.sex = sex;
        this.nation = nation;
        this.talents = talents;
        this.constellations = constellations;
        this.ascensions = ascensions;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Element getElement() {
        return element;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Sex getSex() {
        return sex;
    }

    public Nation getNation() {
        return nation;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public List<CharacterAscension> getAscensions() {
        return ascensions;
    }

    public enum Sex {
        MALE("Male"),
        FEMALE("Female");

        private final String value;

        Sex(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
