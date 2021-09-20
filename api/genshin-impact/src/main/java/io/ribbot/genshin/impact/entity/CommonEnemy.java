package io.ribbot.genshin.impact.entity;

import io.smallrye.common.constraint.Nullable;

public class CommonEnemy {
    private final EnemyType type;
    private final String name;
    private final EnemyNamingStrategy namingStrategy;

    public CommonEnemy(EnemyType type, @Nullable String name, @Nullable EnemyNamingStrategy namingStrategy) {
        this.type = type;
        this.name = name;
        this.namingStrategy = namingStrategy;
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
}
