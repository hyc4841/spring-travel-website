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