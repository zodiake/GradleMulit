create table category(
	id bigint not null AUTO_INCREMENT,
	name varchar(40) not null,
	parent_id bigint,
	activate smallint,
	created_time timestamp,
	created_by varchar(20),
	updated_time timestamp,
	updated_by varchar(20),
	category_type char(2),
	primary key(id),
	foreign key (parent_id) references category(id)
);
-- newtable
create table province(
	id bigint not null AUTO_INCREMENT,
	name varchar(40) not null,
	primary key(id)
);

create table city(
	id bigint not null AUTO_INCREMENT,
	name varchar(40) not null,
	province_id bigint,
	primary key(id),
	foreign key (province_id) references province(id)
);
-- end new table

create table content(
	id bigint not null auto_increment,
	content varchar(4000),
	primary key (id)
);

create table subject(
	id bigint not null auto_increment,
	name varchar(40) not null,
	show_on_index smallint,
	activate smallint,
	category_id bigint,
	created_time timestamp,
	primary key(id),
	foreign key (category_id) references category(id)
);

create table site_user(
	id bigint not null auto_increment,
	name varchar(20),
	password char(64),
	enabled smallint,
	email varchar(40),
	phone varchar(11),
	site_authority varchar(20),
	created_time timestamp,
	authenticated_time timestamp,
	primary key(id)
);

create TABLE provider(
	id bigint NOT NULL AUTO_INCREMENT,
	company_name_china varchar(64),
	company_name_english varchar(64),
	legal_person varchar(20),
	registered_capital varchar(20),
	main_product varchar(64),
	content_id bigint,
	business_license_url varchar(40),
	tax_registration_url varchar(40),
	structure_code_url varchar(40),
	provider_name varchar(20),
	position varchar(20),
	provider_phone varchar(20),
	fax varchar(20),
	address varchar(100),
	code varchar(10),
	website varchar(50),
	province_id bigint,
	city_id bigint,
	provider_sex smallint,
	compony_type smallint,
	business_type smallint,
	scale smallint,
	output smallint,
	industry_information smallint,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES site_user(id),
	foreign key (content_id) references content(id),
	foreign key (province_id) references province(id),
	foreign key (city_id) references city(id)
);

create table common_user(
	id bigint not NULL auto_increment,
	real_name varchar(20),
	sex smallint,
	company varchar(64),
	department varchar(64),
	title smallint,
	company_phone varchar(15),
	fax varchar(15),
	address varchar(100),
	code varchar(10),
	province_id bigint,
	city_id bigint,
	industry_information smallint,
	position smallint,
	primary key (id),
	foreign key (id) references site_user(id),
	foreign key (province_id) references province(id),
	foreign key (city_id) references city(id)
);

create table brand(
	id bigint not null auto_increment,
	name varchar(50),
	created_time timestamp,
	activate smallint,
	coverImg varchar(40),
	primary key(id),
);


create table product(
	id bigint not null auto_increment,
	name varchar(20) not null,
	name_english varchar(64),
	model varchar(20),
	place_of_production smallint,
	specifications varchar(20),
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
	serialno varchar(20),
	original char(2),
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

create table subject_product(
	product_id bigint,
	subject_id bigint,
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
	href varchar(225),
	primary key(id)
);

create table advertisement_content(
	id bigint not null AUTO_INCREMENT,
	content varchar(5000),
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
	foreign key(category_id) references category(id)
);
