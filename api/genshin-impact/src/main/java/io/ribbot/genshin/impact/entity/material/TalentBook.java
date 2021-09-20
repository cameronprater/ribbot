package io.ribbot.genshin.impact.entity.material;

import io.ribbot.genshin.impact.entity.Rarity;
import io.ribbot.genshin.impact.entity.TalentBookSeries;
import io.ribbot.genshin.impact.entity.TalentBookType;

public class TalentBook extends Material {
    private final TalentBookType type;
    private final TalentBookSeries series;

    public TalentBook(String name, Rarity rarity, TalentBookType type, TalentBookSeries series) {
        super(name, rarity);
        this.type = type;
        this.series = series;
    }

    public TalentBookType getType() {
        return type;
    }

    public TalentBookSeries getSeries() {
        return series;
    }
}
