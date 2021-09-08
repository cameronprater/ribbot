package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.WeeklyBossDrop;

import java.util.List;

public class WeeklyBoss {
    private final String name;
    private final Domain domain;
    private final List<WeeklyBossDrop> drops;

    public WeeklyBoss(String name, Domain domain, List<WeeklyBossDrop> drops) {
        this.name = name;
        this.domain = domain;
        this.drops = drops;
    }

    public String getName() {
        return name;
    }

    public Domain getDomain() {
        return domain;
    }

    public List<WeeklyBossDrop> getDrops() {
        return drops;
    }
}
