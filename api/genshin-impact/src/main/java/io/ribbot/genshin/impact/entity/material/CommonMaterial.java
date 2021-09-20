package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.CommonMaterialType;
import io.ribbot.genshin.impact.entity.Rarity;

public class CommonMaterial extends Material {
    private final CommonMaterialType type;

    public CommonMaterial(String name, Rarity rarity, CommonMaterialType type) {
        super(name, rarity);
        this.type = type;
    }

    public CommonMaterialType getType() {
        return type;
    }
}
