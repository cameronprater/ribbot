package io.ribbot.genshin.impact;

import io.ribbot.genshin.impact.entity.*;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

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
            for (Nation.Name nationName : Nation.Name.values()) {
                statement.setString(1, nationName.getValue());
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

    private void insertEnemyTypes(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO enemy_type VALUES (?)")) {
            for (EnemyType.Name enemyTypeName : EnemyType.Name.values()) {
                statement.setString(1, enemyTypeName.getValue());
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
            for (CommonMaterialType.Name commonMaterialTypeName : CommonMaterialType.Name.values()) {
                statement.setString(1, commonMaterialTypeName.getValue());
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

    private void insertCommonEnemies(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO common_enemy VALUES (?, ?, ?)")) {
            for (Element element : Element.values()) {
                String elementName = element.getName();
                // slimes
                statement.setString(1, EnemyType.Name.SLIME.getValue());
                statement.setString(2, elementName);
                statement.setString(3, EnemyNamingStrategy.BEFORE.getName());
                statement.executeUpdate();
                // large slimes
                statement.setString(1, EnemyType.Name.LARGE_SLIME.getValue());
                statement.setString(2, elementName);
                statement.setString(3, EnemyNamingStrategy.BETWEEN.getName());
                statement.executeUpdate();
                // samachurls
                statement.setString(1, EnemyType.Name.SAMACHURL.getValue());
                statement.setString(2, elementName);
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
        insertEnemyTypes(context);
        insertNamingStrategies(context);
        insertCommonMaterialTypes(context);
        insertWeekdays(context);
        insertCommonEnemies(context);
    }
}
