create table category(
	id bigint not null AUTO_INCREMENT,
	name varchar(40) not null,
	parent_id bigint,
	activate smallint,
	created_time timestamp,
	created_by varchar(20),
	updated_time timestamp,
	updated_by varchar(20),
	primary key(id),
	foreign key (parent_id) references category(id)
);

create table subject(
	id bigint not null auto_increment,
	name varchar(40) not null,
	show_on_index smallint,
	activate smallint,
	primary key(id)
);

create table site_user(
	id bigint not null auto_increment,
	name varchar(20),
	password char(64),
	enabled smallint,
	site_authority varchar(20),
	created_time timestamp,
	authenticated_time timestamp,
	primary key(id)
);

create TABLE provider(
	id bigint NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES site_user(id)
);

create table common_user(
	id bigint not null auto_increment,
	primary key (id),
	foreign key (id) references site_user(id)
);

create table brand(
	id bigint not null auto_increment,
	name varchar(50),
	created_time timestamp,
	acitvate smallint,
	primary key(id),
);

create table content(
	id bigint not null auto_increment,
	content varchar(4000),
	primary key (id)
);

create table product(
	id bigint not null auto_increment,
	name varchar(20) not null,
	cover_img varchar(40),
	private float,
	first_category_id bigint,
	second_category_id bigint,
	third_category_id bigint,
	price float,
	url varchar(50),
	brand_id bigint,
	created_by bigint,
	content_id bigint,
	created_time timestamp,
	primary key(id),
	foreign key (first_category_id) references category(id),
	foreign key (second_category_id) references category(id),
	foreign key (third_category_id) references category(id),
	foreign key (brand_id) references brand(id),
	foreign key (created_by) references provider(id),
	foreign key (content_id) references content(id)
);

create table review(
	id bigint not null auto_increment,
	content varchar(400),
	product_id bigint,
	user_id bigint,
	created_time timestamp,
	primary key(id),
	foreign key(user_id) references site_user(id),
	foreign key(product_id) references product(id)
);

create table consumable(
	id bigint not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table instrument(
	id bigint not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table reagents(
	id bigint not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table service(
	id bigint not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table product_subject(
	product_id bigint,
	subject_id bigint,
	added_on timestamp,
	sort_order int,
	primary key(product_id,subject_id),
	foreign key(product_id) references product(id),
	foreign key(subject_id) references subject(id)
);


create table prefer_products(
	user_id bigint not null,
	product_id bigint not null,
	created_time timestamp,
	primary key(user_id,product_id),
	foreign key(user_id) references common_user(id),
	foreign key(product_id) references product(id)
);

create table scroll_image(
	id bigint not null AUTO_INCREMENT,
	image_url varchar(100),
	created_time timestamp,
	sort_number int,
	scroll_type varchar(10),
	primary key(id)
);

create table advertisement_content(
	id bigint not null AUTO_INCREMENT,
	content varchar(5000),
	primary key(id)
);

create table advertisement_category(
	id bigint not null AUTO_INCREMENT,
	name varchar(30),
	primary key(id)
);

create table advertisement(
	id bigint not null AUTO_INCREMENT,
	cover_img varchar(225),
	content_id bigint,
	created_time timestamp,
	description varchar(200),
	category_id bigint,
	primary key(id),
	foreign key(content_id) references advertisement_content(id),
	foreign key(category_id) references advertisement_category(id)
);
