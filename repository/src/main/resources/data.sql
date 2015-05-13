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

--test content
insert into content values(1,'product1-content');
insert into content values(2,'product2-content');
insert into content values(3,'product3-content');
insert into content values(4,'product4-content');
insert into content values(5,'product5-content');
insert into content values(6,'product6-content');
insert into content values(7,'product7-content');
insert into content values(8,'product8-content');

--test product
--tom product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id) 
values(1,'instrument1','',10.0,'/product/1',1,2,4,1,1);

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
insert into prefer_products(user_id,product_id,created_time) values(3,1,'2012-1-1');
insert into prefer_products(user_id,product_id,created_time) values(4,1,'2013-1-1');
insert into prefer_products(user_id,product_id,created_time) values(3,2,'2014-1-1');
insert into prefer_products(user_id,product_id,created_time) values(4,2,'2015-1-1');

--test subject
insert into subject(id,name,show_on_index,activate) values (1,'subject1',1,1);
insert into subject(id,name,show_on_index,activate) values (2,'subject2',1,1);
insert into subject(id,name,show_on_index,activate) values (3,'subject3',1,1);
insert into subject(id,name,show_on_index,activate) values (4,'subject4',1,1);

--test product_subject
insert into product_subject(product_id,subject_id)values(1,1);
insert into product_subject(product_id,subject_id)values(2,1);
insert into product_subject(product_id,subject_id)values(3,1);
insert into product_subject(product_id,subject_id)values(4,1);

--test reviews
insert into review(id,content,product_id,user_id) values(1,'haha,very good user1',1,1);
insert into review(id,content,product_id,user_id) values(2,'haha,very good user2',1,2);
insert into review(id,content,product_id,user_id) values(3,'haha,very good user3',1,3);

--test instrument
insert into instrument(id) values(1);

--test scrollImage
insert into scroll_image(id,image_url,sort_number,scroll_type) values(1,'/upload/img/scroll1.jpg',1,'INDEX');
insert into scroll_image(id,image_url,sort_number,scroll_type) values(2,'/upload/img/scroll1.jpg',1,'INDEX');
insert into scroll_image(id,image_url,sort_number,scroll_type) values(3,'/upload/img/scroll1.jpg',1,'INDEX');
insert into scroll_image(id,image_url,sort_number,scroll_type) values(4,'/upload/img/scroll1.jpg',1,'INDEX');
insert into scroll_image(id,image_url,sort_number,scroll_type) values(5,'/upload/img/scroll1.jpg',1,'INDEX');

--test advertisementCategory
insert into advertisement_category(id,name) values(1,'行业要闻');
insert into advertisement_category(id,name) values(2,'商机');
insert into advertisement_category(id,name) values(3,'成果');
insert into advertisement_category(id,name) values(4,'动态');
insert into advertisement_category(id,name) values(5,'专访');

--test advertisement
insert into advertisement(id,cover_img,created_time,category_id,description) values (1,'/upload/img/scroll1.jpg','2012-1-1',1,'first');
insert into advertisement(id,cover_img,created_time,category_id,description) values (2,'/upload/img/scroll1.jpg','2012-1-2',2,'second');
insert into advertisement(id,cover_img,created_time,category_id,description) values (3,'/upload/img/scroll1.jpg','2012-1-3',1,'third');
insert into advertisement(id,cover_img,created_time,category_id,description) values (4,'/upload/img/scroll1.jpg','2012-1-4',2,'fourth');
insert into advertisement(id,cover_img,created_time,category_id,description) values (5,'/upload/img/scroll1.jpg','2012-1-5',1,'fifth');