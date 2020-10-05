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


create table books
(
  id int not null auto_increment primary key,
  name varchar(100) not null,
  author varchar(100) not null,
  edition varchar(100) not null,
  edition_date int not null,
  price int not null,
  genres_id int not null,
  picture_address varchar(100) default 'default.jpg',
  review text,
  count int not null default 1,
  constraint id_UNIQUE unique (id),
  constraint fk_ganres_books foreign key (genres_id) references genres (id)
);

INSERT INTO books VALUES (null, 'Good Omens', 'Terry Pratchett', 'some edition', 2004, 323, 3, default, 'some description', 1);
INSERT INTO books VALUES (null, 'The Exorcist', 'William Peter Blatty', 'some edition', 1994, 222, 4, default, 'some description', default);
INSERT INTO books VALUES (null, 'Harry Potter', 'J.K. Rowling', 'some edition', 2011, 436, 1, default, 'some description', 2);
INSERT INTO books VALUES (null, 'A Game of Thrones', 'TGeorge R.R. Martin', 'some edition', 2013, 566, 1, default, 'some description', 1);
INSERT INTO books VALUES (null, 'The Nightingale', 'Kristin Hannah', 'some edition', 1994, 214, 2, default, 'some description', 1);
INSERT INTO books VALUES (null, 'Steve Jobs', 'Walter Isaacson', 'some edition', 2014, 565, 5, default, 'some description', 3);

create table users
(
  id int not null auto_increment primary key,
  login varchar(20) not null unique,
  password varchar(128) not null,
  first_name varchar(20) not null,
  last_name varchar(20) not null,
  email varchar(100) not null unique,
  telephone varchar(15) not null unique,
  role_id int,
  debt int
);

INSERT INTO users VALUES (null, 'admmin', '1234', 'Ad', 'min', 'admin@mail.ua', '9993452122', 0, 0);
INSERT INTO users VALUES (null, 'librarian', '123', 'Антонина', 'Павловна', 'vnykzaregal@ya.ru', '123456789', 1, 0);
INSERT INTO users VALUES (null, 'user', '12', 'Антон', 'Артс', 'aa@gmail.com', '0505557886', 2, 0);
INSERT INTO users VALUES (null, 'user2', '12', 'Олег', 'Ортс', 'oo@gmail.com', '0507777777', 2, 0);

create table orders
(
  id int not null auto_increment primary key,
  user_id int,
  book_id int references books (id) on delete cascade,
  price int,
  day_count int not null,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  constraint fk_user_id foreign key(user_id) references users (id),
  constraint fk_book_id foreign key(book_id) references books (id) on delete cascade
);

INSERT INTO orders (user_id, book_id, price, day_count) VALUES (2, 2, 12, 2);
INSERT INTO orders (user_id, book_id, price, day_count) VALUES (1, 3, 22, 1);
INSERT INTO orders (user_id, book_id, price, day_count) VALUES (1, 1, 24, 5);