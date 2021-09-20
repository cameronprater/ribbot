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

public class V5__Data extends BaseJavaMigration {

    // TODO insert ascension gems as materials
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

    // TODO insert talent books as materials
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

    // TODO
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
