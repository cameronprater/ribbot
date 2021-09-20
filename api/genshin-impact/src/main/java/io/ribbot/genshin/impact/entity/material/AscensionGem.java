package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.AscensionGemSize;
import io.ribbot.genshin.impact.entity.AscensionGemType;
import io.ribbot.genshin.impact.entity.Rarity;

public class AscensionGem extends Material {
    private final AscensionGemType type;
    private final AscensionGemSize size;

    public AscensionGem(String name, Rarity rarity, AscensionGemType type, AscensionGemSize size) {
        super(name, rarity);
        this.type = type;
        this.size = size;
    }

    public AscensionGemType getType() {
        return type;
    }

    public AscensionGemSize getSize() {
        return size;
    }
}
