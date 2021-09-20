package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.CommonMaterialType;
import io.ribbot.genshin.impact.entity.EnemyType;
import io.ribbot.genshin.impact.entity.Rarity;

import java.util.List;

public class CommonMaterial extends Material {
    private final CommonMaterialType type;
    private final List<EnemyType> droppedBy;

    public CommonMaterial(String name, Rarity rarity, CommonMaterialType type, List<EnemyType> droppedBy) {
        super(name, rarity);
        this.type = type;
        this.droppedBy = droppedBy;
    }

    public CommonMaterialType getType() {
        return type;
    }

    public List<EnemyType> getDroppedBy() {
        return droppedBy;
    }
}
