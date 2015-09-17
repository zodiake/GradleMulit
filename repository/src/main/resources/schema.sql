create table category(
	id bigint not null AUTO_INCREMENT,
	name varchar(40) not null,
	parent_id bigint,
	activate smallint,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(20),
	updated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(20),
	category_type char(2),
	url varchar(50),
	primary key(id),
	foreign key (parent_id) references category(id)
);

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

create table content(
	id bigint not null auto_increment,
	content varchar(4000),
	primary key (id)
);

-- 专题
create table subject(
	id bigint not null auto_increment,
	name varchar(40) not null,
	show_on_index smallint,
	activate smallint,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	created_by varchar(64),
	updated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	content_id bigint,
	image varchar(100),
	summary varchar(200),
	category_id bigint,
	primary key(id),
	foreign key (content_id) references content(id),
	foreign key (category_id) references category(id)
);

create table solution(
	id int not null auto_increment,
	name varchar(40) not null,
	subject_id bigint,
	active smallint,
	primary key(id),
	foreign key (subject_id) references subject(id)
);

create table site_user(
	id bigint not null auto_increment,
	name varchar(20),
	password char(64),
	enabled smallint,
	email varchar(40),
	phone varchar(11),
	site_authority varchar(20),
	create_time timestamp DEFAULT CURRENT_TIMESTAMP,
	real_name varchar(20),			
	sex smallint,
	score int default 0,
	primary key(id)
);

create table industry_info(
	id int not null auto_increment,
	name varchar(50),
	industry_type char(1),
	primary key (id)
);

create TABLE provider(
	id bigint NOT NULL AUTO_INCREMENT,
	company_name_china varchar(64),
	company_name_english varchar(64),
	legal_person varchar(20),
	registered_capital varchar(20),
	main_product varchar(64),
	business_license_url varchar(100),
	tax_registration_url varchar(100),
	structure_code_url varchar(100),
	position varchar(40),
	provider_phone varchar(20),
	fax varchar(20),
	address varchar(100),
	code varchar(10),
	authenticated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	website varchar(50),
	province_id bigint,
	city_id bigint,
	compony_type smallint,
	business_type smallint,
	scale smallint,
	output smallint,
	industry_information smallint,
	info_id int,
	is_authenticated int default 0,
	content varchar(1500),
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES site_user(id),
	foreign key (province_id) references province(id),
	foreign key (city_id) references city(id),
	foreign key (info_id) references industry_info(id)
);

create table common_user(
	id bigint not NULL auto_increment,
	company varchar(64),
	department varchar(64),
	position varchar(40),
	company_phone varchar(15),
	fax varchar(15),
	address varchar(100),
	code varchar(10),
	province_id bigint,
	city_id bigint,
	info_id int,
	primary key (id),
	foreign key (id) references site_user(id),
	foreign key (province_id) references province(id),
	foreign key (city_id) references city(id),
	foreign key (info_id) references industry_info(id)
);

create table brand(
	id bigint not null auto_increment,
	name varchar(50),
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	activate smallint default 1,
	cover_img varchar(90),
	href varchar(225),
	primary key(id)
);

create table product(
	id bigint not null auto_increment,
	name varchar(40) not null,
	model varchar(20),
	place_of_production smallint,
	status smallint,
	specifications varchar(20),
	cover_img varchar(100),
	first_category_id bigint,
	second_category_id bigint,
	third_category_id bigint,
	price float,
	label varchar(150),
	url varchar(50),
	brand_id bigint,
	created_by bigint,
	content_id bigint,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	serialno varchar(20),
	product_type char(1),
	authenticated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	primary key(id),
	foreign key (first_category_id) references category(id),
	foreign key (second_category_id) references category(id),
	foreign key (third_category_id) references category(id),
	foreign key (brand_id) references brand(id),
	foreign key (created_by) references provider(id),
	foreign key (content_id) references content(id)
);

create table solution_product(
	solution_id int,
	product_id bigint,
	primary key(solution_id,product_id),
	foreign key(solution_id) references solution(id),
	foreign key(product_id) references product(id)
);

create table review(
	id bigint not null auto_increment,
	content varchar(400),
	product_id bigint,
	user_id bigint,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	primary key(id),
	foreign key(user_id) references site_user(id),
	foreign key(product_id) references product(id)
);

create table prefer_products(
	user_id bigint not null,
	product_id bigint not null,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	primary key(user_id,product_id),
	foreign key(user_id) references common_user(id),
	foreign key(product_id) references product(id)
);

create table scroll_image(
	id bigint not null AUTO_INCREMENT,
	image_url varchar(100),
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	sort_number int,
	scroll_type int,
	href varchar(225),
	primary key(id)
);

create table information_content(
	id bigint not null AUTO_INCREMENT,
	content varchar(5000),
	primary key(id)
);
-- 资讯
create table information(
	id bigint not null AUTO_INCREMENT,
	title varchar(100),
	activate smallint,
	content_id bigint,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	create_by varchar(50),
	category_id bigint,
	summary varchar(200),
	primary key(id),
	foreign key(content_id) references information_content(id),
	foreign key(category_id) references category(id)
);
-- 广告
create table advertisement_content(
	id bigint not null AUTO_INCREMENT,
	content varchar(5000),
	primary key(id)
);
create table advertisement(
	id bigint not null AUTO_INCREMENT,
	category_id bigint,
	cover_img char(100),
	content_id bigint,
	url varchar(100),
	activate smallint ,
	created_time timestamp DEFAULT CURRENT_TIMESTAMP,
	updated_time timestamp DEFAULT CURRENT_TIMESTAMP,
	foreign key(content_id) references advertisement_content(id),
	primary key(id),
	foreign key(category_id) references category(id)
);
-- 采购申请
create table buy_record(
	id bigint not null AUTO_INCREMENT,
	name varchar(100),
	user_id bigint,
	fund_category varchar(100),
	reason varchar(200),
	create_time timestamp DEFAULT CURRENT_TIMESTAMP,
	arrival_time timestamp DEFAULT CURRENT_TIMESTAMP,
	price float,
	no_id varchar(20),
	primary key(id),
	foreign key(user_id) references common_user(id)
);
create table buy_product(
	id bigint not null AUTO_INCREMENT,
	buy_id bigint not null,
	product_id bigint not null,
	number int,
	primary key(id),
	foreign key(buy_id) references buy_record(id),
	foreign key(product_id) references product(id)
);

create table site_menu(
	id bigint not null AUTO_INCREMENT,
	name varchar(20),
	href varchar(30),
	primary key(id)
);

create table site_role(
	id bigint not null AUTO_INCREMENT,
	role_name varchar(20),
	primary key(id)
);

create table role_menu(
	role_id bigint not null,
	menu_id bigint not null,
	primary key(role_id,menu_id),
	foreign key(role_id) references site_role(id),
	foreign key(menu_id) references site_menu(id)
);

create table user_role(
	user_id bigint not null,
	role_id bigint not null,
	primary key(user_id,role_id),
	foreign key(role_id) references site_role(id),
	foreign key(user_id) references site_user(id)
);
