create table category(
	id int not null AUTO_INCREMENT,
	name varchar(40) not null,
	parent_id int,
	activate smallint,
	createdTime timestamp,
	createdBy varchar(20),
	primary key(id),
	foreign key (parent_id) references category(id)
);

create table product(
	id int not null auto_increment,
	name varchar(20) not null,
	desc varchar(300),
	coverImg varchar(40),
	private float,
	category_id int,
	primary key(id),
	foreign key (category_id) references category(id)
);

create table subject(
	id int not null auto_increment,
	name varchar(40) not null,
	showOnIndex smallint,
	activate smallint,
	primary key(id)
);

create table subject_product(
	product_id int,
	subject_id int,
	primary key(product_id,subject_id),
	foreign key(product_id) references product(id),
	foreign key(subject_id) references subject(id)
);

create table site_user(
	id int not null auto_increment,
	name varchar(20),
	password char(64),
	enabled smallint,
	primary key(id)
);