CREATE TABLE author(
	id int,
	first_name varchar(10) NOT NULL,
	last_name varchar(10) NOT NULL,
	address varchar(10) NOT NULL,
	email varchar(10) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE book(
	id int
	name varchar(10) NOT NULL,
	genra varchar(10) NOT NULL,
	published_date date NOT NULL,
	author_id int,
	PRIMARY KEY (id),
	FOREIGN KEY (author_id) REFERENCES author(id)
);