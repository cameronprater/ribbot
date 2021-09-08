package io.ribbot.genshin.entity;

import io.smallrye.common.constraint.Nullable;

public class Character {
    private final String name;
    private final Rarity rarity;
    private final Element element;
    private final WeaponType weaponType;
    private final Sex sex;
    private final Nation nation;

    private Character(String name, @Nullable Rarity rarity, @Nullable Element element, @Nullable WeaponType weaponType, Sex sex, @Nullable Nation nation) {
        this.name = name;
        this.rarity = rarity;
        this.element = element;
        this.weaponType = weaponType;
        this.sex = sex;
        this.nation = nation;
    }

    public Character(String name, Sex sex) {
        this(name, null, null, null, sex, null);
    }

    public Character withRarity(Rarity rarity) {
        return new Character(name, rarity, element, weaponType, sex, nation);
    }

    public Character withElement(Element element) {
        return new Character(name, rarity, element, weaponType, sex, nation);
    }

    public Character withWeaponType(WeaponType weaponType) {
        return new Character(name, rarity, element, weaponType, sex, nation);
    }

    public Character withNation(Nation nation) {
        return new Character(name, rarity, element, weaponType, sex, nation);
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
