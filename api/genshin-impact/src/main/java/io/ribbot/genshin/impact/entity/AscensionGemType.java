package io.ribbot.genshin.impact.entity;

import io.smallrye.common.constraint.Nullable;

public enum AscensionGemType {
    BRILLIANT_DIAMOND("Brilliant Diamond", null),
    AGNIDUS_AGATE("Agnidus Agate", Element.PYRO),
    VARUNADA_LAZURITE("Varunda Lazurite", Element.HYDRO),
    VAJRADA_AMETHYST("Vajrada Amethyst", Element.ELECTRO),
    VAYUDA_TURQUOISE("Vayuda Turquoise", Element.ANEMO),
    SHIVADA_JADE("Shivada Jade", Element.CRYO),
    PRITHIVA_TOPAZ("Prithiva Topaz", Element.GEO);

    private final String name;
    private final Element element;

    AscensionGemType(String name, @Nullable Element element) {
        this.name = name;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public Element getElement() {
        return element;
    }
}
