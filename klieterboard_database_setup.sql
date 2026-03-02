CREATE DATABASE klieterboard;

CREATE TABLE klieterboard.user (

	id integer UNIQUE NOT NULL,
	kilter_id integer UNIQUE NOT NULL,
	username varchar(255) UNIQUE NOT NULL,
	name varchar(255),
	score integer,
	grade integer,
	
	PRIMARY KEY (id)
);
	
CREATE TABLE klieterboard.friends (

	id integer UNIQUE NOT NULL,
	username varchar(255) UNIQUE NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE klieterboard.winner(

    id integer UNIQUE NOT NULL,
    season_year integer NOT NULL,
    season_semester integer NOT NULL,
    username integer NOT NULL,
    score integer NOT NULL,

    PRIMARY KEY (id)
);

