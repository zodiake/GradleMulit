--test category
insert into category(id,name,parent_id,activate,created_Time,created_By) values(1,'firstCategory1',null,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(2,'secondCategory1-2',1,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(3,'secondCategory1-3',1,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(4,'thirdCategory2-4',2,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(5,'thirdCategory2-5',2,1,'2012-1-1','tom');

--test user
insert into site_user(id,name,password,enabled,site_authority) values(1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_PROVIDER');
insert into site_user(id,name,password,enabled,site_authority) values(2,'mary2','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_PROVIDER');
insert into site_user(id,name,password,enabled,site_authority) values(3,'mary3','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_COMMONUSER');
insert into site_user(id,name,password,enabled,site_authority) values(4,'mary4','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_COMMONUSER');
insert into site_user(id,name,password,enabled,site_authority) values(5,'admin','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_ADMIN');

-- test common user
insert into common_user(id) values(3);
insert into common_user(id) values(4);

--test provider
insert into provider(id) values(1);
insert into provider(id) values(2);

--test product
--tom product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id) 
values(1,'product1','',10.0,'/product/1',1,2,4,1,1);
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(2,'product2','',10.0,'/product/2',1,3,5,1,2);
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(3,'product3','',10.0,'/product/3',2,2,4,1,3);
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(4,'product4','',10.0,'/product/4',2,3,5,1,4);

--mary2 product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(5,'product1','',10.0,'/product/1',1,2,4,2,5);
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(6,'product2','',10.0,'/product/2',1,3,5,2,6);
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(7,'product3','',10.0,'/product/3',2,2,4,2,7);
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id)
values(8,'product4','',10.0,'/product/4',2,3,5,2,8);

--test product_category
insert into prefer_products(user_id,product_id) values(3,1);
insert into prefer_products(user_id,product_id) values(4,1);
insert into prefer_products(user_id,product_id) values(3,2);
insert into prefer_products(user_id,product_id) values(4,2);

--test content
insert into content(1,'product1-content');
insert into content(2,'product2-content');
insert into content(3,'product3-content');
insert into content(4,'product4-content');
insert into content(5,'product5-content');
insert into content(6,'product6-content');
insert into content(7,'product7-content');
insert into content(8,'product8-content');