package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.Rarity;

public abstract class Material {
    private final String name;
    private final Rarity rarity;

    public Material(String name, Rarity rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
