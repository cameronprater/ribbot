CREATE VIEW IF NOT EXISTS character_ AS
    SELECT
        char.name, char.rarity, char.element, char.weapon_type, char.sex, char.nation,
        talent.name, talent.type, talent.info,
        const.name, const.level, const.effect, const.activation_material,
        const_mat.rarity,
        lvl.level, lvl.mora, lvl.ascension_phase, lvl.common_material, lvl.common_material_quantity, lvl.talent_book,
            lvl.talent_book_quantity, lvl.weekly_boss_drop, lvl.weekly_boss_drop_quantity, lvl.crown_of_insight,
        common_mat.
        talent_book.
        weekly_boss_drop.
        weekly_boss.
        ascension.phase, ascension.mora, ascension.ascension_gem, ascension.ascension_gem_quantity,
            ascension.normal_boss_drop, ascension.normal_boss_drop_quantity, ascension.local_specialty,
            ascension.local_specialty_quantity, ascension.common_material, ascension.common_material_quantity,


    FROM
        character char
            LEFT JOIN talent ON character.name = talent.name


CREATE VIEW IF NOT EXISTS character_ascension_phase AS ;