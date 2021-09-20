package io.ribbot.genshin.impact.entity;

import java.util.List;

public class CommonMaterialType {
    private final Name name;
    private final List<EnemyType> droppedBy;

    public CommonMaterialType(Name name, List<EnemyType> droppedBy) {
        this.name = name;
        this.droppedBy = droppedBy;
    }

    public Name getName() {
        return name;
    }

    public List<EnemyType> getDroppedBy() {
        return droppedBy;
    }

    public enum Name {
        SLIME("Slime"),
        MASK("Mask"),
        SCROLL("Scroll"),
        ARROWHEAD("Arrowhead"),
        FATUI_INSIGNIA("Fatui Insignia"),
        TREASURE_HOARDER_INSIGNIA("Treasure Hoarder Insignia"),
        NECTAR("Nectar"),
        HANDGUARD("Handguard"),
        SPECTRAL("Spectral");

        private final String value;

        Name(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
