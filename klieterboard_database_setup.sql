CREATE DATABASE klieterboard;

CREATE TABLE kilterleaderboard.user (

	id integer UNIQUE NOT NULL,
	kilter_id integer UNIQUE NOT NULL,
	username varchar(255) UNIQUE NOT NULL,
	name varchar(255),
	score integer,
	grade integer,
	
	PRIMARY KEY (id)
);
	
CREATE TABLE kilterleaderboard.friends (

	id integer UNIQUE NOT NULL,
	username varchar(255) UNIQUE NOT NULL,

	PRIMARY KEY (id)
);