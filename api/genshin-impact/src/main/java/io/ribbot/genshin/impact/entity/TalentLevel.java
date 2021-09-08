package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.CommonAscensionMaterial;
import io.ribbot.genshin.impact.entity.material.TalentBook;
import io.ribbot.genshin.impact.entity.material.WeeklyBossDrop;
import io.smallrye.common.constraint.Nullable;

public class TalentLevel {
    private final int level;
    private final int mora;
    private final int ascensionPhase;
    private final CommonAscensionMaterial commonMaterial;
    private final int commonMaterialQuantity;
    private final TalentBook talentBook;
    private final int talentBookQuantity;
    private final WeeklyBossDrop weeklyBossDrop;
    private final int weeklyBossDropQuantity;
    private final boolean crownOfInsight;

    public TalentLevel(int level, int mora, int ascensionPhase, CommonAscensionMaterial commonMaterial,
                       int commonMaterialQuantity, TalentBook talentBook, int talentBookQuantity,
                       @Nullable WeeklyBossDrop weeklyBossDrop, @Nullable int weeklyBossDropQuantity, boolean crownOfInsight) {
        this.level = level;
        this.mora = mora;
        this.ascensionPhase = ascensionPhase;
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

    public int getAscensionPhase() {
        return ascensionPhase;
    }

    public CommonAscensionMaterial getCommonMaterial() {
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

    public boolean needsCrownOfInsight() {
        return crownOfInsight;
    }
}
