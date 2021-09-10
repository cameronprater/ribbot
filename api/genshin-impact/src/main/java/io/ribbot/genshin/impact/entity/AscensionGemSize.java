package io.ribbot.genshin.impact.entity;

public enum AscensionGemSize {
    SLIVER("Sliver"),
    FRAGMENT("Fragment"),
    CHUNK("Chunk"),
    GEMSTONE("Gemstone");

    private final String name;

    AscensionGemSize(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
