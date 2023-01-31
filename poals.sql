CREATE DATABASE IF NOT EXISTS "poals";

CREATE TABLE IF NOT EXISTS user (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(128) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS bucket (
    bucketname VARCHAR(20) NOT NULL,
    username VARCHAR(20) NOT NULL,
    PRIMARY KEY (bucketname),
    FOREIGN KEY (username) REFERENCES user(username)
);

CREATE TABLE IF NOT EXISTS object (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    bucketname VARCHAR(20) NOT NULL,
    objectname VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (username) REFERENCES user(username),
    FOREIGN KEY (bucketname) REFERENCES bucket(bucketname)
);

CREATE TABLE IF NOT EXISTS file (
    id INT NOT NULL AUTO_INCREMENT,
    content LONGBLOB NOT NULL,
    hash VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS version (
    idobject INT NOT NULL,
    idfile INT NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (
        idfile,
        idobject,
        date
    ),
    FOREIGN KEY (idfile) REFERENCES file(id),
    FOREIGN KEY (idobject) REFERENCES object(id)
);