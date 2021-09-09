CREATE TABLE IF NOT EXISTS nation (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS element (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS weapon_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS rarity (
    stars INTEGER PRIMARY KEY NOT NULL
)
CREATE TABLE IF NOT EXISTS character (
    name TEXT PRIMARY KEY NOT NULL,
    rarity INTEGER NOT NULL,
    element TEXT,
    weapon_type TEXT NOT NULL,
    sex TEXT NOT NULL,
    nation TEXT,
    FOREIGN KEY rarity REFERENCES rarity(stars),
    FOREIGN KEY element REFERENCES element(name),
    FOREIGN KEY weapon_type REFERENCES weapon_type(name),
    FOREIGN KEY nation REFERENCES nation(name),
    CHECK (sex = 'Male' OR sex = 'Female')
);
CREATE TABLE IF NOT EXISTS talent_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS talent (
    name TEXT PRIMARY KEY NOT NULL,
    character TEXT NOT NULL,
    type TEXT NOT NULL,
    info TEXT NOT NULL,
    FOREIGN KEY character REFERENCES character(name),
    FOREIGN KEY type REFERENCES talent_type(name)
);
CREATE UNIQUE INDEX character_talent_type ON talent (character, type);
CREATE TABLE IF NOT EXISTS constellation (
    name TEXT PRIMARY KEY NOT NULL,
    character TEXT NOT NULL,
    level INTEGER NOT NULL,
    effect TEXT NOT NULL,
    activation_material TEXT NOT NULL,
    FOREIGN KEY character REFERENCES character(name),
    FOREIGN KEY activation_material REFERENCES constellation_activation_material(name)
);
CREATE UNIQUE INDEX character_constellation_level ON constellation (character, level);
CREATE TABLE IF NOT EXISTS character_ascension (
    character TEXT NOT NULL,
    phase INTEGER NOT NULL,
    mora INTEGER NOT NULL,
    ascension_gem TEXT NOT NULL,
    ascension_gem_quantity INTEGER NOT NULL,
    normal_boss_drop TEXT,
    normal_boss_drop_quantity INTEGER,
    local_specialty TEXT NOT NULL,
    local_specialty_quantity INTEGER NOT NULL,
    common_material TEXT NOT NULL,
    common_material_quantity INTEGER NOT NULL,
    FOREIGN KEY character REFERENCES character(name),
    FOREIGN KEY ascension_gem REFERENCES ascension_gem(name),
    FOREIGN KEY normal_boss_drop REFERENCES normal_boss_drop(name),
    FOREIGN KEY local_specialty REFERENCES local_specialty(name),
    FOREIGN KEY common_material REFERENCES common_ascension_material(name),
    PRIMARY KEY (character, phase)
);
CREATE TABLE IF NOT EXISTS talent_level (
    talent TEXT NOT NULL,
    level INTEGER NOT NULL,
    mora INTEGER NOT NULL,
    ascension_phase INTEGER NOT NULL,
    common_material TEXT NOT NULL,
    common_material_quantity INTEGER NOT NULL,
    talent_book TEXT NOT NULL,
    talent_book_quantity INTEGER NOT NULL,
    weekly_boss_drop TEXT,
    weekly_boss_drop_quantity INTEGER,
    crown_of_insight INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY talent REFERENCES talent(name),
    FOREIGN KEY common_material REFERENCES character_ascension_material(name),
    FOREIGN KEY talent_book REFERENCES talent_book(name),
    FOREIGN KEY weekly_boss_drop REFERENCES weekly_boss_drop(name),
    PRIMARY KEY (talent, level)
);
CREATE TABLE IF NOT EXISTS material (
    name TEXT PRIMARY KEY NOT NULL,
    rarity INTEGER NOT NULL,
    FOREIGN KEY rarity REFERENCES rarity(stars)
);
CREATE TABLE IF NOT EXISTS local_specialty (
    name TEXT PRIMARY KEY NOT NULL,
    nation TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY nation REFERENCES nation(name)
);
CREATE TABLE IF NOT EXISTS constellation_activation_material (
    name TEXT PRIMARY KEY NOT NULL,
    FOREIGN KEY name REFERENCES material(name)
);
CREATE TABLE IF NOT EXISTS enemy_naming_strategy (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS enemy_type (
    name TEXT PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS common_enemy (
    type TEXT NOT NULL,
    name TEXT NOT NULL,
    naming_strategy TEXT NOT NULL,
    FOREIGN KEY type REFERENCES enemy_type(name),
    FOREIGN KEY naming_strategy REFERENCES enemy_naming_strategy(name),
    PRIMARY KEY (type, name)
);
CREATE TABLE IF NOT EXISTS common_ascension_material (
    name TEXT PRIMARY KEY NOT NULL,
    FOREIGN KEY name REFERENCES material(name)
);
CREATE TABLE IF NOT EXISTS common_enemy_drop (
    enemy_type TEXT NOT NULL,
    enemy_name TEXT NOT NULL,
    material TEXT NOT NULL,
    FOREIGN KEY enemy_type, enemy_name REFERENCES common_enemy(type, name),
    FOREIGN KEY material REFERENCES common_ascension_material(name),
    PRIMARY KEY (enemy, material)
);
CREATE TABLE IF NOT EXISTS ascension_gem (
    name TEXT PRIMARY KEY NOT NULL,
    element TEXT,
    FOREIGN KEY element REFERENCES element(name)
);
CREATE TABLE IF NOT EXISTS normal_boss (
    name TEXT PRIMARY KEY NOT NULL,
    nation TEXT NOT NULL,
    FOREIGN KEY nation REFERENCES nation(name)
);
CREATE TABLE IF NOT EXISTS normal_boss_drop (
    name TEXT PRIMARY KEY NOT NULL,
    FOREIGN KEY name REFERENCES material(name)
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
CREATE TABLE IF NOT EXISTS talent_book (
    name TEXT PRIMARY KEY NOT NULL,
    weekday_one TEXT NOT NULL,
    weekday_two TEXT NOT NULL,
    weekday_three TEXT NOT NULL,
    domain TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY weekday_one REFERENCES weekday(name),
    FOREIGN KEY weekday_two REFERENCES weekday(name),
    FOREIGN KEY weekday_three REFERENCES weekday(name),
    FOREIGN KEY domain REFERENCES domain(name)
);
CREATE TABLE IF NOT EXISTS weekly_boss (
    name TEXT PRIMARY KEY NOT NULL,
    domain TEXT NOT NULL,
    FOREIGN KEY domain REFERENCES domain(name)
);
CREATE TABLE IF NOT EXISTS weekly_boss_drop (
    name TEXT PRIMARY KEY NOT NULL,
    weekly_boss TEXT NOT NULL,
    FOREIGN KEY name REFERENCES material(name),
    FOREIGN KEY weekly_boss REFERENCES weekly_boss(name)
);