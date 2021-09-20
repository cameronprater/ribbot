package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.ConstellationActivationMaterial;

import java.util.List;

public class Constellation {
    private final Element element;
    private final ConstellationActivationMaterial activationMaterial;
    private final List<ConstellationLevel> levels;
    private final List<TalentLevelUp> talentLevelUps;

    public Constellation(Element element, ConstellationActivationMaterial activationMaterial, List<ConstellationLevel> levels, List<TalentLevelUp> talentLevelUps) {
        this.element = element;
        this.activationMaterial = activationMaterial;
        this.levels = levels;
        this.talentLevelUps = talentLevelUps;
    }

    public Element getElement() {
        return element;
    }

    public ConstellationActivationMaterial getActivationMaterial() {
        return activationMaterial;
    }

    public List<ConstellationLevel> getLevels() {
        return levels;
    }

    public List<TalentLevelUp> getTalentLevels() {
        return talentLevelUps;
    }
}
