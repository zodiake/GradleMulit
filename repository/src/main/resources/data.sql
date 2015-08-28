--test province
insert into province(id,name) values(1,'浙江省');
insert into province(id,name) values(2,'福建省');

--test city
insert into city(id,name,province_id) values(1,'杭州市','1');
insert into city(id,name,province_id) values(2,'绍兴市','1');
insert into city(id,name,province_id) values(3,'台州市','1');
insert into city(id,name,province_id) values(4,'福州市','2');
insert into city(id,name,province_id) values(5,'泉州市','2');

--test brand
insert into brand(id,name,activate,cover_img,created_time) values(1,'brand1',1,'/img/brand1.jpg','2012-1-1');
insert into brand(id,name,activate,cover_img,created_time) values(2,'brand2',1,'/img/brand1.jpg','2012-1-1');
insert into brand(id,name,activate,cover_img,created_time) values(3,'brand3',1,'/img/brand1.jpg','2012-1-1');
insert into brand(id,name,activate,cover_img,created_time) values(4,'brand4',1,'/img/brand1.jpg','2012-1-1');

--test category
--first category
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(1,'仪器',null,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(2,'试剂',null,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(3,'耗材',null,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(4,'服务',null,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(5,'资讯',null,1,'2012-1-1','tom','ic');
insert into category(id,name,parent_id,activate,created_Time,created_By)
values(6,'专题',null,1,'2012-1-1','tom');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(20,'广告位1',null,1,'2012-1-1','tom','ac');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type) 
values(10,'secondCategory1-2',1,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(11,'secondCategory1-3',1,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(18,'secondCategory2-1',2,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(19,'secondCategory18-19',18,1,'2012-1-1','tom','pc');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(12,'thirdCategory2-4',11,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(13,'thirdCategory2-5',10,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(14,'thirdCategory3-6',10,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(15,'thirdCategory3-7',10,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(16,'thirdCategory3-6',11,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(17,'thirdCategory3-7',11,1,'2012-1-1','tom','pc');

--test advertise category
insert into category(id,name,category_type,activate,url) values(7,'行业要闻','ic',1,'HYYW');
insert into category(id,name,category_type,activate,url) values(8,'新品成果','ic',1,'XPCG');
insert into category(id,name,category_type,activate,url) values(9,'厂商动态','ic',1,'CSDT');

--test content
insert into content values(1,'product1-content');
insert into content values(2,'product2-content');
insert into content values(3,'product3-content');
insert into content values(4,'product4-content');
insert into content values(5,'product5-content');
insert into content values(6,'product6-content');
insert into content values(7,'product7-content');
insert into content values(8,'product8-content');

--test user
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_PROVIDER','1234561@qq.com','13700000001','张三',1,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(2,'mary2','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_PROVIDER','1234562@qq.com','13700000002','lisi',1,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(3,'mary3','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234563@qq.com','13700000003','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(4,'pjf1','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234564@qq.com','13700000004','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(6,'pjf12','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234565@qq.com','13700000004','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(11,'pjf123','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234566@qq.com','13700000004','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(5,'admin','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_ADMIN','1234567@qq.com','13700000005','admin',1,systimestamp);

--test industry_info
insert into  industry_info(id,name,industry_type) values(1,'a1','p');
insert into  industry_info(id,name,industry_type) values(2,'a2','p');
insert into  industry_info(id,name,industry_type) values(3,'a3','u');
insert into  industry_info(id,name,industry_type) values(4,'a4','u');
insert into  industry_info(id,name,industry_type) values(5,'a5','u');
insert into  industry_info(id,name,industry_type) values(6,'a6','u');

-- test common user
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(3,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',1,3,3);
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(4,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',2,5,4);
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(6,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',2,5,5);
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(11,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',2,5,6);

--test provider
insert into provider(id,company_name_china,company_name_english,legal_person,registered_capital,main_product,business_license_url,tax_registration_url,structure_code_url,position,provider_phone,fax,address,code,website,business_type,scale,output,industry_information,province_id,city_id,is_authenticated,compony_type,info_id,content) 
values(1,'上海恒企','hengqixinxi','bmw','两亿','人体强化药剂','/provider/1','/provider/1','/provider/1','boss','12345678901','010-12345678','外航渡路1234号','123456','www.qq.com',0,0,0,0,1,1,1,1,1,'111111111111111111111111111111111111111111111111111111');
insert into provider(id,company_name_china,company_name_english,legal_person,registered_capital,main_product,business_license_url,tax_registration_url,structure_code_url,position,provider_phone,fax,address,code,website,business_type,scale,output,industry_information,province_id,city_id,is_authenticated,compony_type,info_id,content)
values(2,'上海申捷','shenjie','bmw','两亿','人体强化药剂','/provider/2','/provider/2','/provider/2','boss','12345678901','010-12345678','外航渡路123发的4号','123456','www.qq.com',0,0,0,0,2,4,1,2,2,'22222222222222222222222222222222222222222222222222222222');

--test product
--tom product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type) 
values(1,'instrument1','/img/banner1.jpg',10.0,'/product/1',1,10,13,1,1,'instrument1English','HD300',1,'10支',1,1,systimestamp,'c');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(2,'product2','/img/banner1.jpg',10.0,'/product/2',1,10,13,1,2,'product2English','HD302',1,'10支',1,1,systimestamp,'c');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(3,'product3','/img/banner1.jpg',10.0,'/product/3',1,10,13,1,3,'product3English','HD303',1,'10支',1,1,systimestamp,'i');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(4,'product4','/img/banner1.jpg',10.0,'/product/4',1,10,13,1,4,'product3English','HD303',1,'10支',1,1,systimestamp,'s');

--mary2 product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(5,'product5','/img/banner1.jpg',10.0,'/product/1',1,10,13,2,5,'product3English','HD303',1,'10支',1,1,systimestamp,'c');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(6,'product6','/img/banner1.jpg',10.0,'/product/2',1,10,13,2,6,'product3English','HD303',1,'10支',1,1,systimestamp,'c');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(7,'product7','/img/banner1.jpg',10.0,'/product/3',1,10,13,2,7,'product3English','HD303',1,'10支',1,1,systimestamp,'c');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,name_english,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(8,'product8','/img/banner1.jpg',10.0,'/product/4',1,10,13,2,8,'product3English','HD303',1,'10支',1,1,systimestamp,'c');

--test product_category
insert into prefer_products(user_id,product_id,created_time) values(3,1,'2012-1-1');
insert into prefer_products(user_id,product_id,created_time) values(11,1,'2013-1-1');
insert into prefer_products(user_id,product_id,created_time) values(11,2,'2014-1-1');
insert into prefer_products(user_id,product_id,created_time) values(4,2,'2015-1-1');

--test subject 专题
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (1,'subject1',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (2,'subject2',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (3,'subject3',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (4,'subject4',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (5,'subject1',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (6,'subject2',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (7,'subject3',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (8,'subject4',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (9,'subject1',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,u_id) values (10,'subject2',1,1,'上海申捷卫生科技',systimestamp,1,'XMTMwNTc4NzcxMg==');

--test reviews
insert into review(id,content,product_id,user_id,created_time) values(1,'haha,very good user1',1,3,'2015-08-15');
insert into review(id,content,product_id,user_id,created_time) values(2,'haha,very good user2',1,3,'2012-09-28');
insert into review(id,content,product_id,user_id,created_time) values(3,'haha,very good user3',1,3,'2012-09-28');

--test scrollImage
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(1,'/img/banner1.jpg',1,'INDEX','www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(2,'/img/banner2.jpg',1,'INDEX','www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(3,'/img/banner1.jpg',1,'INDEX','www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(4,'/img/banner2.jpg',1,'INDEX','www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(5,'/img/banner1.jpg',1,'INDEX','www.baiduc.com');

--test information_content
insert into information_content(id,content) values (1,'我国首个通过透皮贴剂技术治疗尿频尿急的“奥昔布宁透皮贴 剂临床总结会”在南京市中山科技园隆重召开。我国首个通过透皮贴剂技术治疗尿频尿急的“奥昔布宁透皮贴 剂临床总结会”在南京市中山科技园隆重召开。昔布宁透皮贴 剂临床总结会”在南京市中山科技园隆重召开。我国首个通过透皮贴剂技术治疗尿频尿急的“奥昔布宁透皮贴 剂临床总结会”在南京市中山科技园隆重召开。。我国首个通过透皮贴剂技术治疗尿频尿急的“奥昔布宁透皮贴 剂临床总结会”在南京。我国首个通过透皮贴剂技术治疗尿频尿急的“奥昔布宁透皮贴 剂临床总结会”在南京');
insert into information_content(id,content) values (2,'1213141fdfdf');

--test Information 资讯
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (1,'2012-1-1',7,'first',1,'shenjie','2013-1-1',1);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (2,'2012-1-12',7,'tre',2,'shenjie','2013-1-1',1);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (3,'2012-1-1',7,'first',1,'shenjie','2013-1-1',1);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (4,'2012-1-12',8,'tre4',2,'shenjie','2013-1-1',1);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (5,'2012-1-12',8,'tre5',2,'shenjie','2013-1-1',1);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (6,'2012-1-12',8,'tre6',2,'shenjie','2013-1-1',1);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (7,'2012-1-12',9,'tre7',2,'shenjie','2013-1-1',0);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (8,'2012-1-12',9,'tre8',2,'shenjie','2013-1-1',0);
insert into Information(id,created_time,category_id,title,content_id,create_by,updated_time,activate) values (9,'2012-1-12',9,'tre9',2,'shenjie','2013-1-1',0);

insert into advertisement(id,cover_img,created_time,category_id,url,activate) values (1,'/upload/img/scroll1.jpg','2012-1-1',20,'www.baidu.com',1);
insert into advertisement(id,cover_img,created_time,category_id,url,activate) values (2,'/upload/img/scroll1.jpg','2012-1-2',20,'www.baidu.com',1);
insert into advertisement(id,cover_img,created_time,category_id,url,activate) values (3,'/upload/img/scroll1.jpg','2012-1-3',20,'www.baidu.com',1);
insert into advertisement(id,cover_img,created_time,category_id,url,activate) values (4,'/upload/img/scroll1.jpg','2012-1-4',20,'www.baidu.com',0);
insert into advertisement(id,cover_img,created_time,category_id,url,activate) values (5,'/upload/img/scroll1.jpg','2012-1-5',20,'www.baidu.com',0);


-- test buy_record
insert into buy_record(id,name,user_id,fund_category,reason,create_time,arrival_time,price,no_id)values(1,'test1',11,'test category','test reason','2015-06-06','2015-06-08',100,'20150820001');
insert into buy_record(id,name,user_id,fund_category,reason,create_time,arrival_time,price,no_id)values(2,'test2',11,'test category','test reason','2015-06-06','2015-06-08',100,'20150820002');

-- test buy_product
insert into buy_product(buy_id,product_id,number)values(1,1,10);
insert into buy_product(buy_id,product_id,number)values(2,1,10);
