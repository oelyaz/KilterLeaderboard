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
