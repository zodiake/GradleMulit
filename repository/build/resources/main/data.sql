--test category
insert into category(id,name,parent_id,activate,created_Time,created_By) values(1,'parentCategory1',null,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(2,'childCategory1',1,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(3,'childCategory2',1,1,'2012-1-1','tom');

--test user
insert into site_user(id,name,password,enabled) values(1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1);

--
select category0_.id as id1_0_, category0_.activate as activate2_0_, 
category0_.created_by as created_3_0_, category0_.created_time as created_4_0_, 
category0_.name as name5_0_, category0_.parent_id as parent_i6_0_ from category category0_