DROP DATABASE IF EXISTS library;
CREATE DATABASE library CHARACTER SET utf8 COLLATE utf8_bin;

USE library;

create table genres
(
  id int not null auto_increment primary key,
  name varchar(50) not null unique,
  constraint id_UNIQUE unique (id)
);

INSERT INTO genres (name) VALUES ('fantasy');
INSERT INTO genres (name) VALUES ('history');
INSERT INTO genres (name) VALUES ('humor');
INSERT INTO genres (name) VALUES ('horror');
INSERT INTO genres (name) VALUES ('different');

create table authors
(
	id int not null auto_increment primary key,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    date_of_birth date not null
);

INSERT INTO authors VALUES (null, 'Terry', 'Pratchett', '1948-04-28');
INSERT INTO authors VALUES (null, 'William', 'Blatty', '1928-01-07');
INSERT INTO authors VALUES (null, 'Joanne', 'Rowling', '1965-07-31');
INSERT INTO authors VALUES (null, 'George', 'Martin', '1948-09-20');
INSERT INTO authors VALUES (null, 'Kristin', 'Hannah', '1960-09-25');
INSERT INTO authors VALUES (null, 'Walter', 'Isaacson', '1952-05-20');

create table books
(
  id int not null auto_increment primary key,
  name varchar(100) not null,
  author_id int not null,
  edition varchar(100) not null,
  edition_date int not null,
  price int not null,
  genres_id int not null,
  picture_address varchar(100) default 'img/books/default.jpg',
  review text,
  count int not null default 1,
  constraint id_UNIQUE unique (id),
  constraint fk_authors_books foreign key (author_id) references authors (id),
  constraint fk_ganres_books foreign key (genres_id) references genres (id)
);

INSERT INTO books VALUES (null, 'Good Omens', '1', 'some edition', 2004, 323, 3, default, 'some description', 1);
INSERT INTO books VALUES (null, 'The Exorcist', '2', 'some edition', 1994, 222, 4, default, 'some description', default);
INSERT INTO books VALUES (null, 'Harry Potter', '3', 'some edition', 2011, 436, 1, default, 'some description', 2);
INSERT INTO books VALUES (null, 'A Game of Thrones', '4', 'some edition', 2013, 566, 1, default, 'some description', 1);
INSERT INTO books VALUES (null, 'The Nightingale', '5', 'some edition', 1994, 214, 2, default, 'some description', 1);
INSERT INTO books VALUES (null, 'Steve Jobs', '6', 'some edition', 2014, 565, 5, default, 'some description', 3);

create table users
(
  id int not null auto_increment primary key,
  login varchar(20) not null unique,
  password varchar(128) not null,
  first_name varchar(20) not null,
  last_name varchar(20) not null,
  email varchar(100) not null unique,
  telephone varchar(15) not null unique,
  role_id int not null default 2,
  block_status int not null default 0
);

INSERT INTO users VALUES (null, 'ad', '12', 'Ad', 'min', 'admin@mail.ua', '9993452122', 0, default);
INSERT INTO users VALUES (null, 'librarian', '123', 'Антонина', 'Павловна', 'vnykzaregal@ya.ru', '123456789', 1, default);
INSERT INTO users VALUES (null, 'us', '12', 'Антон', 'Артс', 'aa@gmail.com', '0505557886', 2, default);
INSERT INTO users VALUES (null, 'user2', '12', 'Олег', 'Ортс', 'oo@gmail.com', '0507777777', 2, default);

create table order_status
(
	id int not null primary key,
    title varchar(20) not null
);

INSERT INTO order_status VALUES (1, 'processing');
INSERT INTO order_status VALUES (2, 'readingRoom');
INSERT INTO order_status VALUES (3, 'out');
INSERT INTO order_status VALUES (4, 'out of time');
INSERT INTO order_status VALUES (5, 'close');
INSERT INTO order_status VALUES (6, 'not available');

create table orders
(
  id int not null auto_increment primary key,
  user_id int,
  book_id int references books (id) on delete cascade,
  status_id int not null default 1,
  debt int,
  day_count int not null,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  constraint fk_user_id foreign key(user_id) references users (id),
  constraint fk_book_id foreign key(book_id) references books (id) on delete cascade
);

INSERT INTO orders (user_id, book_id, status_id, debt, day_count) VALUES (2, 2, 3, 12, 2);
INSERT INTO orders (user_id, book_id, status_id, debt, day_count) VALUES (1, 3, 4, 22, 1);
INSERT INTO orders (user_id, book_id, status_id, debt, day_count) VALUES (1, 1, 2, 24, 5);