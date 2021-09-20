CREATE TABLE IF NOT EXISTS rarity (
    stars INTEGER PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS element (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS weapon_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS nation (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS character (
    name TEXT PRIMARY KEY NOT NULL,
    rarity INTEGER NOT NULL,
    weapon_type TEXT NOT NULL,
    sex TEXT NOT NULL,
    nation TEXT,
    FOREIGN KEY rarity REFERENCES rarity(stars),
    FOREIGN KEY weapon_type REFERENCES weapon_type(name),
    FOREIGN KEY nation REFERENCES nation(name),
    CHECK (sex = 'Male' OR sex = 'Female')
);
CREATE TABLE IF NOT EXISTS talent_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS talent (
    name TEXT NOT NULL,
    character TEXT NOT NULL,
    element TEXT NOT NULL,
    type TEXT NOT NULL,
    info TEXT NOT NULL,
    FOREIGN KEY (character, element) REFERENCES constellation(character, element),
    FOREIGN KEY type REFERENCES talent_type(name),
    PRIMARY KEY (character, element, type)
);
CREATE TABLE IF NOT EXISTS material (
    name TEXT PRIMARY KEY NOT NULL,
    rarity INTEGER,
    FOREIGN KEY rarity REFERENCES rarity(stars)
);
CREATE TABLE IF NOT EXISTS constellation_activation_material (
    name TEXT PRIMARY KEY NOT NULL,
    FOREIGN KEY name REFERENCES material(name)
);
CREATE TABLE IF NOT EXISTS ascension_gem_type (
    name TEXT PRIMARY KEY NOT NULL,
    element TEXT UNIQUE,
    FOREIGN KEY element REFERENCES element(name)
);
CREATE TABLE IF NOT EXISTS ascension_gem_size (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS ascension_gem (
    type TEXT NOT NULL,
    size TEXT NOT NULL,
    name TEXT NOT NULL,
    FOREIGN KEY type REFERENCES ascension_gem_type(name),
    FOREIGN KEY size REFERENCES ascension_gem_size(name),
    FOREIGN KEY name REFERENCES material(name),
    PRIMARY KEY (type, size),
    CHECK (type || ' ' || size) = name)
);
CREATE TABLE IF NOT EXISTS normal_boss (
    name TEXT PRIMARY KEY NOT NULL,
    nation TEXT NOT NULL,
    FOREIGN KEY nation REFERENCES nation(name)
);
CREATE TABLE IF NOT EXISTS normal_boss_drop (
    name TEXT PRIMARY KEY NOT NULL,
    normal_boss TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY normal_boss REFERENCES normal_boss(name)
);
CREATE TABLE IF NOT EXISTS local_specialty (
    name TEXT PRIMARY KEY NOT NULL,
    nation TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY nation REFERENCES nation(name)
);
CREATE TABLE IF NOT EXISTS enemy_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS enemy_naming_strategy (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS common_material_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS common_material (
    name TEXT PRIMARY KEY NOT NULL,
    type TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY type REFERENCES common_material_type(name),
    -- TODO
    CHECK ()
);
CREATE TABLE IF NOT EXISTS common_enemy_drop (
    enemy_type TEXT NOT NULL,
    common_material_type TEXT NOT NULL,
    FOREIGN KEY enemy_type REFERENCES enemy_type(name),
    FOREIGN KEY common_material_type REFERENCES common_material_type(name),
    PRIMARY KEY (enemy_type, common_material_type)
);
CREATE TABLE IF NOT EXISTS domain (
    name TEXT PRIMARY KEY NOT NULL,
    nation TEXT NOT NULL,
    FOREIGN KEY nation REFERENCES nation(name)
);
CREATE TABLE IF NOT EXISTS weekday (
    name TEXT PRIMARY KEY NOT NULL,
    CHECK (name = 'Sunday' OR name = 'Monday' OR name = 'Tuesday' OR
             name = 'Wednesday' OR name = 'Thursday' OR name = 'Friday' OR
             name = 'Saturday')
);
CREATE TABLE IF NOT EXISTS talent_book_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS talent_book_series (
    name TEXT PRIMARY KEY NOT NULL,
    domain TEXT NOT NULL,
    weekday_one TEXT NOT NULL,
    weekday_two TEXT NOT NULL,
    FOREIGN KEY domain REFERENCES domain(name),
    FOREIGN KEY weekday_one REFERENCES weekday(name),
    FOREIGN KEY weekday_two REFERENCES weekday(name)
);
CREATE TABLE IF NOT EXISTS talent_book (
    type TEXT NOT NULL,
    series TEXT NOT NULL,
    name TEXT NOT NULL,
    FOREIGN KEY type REFERENCES talent_book_type(name),
    FOREIGN KEY series REFERENCES talent_book_series(name),
    FOREIGN KEY name REFERENCES material(name),
    PRIMARY KEY (type, series),
    -- TODO
    CHECK (type || ' of ' || series) = name)
);
CREATE TABLE IF NOT EXISTS weekly_boss (
    name TEXT PRIMARY KEY NOT NULL,
    domain TEXT,
    FOREIGN KEY domain REFERENCES domain(name)
);
CREATE TABLE IF NOT EXISTS weekly_boss_drop (
    name TEXT PRIMARY KEY NOT NULL,
    weekly_boss TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY weekly_boss REFERENCES weekly_boss(name)
);
CREATE TABLE IF NOT EXISTS constellation (
    character TEXT NOT NULL,
    element TEXT NOT NULL,
    activation_material TEXT NOT NULL,
    FOREIGN KEY character REFERENCES character(name),
    FOREIGN KEY element REFERENCES element(name),
    FOREIGN KEY activation_material REFERENCES constellation_activation_material(name),
    PRIMARY KEY (character, element)
);
CREATE TABLE IF NOT EXISTS constellation_level (
    name TEXT PRIMARY KEY NOT NULL,
    character TEXT NOT NULL,
    element TEXT NOT NULL,
    level INTEGER NOT NULL,
    effect TEXT NOT NULL,
    FOREIGN KEY (character, element) REFERENCES constellation(character, element),
    UNIQUE (character, element, level)
);
CREATE TABLE IF NOT EXISTS ascension_phase (
    phase INTEGER PRIMARY KEY NOT NULL,
    mora INTEGER NOT NULL,
    ascension_gem_size TEXT NOT NULL,
    ascension_gem_quantity INTEGER NOT NULL,
    normal_boss_drop_quantity INTEGER NOT NULL,
    local_specialty_quantity INTEGER NOT NULL,
    common_material_rarity INTEGER NOT NULL,
    common_material_quantity INTEGER NOT NULL,
    FOREIGN KEY ascension_gem_size REFERENCES ascension_gem_size(name)
);
CREATE TABLE IF NOT EXISTS character_ascension (
    character TEXT PRIMARY KEY NOT NULL,
    ascension_gem_type TEXT NOT NULL,
    normal_boss_drop TEXT,
    local_specialty TEXT NOT NULL,
    common_material_type TEXT NOT NULL,
    FOREIGN KEY character REFERENCES character(name),
    FOREIGN KEY ascension_gem_type REFERENCES ascension_gem_type(name),
    FOREIGN KEY normal_boss_drop REFERENCES normal_boss_drop(name),
    FOREIGN KEY local_specialty REFERENCES local_specialty(name),
    FOREIGN KEY common_material_type REFERENCES common_material_type(name)
);
CREATE TABLE IF NOT EXISTS talent_level (
    level INTEGER PRIMARY KEY NOT NULL,
    mora INTEGER NOT NULL,
    common_material_rarity INTEGER NOT NULL,
    common_material_quantity INTEGER NOT NULL,
    talent_book_type TEXT NOT NULL,
    talent_book_quantity INTEGER NOT NULL,
    weekly_boss_drop_quantity INTEGER NOT NULL DEFAULT 0,
    crown_of_insight INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY talent_book_type REFERENCES talent_book_type(name)
);
CREATE TABLE IF NOT EXISTS talent_level_up (
    character TEXT NOT NULL,
    element TEXT NOT NULL,
    common_material_type TEXT NOT NULL,
    talent_book_series_one TEXT NOT NULL,
    talent_book_series_two TEXT DEFAULT NULL,
    talent_book_series_three TEXT DEFAULT NULL,
    weekly_boss_drop TEXT NOT NULL,
    FOREIGN KEY (character, element) REFERENCES constellation(character, element),
    FOREIGN KEY common_material_type REFERENCES common_material_type(name),
    FOREIGN KEY talent_book_series_one REFERENCES talent_book_series(name),
    FOREIGN KEY talent_book_series_two REFERENCES talent_book_series(name),
    FOREIGN KEY talent_book_series_three REFERENCES talent_book_series(name),
    FOREIGN KEY weekly_boss_drop REFERENCES weekly_boss_drop(name),
    PRIMARY KEY (character, element)
);
CREATE TABLE IF NOT EXISTS common_enemy (
    type TEXT NOT NULL,
    name TEXT,
    naming_strategy TEXT,
    FOREIGN KEY type REFERENCES enemy_type(name),
    FOREIGN KEY naming_strategy REFERENCES enemy_naming_strategy(name),
    PRIMARY KEY (type, name)
);