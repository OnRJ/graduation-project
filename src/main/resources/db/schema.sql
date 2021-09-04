DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS meal;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          boolean             DEFAULT true  NOT NULL,
    role             VARCHAR                           NOT NULL
);
CREATE UNIQUE INDEX user_unique_email_idx ON users (email);

CREATE TABLE restaurant
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR NOT NULL
);

CREATE UNIQUE INDEX restaurant_unique_name_idx ON restaurant (name);

CREATE TABLE menu (
    id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id  INTEGER               NOT NULL,
    date           DATE                  DEFAULT now(),
    CONSTRAINT menu_restaurant_date_idx UNIQUE (restaurant_id, date),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE meal
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    menu_id         INTEGER                             NOT NULL,
    name            VARCHAR                             NOT NULL,
    price           NUMERIC                             NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    datetime       DATE             DEFAULT now()      NOT NULL,
    restaurant_id  INTEGER                             NOT NULL,
    user_id        INTEGER                             NOT NULL,
    CONSTRAINT vote_date_idx UNIQUE (user_id, datetime),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);