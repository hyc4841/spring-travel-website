drop table if exists item cascade;
drop table if exists member cascade;
drop table if exists orders cascade;
create table item
(
item_id bigint generated by default as identity,
item_name varchar(50),
price bigint,
quantity integer,
primary key (item_id)
);

create table member
(
id bigint generated by default as identity,
first_name varchar(20),
last_name varchar(20),
member_id varchar(20),
password varchar(30),
email varchar(30),
address varchar(50),
primary key (id)
);

create table orders
(
order_num bigint generated by default as identity,
order_member_id bigint,
order_item_id bigint,
order_item_quantity integer,
delivery_address varchar(50),
order_date varchar(30),
total_price bigint,
primary key (order_num),
foreign key (order_member_id) references member(id),
foreign key (order_item_id) references item(item_id)
);

create table bulletinboard
(
post_id bigint generated by default as identity,
member_id bigint,
title varchar(100),
content varchar(10000),
category varchar(30),
post_date varchar(30),
edit_date varchar(30),
primary key (post_id),
foreign key (member_id) references member(id)
);

create table seller
(
seller_id bigint generated by default as identity,
company_name varchar(30),
representative varchar(10),
address varchar(50),
contact_number varchar(50),
email varchar(50),
business_number varchar(15),
primary key (seller_id)
);

create table accommodation
(
accommodation_id bigint generated by default as identity,
name varchar(30),
location varchar(100),
category varchar(10),
introduction varchar(500),
service varchar(5) array,
business_number varchar(15),
information varchar(1000),
seller bigint,
primary key (accommodation_id),
foreign key (seller) references seller(seller_id)
);

create table room
(
room_id bigint generated by default as identity,
name varchar(30),
personnel int,
max_personnel int,
accommodation bigint,
primary key (room_id),
foreign key (accommodation) references accommodation(accommodation_id)
);

create table review
(
review_id bigint generated by default as identity,
content varchar(1000),
img_url varchar(255) array,
score int,
room bigint,
accommodation bigint,
writer bigint,
primary key (review_id),
foreign key (room) references room(room_id),
foreign key (accommodation) references accommodation(accommodation_id),
foreign key (writer) references member(id)
);

create table reservation
(
reservation_id bigint generated by default as identity,
member bigint,
seller bigint,
accommodation bigint,
room bigint,
personnel int,
r_date date,
start_date date,
end_date date,
primary key (reservation_id),
foreign key (member) references member(id),
foreign key (seller) references seller(seller_id),
foreign key (accommodation) references accommodation(accommodation_id),
foreign key (room) references room(room_id)
);