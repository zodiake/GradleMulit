--test category
insert into category(id,name,parent_id,activate,createdTime,createdBy) values(1,'parentCategory1',null,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,createdTime,createdBy) values(2,'childCategory1',1,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,createdTime,createdBy) values(3,'childCategory2',1,1,'2012-1-1','tom');

--test user
insert into site_user(id,name,password,enabled) values(1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1);

--