package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.CommonAscensionMaterial;

import java.util.List;

public class CommonEnemy {
    private final EnemyType type;
    private final String name;
    private final List<CommonAscensionMaterial> drops;

    public CommonEnemy(EnemyType type, String name, List<CommonAscensionMaterial> drops) {
        this.type = type;
        this.name = name;
        this.drops = drops;
    }

    public EnemyType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<CommonAscensionMaterial> getDrops() {
        return drops;
    }
}
