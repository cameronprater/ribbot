package io.ribbot.genshin.impact.entity;

public enum Element {
    PYRO("Pyro"),
    HYDRO("Hyrdo"),
    ANEMO("Anemo"),
    ELECTRO("Electro"),
    DENDRO("Dendro"),
    CRYO("Cryo"),
    GEO("Geo");

    private final String name;

    Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
