package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.Rarity;
import io.smallrye.common.constraint.Nullable;

public abstract class Material {
    private final String name;
    private final Rarity rarity;

    public Material(String name, @Nullable Rarity rarity) {
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
