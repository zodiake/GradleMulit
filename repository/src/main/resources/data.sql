--test category
insert into category(id,name,parent_id,activate,created_Time,created_By) values(1,'parentCategory1',null,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(2,'childCategory1',1,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By) values(3,'childCategory2',1,1,'2012-1-1','tom');

--test user
insert into site_user(id,name,password,enabled,site_authority) values(1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_PROVIDER');
insert into site_user(id,name,password,enabled,site_authority) values(2,'mary2','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_PROVIDER');
insert into site_user(id,name,password,enabled,site_authority) values(3,'mary3','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_MANUFACTURER');
insert into site_user(id,name,password,enabled,site_authority) values(4,'mary4','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_MANUFACTURER');

--test provider
insert into provider(id) values(1);
insert into provider(id) values(2);

--test product
--tom product
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(1,'product1','desc1','',10.0,'/product/1',1,1);
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(2,'product2','desc1','',10.0,'/product/2',2,1);
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(3,'product3','desc1','',10.0,'/product/3',3,1);
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(4,'product4','desc1','',10.0,'/product/4',1,1);

--mary2 product
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(5,'product5','desc1','',10.0,'/product/5',1,2);
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(6,'product6','desc1','',10.0,'/product/6',2,2);
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(7,'product7','desc1','',10.0,'/product/7',3,2);
insert into product(id,name,description,cover_Img,price,url,category_id,created_by) values(8,'product8','desc1','',10.0,'/product/8',1,2);

--test product_category
insert into prefer_products(user_id,product_id) values(1,1);
insert into prefer_products(user_id,product_id) values(2,1);
insert into prefer_products(user_id,product_id) values(1,2);
insert into prefer_products(user_id,product_id) values(2,2);