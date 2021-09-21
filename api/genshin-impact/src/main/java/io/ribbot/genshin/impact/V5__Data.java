package io.ribbot.genshin.impact;

import io.ribbot.genshin.impact.entity.*;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class V5__Data extends BaseJavaMigration {

    private void insertMaterial(PreparedStatement statement, String name, int rarity) throws Exception {
        statement.setString(1, name);
        statement.setInt(2, rarity);
        statement.executeUpdate();
    }

    private void insertAscensionGems(Context context) throws Exception {
        Map<AscensionGemSize, Optional<?>> inserted = new HashMap<>();
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem_type VALUES (?, ?)");
             PreparedStatement statement2 = context.getConnection().prepareStatement("INSERT INTO ascension_gem_size VALUES (?)");
             PreparedStatement statement3 = context.getConnection().prepareStatement("INSERT INTO ascension_gem VALUES (?, ?, ?)");
             PreparedStatement statement4 = context.getConnection().prepareStatement("INSERT INTO material VALUES (?, ?)")) {
            for (AscensionGemType ascensionGemType : AscensionGemType.values()) {
                String ascensionGemTypeName = ascensionGemType.getName();
                statement.setString(1, ascensionGemTypeName);
                statement.setString(2, Optional.ofNullable(ascensionGemType.getElement()).map(Element::getName).orElse(null));
                statement.executeUpdate();

                for (AscensionGemSize ascensionGemSize : AscensionGemSize.values()) {
                    String ascensionGemSizeName = ascensionGemType.getName();
                    if (inserted.get(ascensionGemSize) == null) {
                        statement2.setString(1, ascensionGemSizeName);
                        statement2.executeUpdate();
                        inserted.put(ascensionGemSize, Optional.empty());
                    }

                    String name = ascensionGemTypeName + ' ' + ascensionGemSizeName;
                    switch (ascensionGemSize) {
                        case SLIVER:
                            insertMaterial(statement4, name, 2);
                            break;
                        case FRAGMENT:
                            insertMaterial(statement4, name, 3);
                            break;
                        case CHUNK:
                            insertMaterial(statement4, name, 4);
                            break;
                        case GEMSTONE:
                            insertMaterial(statement4, name, 5);
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                    statement3.setString(1, ascensionGemTypeName);
                    statement3.setString(2, ascensionGemSizeName);
                    statement3.setString(3, name);
                    statement3.executeUpdate();
                }
            }
        }
    }

    private void insertTalentBooks(Context context) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO talent_book_type VALUES (?)");
             ResultSet rows = context.getConnection().createStatement().executeQuery("SELECT DISTINCT name FROM talent_book_series");
             PreparedStatement statement2 = context.getConnection().prepareStatement("INSERT INTO talent_book VALUES (?, ?, ?)");
             PreparedStatement statement3 = context.getConnection().prepareStatement("INSERT INTO material VALUES (?, ?)")) {
            for (TalentBookType talentBookType : TalentBookType.values()) {
                String talentBookTypeName = talentBookType.getName();
                statement.setString(1, talentBookTypeName);
                statement.executeUpdate();

                while (rows.next()) {
                    String talentBookSeries = rows.getString(1);
                    String name;
                    switch (talentBookType) {
                        case GUIDE:
                            insertMaterial(statement3, name=talentBookTypeName + " of " + talentBookSeries, 2);
                            break;
                        case TEACHINGS:
                            insertMaterial(statement3, name=talentBookTypeName + " to " + talentBookSeries, 3);
                            break;
                        case PHILOSOPHIES:
                            insertMaterial(statement3, name=talentBookTypeName + " of " + talentBookSeries, 4);
                            break;
                        default:
                            throw new IllegalStateException();
                    }
                    statement2.setString(1, talentBookTypeName);
                    statement2.setString(2, talentBookSeries);
                    statement2.setString(3, name);
                    statement2.executeUpdate();
                }
            }
        }
    }

    private void insertLumineData(Context context) throws Exception {
        String query = "SELECT * FROM %s WHERE character = 'Aether'";
        try (Connection connection = context.getConnection()) {
            // insert constellations
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO constellation VALUES (?, ?, ?)");
                 ResultSet rows = connection.createStatement().executeQuery(String.format(query, "constellation"))) {
                while (rows.next()) {
                    statement.setString(1, "Lumine");
                    for (int i = 2; i < 4; i++) {
                        statement.setString(i, rows.getString(i));
                    }
                    statement.executeUpdate();
                }
            }

            // insert constellation levels
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO constellation_level VALUES (?, ?, ?, ?, ?)");
                 ResultSet rows = connection.createStatement().executeQuery(String.format(query, "constellation_level"))) {
                while (rows.next()) {
                    statement.setString(1, rows.getString(1));
                    statement.setString(2, "Lumine");
                    statement.setString(3, rows.getString(3));
                    statement.setInt(4, rows.getInt(4));
                    statement.setString(5, rows.getString(5));
                    statement.executeUpdate();
                }
            }

            // insert talents
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO talent VALUES (?, ?, ?, ?, ?)");
                 ResultSet rows = connection.createStatement().executeQuery(String.format(query, "talent"))) {
                while (rows.next()) {
                    statement.setString(2, "Lumine");
                    for (int i = 1; i < 6; i++) {
                        if (i == 2) {
                            continue;
                        }
                        statement.setString(i, rows.getString(i));
                    }
                    statement.executeUpdate();
                }
            }

            // insert ascension
            try (Statement statement = connection.createStatement();
                 ResultSet row = connection.createStatement().executeQuery(String.format(query, "character_ascension"))) {
                String sql = "INSERT INTO character_ascension VALUES ('Lumine', %s, %s, %s, %s)";
                statement.executeUpdate(String.format(sql, row.getString(2), row.getString(3), row.getString(4), row.getString(5)));
            }

            // insert talent level ups
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO talent_level_up VALUES (?, ?, ?, ?, ?, ?, ?)");
                 ResultSet rows = connection.createStatement().executeQuery(String.format(query, "talent_level_up"))) {
                while (rows.next()) {
                    statement.setString(1, "Lumine");
                    for (int i = 2; i < 8; i++) {
                        statement.setString(i, rows.getString(i));
                    }
                    statement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void migrate(Context context) throws Exception {
        insertAscensionGems(context);
        insertTalentBooks(context);
        insertLumineData(context);
    }
}
