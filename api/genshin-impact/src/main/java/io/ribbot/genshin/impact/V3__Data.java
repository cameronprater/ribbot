package io.ribbot.genshin.impact;

import io.ribbot.genshin.impact.entity.*;
import io.ribbot.genshin.impact.entity.Nation.Name;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class V3__Data extends BaseJavaMigration {

    private void insertRarities(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO rarity VALUES (?)")) {
            for (Rarity rarity : Rarity.values()) {
                statement.setInt(1, rarity.getStars());
                statement.executeUpdate();
            }
        }
    }

    private void insertElements(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO element VALUES (?)")) {
            for (Element element : Element.values()) {
                statement.setString(1, element.getName());
                statement.executeUpdate();
            }
        }
    }

    private void insertWeaponTypes(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO weapon_type VALUES (?)")) {
            for (WeaponType weaponType : WeaponType.values()) {
                statement.setString(1, weaponType.getName());
                statement.executeUpdate();
            }
        }
    }

    private void insertNations(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO nation VALUES (?)")) {
            for (Name name : Name.values()) {
                statement.setString(1, name.getValue());
                statement.executeUpdate();
            }
        }
    }

    private void insertTalentTypes(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO talent_type VALUES (?)")) {
            for (TalentType talentType : TalentType.values()) {
                statement.setString(1, talentType.getName());
                statement.executeUpdate();
            }
        }
    }

    private void insertAscensionGems(Context context) throws Exception {
        Map<AscensionGemSize, Optional<?>> inserted = new HashMap<>();
        for (AscensionGemType ascensionGemType : AscensionGemType.values()) {
            try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem_type VALUES (?, ?)")) {
                statement.setString(1, ascensionGemType.getName());
                statement.setString(2, Optional.ofNullable(ascensionGemType.getElement()).map(Element::getName).orElse(null));
                statement.executeUpdate();
            }
            for (AscensionGemSize ascensionGemSize : AscensionGemSize.values()) {
                inserted.computeIfAbsent(ascensionGemSize, x -> {
                    try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem_size VALUES (?)")) {
                        statement.setString(1, ascensionGemSize.getName());
                        statement.executeUpdate();
                        return Optional.empty();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem VALUES (?, ?, ?)")) {
                    statement.setString(1, ascensionGemType.getName());
                    statement.setString(2, ascensionGemSize.getName());
                    statement.setString(3, ascensionGemType.getName() + ' ' + ascensionGemSize.getName());
                    statement.executeUpdate();
                }
            }
        }
    }

    private void insertEnemyTypes(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO enemy_type VALUES (?)")) {
            for (EnemyType enemyType : EnemyType.values()) {
                statement.setString(1, enemyType.getName());
                statement.executeUpdate();
            }
        }
    }

    private void insertNamingStrategies(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO enemy_naming_strategy VALUES (?)")) {
            for (EnemyNamingStrategy enemyNamingStrategy : EnemyNamingStrategy.values()) {
                statement.setString(1, enemyNamingStrategy.name());
                statement.executeUpdate();
            }
        }
    }

    private void insertCommonMaterialTypes(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO common_material_type VALUES (?)")) {
            for (CommonMaterialType commonMaterialType : CommonMaterialType.values()) {
                statement.setString(1, commonMaterialType.getName());
                statement.executeUpdate();
            }
        }
    }

    private void insertWeekdays(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO weekday VALUES (?)")) {
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                statement.setString(1, dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));
                statement.executeUpdate();
            }
        }
    }

    private void insertTalentBooks(Context context) throws Exception {
        for (TalentBookType talentBookType : TalentBookType.values()) {
            try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO talent_book_type VALUES (?)")) {
                statement.setString(1, talentBookType.getName());
                statement.executeUpdate();
            }
            try (ResultSet rows = context.getConnection().createStatement().executeQuery("SELECT DISTINCT name FROM talent_book_series")) {
                while (rows.next()) {
                    try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO talent_book VALUES (?, ?, ?)")) {
                        statement.setString(1, talentBookType.getName());
                        statement.setString(2, rows.getString(1));
                        statement.setString(3, talentBookType.getName() + ' ' + rows.getString(1));
                        statement.executeUpdate();
                    }
                }
            }
        }
    }

    private void insertLumineData(Context context) throws Exception {
        // insert talents
        // insert constellations
        // insert constellation levels
        // insert ascension
        // insert talent level ups
    }

    private void insertCommonEnemies(Context context) throws Exception {
        for (Element element : Element.values()) {
            try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO common_enemy VALUES (?, ?, ?)")) {
                // slimes
                statement.setString(1, EnemyType.SLIME.getName());
                statement.setString(2, element.getName());
                statement.setString(3, EnemyNamingStrategy.BEFORE.getName());
                statement.executeUpdate();
                // large slimes
                statement.setString(1, EnemyType.LARGE_SLIME.getName());
                statement.setString(2, element.getName());
                statement.setString(3, EnemyNamingStrategy.BETWEEN.getName());
                statement.executeUpdate();
                // samachurls
                statement.setString(1, EnemyType.SAMACHURL.getName());
                statement.setString(2, element.getName());
                statement.setString(3, EnemyNamingStrategy.BEFORE.getName());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void migrate(Context context) throws Exception {
        insertRarities(context);
        insertElements(context);
        insertWeaponTypes(context);
        insertNations(context);
        insertTalentTypes(context);
        insertAscensionGems(context);
        insertEnemyTypes(context);
        insertNamingStrategies(context);
        insertCommonMaterialTypes(context);
        insertWeekdays(context);
        insertTalentBooks(context);
        insertLumineData(context);
        insertCommonEnemies(context);
    }
}
