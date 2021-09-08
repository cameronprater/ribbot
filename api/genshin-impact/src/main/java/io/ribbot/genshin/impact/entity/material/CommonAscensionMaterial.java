package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.CommonEnemy;
import io.ribbot.genshin.impact.entity.Rarity;

import java.util.List;

public class CommonAscensionMaterial extends Material {
    private final List<CommonEnemy> droppedBy;

    public CommonAscensionMaterial(String name, Rarity rarity, List<CommonEnemy> droppedBy) {
        super(name, rarity);
        this.droppedBy = droppedBy;
    }

    public List<CommonEnemy> getDroppedBy() {
        return droppedBy;
    }
}
