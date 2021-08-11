CREATE TABLE IF NOT EXISTS guild (
    id INTEGER PRIMARY KEY NOT NULL
);
CREATE TABLE IF NOT EXISTS channel (
    id INTEGER PRIMARY KEY NOT NULL,
    guild_id INTEGER NOT NULL,
    FOREIGN KEY (guild_id) REFERENCES guild(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS message (
    id INTEGER PRIMARY KEY NOT NULL,
    channel_id INTEGER NOT NULL,
    FOREIGN KEY (channel_id) REFERENCES channel(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS role (
    id INTEGER id PRIMARY KEY NOT NULL,
    guild_id INTEGER NOT NULL,
    FOREIGN KEY (guild_id) REFERENCES guild(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS role_button (
    button_id TEXT NOT NULL,
    message_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (message_id) REFERENCES message(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    PRIMARY KEY (button_id, message_id)
);
CREATE TABLE IF NOT EXISTS role_select_menu (
    select_menu_id TEXT NOT NULL,
    message_id INTEGER NOT NULL,
    FOREIGN KEY (message_id) REFERENCES message(id) ON DELETE CASCADE,
    PRIMARY KEY (select_menu_id, message_id)
);
CREATE TABLE IF NOT EXISTS role_select_option (
    select_menu_id TEXT NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (select_menu_id) REFERENCES role_select_menu(select_menu_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    PRIMARY KEY (select_menu_id, role_id)
);