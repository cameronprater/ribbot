package io.ribbot.genshin.impact.entity;

public enum EnemyType {
    ;

    private final String name;
    private final EnemyNamingStrategy namingStrategy;

    EnemyType(String name, EnemyNamingStrategy namingStrategy) {
        this.name = name;
        this.namingStrategy = namingStrategy;
    }

    public String getName() {
        return name;
    }

    public EnemyNamingStrategy getNamingStrategy() {
        return namingStrategy;
    }
}
