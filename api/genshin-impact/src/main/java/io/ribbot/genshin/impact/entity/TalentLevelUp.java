package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.CommonMaterial;
import io.ribbot.genshin.impact.entity.material.TalentBook;
import io.ribbot.genshin.impact.entity.material.WeeklyBossDrop;

public class TalentLevelUp {
    private final int level;
    private final int mora;
    private final CommonMaterial commonMaterial;
    private final int commonMaterialQuantity;
    private final TalentBook talentBook;
    private final int talentBookQuantity;
    private final WeeklyBossDrop weeklyBossDrop;
    private final int weeklyBossDropQuantity;
    private final boolean crownOfInsight;

    public TalentLevelUp(int level, int mora, CommonMaterial commonMaterial, int commonMaterialQuantity, TalentBook talentBook, int talentBookQuantity, WeeklyBossDrop weeklyBossDrop, int weeklyBossDropQuantity, boolean crownOfInsight) {
        this.level = level;
        this.mora = mora;
        this.commonMaterial = commonMaterial;
        this.commonMaterialQuantity = commonMaterialQuantity;
        this.talentBook = talentBook;
        this.talentBookQuantity = talentBookQuantity;
        this.weeklyBossDrop = weeklyBossDrop;
        this.weeklyBossDropQuantity = weeklyBossDropQuantity;
        this.crownOfInsight = crownOfInsight;
    }

    public int getLevel() {
        return level;
    }

    public int getMora() {
        return mora;
    }

    public CommonMaterial getCommonMaterial() {
        return commonMaterial;
    }

    public int getCommonMaterialQuantity() {
        return commonMaterialQuantity;
    }

    public TalentBook getTalentBook() {
        return talentBook;
    }

    public int getTalentBookQuantity() {
        return talentBookQuantity;
    }

    public WeeklyBossDrop getWeeklyBossDrop() {
        return weeklyBossDrop;
    }

    public int getWeeklyBossDropQuantity() {
        return weeklyBossDropQuantity;
    }

    public boolean requiresCrownOfInsight() {
        return crownOfInsight;
    }
}
