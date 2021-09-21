-- TODO
CREATE VIEW IF NOT EXISTS detailed_character AS
    SELECT *
        FROM character AS ch
            INNER JOIN constellation AS const ON ch.name = const.character
            INNER JOIN material AS const_mat ON const.constellation_activation_material = const_mat.name
            INNER JOIN constellation_level AS const_lvl ON const.character = const_lvl.character AND const.element = const_lvl.element
            INNER JOIN talent_level_up AS talent_lvl_up ON const.character = talent_lvl_up.character AND const.element = talent_lvl_up.element
            INNER JOIN character_ascension AS ch_asc ON ch.name = ch_asc.character;
