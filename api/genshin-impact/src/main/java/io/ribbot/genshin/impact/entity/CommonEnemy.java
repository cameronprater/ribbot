package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.CommonMaterial;
import io.smallrye.common.constraint.Nullable;

import java.util.List;

public class CommonEnemy {
    private final EnemyType type;
    private final String name;
    private final EnemyNamingStrategy namingStrategy;
    private final List<CommonMaterial> drops;

    public CommonEnemy(EnemyType type, @Nullable String name, @Nullable EnemyNamingStrategy namingStrategy,
                       List<CommonMaterial> drops) {
        this.type = type;
        this.name = name;
        this.namingStrategy = namingStrategy;
        this.drops = drops;
    }

    public EnemyType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public EnemyNamingStrategy getNamingStrategy() {
        return namingStrategy;
    }

    public List<CommonMaterial> getDrops() {
        return drops;
    }
}
