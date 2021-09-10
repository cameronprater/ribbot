-- characters
INSERT INTO character VALUES ('Aether', 5, NULL, 'Sword', 'Male', NULL);
INSERT INTO character VALUES ('Albedo', 5, 'Geo', 'Sword', 'Male', 'Mondstadt');
INSERT INTO character VALUES ('Aloy', 5, 'Cryo', 'Bow', 'Female', NULL);
INSERT INTO character VALUES ('Amber', 4, 'Pyro', 'Bow', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Barbara', 4, 'Hydro', 'Catalyst', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Beidou', 4, 'Electro', 'Claymore', 'Female', 'Liyue');
INSERT INTO character VALUES ('Bennett', 4, 'Pyro', 'Sword', 'Male', 'Mondstadt');
INSERT INTO character VALUES ('Chongyun', 4, 'Cryo', 'Claymore', 'Male', 'Liyue');
INSERT INTO character VALUES ('Diluc', 5, 'Pyro', 'Claymore', 'Male', 'Mondstadt');
INSERT INTO character VALUES ('Diona', 4, 'Cryo', 'Bow', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Eula', 5, 'Cryo', 'Claymore', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Fischl', 4, 'Electro', 'Bow', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Ganyu', 5, 'Cryo', 'Bow', 'Female', 'Liyue');
INSERT INTO character VALUES ('Hu Tao', 5, 'Pyro', 'Polearm', 'Female', 'Liyue');
INSERT INTO character VALUES ('Jean', 5, 'Anemo', 'Sword', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Kaedehara Kazuha', 5, 'Anemo', 'Sword', 'Male', 'Inazuma');
INSERT INTO character VALUES ('Kaeya', 4, 'Cryo', 'Sword', 'Male', 'Mondstadt');
INSERT INTO character VALUES ('Kamisato Ayaka', 5, 'Cryo', 'Sword', 'Female', 'Inazuma');
INSERT INTO character VALUES ('Keqing', 5, 'Electro', 'Sword', 'Female', 'Liyue');
INSERT INTO character VALUES ('Klee', 5, 'Pyro', 'Catalyst', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Kujou Sara', 4, 'Electro', 'Bow', 'Female', 'Inazuma');
INSERT INTO character VALUES ('Lisa', 4, 'Electro', 'Catalyst', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Lumine', 5, NULL, 'Sword', 'Female', NULL);
INSERT INTO character VALUES ('Mona', 5, 'Hydro', 'Catalyst', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Ningguang', 4, 'Geo', 'Catalyst', 'Female', 'Liyue');
INSERT INTO character VALUES ('Noelle', 4, 'Geo', 'Claymore', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Qiqi', 5, 'Cryo', 'Sword', 'Female', 'Liyue');
INSERT INTO character VALUES ('Raiden Shogun', 5, 'Electro', 'Polearm', 'Female', 'Inazuma');
INSERT INTO character VALUES ('Razor', 4, 'Electro', 'Claymore', 'Male', 'Mondstadt');
INSERT INTO character VALUES ('Rosaria', 4, 'Cryo', 'Polearm', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Sayu', 4, 'Anemo', 'Claymore', 'Female', 'Inazuma');
INSERT INTO character VALUES ('Sucrose', 4, 'Anemo', 'Catalyst', 'Female', 'Mondstadt');
INSERT INTO character VALUES ('Tartaglia', 5, 'Hydro', 'Bow', 'Male', 'Snezhnaya');
INSERT INTO character VALUES ('Venti', 5, 'Anemo', 'Bow', 'Male', 'Mondstadt');
INSERT INTO character VALUES ('Xiangling', 4, 'Pyro', 'Polearm', 'Female', 'Liyue');
INSERT INTO character VALUES ('Xiao', 5, 'Anemo', 'Polearm', 'Male', 'Liyue');
INSERT INTO character VALUES ('Xingqiu', 4, 'Hydro', 'Sword', 'Male', 'Liyue');
INSERT INTO character VALUES ('Xinyan', 4, 'Pyro', 'Claymore', 'Female', 'Liyue');
INSERT INTO character VALUES ('Yanfei', 4, 'Pyro', 'Catalyst', 'Female', 'Liyue');
INSERT INTO character VALUES ('Yoimiya', 5, 'Pyro', 'Bow', 'Female', 'Inazuma');
INSERT INTO character VALUES ('Zhongli', 5, 'Geo', 'Polearm', 'Male', 'Liyue');

-- talents

-- constellations
INSERT INTO constellation VALUES ('Raging Vortex', 'Aether', 1, 'Anemo',
                                    'Palm Vortex pulls in opponents and objects within a 5m radius.', 'Memory of Roving Gales');
INSERT INTO constellation VALUES ('Uprising Whirlwind', 'Aether', 2, 'Anemo',
                                    'Increases Energy Recharge by 16%.', 'Memory of Roving Gales');
INSERT INTO constellation VALUES ('Sweeping Gust', 'Aether', 3, 'Anemo',
                                    'Increases the Level of Gust Surge by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Memory of Roving Gales');
INSERT INTO constellation VALUES ('Cherishing Breezes', 'Aether', 4, 'Anemo',
                                    'Reduces DMG taken while casting Palm Vortex by 10%.', 'Memory of Roving Gales');
INSERT INTO constellation VALUES ('Vortex Stellaris', 'Aether', 5, 'Anemo',
                                    'Increases the Level of Palm Vortex by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Memory of Roving Gales');
INSERT INTO constellation VALUES ('Intertwined Winds', 'Aether', 6, 'Anemo',
                                    'Targets who take DMG from Gust Surge have their Anemo RES decreased by 20%.' + char(10) +
                                    'If an Elemental Absorption occurred, then their RES towards ' +
                                    'the corresponding Element is also decreased by 20%.', 'Memory of Roving Gales');

INSERT INTO constellation VALUES ('Invincible Stonewall', 'Aether', 1, 'Geo',
                                    'Party members within the radius of Wake of Earth have their CRIT Rate increased ' +
                                    'by 10% and have increased resistance against interruption.', 'Memory of Immovable Crystals');
INSERT INTO constellation VALUES ('Rockcore Meltdown', 'Aether', 2, 'Geo',
                                    'When the meteorite created by Starfell Sword is destroyed, it will also explode, ' +
                                    'dealing additional AoE Geo DMG equal to the amount of damage dealt by Starfell ' +
                                    'Sword.', 'Memory of Immovable Crystals');
INSERT INTO constellation VALUES ('Will of the Rock', 'Aether', 3, 'Geo',
                                    'Increases the Level of Wake of Earth by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Memory of Immovable Crystals');
INSERT INTO constellation VALUES ('Reaction Force', 'Aether', 4, 'Geo',
                                    'The shockwave triggered by Wake of Earth regenerates 5 Energy for every opponent hit.' + char(10) +
                                    'A maximum of 25 Energy can be regenerated in this manner at ' +
                                    'any one time.', 'Memory of Immovable Crystals');
INSERT INTO constellation VALUES ('Meteorite Impact', 'Aether', 5, 'Geo',
                                    'Increases the Level of Starfell Sword by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Memory of Immovable Crystals');
INSERT INTO constellation VALUES ('Everlasting Boulder', 'Aether', 6, 'Geo',
                                    'The barrier created by Wake of Earth lasts 5s longer.' + char(10) +
                                    'The meteorite created by Starfell Sword lasts 10s longer.', 'Memory of Immovable Crystals');

INSERT INTO constellation VALUES ('Spring Thunder of Fertility', 'Aether', 1, 'Electro',
                                    'The number of Abundance Amulets that can be generated using Lightning Blade is ' +
                                    'increased to 3.', 'Memory of Violet Flash');
INSERT INTO constellation VALUES ('Violet Vehemence', 'Aether', 2, 'Electro',
                                    'When Falling Thunder created by Bellowing Thunder hits an opponent, it will ' +
                                    'decrease their Electro RES by 15% for 8s.', 'Memory of Violet Flash');
INSERT INTO constellation VALUES ('Distant Crackling', 'Aether', 3, 'Electro',
                                    'Increases the Level of Bellowing Thunder by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Memory of Violet Flash');
INSERT INTO constellation VALUES ('Fickle Cloudstrike', 'Aether', 4, 'Electro',
                                    'When a character obtains Abundance Amulets generated by Lightning Blade, if this ' +
                                    'character\'s Energy is less than 35%, the Energy restored by the Abundance ' +
                                    'Amulets is increased by 100%.', 'Memory of Violet Flash');
INSERT INTO constellation VALUES ('Clamor in the Wilds', 'Aether', 5, 'Electro',
                                    'Increases the Level of Lightning Blade by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Memory of Violet Flash');
INSERT INTO constellation VALUES ('World-Shaker', 'Aether', 6, 'Electro',
                                    'Every 2 Falling Thunder attacks triggered by Bellowing Thunder will increase the ' +
                                    'DMG dealt by the next Falling Thunder by 200%, and will restore an additional 1 ' +
                                    'Energy to the current character.', 'Memory of Violet Flash');

INSERT INTO constellation VALUES ('Flower of Eden', 'Albedo', 1, 'Anemo',
                                    'Transient Blossoms generated by Albedo\'s Abiogenesis: Solar Isotoma regenerate ' +
                                    '1.2 Energy for Albedo.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Opening of Phanerozoic', 'Albedo', 2, 'Anemo',
                                    'Transient Blossoms generated by Abiogenesis: Solar Isotoma grant Albedo Fatal Reckoning for 30s:' + char(10) +
                                    'Unleashing Rite of Progeniture: Tectonic Tide consumes all stacks of Fatal ' +
                                    'Reckoning. Each stack of Fatal Reckoning consumed increases the DMG dealt by ' +
                                    'Fatal Blossoms and Rite of Progeniture: Tectonic Tide\'s burst DMG by 30% of Albedo\'s DEF.' + char(10) +
                                    'This effect stacks up to 4 times.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Grace of Helios', 'Albedo', 3, 'Anemo',
                                    'Increases the Level of Abiogenesis: Solar Isotoma by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Descent of Divinity', 'Albedo', 4, 'Anemo',
                                    'Active party members within the Solar Isotoma field have their Plunging Attack ' +
                                    'DMG increased by 30%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Tide of Hadean', 'Albedo', 5, 'Anemo',
                                    'Increases the Level of Rite of Progeniture: Tectonic Tide by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Dust of Purification', 'Albedo', 6, 'Anemo',
                                    'Active party members within the Solar Isotoma field who are protected by a ' +
                                    'shield created by Crystallize have their DMG increased by 17%.',
                                    'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('One Arrow to Rule Them All', 'Amber', 1, 'Pyro',
                                    'Fires 2 arrows per Aimed Shot. The second arrow deals 20% of the first arrow\'s DMG.',
                                    'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Bunny Triggered', 'Amber', 2, 'Pyro',
                                    'Baron Bunny, new and improved! Hitting Baron Bunny\'s foot with a fully-charged Aimed Shot manually detonates it.' + char(10) +
                                    'Explosion via manual detonation deals 200% additional DMG.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('It Burns!', 'Amber', 3, 'Pyro',
                                    'Increases the Level of Fiery Rain by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('It\'s Not Just Any Doll...', 'Amber', 4, 'Pyro',
                                    'Decreases Explosive Puppet\'s CD by 20%. Adds 1 additional charge.',
                                    'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('It\'s Baron Bunny!', 'Amber', 5, 'Pyro',
                                    'Increases the Level of Explosive Puppet by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Wildfire', 'Amber', 6, 'Pyro',
                                    'Fiery Rain increases all party members\' Movement SPD by 15% and ATK by 15% for 10s.',
                                    'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Gleeful Songs', 'Barbara', 1, 'Hydro',
                                    'Barbara regenerates 1 Energy every 10s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Vitality Burst', 'Barbara', 2, 'Hydro',
                                    'Decreases the CD of Let the Show Begin♪ by 15%.' + char(10) +
                                    'During the ability\'s duration, your active character gains a 15% Hydro DMG Bonus.',
                                    'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Star of Tomorrow', 'Barbara', 3, 'Hydro',
                                    'Increases the Level of Shining Miracle♪ by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Attentiveness be My Power', 'Barbara', 4, 'Hydro',
                                    '', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('The Purest Companionship', 'Barbara', 5, 'Hydro',
                                    'Increases the Level of Let the Show Begin♪ by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Dedicating Everything to You', 'Barbara', 6, 'Hydro',
                                    'When Barbara is in the party but not on the field, and one of your own party members falls:' + char(10) +
                                    'Automatically revives the fallen character.' + char(10) +
                                    'Fully restores the revived character\'s HP to 100%.' + char(10) +
                                    'This effect can only occur once every 15 mins.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Sea Beast\'s Scourge', 'Beidou', 1, 'Electro',
                                    'When Stormbreaker is used:' + char(10) +
                                    'Creates a shield that absorbs up to 16% of Beidou\'s Max HP for 15s.' + char(10) +
                                    'This shield absorbs Electro DMG 250% more effectively.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Upon the Turbulent Sea, the Thunder Arises', 'Beidou', 2, 'Electro',
                                    'Stormbreaker\'s arc lightning can jump to 2 additional targets.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Summoner of Storm', 'Beidou', 3, 'Electro',
                                    'Increases the Level of Tidecaller by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Stunning Revenge', 'Beidou', 4, 'Electro',
                                    'Within 10s of taking DMG, Beidou\'s Normal Attacks gain 20% additional Electro DMG.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Crimson Tidewalker', 'Beidou', 5, 'Electro',
                                    'Increases the Level of Stormbreaker by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Bane of Evil', 'Beidou', 6, 'Electro',
                                    'During the duration of Stormbreaker, the Electro RES of surrounding opponents is ' +
                                    'decreased by 15%.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Grand Expectation', 'Bennett', 1, 'Pyro',
                                    'Fantastic Voyage\'s ATK increase no longer has an HP restriction, and gains an ' +
                                    'additional 20% of Bennett\'s Base ATK.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Impasse Conqueror', 'Bennett', 2, 'Pyro',
                                    'When Bennett\'s HP falls below 70%, his Energy Recharge is increased by 30%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Unstoppable Fervor', 'Bennett', 3, 'Pyro',
                                    'Increases the Level of Passion Overload by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Unexpected Odyssey', 'Bennett', 4, 'Pyro',
                                    'Using a Normal Attack when executing the second attack of Passion Overload\'s ' +
                                    'Charge Level 1 allows an additional attack to be performed.' + char(10) +
                                    'This additional attack does 135% of the second attack\'s DMG.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('True Explorer', 'Bennett', 5, 'Pyro',
                                    'Increases the Level of Fantastic Voyage by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Fire Ventures with Me', 'Bennett', 6, 'Pyro',
                                    'Sword, Claymore, or Polearm-wielding characters inside Fantastic Voyage\'s ' +
                                    'radius gain a 15% Pyro DMG Bonus and their weapons are infused with Pyro.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Ice Unleashed', 'Chongyun', 1, 'Cryo',
                                    'The last attack of Chongyun\'s Normal Attack combo releases 3 ice blades. Each ' +
                                    'blade deals 50% of Chongyun\'s ATK as Cryo DMG to all opponents in its path.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Atmospheric Revolution', 'Chongyun', 2, 'Cryo',
                                    'Elemental Skills and Elemental Bursts cast within the Frost Field created by ' +
                                    'Spirit Blade: Chonghua\'s Layered Frost have their CD time decreased by 15%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Cloudburst', 'Chongyun', 3, 'Cryo',
                                    'Increases the Level of Spirit Blade: Cloud-Parting Star by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Frozen Skies', 'Chongyun', 4, 'Cryo',
                                    'Chongyun regenerates 1 Energy every time he hits an opponent affected by Cryo.' + char(10) +
                                    'This effect can only occur once every 2s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('The True Path', 'Chongyun', 5, 'Cryo',
                                    'Increases the Level of Spirit Blade: Chonghua\'s Layered Frost by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Rally of Four Blades', 'Chongyun', 6, 'Cryo',
                                    'Spirit Blade: Cloud-Parting Star deals 15% more DMG to opponents with a lower ' +
                                    'percentage of their Max HP remaining than Chongyun.' + char(10) +
                                    'This skill will also summon 1 additional spirit blade.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Conviction', 'Diluc', 1, 'Pyro',
                                    'Diluc deals 15% more DMG to opponents whose HP is above 50%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Searing Ember', 'Diluc', 2, 'Pyro',
                                    'When Diluc takes DMG, his ATK increases by 10% and his ATK SPD increases by 5%. Lasts for 10s.' + char(10) +
                                    'This effect can stack up to 3 times and can only occur once every 1.5s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Fire and Steel', 'Diluc', 3, 'Pyro'
                                    'Increases the Level of Searing Onslaught by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Flowing Flame', 'Diluc', 4, 'Pyro',
                                    'Casting Searing Onslaught in rhythm greatly increases damage dealt.' + char(10) +
                                    '2s after casting Searing Onslaught, casting the next Searing Onslaught in the ' +
                                    'combo deals 40% additional DMG. This effect lasts for 2s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Phoenix, Harbinger of Dawn', 'Diluc', 5, 'Pyro',
                                    'Increases the Level of Dawn by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Flaming Sword, Nemesis of the Dark', 'Diluc', 6, 'Pyro',
                                    'After casting Searing Onslaught, the next 2 Normal Attacks within the next 6s ' +
                                    'will have their DMG and ATK SPD increased by 30%.' + char(10) +
                                    'Additionally, Searing Onslaught will not interrupt the Normal Attack combo.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('A Lingering Flavor', 'Diona', 1, 'Cryo',
                                    'Regenerates 15 Energy for Diona after the effects of Signature Mix end.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Shaken, Not Purred', 'Diona', 2, 'Cryo',
                                    'Increases Icy Paws\' DMG by 15%, and increases its shield\'s DMG Absorption by 15%.' + char(10) +
                                    'Additionally, when paws hit their targets, creates a shield for other nearby ' +
                                    'characters on the field with 50% of the Icy Paws shield\'s DMG Absorption for 5s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('A-Another Round?', 'Diona', 3, 'Cryo',
                                    'Increases the Level of Signature Mix by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Wine Industry Slayer', 'Diona', 4, 'Cryo',
                                    'Within the radius of Signature Mix, Diona\'s charge time for aimed shots is reduced by 60%', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Double Shot, On The Rocks', 'Diona', 5, 'Cryo',
                                    'Increases the Level of Icy Paws by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Cat\'s Tail Closing Time', 'Diona', 6, 'Cryo',
                                    'Characters within Signature Mix\'s radius will gain the following effects based on their HP amounts:' + char(10) +
                                    'Increases Incoming Healing Bonus by 30% when HP falls below or is equal to 50%.' + char(10) +
                                    'Elemental Mastery increased by 200 when HP is above 50%.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Tidal Illusion', 'Eula', 1, 'Cryo',
                                    'Every time Icetide Vortex\'s Grimheart stacks are consumed, Eula\'s Physical DMG is increased by 30% for 6s.' + char(10) +
                                    'Each stack consumed will increase the duration of this effect by 6s up to a maximum of 18s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lady of Seafoam', 'Eula', 2, 'Cryo',
                                    'Decreases the CD of Icetide Vortex\'s Holding Mode, rendering it identical to Tapping CD.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lawrence Pedigree', 'Eula', 3, 'Cryo',
                                    'Increases the Level of Glacial Illumination by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('The Obstinacy of One\'s Inferiors', 'Eula', 4, 'Cryo',
                                    'Lightfall Swords deal 25% increased DMG against opponents with less than 50% HP.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Chivalric Quality', 'Eula', 5, 'Cryo',
                                    'Increases the Level of Icetide Vortex by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Noble Obligation', 'Eula', 6, 'Cryo',
                                    'Lightfall Swords created by Glacial Illumination start with 5 stacks of energy. ' +
                                    'Normal Attacks, Elemental Skills, and Elemental Bursts have a 50% chance to ' +
                                    'grant the Lightfall Sword an additional stack of energy.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Gaze of the Deep', 'Fischl', 1, 'Electro',
                                    'Even when Oz is not present in combat, he can still watch over Fischl through ' +
                                    'his raven eyes. When Fischl performs a Normal Attack against an opponent, Oz ' +
                                    'fires a joint attack, dealing DMG equal to 22% of Fischl\'s ATK.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Devourer of All Sins', 'Fischl', 2, 'Electro',
                                    'When Nightrider is used, it deals an additional 200% ATK as DMG, and its AoE is ' +
                                    'increased by 50%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Wings of Nightmare', 'Fischl', 3, 'Electro',
                                    'Increases the Level of Nightrider by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Her Pilgrimage of Bleak', 'Fischl', 4, 'Electro',
                                    'When Midnight Phantasmagoria is used, it deals 222% of ATK as Electro DMG to surrounding opponents.' + char(10) +
                                    'When the skill duration ends, Fischl regenerates 20% of her HP.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Against the Fleeing Light', 'Fischl', 5, 'Electro',
                                    'Increases the Level of Midnight Phantasmagoria by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Evernight Raven', 'Fischl', 6, 'Electro',
                                    'Extends the duration of Oz\'s presence on the field by 2s. Additionally, Oz ' +
                                    'performs joint attacks with your active character when present, dealing 30% of ' +
                                    'Fischl\'s ATK as Electro DMG.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Dew-Drinker', 'Ganyu', 1, 'Cryo',
                                    'Taking DMG from a Charge Level 2 Frostflake Arrow or Frostflake Arrow Bloom decreases opponents\' Cryo RES by 15% for 6s.' + char(10) ""
                                    'A hit regenerates 2 Energy for Ganyu. This effect can only occur once per Charge ' +
                                    'Level 2 Frostflake Arrow, regardless if Frostflake Arrow itself or its Bloom hit the target.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('The Auspicious', 'Ganyu', 2, 'Cryo',
                                    'Trail of the Qilin gains 1 additional charge.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Cloud-Strider', 'Ganyu', 3, 'Cryo',
                                    'Increases the Level of Celestial Shower by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Cryo', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Westward Sojourn', 'Ganyu', 4, 'Cryo',
                                    'Opponents standing within the AoE of Celestial Shower take increased DMG. This effect strengthens over time.' + char(10) +
                                    'Increased DMG taken begins at 5% and increases by 5% every 3s, up to a maximum of 25%.' + char(10) +
                                    'The effect lingers for 3s after the opponent leaves the AoE.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('The Merciful', 'Ganyu', 5, 'Cryo',
                                    'Increases the Level of Trail of the Qilin by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('The Clement', 'Ganyu', 6, 'Cryo',
                                    'Using Trail of the Qilin causes the next Frostflake Arrow shot within 30s to not ' +
                                    'require charging.',  'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Crimson Bouquet', 'Hu Tao', 1, 'Pyro',
                                    'While in a Paramita Papilio state activated by Guide to Afterlife, Hu Tao\'s ' +
                                    'Charge Attacks do not consume Stamina.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Ominous Rainfall', 'Hu Tao', 2, 'Pyro',
                                    'Increases the Blood Blossom DMG by an amount equal to 10% of Hu Tao\'s Max HP at the time the effect is applied.' + char(10) +
                                    'Additionally, Spirit Soother will also apply the Blood Blossom effect.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lingering Carmine', 'Hu Tao', 3, 'Pyro',
                                    'Increases the Level of Guide to Afterlife by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Garden of Eternal Rest', 'Hu Tao', 4, 'Pyro',
                                    'Upon defeating an enemy affected by a Blood Blossom that Hu Tao applied herself, ' +
                                    'all nearby allies in the party (excluding Hu Tao herself) will have their CRIT ' +
                                    'Rate increased by 12% for 15s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Floral Incense', 'Hu Tao', 5, 'Pyro',
                                    'Increases the Level of Spirit Soother by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Butterfly\'s Embrace', 'Hu Tao', 6, 'Pyro',
                                    'Triggers when Hu Tao\'s HP drops below 25%, or when she suffers a lethal strike:' + char(10) +
                                    'Hu Tao will not fall as a result of the DMG sustained. Additionally, for the ' +
                                    'next 10s, all of her Elemental and Physical RES is increased by 200%, her CRIT ' +
                                    'Rate is increased by 100%, and her resistance to interruption is greatly increased.' + char(10) +
                                    'This effect triggers automatically when Hu Tao has 1 HP left.' + char(10) +
                                    'Can only occur once every 60s.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Spiraling Tempest', 'Jean', 1, 'Anemo',
                                    'Increases the pulling speed of Gale Blade after holding for more than 1s, and ' +
                                    'increases the DMG dealt by 40%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('People\'s Aegis', 'Jean', 2, 'Anemo',
                                    'When Jean picks up an Elemental Orb/Particle, all party members have their ' +
                                    'Movement SPD and ATK SPD increased by 15% for 15s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('When the West Wind Arises', 'Jean', 3, 'Anemo',
                                    'Increases the Level of Dandelion Breeze by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lands of Dandelion', 'Jean', 4, 'Anemo',
                                    'Within the Field created by Dandelion Breeze, all opponents have their Anemo RES decreased by 40%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Outbursting Gust', 'Jean', 5, 'Anemo',
                                    'Increases the Level of Gale Blade by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lion\'s Fang, Fair Protector of Mondstadt', 'Jean', 6, 'Anemo',
                                    'Incoming DMG is decreased by 35% within the Field created by Dandelion Breeze. ' +
                                    'Upon leaving the Dandelion Field, this effect lasts for 3 attacks or 10s.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Scarlet Hills', 'Kaedehara Kazuha', 1, 'Anemo',
                                    'Decreases Chihayaburu\'s CD by 10%.' + char(10) +
                                    'Using Kazuha Slash resets the CD of Chihayaburu.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Yamaarashi Tailwind', 'Kaedehara Kazuha', 2, 'Anemo',
                                    'The Autumn Whirlwind field created by Kazuha Slash has the following effects:' + char(10) +
                                    'Increases Kaedehara Kazuha\'s own Elemental Mastery by 200.' + char(10) +
                                    'Increases the Elemental Mastery of characters within the field by 200.' + char(10) +
                                    'The Elemental Mastery-increasing effects of this Constellation do not stack.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Maple Monogatari', 'Kaedehara Kazuha', 3, 'Anemo',
                                    'Increases the Level of Chihayaburu by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Oozora Genpou', 'Kaedehara Kazuha', 4, 'Anemo',
                                    'When Kaedehara Kazuha\'s Energy is lower than 45, he obtains the following effects:' + char(10) +
                                    'Pressing or Holding Chihayaburu regenerates 3 or 4 Energy for Kaedehara Kazuha, respectively.' + char(10) +
                                    'When gliding, Kaedehara Kazuha regenerates 2 Energy per second.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Wisdom of Bansei', 'Kaedehara Kazuha', 5, 'Anemo',
                                    'Increases the Level of Kazuha Slash by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Crimson Momiji', 'Kaedehara Kazuha', 6, 'Anemo',
                                    'After using Chihayaburu or Kazuha Slash, Kaedehara Kazuha gains an Anemo ' +
                                    'Infusion for 5s. Additionally, each point of Elemental Mastery will increase the ' +
                                    'DMG dealt by Kaedehara Kazuha\'s Normal, Charged, and Plunging Attack by 0.2%.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Excellent Blood', 'Kaeya', 1, 'Cryo',
                                    'The CRIT Rate of Kaeya\'s Normal and Charge Attacks against opponents affected ' +
                                    'by Cryo is increased by 15%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Never-Ending Performance', 'Kaeya', 2, 'Cryo',
                                    'Every time Glacial Waltz defeats an opponent during its duration, its duration is increased by 2.5s, up to a maximum of 15s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Dance of Frost', 'Kaeya', 3, 'Cryo',
                                    'Increases the Level of Frostgnaw by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Frozen Kiss', 'Kaeya', 4, 'Cryo',
                                    'Triggers automatically when Kaeya\'s HP falls below 20%:' + char(10) +
                                    'Creates a shield that absorbs damage equal to 30% of Kaeya\'s Max HP. Lasts for 20s.' + char(10) +
                                    'This shield absorbs Cryo DMG with 250% efficiency.' + char(10) +
                                    'Can only occur once every 60s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Frostbiting Embrace', 'Kaeya', 5, 'Cryo',
                                    'Increases the Level of Glacial Waltz by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Glacial Whirlwind', 'Kaeya', 6, 'Cryo',
                                    'Glacial Waltz will generate 1 additional icicle, and will regenerate 15 Energy when cast.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Snowswept Sakura', 'Kamisato Ayaka', 1, 'Cryo',
                                    'When Kamisato Ayaka\'s Normal or Charged Attacks deal Cryo DMG to opponents, it ' +
                                    'has a 50% chance of decreasing the CD of Kamisato Art: Hyouka by 0.3s. This ' +
                                    'effect can occur once every 0.1s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Blizzard Blade Seki no To', 'Kamisato Ayaka', 2, 'Cryo',
                                    'When casting Kamisato Art: Soumetsu, unleashes 2 smaller additional Frostflake ' +
                                    'Seki no To, each dealing 20% of the original storm\'s DMG.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Frostbloom Kamifubuki', 'Kamisato Ayaka', 3, 'Cryo',
                                    'Increases the Level of Kamisato Art: Soumetsu by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Ebb and Flow', 'Kamisato Ayaka', 4, 'Cryo',
                                    'Opponents damaged by Kamisato Art: Soumetsu\'s Frostflake Seki no To will have ' +
                                    'their DEF decreased by 30% for 6s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Blossom Cloud Irutsuki', 'Kamisato Ayaka', 5, 'Cryo',
                                    'Increases the Level of Kamisato Art: Hyouka by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Dance of Suigetsu', 'Kamisato Ayaka', 6, 'Cryo',
                                    'Kamisato Ayaka gains Usurahi Butou every 10s, increasing her Charged Attack DMG ' +
                                    'by 298%. This buff will be cleared 0.5s after Ayaka\'s Charged ATK hits an ' +
                                    'opponent, after which the timer for this ability will restart.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Thundering Might', 'Keqing', 1, 'Electro',
                                    'Recasting Stellar Restoration while a Lightning Stiletto is present causes ' +
                                    'Keqing to deal 50% of her ATK as AoE Electro DMG at the start point and terminus ' +
                                    'of her Blink.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Keen Extraction', 'Keqing', 2, 'Electro',
                                    'When Keqing\'s Normal and Charged Attacks hit opponents affected by Electro, ' +
                                    'they have a 50% chance of producing an Elemental Particle.' + char(10) +
                                    'This effect can only occur once every 5s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Foreseen Reformation', 'Keqing', 3, 'Electro',
                                    'Increases the Level of Starward Sword by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Attunement', 'Keqing', 4, 'Electro',
                                    'For 10s after Keqing triggers an Electro-related Elemental Reaction, her ATK is increased by 25%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Beckoning Stars', 'Keqing', 5, 'Electro',
                                    'Increases the Level of Stellar Restoration by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Tenacious Star', 'Keqing', 6, 'Electro',
                                    'When initiating a Normal Attack, a Charged Attack, Elemental Skill or Elemental ' +
                                    'Burst, Keqing gains a 6% Electro DMG Bonus for 8s.' + char(10) +
                                    'Effects triggered by Normal Attacks, Charged Attacks, Elemental Skills and ' +
                                    'Elemental Bursts are considered independent entities.', 'Five Star Stella Fortuna';

INSERT INTO constellation VALUES ('Chained Reactions', 'Klee', 1, 'Pyro',
                                    'Attacks and Skills have a certain chance to summon sparks that bombard ' +
                                    'opponents, dealing DMG equal to 120% of Sparks \'n\' Splash\'s DMG.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Explosive Frags', 'Klee', 2, 'Pyro',
                                    'Being hit by Jumpy Dumpty\'s mines decreases opponents\' DEF by 23% for 10s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Exquisite Compound', 'Klee', 3, 'Pyro',
                                    'Increases the Level of Jumpy Dumpty by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Sparkly Explosion', 'Klee', 4, 'Pyro',
                                    'If Klee leaves the field during the duration of Sparks \'n\' Splash, her ' +
                                    'departure triggers an explosion that deals 555% of her ATK as AoE Pyro DMG.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Nova Burst', 'Klee', 5, 'Pyro',
                                    'Increases the Level of Sparks \'n\' Splash by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Blazing Delight', 'Klee', 6, 'Pyro',
                                    'While under the effects of Sparks \'n\' Splash, Klee will regenerate 3 Energy for
                                    'all members of the party (excluding Klee) every 3s.' + char(10) +
                                    'When Sparks \'n\' Splash is used, all party members will gain a 10% Pyro DMG Bonus for 25s.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Crow\'s Eye', 'Kujou Sara', 1, 'Electro',
                                    'When Tengu Juurai grant characters ATK Bonuses or hits opponents, the CD of Tengu Stormcall is decreased by 1s.' + char(10) +
                                    'This effect can be triggered once every 3s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Dark Wings', 'Kujou Sara', 2, 'Electro',
                                    'Unleashing Tengu Stormcall will leave a weaker Crowfeather at Kujou Sara\'s ' +
                                    'original position that will deal 30% of its original DMG.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('The War Within', 'Kujou Sara', 3, 'Electro',
                                    'Increases the Level of Subjugation: Koukou Sendou by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Conclusive Proof', 'Kujou Sara', 4, 'Electro',
                                    'The number of Tengu Juurai: Stormcluster released by Subjugation: Koukou Sendou ' +
                                    'is increased to 6.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Spellsinger', 'Kujou Sara', 5, 'Electro',
                                    'Increases the Level of Tengu Stormcall by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Sin of Pride', 'Kujou Sara', 6, 'Electro',
                                    'The Electro DMG of characters who have had their ATK increased by Tengu Juurai ' +
                                    'has its Crit DMG increased by 60%.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Infinite Circuit', 'Lisa', 1, 'Electro',
                                    'Lisa regenerates 2 Energy for every opponent hit while holding Violet Arc.' + char(10) +
                                    'A maximum of 10 Energy can be regenerated in this manner at any one time.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Electromagnetic Field', 'Lisa', 2, 'Electro',
                                    'Holding Violet Arc has the following effects:' + char(10) +
                                    'Increases DEF by 25%.' + char(10) +
                                    'Increases Lisa\'s resistance to interruption.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Resonant Thunder', 'Lisa', 3, 'Electro',
                                    'Increases the Level of Lightning Rose by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Plasma Eruption', 'Lisa', 4, 'Electro',
                                    'Increases the number of lightning bolts released by Lightning Rose by 1-3.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Electrocute', 'Lisa', 5, 'Electro',
                                    'Increases the Level of Violet Arc by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Pulsating Witch', 'Lisa', 6, 'Electro',
                                    'When Lisa takes the field, she applies 3 stacks of Violet Arc\'s Conductive status onto nearby opponents.' + char(10) +
                                    'This effect can only occur once every 5s.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Prophecy of Submersion', 'Mona', 1, 'Hydro',
                                    'When any of your own party members hits an opponent affected by an Omen, the ' +
                                    'effects of Hydro-related Elemental Reactions are enhanced for 8s:' + char(10) +
                                    'Electro-Charged DMG increases by 15%.' + char(10) +
                                    'Vaporize DMG increases by 15%.' + char(10) +
                                    'Hydro Swirl DMG increases by 15%.' + char(10) +
                                    'Frozen duration is extended by 15%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lunar Chain', 'Mona', 2, 'Hydro',
                                    'When a Normal Attack hits, there is a 20% chance that it will be automatically ' +
                                    'followed by a Charged Attack.' + char(10) +
                                    'This effect can only occur once every 5s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Restless Revolution', 'Mona', 3, 'Hydro',
                                    'Increases the Level of Stellaris Phantasm by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Prophecy of Oblivion', 'Mona', 4, 'Hydro',
                                    'When any party member attacks an opponent affected by an Omen, their CRIT Rate is increased by 15%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Mockery of Fortuna', 'Mona', 5, 'Hydro',
                                    'Increases the Level of Mirror Reflection of Doom by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Rhetorics of Calamitas', 'Mona', 6, 'Hydro',
                                    'Upon entering Illusory Torrent, Mona gains a 60% increase to the DMG of her next ' +
                                    'Charged Attack per second of movement.' + char(10) +
                                    'A maximum DMG Bonus of 180% can be achieved in this manner. The effect lasts for ' +
                                    'no more than 8s.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Piercing Fragments', 'Ningguang', 1, 'Geo',
                                    'When a Normal Attack hits, it deals AoE DMG.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Shock Effect', 'Ningguang', 2, 'Geo',
                                    'When Jade Screen is shattered, its CD will reset.' + char(10) +
                                    'Can occur once every 6s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Majesty be the Array of Stars', 'Ningguang', 3, 'Geo',
                                    'Increases the Level of Starshatter by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Exquisite be the Jade, Outshining All Beneath', 'Ningguang', 4, 'Geo',
                                    'Jade Screen increases nearby characters' Elemental RES by 10%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Invincible be the Jade Screen', 'Ningguang', 5, 'Geo',
                                    'Increases the Level of Jade Screen by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Grandeur be the Seven Stars', 'Ningguang', 6, 'Geo',
                                    'When Starshatter is used, Ningguang gains 7 Star Jades.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('I Got Your Back', 'Noelle', 1, 'Geo',
                                    'While Sweeping Time and Breastplate are both in effect, the chance of ' +
                                    'Breastplate\'s healing effects activating is increased to 100%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Combat Maid', 'Noelle', 2, 'Geo',
                                    'Decreases the Stamina Consumption of Noelle\'s Charged Attacks by 20% and ' +
                                    'increases her Charged Attack DMG by 15%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Invulnerable Maid', 'Noelle', 3, 'Geo',
                                    'Increases the Level of Breastplate by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('To Be Cleaned', 'Noelle', 4, 'Geo',
                                    'When Breastplate\'s duration expires or it is destroyed by DMG, it will deal ' +
                                    '400% ATK of Geo DMG to surrounding opponents.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Favonius Sweeper Master', 'Noelle', 5, 'Geo',
                                    'Increases the Level of Sweeping Time by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Must Be Spotless', 'Noelle', 6, 'Geo',
                                    'Sweeping Time increases Noelle\'s ATK by an additional 50% of her DEF.' + char(10) +
                                    'Additionally, every opponent defeated during the skill\'s duration adds 1s to ' +
                                    'the duration, up to 10s.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Ascetics of Frost', 'Qiqi', 1, 'Cryo',
                                    'When the Herald of Frost hits an opponent marked by a Fortune-Preserving ' +
                                    'Talisman, Qiqi regenerates 2 Energy.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Frozen to the Bone', 'Qiqi', 2, 'Cryo',
                                    'Qiqi\'s Normal and Charge Attack DMG against opponents affected by Cryo is ' +
                                    'increased by 15%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Ascendant Praise', 'Qiqi', 3, 'Cryo',
                                    'Increases the Level of Adeptus Art: Preserver of Fortune by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Divine Suppression', 'Qiqi', 4, 'Cryo',
                                    'Targets marked by the Fortune-Preserving Talisman have their ATK decreased by 20%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Crimson Lotus Bloom', 'Qiqi', 5, 'Cryo',
                                    'Increases the Level of Adeptus Art: Herald of Frost by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Rite of Resurrection', 'Qiqi', 6, 'Cryo',
                                    'Using Adeptus Art: Preserver of Fortune revives all fallen party members nearby and regenerates 50% of their HP.' + char(10) +
                                    'This effect can only occur once every 15 mins.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Ominous Inscription', 'Raiden Shogun', 1, 'Electro',
                                    'Chakra Desiderata will gather Resolve even faster. When Electro characters use ' +
                                    'their Elemental Bursts, the Resolve gained is increased by 80%. When characters ' +
                                    'of other Elemental Types use their Elemental Bursts, the Resolve gained is increased by 20%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Steelbreaker', 'Raiden Shogun', 2, 'Electro',
                                    'Secret Art: Musou Shinsetsu\'s Musou no Hitotachi and Musou Isshin attacks will ' +
                                    'ignore 60% of opponents\' DEF.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Shinkage Bygones', 'Raiden Shogun', 3, 'Electro',
                                    'Increases the Level of Secret Art: Musou Shinsetsu by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Pledge of Propriety', 'Raiden Shogun', 4, 'Electro',
                                    'When the Musou Isshin state applied by Secret Art: Musou Shinsetsu expires, all ' +
                                    'nearby party members (excluding the Raiden Shogun) gain 30% bonus ATK for 10s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Shogun\'s Descent', 'Raiden Shogun', 5, 'Electro',
                                    'Increases the Level of Transcendence: Baleful Omen by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Wishbearer', 'Raiden Shogun', 6, 'Electro',
                                    'While in the Musou Isshin state applied by Secret Art: Musou Shinsetsu, attacks ' +
                                    'by the Raiden Shogun that are considered part of her Elemental Burst will ' +
                                    'decrease all nearby party members\' (but not including the Raiden Shogun
                                    'herself) Elemental Burst CD by 1s when they hit opponents.' + char(10) +
                                    'This effect can trigger once every 1s, and can trigger a total of 5 times during the state\'s duration.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Wolf\'s Instinct', 'Razor', 1, 'Electro',
                                    'Picking up an Elemental Orb or Particle increases Razor\'s DMG by 10% for 8s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Suppression', 'Razor', 2, 'Electro',
                                    'Increases CRIT Rate against opponents with less than 30% HP by 10%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Soul Companion', 'Razor', 3, 'Electro',
                                    'Increases the Level of Lightning Fang by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Bite', 'Razor', 4, 'Electro',
                                    'When casting Claw and Thunder (Press), opponents hit will have their DEF ' +
                                    'decreased by 15% for 7s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Sharpened Claws', 'Razor', 5, 'Electro',
                                    'Increases the Level of Claw and Thunder by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lupus Fulguris', 'Razor', 6, 'Electro',
                                    'Every 10s, Razor\'s sword charges up, causing the next Normal Attack to release ' +
                                    'lightning that deals 100% of Razor\'s ATK as Electro DMG.' + char(10) +
                                    'When Razor is not using Lightning Fang, a lightning strike on an opponent will ' +
                                    'grant Razor an Electro Sigil for Claw and Thunder.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Unholy Revelation', 'Rosaria', 1, 'Cryo',
                                    'When Rosaria deals a CRIT Hit, her ATK SPD increases by 10% and her Normal ' +
                                    'Attack DMG increases by 10% for 4s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Land Without Promise', 'Rosaria', 2, 'Cryo',
                                    'The duration of the Ice Lance created by Rites of Termination is increased by 4s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('The Wages of Sin', 'Rosaria', 3, 'Cryo',
                                    'Increases the Level of Ravaging Confession by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Painful Grace', 'Rosaria', 4, 'Cryo',
                                    'Ravaging Confession\'s CRIT Hits regenerate 5 Energy for Rosaria.' + char(10) +
                                    'Can only be triggered once each time Ravaging Confession is cast.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Last Rites', 'Rosaria', 5, 'Cryo',
                                    'Increases the Level of Rites of Termination by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Divine Retribution', 'Rosaria', 6, 'Cryo',
                                    'Rites of Termination\'s attack decreases opponents\' Physical RES by 20% for 10s.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Multi-Task no Jutsu', 'Sayu', 1, 'Anemo',
                                    'The Muji-Muji Daruma created by Yoohoo Art: Mujina Flurry will ignore HP limits ' +
                                    'and can simultaneously attack nearby opponents and heal characters.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Egress Prep', 'Sayu', 2, 'Anemo',
                                    'Yoohoo Art: Fuuin Dash gains the following effects:' + char(10) +
                                    'DMG of Fuufuu Whirlwind Kick in Press Mode increased by 3.3%.' + char(10) +
                                    'Every 0.5s in the Fuufuu Windwheel state will increase the DMG of this Fuufuu ' +
                                    'Whirlwind Kick by 3.3%. The maximum DMG increase possible through this method is 66%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Eh, the Bunshin Can Handle It', 'Sayu', 3, 'Anemo',
                                    'Increases the Level of Yoohoo Art: Mujina Flurry by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Skiving: New and Improved', 'Sayu', 4, 'Anemo',
                                    'Sayu recovers 1.2 Energy when she triggers a Swirl reaction.' + char(10) +
                                    'This effect occurs once every 2s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Speed Comes First', 'Sayu', 5, 'Anemo',
                                    'Increases the Level of Yoohoo Art: Fuuin Dash by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Sleep O\'Clock', 'Sayu', 6, 'Anemo',
                                    'The Muji-Muji Daruma created by Sayu\'s Yoohoo Art: Mujina Flurry will now also ' +
                                    'benefit from her Elemental Mastery. Each point of Sayu\'s Elemental Mastery will ' +
                                    'produce the following effects:' + char(10) +
                                    'Increases the damage dealt by the Muji-Muji Daruma\'s attacks by 0.2% ATK. A ' +
                                    'maximum of 400% ATK can be gained via this method.' + char(10) +
                                    'Increases the HP restored by the Muji-Muji Daruma by 3. A maximum of 6,000 ' +
                                    'additional HP can be restored in this manner.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Clustered Vacuum Field', 'Sucrose', 1, 'Anemo',
                                    'Astable Anemohypostasis Creation - 6308 gains 1 additional charge.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Beth: Unbound Form', 'Sucrose', 2, 'Anemo',
                                    'The duration of Forbidden Creation - Isomer 75 / Type II is increased by 2s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Flawless Alchemistress', 'Sucrose', 3, 'Anemo',
                                    'Increases the Level of Astable Anemohypostasis Creation - 6308 by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Alchemania', 'Sucrose', 4, 'Anemo',
                                    'Every 7 Normal and Charged Attacks, Sucrose will reduce the CD of Astable ' +
                                    'Anemohypostasis Creation - 6308 by 1-7s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Caution: Standard Flask', 'Sucrose', 5, 'Anemo',
                                    'Increases the Level of Forbidden Creation - Isomer 75 / Type II by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Chaotic Entropy', 'Sucrose', 6, 'Anemo',
                                    'If Forbidden Creation - Isomer 75 / Type II triggers an Elemental Absorption, ' +
                                    'all party members gain a 20% Elemental DMG Bonus for the corresponding absorbed ' +
                                    'element during its duration.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Foul Legacy: Tide Withholder', 'Tartaglia', 1, 'Hydro',
                                    'Decreases the CD of Foul Legacy: Raging Tide by 20%', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Foul Legacy: Understream', 'Tartaglia', 2, 'Hydro',
                                    'When opponents affected by Riptide are defeated, Tartaglia regenerates 4 Elemental Energy.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Abyssal Mayhem: Vortex of Turmoil', 'Tartaglia', 3, 'Hydro',
                                    'Increases the Level of Foul Legacy: Raging Tide by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Abyssal Mayhem: Hydrospout', 'Tartaglia', 4, 'Hydro',
                                    'If Tartaglia is in Foul Legacy: Raging Tide\'s Melee Stance, triggers Riptide ' +
                                    'Slash against opponents on the field affected by Riptide every 4s, otherwise, triggers Riptide Flash.' + char(10) +
                                    'Riptide Slashes and Riptide Flashes triggered by this Constellation effect are ' +
                                    'not subject to the time intervals that would typically apply to these two ' +
                                    'Riptide effects, nor do they have any effect on those time intervals.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Havoc: Formless Blade', 'Tartaglia', 5, 'Hydro',
                                    'Increases the Level of Havoc: Obliteration by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Havoc: Annihilation', 'Tartaglia', 6, 'Hydro',
                                    'When Havoc: Obliteration is cast in Melee Stance, the CD of Foul Legacy: Raging Tide is reset.' + char(10) +
                                    'This effect will only take place once Tartaglia returns to his Ranged Stance.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Splitting Gales', 'Venti', 1, 'Anemo',
                                    'Fires 2 additional arrows per Aimed Shot, each dealing 33% of the original arrow\'s DMG.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Breeze of Reminiscence', 'Venti', 2, 'Anemo',
                                    'Skyward Sonnet decreases opponents\' Anemo RES and Physical RES by 12% for 10s.' + char(10) +
                                    'Opponents launched by Skyward Sonnet suffer an additional 12% Anemo RES and ' +
                                    'Physical RES decrease while airborne.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Ode to Thousand Winds', 'Venti', 3, 'Anemo',
                                    'Increases the Level of Wind\'s Grand Ode by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Hurricane of Freedom', 'Venti', 4, 'Anemo',
                                    'When Venti picks up an Elemental Orb or Particle, he receives a 25% Anemo DMG Bonus for 10s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Concerto dal Cielo', 'Venti', 5, 'Anemo',
                                    'Increases the Level of Skyward Sonnet by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Storm of Defiance', 'Venti', 6, 'Anemo',
                                    'Targets who take DMG from Wind\'s Grand Ode have their Anemo RES decreased by 20%.' + char(10) +
                                    'If an Elemental Absorption occurred, then their RES towards the corresponding ' +
                                    'Element is also decreased by 20%.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Crispy Outside, Tender Inside', 'Xiangling', 1, 'Pyro',
                                    'Opponents hit by Guoba\'s attacks have their Pyro RES reduced by 15% for 6s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Oil Meets Fire', 'Xiangling', 2, 'Pyro',
                                    'The last attack in a Normal Attack sequence applies the Implode status onto the ' +
                                    'opponent for 2s. An explosion will occur once this duration ends, dealing 75% of ' +
                                    'Xiangling\'s ATK as AoE Pyro DMG.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Deepfry', 'Xiangling', 3, 'Pyro',
                                    'Increases the Level of Pyronado by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Slowbake', 'Xiangling', 4, 'Pyro',
                                    'Pyronado\'s duration is increased by 40%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Guoba Mad', 'Xiangling', 5, 'Pyro',
                                    'Increases the Level of Guoba Attack by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Condensed Pyronado', 'Xiangling', 6, 'Pyro',
                                    'For the duration of Pyronado, all party members receive a 15% Pyro DMG Bonus.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Dissolution Eon: Destroyer of Worlds', 'Xiao', 1, 'Anemo',
                                    'Increases Lemniscatic Wind Cycling\'s charges by 1.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Annihilation Eon: Blossom of Kaleidos', 'Xiao', 2, 'Anemo',
                                    'When in the party and not on the field, Xiao\'s Energy Recharge is increased by 25%.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Conqueror of Evil: Wrath Deity', 'Xiao', 3, 'Anemo',
                                    'Increases the Level of Lemniscatic Wind Cycling by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Transcension: Extinction of Suffering', 'Xiao', 4, 'Anemo',
                                    'When Xiao\'s HP falls below 50%, he gains a 100% DEF Bonus.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Evolution Eon: Origin of Ignorance', 'Xiao', 5, 'Anemo',
                                    'Increases the Level of Bane of All Evil by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Conqueror of Evil: Guardian Yaksha', 'Xiao', 6, 'Anemo',
                                    'While under the effects of Bane of All Evil, hitting at least 2 opponents with ' +
                                    'Xiao\'s Plunging Attack will immediately grant him 1 charge of Lemniscatic Wind ' +
                                    'Cycling, and for the next 1s, he may use Lemniscatic Wind Cycling while ignoring its CD.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('The Scent Remained', 'Xingqiu', 1, 'Hydro',
                                    'Increases the maximum number of Rain Swords by 1.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Rainbow Upon the Azure Sky', 'Xingqiu', 2, 'Hydro',
                                    'Extends the duration of Guhua Sword: Raincutter by 3s.' + char(10) +
                                    'Decreases the Hydro RES of opponents hit by sword rain attacks by 15% for 4s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Weaver of Verses', 'Xingqiu', 3, 'Hydro',
                                    'Increases the Level of Guhua Sword: Raincutter by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Evilsoother', 'Xingqiu', 4, 'Hydro',
                                    'Throughout the duration of Guhua Sword: Raincutter, the DMG dealt by Guhua ' +
                                    'Sword: Fatal Rainscreen is increased by 50%.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Embrace of Rain', 'Xingqiu', 5, 'Hydro',
                                    'Increases the Level of Guhua Sword: Fatal Rainscreen by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Hence, Call Them My Own Verses', 'Xingqiu', 6, 'Hydro',
                                    'Activating 2 of Guhua Sword: Raincutter\'s sword rain attacks greatly enhances ' +
                                    'the third sword rain attack. On hit, the third sword rain attack also ' +
                                    'regenerates 3 Energy for Xingqiu.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Fatal Acceleration', 'Xinyan', 1, 'Pyro',
                                    'Upon scoring a CRIT Hit, increases ATK SPD of Xinyan\'s Normal and Charged Attacks by 12% for 5s.' + char(10) +
                                    'Can only occur once every 5s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Impromptu Opening', 'Xinyan', 2, 'Pyro',
                                    'Riff Revolution\'s Physical DMG has its CRIT Rate increased by 100%, and will ' +
                                    'form a shield at Shield Level 3: Rave when cast.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Double-Stop', 'Xinyan', 3, 'Pyro',
                                    'Increases the Level of Sweeping Fervor by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Wildfire Rhythm', 'Xinyan', 4, 'Pyro',
                                    'Sweeping Fervor\'s swing DMG decreases opponent\'s Physical RES by 15% for 12s.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Screamin\' for an Encore', 'Xinyan', 5, 'Pyro',
                                    'Increases the Level of Riff Revolution by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Rockin\' in a Flaming World', 'Xinyan', 6, 'Pyro',
                                    'Decreases the Stamina Consumption of Xinyan\'s Charged Attacks by 30%. ' +
                                    'Additionally, Xinyan\'s Charged Attacks gain an ATK Bonus equal to 50% of her DEF.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('The Law Knows No Kindness', 'Yanfei', 1, 'Pyro',
                                    'When Yanfei uses her Charged Attack, each existing Scarlet Seal additionally ' +
                                    'reduces the stamina cost of this Charged Attack by 10% and increases resistance ' +
                                    'against interruption during its release.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Right of Final Interpretation', 'Yanfei', 2, 'Pyro',
                                    'Increases Yanfei\'s Charged Attack CRIT Rate by 20% against enemies below 50% HP.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Samadhi Fire-Forged', 'Yanfei', 3, 'Pyro',
                                    'Increases the Level of Signed Edict by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Supreme Amnesty', 'Yanfei', 4, 'Pyro',
                                    'When Done Deal is used:' + char(10) +
                                    'Creates a shield that absorbs up to 45% of Yanfei\'s Max HP for 15s.' + char(10) +
                                    'This shield absorbs Pyro DMG 250% more effectively.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Abiding Affidavit', 'Yanfei', 5, 'Pyro',
                                    'Increases the Level of Done Deal by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Four Star Stella Fortuna');
INSERT INTO constellation VALUES ('Extra Clause', 'Yanfei', 6, 'Pyro',
                                    'Increases the maximum number of Scarlet Seals by 1.', 'Four Star Stella Fortuna');

INSERT INTO constellation VALUES ('Agate Ryuukin', 'Yoimiya', 1, 'Pyro',
                                    'The Aurous Blaze created by Ryuukin Saxifrage lasts for an extra 4s.' + char(10) +
                                    'Additionally, when an opponent affected by Aurous Blaze is defeated within its ' +
                                    'duration, Yoimiya\'s ATK is increased by 20% for 20s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('A Procession of Bonfires', 'Yoimiya', 2, 'Pyro',
                                    'When Yoimiya\'s Pyro DMG scores a CRIT Hit, Yoimiya will gain a 25% Pyro DMG Bonus for 6s.' + char(10) +
                                    'This effect can be triggered even when Yoimiya is not the active character.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Trickster\'s Flare', 'Yoimiya', 3, 'Pyro',
                                    'Increase the Level of Niwabi Fire-Dance by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Pyrotechnic Professional', 'Yoimiya', 4, 'Pyro',
                                    'When Yoimiya\'s own Aurous Blaze triggers an explosion, Niwabi Fire-Dance\'s CD ' +
                                    'is decreased by 1.2.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('A Summer Festival\'s Eve', 'Yoimiya', 5, 'Pyro',
                                    'Increases the Level of Ryuukin Saxifrage by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Naganohara Meteor Swarm', 'Yoimiya', 6, 'Pyro',
                                    'During Niwabi Fire-Dance, Yoimiya\'s Normal Attacks have a 50% chance of firing ' +
                                    'an extra Kindling Arrow that deals 60% of its original DMG. This DMG is ' +
                                    'considered Normal Attack DMG.', 'Five Star Stella Fortuna');

INSERT INTO constellation VALUES ('Rock, the Backbone of Earth', 'Zhongli', 1, 'Geo',
                                    'Increases the maximum number of Stone Steles created by Dominus Lapidis that may ' +
                                    'exist simultaneously to 2.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Stone, the Cradle of Jade', 'Zhongli', 2, 'Geo',
                                    'Planet Befall grants nearby characters on the field a Jade Shield when it descends.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Jade, Shimmering through Darkness', 'Zhongli', 3, 'Geo',
                                    'Increases the Level of Dominus Lapidis by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Topaz, Unbreakable and Fearless', 'Zhongli', 4, 'Geo',
                                    'Increases Planet Befall\'s AoE by 20% and increases the duration of Planet ' +
                                    'Befall\'s Petrification effect by 2s.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Lazuli, Herald of the Order', 'Zhongli', 5, 'Geo',
                                    'Increases the Level of Planet Befall by 3.' + char(10) +
                                    'Maximum upgrade level is 15.', 'Five Star Stella Fortuna');
INSERT INTO constellation VALUES ('Chrysos, Bounty of Dominator', 'Zhongli', 6, 'Geo',
                                    'When the Jade Shield takes DMG, 40% of that incoming DMG is converted to HP for the current character.' + char(10) +
                                    'A single instance of regeneration cannot exceed 8% of that character\'s Max HP.', 'Five Star Stella Fortuna');

-- ascensions

-- talents

-- talent levels

-- materials

-- local specialties
INSERT INTO local_specialty VALUES ('Calla Lily', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Cecilia', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Dandelion Seed', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Philanemo Mushroom', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Small Lamp Grass', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Valberry', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Windwheel Aster', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Wolfhook', 'Mondstadt');
INSERT INTO local_specialty VALUES ('Cor Lapis', 'Liyue');
INSERT INTO local_specialty VALUES ('Glaze Lily', 'Liyue');
INSERT INTO local_specialty VALUES ('Jueyun Chili', 'Liyue');
INSERT INTO local_specialty VALUES ('Notilucous Jade', 'Liyue');
INSERT INTO local_specialty VALUES ('Qingxin', 'Liyue');
INSERT INTO local_specialty VALUES ('Silk Flower', 'Liyue');
INSERT INTO local_specialty VALUES ('Starconch', 'Liyue');
INSERT INTO local_specialty VALUES ('Violetgrass', 'Liyue');
INSERT INTO local_specialty VALUES ('Amakumo Fruit', 'Inazuma');
INSERT INTO local_specialty VALUES ('Crystal Marrow', 'Inazuma');
INSERT INTO local_specialty VALUES ('Dendrobium', 'Inazuma');
INSERT INTO local_specialty VALUES ('Nadu Weed', 'Inazuma');
INSERT INTO local_specialty VALUES ('Onikabuto', 'Inazuma');
INSERT INTO local_specialty VALUES ('Sakura Bloom', 'Inazuma');
INSERT INTO local_specialty VALUES ('Sango Pearl', 'Inazuma');
INSERT INTO local_specialty VALUES ('Sea Ganoderma', 'Inazuma');

-- constellation activation materials
INSERT INTO constellation_activation_material VALUES ('Four Star Stella Fortuna');
INSERT INTO constellation_activation_material VALUES ('Five Star Stella Fortuna');
INSERT INTO constellation_activation_material VALUES ('Memory of Roving Gales');
INSERT INTO constellation_activation_material VALUES ('Memory of Immovable Crystals');
INSERT INTO constellation_activation_material VALUES ('Memory of Violet Flash');

-- common enemies
INSERT INTO common_enemy VALUES ('Large Slime', 'Mutant Electro', 'Between');
INSERT INTO common_enemy VALUES ('Whopperflower', 'Cryo', 'Before');
INSERT INTO common_enemy VALUES ('Whopperflower', 'Pyro', 'Before');
INSERT INTO common_enemy VALUES ('Whopperflower', 'Electro', 'Before');
INSERT INTO common_enemy VALUES ('Specter', 'Anemo', 'Before');
INSERT INTO common_enemy VALUES ('Specter', 'Geo', 'Before');
INSERT INTO common_enemy VALUES ('Specter', 'Hydro', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl', NULL, NULL);
INSERT INTO common_enemy VALUES ('Hilichurl', 'Fighter', 'After');
INSERT INTO common_enemy VALUES ('Hilichurl', 'Berserker', 'After');
INSERT INTO common_enemy VALUES ('Hilichurl Guard', 'Wooden Shield', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Guard', 'Rock Shield', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Guard', 'Ice Shield', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Grenadier', 'Pyro', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Grenadier', 'Cryo', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Grenadier', 'Electro', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Shooter', NULL, NULL);
INSERT INTO common_enemy VALUES ('Hilichurl Shooter', 'Electro', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Shooter', 'Cryo', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl Shooter', 'Pyro', 'Before');
INSERT INTO common_enemy VALUES ('Hilichurl', 'Unusual', 'Before');
INSERT INTO common_enemy VALUES ('Fatui Vanguard', 'Anemoboxer', 'Between');
INSERT INTO common_enemy VALUES ('Fatui Vanguard', 'Electrohammer', 'Between');
INSERT INTO common_enemy VALUES ('Fatui Bracer', 'Geochanter', 'Between');
INSERT INTO common_enemy VALUES ('Fatui Bracer', 'Pyroslinger', 'Between');
INSERT INTO common_enemy VALUES ('Fatui Legionnaire', 'Cryogunner', 'Between');
INSERT INTO common_enemy VALUES ('Fatui Legionnaire', 'Hydrogunner', 'Between');
INSERT INTO common_enemy VALUES ('Treasure Hoarder Potioneer', 'Electro', 'Between');
INSERT INTO common_enemy VALUES ('Treasure Hoarder Potioneer', 'Cryo', 'Between');
INSERT INTO common_enemy VALUES ('Treasure Hoarder Potioneer', 'Hydro', 'Between');
INSERT INTO common_enemy VALUES ('Treasure Hoarder Potioneer', 'Pyro', 'Between');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Crusher', 'After');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Gravedigger', 'After');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Marksman', 'After');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Scout', 'After');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Handyman', 'After');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Pugilist', 'After');
INSERT INTO common_enemy VALUES ('Treasure Hoarder', 'Seaman', 'After');
INSERT INTO common_enemy VALUES ('Nobushi', 'Dancing Thunder', 'After Colon');
INSERT INTO common_enemy VALUES ('Kairagi', 'Fiery Might', 'After Colon');

-- common ascension materials
INSERT INTO common_ascension_material VALUES ('Slime', 'Condensate');
INSERT INTO common_ascension_material VALUES ('Slime', 'Secretions');
INSERT INTO common_ascension_material VALUES ('Slime', 'Concentrate');
INSERT INTO common_ascension_material VALUES ('Mask', 'Damaged');
INSERT INTO common_ascension_material VALUES ('Mask', 'Stained');
INSERT INTO common_ascension_material VALUES ('Mask', 'Ominous');
INSERT INTO common_ascension_material VALUES ('Scroll', 'Divining');
INSERT INTO common_ascension_material VALUES ('Scroll', 'Sealed');
INSERT INTO common_ascension_material VALUES ('Scroll', 'Forbidden Curse');
INSERT INTO common_ascension_material VALUES ('Arrowhead', 'Firm');
INSERT INTO common_ascension_material VALUES ('Arrowhead', 'Sharp');
INSERT INTO common_ascension_material VALUES ('Arrowhead', 'Weathered');
INSERT INTO common_ascension_material VALUES ('Fatui Insignia', 'Recruit\'s');
INSERT INTO common_ascension_material VALUES ('Fatui Insignia', 'Sergeant\'s');
INSERT INTO common_ascension_material VALUES ('Fatui Insignia', 'Lieutenant\'s');
INSERT INTO common_ascension_material VALUES ('Treasure Hoarder Insignia', NULL);
INSERT INTO common_ascension_material VALUES ('Treasure Hoarder Insignia', 'Silver Raven');
INSERT INTO common_ascension_material VALUES ('Treasure Hoarder Insignia', 'Golden Raven');
INSERT INTO common_ascension_material VALUES ('Nectar', 'Whopperflower');
INSERT INTO common_ascension_material VALUES ('Nectar', 'Shimmering');
INSERT INTO common_ascension_material VALUES ('Nectar', 'Energy');
INSERT INTO common_ascension_material VALUES ('Handguard', 'Old');
INSERT INTO common_ascension_material VALUES ('Handguard', 'Kagueuchi');
INSERT INTO common_ascension_material VALUES ('Handguard', 'Famed');
INSERT INTO common_ascension_material VALUES ('Spectral', 'Husk');
INSERT INTO common_ascension_material VALUES ('Spectral', 'Heart');
INSERT INTO common_ascension_material VALUES ('Spectral', 'Nucelus');

-- common enemy drops
INSERT INTO common_enemy_drop VALUES ('Slime', 'Slime');
INSERT INTO common_enemy_drop VALUES ('Large Slime', 'Slime');
INSERT INTO common_enemy_drop VALUES ('Whopperflower', 'Nectar');
INSERT INTO common_enemy_drop VALUES ('Specter', 'Spectral');
INSERT INTO common_enemy_drop VALUES ('Hilichurl', 'Mask');
INSERT INTO common_enemy_drop VALUES ('Hilichurl Guard', 'Mask');
INSERT INTO common_enemy_drop VALUES ('Hilichurl Grenadier', 'Mask');
INSERT INTO common_enemy_drop VALUES ('Hilichurl Shooter', 'Mask');
INSERT INTO common_enemy_drop VALUES ('Hilichurl Shooter', 'Arrowhead');
INSERT INTO common_enemy_drop VALUES ('Samachurl', 'Mask');
INSERT INTO common_enemy_drop VALUES ('Samachurl', 'Scroll');
INSERT INTO common_enemy_drop VALUES ('Fatui Vanguard', 'Fatui Insignia');
INSERT INTO common_enemy_drop VALUES ('Fatui Bracer', 'Fatui Insignia');
INSERT INTO common_enemy_drop VALUES ('Fatui Legionnaire', 'Fatui Insignia');
INSERT INTO common_enemy_drop VALUES ('Treasure Hoarder Potioneer', 'Treasure Hoarder Insignia');
INSERT INTO common_enemy_drop VALUES ('Treasure Hoarder', 'Treasure Hoarder Insignia');
INSERT INTO common_enemy_drop VALUES ('Nobushi', 'Handguard');
INSERT INTO common_enemy_drop VALUES ('Kairagi', 'Handguard');

-- normal bosses
INSERT INTO normal_boss VALUES ('Anemo Hypostasis', 'Mondstadt');
INSERT INTO normal_boss VALUES ('Electro Hypostasis', 'Mondstadt');
INSERT INTO normal_boss VALUES ('Cryo Regisvine', 'Mondstadt');
INSERT INTO normal_boss VALUES ('Cryo Hypostasis', 'Mondstadt');
INSERT INTO normal_boss VALUES ('Oceanid', 'Liyue');
INSERT INTO normal_boss VALUES ('Pyro Regisvine', 'Liyue');
INSERT INTO normal_boss VALUES ('Geo Hypostasis', 'Liyue');
INSERT INTO normal_boss VALUES ('Primo Geovishap', 'Liyue');
INSERT INTO normal_boss VALUES ('Maguu Kenki', 'Inazuma');
INSERT INTO normal_boss VALUES ('Pyro Hypostasis', 'Inazuma');
INSERT INTO normal_boss VALUES ('Perpetual Mechanical Array', 'Inazuma');
INSERT INTO normal_boss VALUES ('Hydro Hypostasis', 'Inazuma');
INSERT INTO normal_boss VALUES ('Thunder Manifestation', 'Inazuma');

-- normal boss drops
INSERT INTO normal_boss_drop VALUES ('Hurricane Seed', 'Anemo Hypostasis');
INSERT INTO normal_boss_drop VALUES ('Lightning Prism', 'Electro Hypostasis');
INSERT INTO normal_boss_drop VALUES ('Basalt Pillar', 'Geo Hypostasis');
INSERT INTO normal_boss_drop VALUES ('Hoarfrost Core', 'Cryo Regisvine');
INSERT INTO normal_boss_drop VALUES ('Everflame Seed', 'Pyro Regisvine');
INSERT INTO normal_boss_drop VALUES ('Cleansing Heart', 'Oceanid');
INSERT INTO normal_boss_drop VALUES ('Juvenile Jade', 'Primo Geovishap');
INSERT INTO normal_boss_drop VALUES ('Crystalline Bloom', 'Cryo Hypostasis');
INSERT INTO normal_boss_drop VALUES ('Marionette Core', 'Maguu Kenki');
INSERT INTO normal_boss_drop VALUES ('Perpetual Heart', 'Perpetual Mechanical Array');
INSERT INTO normal_boss_drop VALUES ('Smoldering Pearl', 'Pyro Hypostasis');
INSERT INTO normal_boss_drop VALUES ('Dew of Repudiation', 'Hydro Hypostasis');
INSERT INTO normal_boss_drop VALUES ('Storm Beads', 'Thunder Manifestation');

-- domains
INSERT INTO domain VALUES ('Forsaken Rift', 'Mondstadt');
INSERT INTO domain VALUES ('Taishan Mansion', 'Liyue');
INSERT INTO domain VALUES ('Violet Court', 'Inazuma');
INSERT INTO domain VALUES ('Confront Stormterror', 'Mondstadt');
INSERT INTO domain VALUES ('Enter the Golden House', 'Liyue');
INSERT INTO domain VALUES ('Beneath the Dragon-Queller', 'Liyue');
INSERT INTO domain VALUES ('Narukami Island: Tenshukaku', 'Inazuma');

-- talent books
INSERT INTO talent_book_series VALUES ('Freedom', 'Forsaken Rift', 'Monday', 'Thursday');
INSERT INTO talent_book_series VALUES ('Resistance', 'Forsaken Rift', 'Tuesday', 'Friday');
INSERT INTO talent_book_series VALUES ('Ballad', 'Forsaken Rift', 'Wednesday', 'Saturday');
INSERT INTO talent_book_series VALUES ('Prosperity', 'Taishan Mansion', 'Monday', 'Thursday');
INSERT INTO talent_book_series VALUES ('Diligence', 'Taishan Mansion', 'Tuesday', 'Friday');
INSERT INTO talent_book_series VALUES ('Gold', 'Taishan Mansion', 'Wednesday', 'Saturday');
INSERT INTO talent_book_series VALUES ('Transience', 'Violet Court', 'Monday', 'Thursday');
INSERT INTO talent_book_series VALUES ('Elegance', 'Violet Court', 'Tuesday', 'Friday');
INSERT INTO talent_book_series VALUES ('Light', 'Violet Court', 'Wednesday', 'Saturday');

-- weekly bosses
INSERT INTO weekly_boss VALUES ('Dvalin', 'Confront Stormterror');
INSERT INTO weekly_boss VALUES ('Andrius', NULL);
INSERT INTO weekly_boss VALUES ('Childe', 'Enter the Golden House');
INSERT INTO weekly_boss VALUES ('Azhdaha', 'Beneath the Dragon-Queller');
INSERT INTO weekly_boss VALUES ('Signora', 'Narukami Island: Tenshukaku');

-- weekly boss drops
INSERT INTO weekly_boss_drop VALUES ('Dvalin\'s Plume', 'Dvalin');
INSERT INTO weekly_boss_drop VALUES ('Dvalin\'s Claw', 'Dvalin');
INSERT INTO weekly_boss_drop VALUES ('Dvalin\'s Sigh', 'Dvalin');
INSERT INTO weekly_boss_drop VALUES ('Tail of Boreas', 'Andrius');
INSERT INTO weekly_boss_drop VALUES ('Ring of Boreas', 'Andrius');
INSERT INTO weekly_boss_drop VALUES ('Spirit Locket of Boreas', 'Andrius');
INSERT INTO weekly_boss_drop VALUES ('Tusk of Monoceros Caeli', 'Childe');
INSERT INTO weekly_boss_drop VALUES ('Shard of a Foul Legacy', 'Childe');
INSERT INTO weekly_boss_drop VALUES ('Shadow of the Warrior', 'Childe');
INSERT INTO weekly_boss_drop VALUES ('Dragon Lord\'s Crown', 'Azhdaha');
INSERT INTO weekly_boss_drop VALUES ('Bloodjade Branch', 'Azhdaha');
INSERT INTO weekly_boss_drop VALUES ('Gilded Scale', 'Azhdaha');
INSERT INTO weekly_boss_drop VALUES ('Molten Moment', 'Signora');
INSERT INTO weekly_boss_drop VALUES ('Hellfire Butterfly', 'Signora');
INSERT INTO weekly_boss_drop VALUES ('Ashen Heart', 'Signora');