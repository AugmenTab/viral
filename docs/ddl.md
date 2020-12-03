# DDL

[ddl.sql](sql/ddl.sql)

```SQL
CREATE TABLE IF NOT EXISTS `Game`
(
    `game_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `username`     TEXT                              NOT NULL,
    `start_time`   INTEGER                           NOT NULL,
    `end_time`     INTEGER,
    `moves`        INTEGER                           NOT NULL,
    `friends_left` INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Game_start_time` ON `Game` (`start_time`);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Game_end_time` ON `Game` (`end_time`);

CREATE TABLE IF NOT EXISTS `Friend`
(
    `friend_id`       INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`            TEXT                              NOT NULL,
    `infection_level` INTEGER                           NOT NULL,
    `demeanor_id`     INTEGER                           NOT NULL,
    `active`          INTEGER                           NOT NULL,
    `address`         INTEGER                           NOT NULL,
    `profile_picture` TEXT                              NOT NULL,
    FOREIGN KEY (`demeanor_id`) REFERENCES `Demeanor` (`demeanor_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Friend_name` ON `Friend` (`name`);

CREATE INDEX IF NOT EXISTS `index_Friend_profile_picture` ON `Friend` (`profile_picture`);

CREATE INDEX IF NOT EXISTS `index_Friend_demeanor_id` ON `Friend` (`demeanor_id`);

CREATE TABLE IF NOT EXISTS `Demeanor`
(
    `demeanor_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`          TEXT                              NOT NULL,
    `infection_min` INTEGER                           NOT NULL,
    `infection_max` INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Demeanor_name` ON `Demeanor` (`name`);

CREATE TABLE IF NOT EXISTS `Action`
(
    `action_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `content`     TEXT                              NOT NULL,
    `public`      INTEGER                           NOT NULL,
    `demeanor_id` INTEGER                           NOT NULL,
    FOREIGN KEY (`demeanor_id`) REFERENCES `Demeanor` (`demeanor_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX IF NOT EXISTS `index_Action_demeanor_id` ON `Action` (`demeanor_id`);

CREATE TABLE IF NOT EXISTS `ActionResponse`
(
    `action_response_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `response_to_id`     INTEGER                           NOT NULL,
    `response_id`        INTEGER                           NOT NULL,
    FOREIGN KEY (`response_to_id`) REFERENCES `Action` (`action_id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (`response_id`) REFERENCES `Action` (`action_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_ActionResponse_response_to_id` ON `ActionResponse` (`response_to_id`);

CREATE INDEX IF NOT EXISTS `index_ActionResponse_response_id` ON `ActionResponse` (`response_id`);

CREATE TABLE IF NOT EXISTS `ActionTaken`
(
    `action_taken_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `friend_id`       INTEGER                           NOT NULL,
    `timestamp`       INTEGER                           NOT NULL,
    `action_id`       INTEGER                           NOT NULL,
    `response_to_id`  INTEGER,
    FOREIGN KEY (`action_id`) REFERENCES `Action` (`action_id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (`response_to_id`) REFERENCES `ActionResponse` (`response_to_id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (`friend_id`) REFERENCES `Friend` (`friend_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX IF NOT EXISTS `index_ActionTaken_action_id` ON `ActionTaken` (`action_id`);

CREATE INDEX IF NOT EXISTS `index_ActionTaken_response_to_id` ON `ActionTaken` (`response_to_id`);

CREATE INDEX IF NOT EXISTS `index_ActionTaken_friend_id` ON `ActionTaken` (`friend_id`);
```