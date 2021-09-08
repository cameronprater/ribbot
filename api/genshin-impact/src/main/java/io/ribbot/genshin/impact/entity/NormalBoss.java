package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.NormalBossDrop;

import java.util.List;

public class NormalBoss {
    private final String name;
    private final Nation nation;
    private final List<NormalBossDrop> drops;

    public NormalBoss(String name, Nation nation, List<NormalBossDrop> drops) {
        this.name = name;
        this.nation = nation;
        this.drops = drops;
    }

    public String getName() {
        return name;
    }

    public Nation getNation() {
        return nation;
    }

    public List<NormalBossDrop> getDrops() {
        return drops;
    }
}
