package io.ribbot.genshin.jdbi;

import io.ribbot.genshin.entity.Character;
import io.ribbot.genshin.entity.Character.Sex;
import io.ribbot.genshin.entity.Element;
import io.ribbot.genshin.entity.Nation;
import io.ribbot.genshin.entity.Rarity;
import io.ribbot.genshin.entity.WeaponType;
import io.smallrye.common.constraint.Nullable;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.Optional;

public interface CharacterDao extends SqlObject {

    @SqlQuery("SELECT * FROM character WHERE name = :name")
    Optional<Character> findByName(String name);

    default ResultIterable<Character> find(@Nullable Rarity rarity, @Nullable Element element, @Nullable WeaponType weaponType,
                                   @Nullable Sex sex, @Nullable Nation nation) {
        Handle handle = getHandle();
        StringBuilder query = new StringBuilder("SELECT * FROM character");
        boolean where = false;
        if (rarity != null) {
            query.append(String.format(" WHERE rarity = %d", rarity.getStars()));
            where = true;
        }
        if (element != null) {
            query.append(String.format(" %s element = %s", where ? "AND" : "WHERE", element.getName()));
            where = true;
        }
        if (weaponType != null) {
            query.append(String.format(" %s weapon_type = %s", where ? "AND" : "WHERE", weaponType.getName()));
            where = true;
        }
        if (sex != null) {
            query.append(String.format(" %s sex = %s", where ? "AND" : "WHERE", sex.getValue()));
            where = true;
        }
        if (nation != null) {
            query.append(String.format(" %s nation = %s", where ? "AND" : "WHERE", nation.getName()));
        }
        ResultIterable<Character> characters = handle.createQuery(query.toString()).mapTo(Character.class);
        handle.close();
        return characters;
    }
}
