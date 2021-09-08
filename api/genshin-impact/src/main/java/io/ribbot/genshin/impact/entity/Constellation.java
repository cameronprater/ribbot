package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.ConstellationActivationMaterial;

public class Constellation {
    private final String name;
    private final int level;
    private final String effect;
    private final ConstellationActivationMaterial activationMaterial;

    public Constellation(String name, int level, String effect, ConstellationActivationMaterial activationMaterial) {
        this.name = name;
        this.level = level;
        this.effect = effect;
        this.activationMaterial = activationMaterial;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getEffect() {
        return effect;
    }

    public ConstellationActivationMaterial getActivationMaterial() {
        return activationMaterial;
    }
}
