--test user
insert into ncuser (id,name,password) values (1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b');
--test post content
insert into content(id,content) values(1,'test content');

--test post
insert into post (id,title,user_id,content_id) values(1,'test title',1,1);

--test user authority
insert into authority(id,name,user_id) values(1,'ROLE_USER',1); 