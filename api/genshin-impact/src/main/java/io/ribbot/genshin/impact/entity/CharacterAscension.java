package io.ribbot.genshin.impact.entity;

import io.ribbot.genshin.impact.entity.material.AscensionGem;
import io.ribbot.genshin.impact.entity.material.CommonMaterial;
import io.ribbot.genshin.impact.entity.material.LocalSpecialty;
import io.ribbot.genshin.impact.entity.material.NormalBossDrop;
import io.smallrye.common.constraint.Nullable;

public class CharacterAscension {
    private final int phase;
    private final int mora;
    private final AscensionGem ascensionGem;
    private final int ascensionGemQuantity;
    private final NormalBossDrop normalBossDrop;
    private final int normalBossDropQuantity;
    private final LocalSpecialty localSpecialty;
    private final int localSpecialtyQuantity;
    private final CommonMaterial commonMaterial;
    private final int commonMaterialQuantity;

    public CharacterAscension(int phase, int mora, AscensionGem ascensionGem, int ascensionGemQuantity, @Nullable NormalBossDrop normalBossDrop, int normalBossDropQuantity, LocalSpecialty localSpecialty, int localSpecialtyQuantity, CommonMaterial commonMaterial, int commonMaterialQuantity) {
        this.phase = phase;
        this.mora = mora;
        this.ascensionGem = ascensionGem;
        this.ascensionGemQuantity = ascensionGemQuantity;
        this.normalBossDrop = normalBossDrop;
        this.normalBossDropQuantity = normalBossDropQuantity;
        this.localSpecialty = localSpecialty;
        this.localSpecialtyQuantity = localSpecialtyQuantity;
        this.commonMaterial = commonMaterial;
        this.commonMaterialQuantity = commonMaterialQuantity;
    }

    public int getPhase() {
        return phase;
    }

    public int getMora() {
        return mora;
    }

    public AscensionGem getAscensionGem() {
        return ascensionGem;
    }

    public int getAscensionGemQuantity() {
        return ascensionGemQuantity;
    }

    public NormalBossDrop getNormalBossDrop() {
        return normalBossDrop;
    }

    public int getNormalBossDropQuantity() {
        return normalBossDropQuantity;
    }

    public LocalSpecialty getLocalSpecialty() {
        return localSpecialty;
    }

    public int getLocalSpecialtyQuantity() {
        return localSpecialtyQuantity;
    }

    public CommonMaterial getCommonMaterial() {
        return commonMaterial;
    }

    public int getCommonMaterialQuantity() {
        return commonMaterialQuantity;
    }
}
