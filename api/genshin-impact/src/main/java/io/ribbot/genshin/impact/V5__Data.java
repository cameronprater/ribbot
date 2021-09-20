package io.ribbot.genshin.impact;

import io.ribbot.genshin.impact.entity.AscensionGemSize;
import io.ribbot.genshin.impact.entity.AscensionGemType;
import io.ribbot.genshin.impact.entity.Element;
import io.ribbot.genshin.impact.entity.TalentBookType;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO
public class V5__Data extends BaseJavaMigration {

    private void insertMaterial(Context context, String name, int rarity) throws Exception {
        try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO material VALUES (?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, rarity);
            statement.executeUpdate();
        }
    }

    private void insertAscensionGems(Context context) throws Exception {
        Map<AscensionGemSize, Optional<?>> inserted = new HashMap<>();
        for (AscensionGemType ascensionGemType : AscensionGemType.values()) {
            String ascensionGemTypeName = ascensionGemType.getName();
            try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem_type VALUES (?, ?)")) {
                statement.setString(1, ascensionGemTypeName);
                statement.setString(2, Optional.ofNullable(ascensionGemType.getElement()).map(Element::getName).orElse(null));
                statement.executeUpdate();
            }
            for (AscensionGemSize ascensionGemSize : AscensionGemSize.values()) {
                String ascensionGemSizeName = ascensionGemType.getName();
                inserted.computeIfAbsent(ascensionGemSize, x -> {
                    try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem_size VALUES (?)")) {
                        statement.setString(1, ascensionGemSizeName);
                        statement.executeUpdate();
                        return Optional.empty();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                String name = ascensionGemTypeName + ' ' + ascensionGemSizeName;
                switch (ascensionGemSize) {
                    case SLIVER:
                        insertMaterial(context, name, );
                    case FRAGMENT:
                        insertMaterial(context, name, );
                    case CHUNK:
                        insertMaterial(context, name, );
                    case GEMSTONE:
                        insertMaterial(context, name, );
                    default:
                        throw new IllegalStateException();
                }

                try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO ascension_gem VALUES (?, ?, ?)")) {
                    statement.setString(1, ascensionGemTypeName);
                    statement.setString(2, ascensionGemSizeName);
                    statement.setString(3, name);
                    statement.executeUpdate();
                }
            }
        }
    }

    private void insertTalentBooks(Context context) throws Exception {
        for (TalentBookType talentBookType : TalentBookType.values()) {
            String talentBookTypeName = talentBookType.getName();
            try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO talent_book_type VALUES (?)")) {
                statement.setString(1, talentBookTypeName);
                statement.executeUpdate();
            }
            try (ResultSet rows = context.getConnection().createStatement().executeQuery("SELECT DISTINCT name FROM talent_book_series")) {
                while (rows.next()) {
                    String talentBookSeries = rows.getString(1);
                    try (PreparedStatement statement = context.getConnection().prepareStatement("INSERT INTO talent_book VALUES (?, ?, ?)")) {
                        statement.setString(1, talentBookTypeName);
                        statement.setString(2, talentBookSeries);
                        // TODO
                        statement.setString(3, talentBookTypeName + " of " + talentBookSeries);
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

    @Override
    public void migrate(Context context) throws Exception {
        insertAscensionGems(context);
        insertTalentBooks(context);
        insertLumineData(context);
    }
}
