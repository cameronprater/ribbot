package io.ribbot.genshin.impact.dao;

import java.util.List;
import java.util.Optional;

import io.ribbot.genshin.impact.entity.Element;
import io.ribbot.genshin.impact.entity.Nation;
import io.ribbot.genshin.impact.entity.Rarity;
import io.ribbot.genshin.impact.entity.WeaponType;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import io.ribbot.genshin.impact.entity.Character;
import io.ribbot.genshin.impact.entity.Character.Sex;
import io.smallrye.common.constraint.Nullable;

public interface CharacterDao extends SqlObject {

    @SqlQuery("SELECT * FROM character WHERE name = :name")
    Optional<Character> findByName(String name);

    default List<Character> find(@Nullable Rarity rarity, @Nullable Element element, @Nullable WeaponType weaponType,
                                 @Nullable Sex sex, @Nullable Nation nation) {
        Handle handle = getHandle();
        StringBuilder query = new StringBuilder("SELECT * FROM character");
        boolean where = false;
        if (rarity != null) {
            query.append(String.format(" WHERE rarity = %d", rarity.getStars()));
            where = true;
        }
        // TODO
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
        List<Character> characters = handle.createQuery(query.toString()).mapToBean(Character.class).list();
        handle.close();
        return characters;
    }
}
