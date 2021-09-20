package io.ribbot.genshin.impact.entity;

import io.smallrye.common.constraint.Nullable;

import java.util.List;

public class Character {
    private final String name;
    private final Rarity rarity;
    private final WeaponType weaponType;
    private final Sex sex;
    private final Nation nation;
    private final List<Constellation> constellations;
    private final List<CharacterAscensionPhase> ascensions;

    public Character(String name, Rarity rarity, WeaponType weaponType, Sex sex, @Nullable Nation nation,
            List<Constellation> constellations, List<CharacterAscensionPhase> ascensions) {
        this.name = name;
        this.rarity = rarity;
        this.weaponType = weaponType;
        this.sex = sex;
        this.nation = nation;
        this.constellations = constellations;
        this.ascensions = ascensions;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
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

    public List<Constellation> getConstellations() {
        return constellations;
    }

    public List<CharacterAscensionPhase> getAscensions() {
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
