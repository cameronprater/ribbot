package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.Element;
import io.ribbot.genshin.impact.entity.Rarity;
import io.smallrye.common.constraint.Nullable;

public class AscensionGem extends Material {
    private final Element element;

    public AscensionGem(String name, Rarity rarity, @Nullable Element element) {
        super(name, rarity);
        this.element = element;
    }

    public Element getElement() {
        return element;
    }
}
