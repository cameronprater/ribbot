package io.ribbot.genshin.impact.entity;

public enum EnemyNamingStrategy {
    AFTER("After"),
    AFTER_COLON("After Colon"),
    BEFORE("Before"),
    BETWEEN("Between");

    private final String name;

    EnemyNamingStrategy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
