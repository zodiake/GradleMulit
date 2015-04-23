create table category(
	id int not null AUTO_INCREMENT,
	name varchar(40) not null,
	parent_id int,
	activate smallint,
	created_time timestamp,
	created_by varchar(20),
	primary key(id),
	foreign key (parent_id) references category(id)
);

create table subject(
	id int not null auto_increment,
	name varchar(40) not null,
	showOnIndex smallint,
	activate smallint,
	primary key(id)
);

create table site_user(
	id int not null auto_increment,
	name varchar(20),
	password char(64),
	enabled smallint,
	site_authority varchar(20),
	created_time timestamp,
	authenticated_time timestamp,
	primary key(id)
);

create table brand(
	id int not null auto_increment,
	name varchar(50),
	created_time timestamp,
	primary key(id),
);

create table product(
	id int not null auto_increment,
	name varchar(20) not null,
	description varchar(300),
	cover_img varchar(40),
	private float,
	category_id int,
	price float,
	url varchar(50),
	MANUFACTURER_ID int,
	brand_id int,
	primary key(id),
	foreign key (category_id) references category(id),
	foreign key (MANUFACTURER_ID) references site_user(id),
	foreign key (brand_id) references brand(id)
);

create table consumable(
	id int not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table instrument(
	id int not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table reagents(
	id int not null,
	primary key(id),
	foreign key(id) references product(id)
);

create table service(
	id int not null,
	primary key(id),
	foreign key(id) references product(id)
=======
	MANUFACTURER varchar(40),
	created_by int ,
	primary key(id),
	foreign key (category_id) references category(id),
	foreign key (created_by) references site_user(id)
>>>>>>> 114451fe293db9a9a87c88be1637b1a39a89c7f0
);

create table product_subject(
	product_id int,
	subject_id int,
	added_on timestamp,
	sort_order int,
	primary key(product_id,subject_id),
	foreign key(product_id) references product(id),
	foreign key(subject_id) references subject(id)
);

create TABLE provider(
	id int NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES site_user(id)
);

create table common_user(
	id int not null auto_increment,
	primary key (id),
	foreign key (id) references site_user(id)
);

create table prefer_products(
	user_id int not null,
	product_id int not null,
	ADDED_ON timestamp,
	primary key(user_id,product_id),
	foreign key(user_id) references common_user(id),
	foreign key(product_id) references product(id)
);
