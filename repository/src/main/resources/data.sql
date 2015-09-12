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
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(6,'专题',null,1,'2012-1-1','tom','sc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
values(20,'广告位1',null,1,'2012-1-1','tom','ac');

--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type) 
--values(10,'secondCategory1-2',1,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(11,'secondCategory1-3',1,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(18,'secondCategory2-1',2,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(19,'secondCategory18-19',18,1,'2012-1-1','tom','pc');
--
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(12,'thirdCategory2-4',11,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(13,'thirdCategory2-5',10,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(14,'thirdCategory3-6',10,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(15,'thirdCategory3-7',10,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(16,'thirdCategory3-8',11,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(17,'thirdCategory3-9',11,1,'2012-1-1','tom','pc');
--
---- test
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(21,'常用生化试剂',2,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(22,'EDTA',21,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(23,'DTT',21,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(24,'Tris',21,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(25,'SDS',21,1,'2012-1-1','tom','pc');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type)
--values(26,'MOPS',21,1,'2012-1-1','tom','pc');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(31,'生物芯片',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(32,'芯片扫描仪',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(33,'芯片点样仪',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(34,'生物芯片系统',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(35,'生物芯片',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(36,'其他',31,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into brand(id,name,activate,cover_img,created_time) values(5,'AMRESCO',1,'/img/brand1.jpg','2012-1-1');



--test advertise category
insert into category(id,name,category_type,activate,url) values(7,'行业要闻','ic',1,'HYYW');
insert into category(id,name,category_type,activate,url) values(8,'新品成果','ic',1,'XPCG');
insert into category(id,name,category_type,activate,url) values(9,'厂商动态','ic',1,'CSDT');

--test content
insert into content values(1,'<p>product1-content</p>');
insert into content values(2,'product2-content');
insert into content values(3,'product3-content');
insert into content values(4,'product4-content');
insert into content values(5,'product5-content');
insert into content values(6,'product6-content');
insert into content values(7,'product7-content');
insert into content values(8,'product8-content');

--test user provider
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(1,'tom','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_PROVIDER','1234561@qq.com','13700000001','张三',1,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(2,'mary2','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_PROVIDER','1234562@qq.com','13700000002','lisi',1,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(7,'peter','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_UNAUTH','1234562@qq.com','13700000002','lisi',1,systimestamp);

--test user commonuser
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(3,'mary3','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234563@qq.com','13700000003','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(4,'pjf1','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234564@qq.com','13700000004','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(6,'pjf12','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234565@qq.com','13700000005','mary3',0,systimestamp);
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(15,'pjf123','ebcd0c0a2fead5ce94ac2100d90bc04bc04596554a976ce3bec32452faeeb007',1,'ROLE_COMMONUSER','1234566@qq.com','13700000006','mary3',0,systimestamp);

--test user admin
insert into site_user(id,name,password,enabled,site_authority,email,phone,real_name,sex,create_time) values(5,'admin','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'ROLE_ADMIN','1234567@qq.com','13700000007','admin',1,systimestamp);

--test industry_info
insert into industry_info(id,name,industry_type) values(1,'a1','p');
insert into industry_info(id,name,industry_type) values(2,'a2','p');
insert into industry_info(id,name,industry_type) values(3,'a3','u');
insert into industry_info(id,name,industry_type) values(4,'a4','u');
insert into industry_info(id,name,industry_type) values(5,'a5','u');
insert into industry_info(id,name,industry_type) values(6,'a6','u');

-- test common user
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(3,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2171号','317500',1,3,3);
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(4,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',2,5,4);
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(6,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',2,5,5);
insert into common_user(id,company,department,company_phone,fax,address,code,province_id,city_id,info_id) 
values(15,'上海恒企信息','生物研究部','010-12345678','010-12345678','万航渡路2170号','317500',2,5,6);

--test provider
insert into provider(id,company_name_china,company_name_english,legal_person,registered_capital,main_product,business_license_url,tax_registration_url,structure_code_url,position,provider_phone,fax,address,code,website,business_type,scale,output,industry_information,province_id,city_id,is_authenticated,compony_type,info_id,content) 
values(1,'上海恒企','hengqixinxi','bmw','两亿','人体强化药剂','/provider/1','/provider/1','/provider/1','boss','12345678901','010-12345678','外航渡路1234号','123456','www.qq.com',0,0,0,0,1,1,1,1,1,'111111111111111111111111111111111111111111111111111111');
insert into provider(id,company_name_china,company_name_english,legal_person,registered_capital,main_product,business_license_url,tax_registration_url,structure_code_url,position,provider_phone,fax,address,code,website,business_type,scale,output,industry_information,province_id,city_id,is_authenticated,compony_type,info_id,content)
values(2,'上海申捷','shenjie','bmw','两亿','人体强化药剂','/provider/2','/provider/2','/provider/2','boss','12345678901','010-12345678','外航渡路123发的4号','123456','www.qq.com',0,0,0,0,2,4,1,2,2,'22222222222222222222222222222222222222222222222222222222');

insert into provider(id,company_name_china,company_name_english,legal_person,registered_capital,main_product,business_license_url,tax_registration_url,structure_code_url,position,provider_phone,fax,address,code,website,business_type,scale,output,industry_information,province_id,city_id,is_authenticated,compony_type,info_id,content)
values(7,'上海申捷','shenjie','bmw','两亿','人体强化药剂','/provider/2','/provider/2','/provider/2','boss','12345678901','010-12345678','外航渡路123发的4号','123456','www.qq.com',0,0,0,0,2,4,1,2,2,'22222222222222222222222222222222222222222222222222222222');

--test product
--tom product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type) 
values(1,'instrument1','/img/banner1.jpg',10.0,'/product/1',1,31,32,1,1,'HD300',1,'10支',1,1,systimestamp,'i');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(2,'product2','/img/banner1.jpg',10.0,'/product/2',1,31,33,1,2,'HD302',1,'10支',1,1,systimestamp,'c');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(3,'product3','/img/banner1.jpg',10.0,'/product/3',1,31,34,1,3,'HD303',1,'10支',1,1,systimestamp,'i');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(4,'product4','/img/banner1.jpg',10.0,'/product/4',1,31,35,1,4,'HD303',1,'10支',1,1,systimestamp,'s');

--mary2 product
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(5,'product5','/img/banner1.jpg',10.0,'/product/1',1,31,32,2,5,'HD303',1,'10支',1,0,systimestamp,'i');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(6,'product6','/img/banner1.jpg',10.0,'/product/2',1,31,32,2,6,'HD303',1,'10支',1,1,systimestamp,'i');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(7,'product7','/img/banner1.jpg',10.0,'/product/3',1,31,32,2,7,'HD303',1,'10支',1,2,systimestamp,'i');
insert into product(id,name,cover_Img,price,url,first_category_id,second_category_id,third_category_id,created_by,content_id,model,place_of_production,specifications,brand_id,status,created_time,product_type)
values(8,'product8','/img/banner1.jpg',10.0,'/product/4',1,31,32,2,8,'HD303',1,'10支',1,3,systimestamp,'i');

--test product_category
insert into prefer_products(user_id,product_id,created_time) values(3,1,'2012-1-1');
insert into prefer_products(user_id,product_id,created_time) values(15,1,'2013-1-1');
insert into prefer_products(user_id,product_id,created_time) values(15,2,'2014-1-1');
insert into prefer_products(user_id,product_id,created_time) values(4,2,'2015-1-1');

--test subject 专题
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (1,'subject1',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (2,'subject2',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (3,'subject3',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (4,'subject4',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (5,'subject5',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (6,'subject6',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (7,'subject7',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (8,'subject8',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (9,'subject9',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');
insert into subject(id,name,show_on_index,activate,created_by,created_time,content_id,image) values (10,'subject10',1,1,'上海申捷卫生科技',systimestamp,1,'/img/banner1.jpg');

insert into solution(id,name,subject_id) values(1,'solution1',1);
insert into solution(id,name,subject_id) values(2,'solution2',1);
insert into solution(id,name,subject_id) values(3,'solution3',1);
insert into solution(id,name,subject_id) values(4,'solution4',2);
insert into solution(id,name,subject_id) values(5,'solution5',2);
insert into solution(id,name,subject_id) values(6,'solution6',2);

insert into solution_product(solution_id,product_id) values(1,1);
insert into solution_product(solution_id,product_id) values(1,2);
insert into solution_product(solution_id,product_id) values(2,1);
insert into solution_product(solution_id,product_id) values(2,3);
insert into solution_product(solution_id,product_id) values(2,4);

--test reviews
insert into review(id,content,product_id,user_id,created_time) values(1,'haha,very good user1',1,3,'2015-08-15');
insert into review(id,content,product_id,user_id,created_time) values(2,'haha,very good user2',1,3,'2012-09-28');
insert into review(id,content,product_id,user_id,created_time) values(3,'haha,very good user3',1,3,'2012-09-28');

--test scrollImage
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(1,'/img/banner1.jpg',1,0,'www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(2,'/img/banner2.jpg',1,0,'www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(3,'/img/banner1.jpg',1,0,'www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(4,'/img/banner2.jpg',1,0,'www.baiduc.com');
insert into scroll_image(id,image_url,sort_number,scroll_type,href) values(5,'/img/banner1.jpg',1,0,'www.baiduc.com');

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
insert into buy_record(id,name,user_id,fund_category,reason,create_time,arrival_time,price,no_id)values(1,'test1',15,'test category','test reason','2015-06-06','2015-06-08',400,'20150820001');
insert into buy_record(id,name,user_id,fund_category,reason,create_time,arrival_time,price,no_id)values(2,'test2',15,'test category','test reason','2015-06-06','2015-06-08',100,'20150820002');

-- test buy_product
insert into buy_product(id,buy_id,product_id,number)values(1,1,1,10);
insert into buy_product(id,buy_id,product_id,number)values(2,2,1,10);
insert into buy_product(id,buy_id,product_id,number)values(3,1,2,10);
insert into buy_product(id,buy_id,product_id,number)values(4,1,3,10);
insert into buy_product(id,buy_id,product_id,number)values(5,1,4,10);

--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(31,'生物芯片',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(32,'芯片扫描仪',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(33,'芯片点样仪',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(34,'生物芯片系统',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(35,'生物芯片',31,1,'2012-1-1','admin','pc','2015-1-1','admin');
--insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(36,'其他',31,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(37,'影像系统',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(38,'凝胶成像系统',37,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(39,'细胞成像系统',37,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(40,'活体成像系统',37,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(41,'专业影像系统',37,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(42,'其他',37,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(43,'测读系统',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(44,'酶标仪',43,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(45,'洗板机',43,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(46,'多功能筛选系统',43,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(47,'大型分析系统',43,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(48,'化学发光检测仪',43,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(49,'分光光度计',43,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(50,'其它',43,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(51,'光谱系统',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(52,'原子吸收光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(53,'可见光光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(54,'荧光光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(55,'红外光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(56,'近红外光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(57,'LIBS光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(58,'拉曼光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(59,'紫外/可见/近红外光谱仪',51,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(60,'其它',51,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(61,'分子生物实验仪器',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(62,'电泳设备',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(63,'紫外设备',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(64,'普通PCR仪',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(65,'定量PCR仪',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(66,'数字PCR仪',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(67,'电穿孔/电融合',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(68,'电洗脱',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(69,'杂交/转印仪器',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(70,'杂交炉',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(71,'DNA/有机/多肽合成',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(72,'转基因仪',61,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(73,'其他',61,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(74,'显微系统',1,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(75,'倒置显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(76,'实体显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(77,'生物显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(78,'电子显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(79,'解剖显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(80,'体视显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(81,'扫描探针显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(82,'激光共聚焦显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(83,'金相显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(84,'原子力显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(85,'其它显微镜',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(86,'显微成像',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(87,'显微操纵',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(88,'显微操作辅助设备',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(89,'附件和滤光片',74,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(90,'其它',74,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(91,'色谱系统',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(92,'液相色谱系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(93,'制备液相色谱系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(94,'毛细管LC系统气相色谱系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(95,'离子色谱系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(96,'薄层色谱系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(97,'自动固相萃取系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(98,'联用色谱系统',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(99,'色谱系统检测器样品管理工具',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(100,'色谱系统附件',91,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(101,'质谱系统',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(102,'飞行质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(103,'四极杆质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(104,'离子阱质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(105,'等离子质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(106,'磁质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(107,'傅立叶离子回旋变换质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(108,'液质联用系统',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(109,'气质联用系统',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(110,'毛细管电泳/质谱联用系统',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(111,'杂交质谱',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(112,'质谱标准品',101,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(113,'其它',101,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(114,'实验室自动化',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(115,'微流体芯片技术',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(116,'氨基酸分析系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(117,'蛋白纯化系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(118,'蛋白质翻译系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(119,'全自动分液系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(120,'核酸提取纯化全自动微孔板处理系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(121,'全自动印迹分析系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(122,'全自动诊断系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(123,'全自动组织匀浆系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(124,'全自动血液采样系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(125,'全自动MALDI样品准备系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(126,'流式细胞样品准备系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(127,'全自动样品收集系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(128,'全自动样品跟踪系统',114,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(129,'其它',114,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(130,'基因组/蛋白组设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(131,' DNA测序仪',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(132,' DNA全基因组测序仪',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(133,'基因分型系统 ',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(134,'双向电泳系统',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(135,'蛋白质分析系统',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(136,'多肽谱分析系统',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(137,'核磁共振NMR',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(138,'蛋白质纯化',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(139,'蛋白质斑点切取系统',130,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(140,'其它',130,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(141,'植物生理',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(142,'毒理学设备',141,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(143,'动物器具',141,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(144,'动物实验仪器',141,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(145,'其它',141,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(146,'动物功能检测设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(147,'动物处理设备',146,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(148,'细胞/组织支持系统',146,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(149,'膜片钳',146,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(150,'电生理设备',146,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(151,'多道药物灌流系统',146,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(152,'辅助设备',146,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(153,'其它',146,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(154,'组织学设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(155,'组织处理',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(156,'组织染色',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(157,'组织包埋',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(158,'组织冷冻设备',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(159,'切片和解剖设备',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(160,'微波系统',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(161,'其它',154,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(162,'过滤装置',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(163,'超滤',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(164,'杯式过滤器',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(165,'离心式过滤器',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(166,'管式过滤器 ',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(167,'空气过滤器',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(168,'针头式过滤器',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(169,'全套过滤装置',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(170,'其它',162,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(171,'电化学仪器',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(172,' PH计',171,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(173,' PH电位滴定仪',171,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(174,'电导仪',171,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(175,'电化学工作站',171,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(176,'其它',171,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(177,'细胞培养仪器',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(178,'细胞培养系统',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(179,'进口发酵罐',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(180,'国产发酵罐',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(181,'细胞反应器',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(182,'细胞培养监测系统',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(183,'发酵罐附件',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(184,'发酵配套设备',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(185,'其它',177,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(186,'细胞分析仪器',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(187,'流式细胞仪',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(188,'细胞计数',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(189,'细胞分析系统',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(190,'荧光微球体',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(191,'微粒超声系统',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(192,'染色体倍性分析仪',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(193,'肿瘤细胞分析仪',186,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(194,'其它',186,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(195,'储存保存设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(196,'实验室冰箱',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(197,'低温冰箱',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(198,'冻存系统',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(199,'程序降温系统',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(200,'液氮容器',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(201,'实验冷柜',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(202,'干燥箱',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(203,'超低温保存箱',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(204,'恒温保存箱',195,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(205,'其它',195,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(206,'天平',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(207,'超微量称量',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(208,'分析天平',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(209,'精密天平',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(210,'电子天平',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(211,'物理天平',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(212,'附件',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(213,'其它',206,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(214,'同位素分析',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(215,'液闪仪',214,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(216,'γ计数器',214,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(217,'同位素影像',214,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(218,'发光检测',214,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(219,'同位素',214,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(220,'其它',214,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(221,'实验室箱体/摇床',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(222,' C02培养箱',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(223,'生化培养箱',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(224,'厌氧/好氧培养箱',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(225,'植物生长箱',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(226,'恒温/恒湿箱',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(227,'烘箱/干燥箱',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(228,'摇床',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(229,'脱色摇床',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(230,'其它',221,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(231,'实验室常用设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(232,'离心机',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(233,'纯水装置',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(234,'均质器',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(235,'水循环',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(236,'超声波清洗',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(237,'超声波提取',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(238,'细胞破碎',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(239,'恒温槽',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(240,'水浴/金属浴',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(241,'清洗器',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(242,'密度计',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(243,'真空泵',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(244,'各类泵',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(245,'旋涡振荡',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(246,'搅拌器',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(247,'粉碎设备',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(248,'气体发生器',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(249,'消毒/灭菌',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(250,'红外测温仪',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(251,'温度计',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(252,'实验炉',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(253,'冻干机',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(254,'离心浓缩',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(255,'蒸馏设备',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(256,'制冰机',231,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(257,'实验室安全设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(258,'生物安全柜',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(259,'层流通风柜',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(260,'动物用通风橱',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(261,'PCR工作台',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(262,'超净工作台',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(263,'实验人员防护',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(264,'P3实验室',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(265,'无尘洁净室',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(266,'其它',257,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(267,'药物检测仪器',1,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(268,'微生物检测',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(269,'溶出度仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(270,'脆碎度仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(271,'抑菌圈测量仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(272,'硬度仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(273,'外形测定仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(274,'性状测定仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(275,'澄明度检测仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(276,'药物稳定性试验箱',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(277,'筛分仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(278,'热解析仪',267,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(279,'物性测定仪器',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(280,'水分测定仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(281,'卡氏/库仑水分测定仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(282,'场流分离系统',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(283,'粒子形状分析仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(284,'孔分析仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(285,'表面物性测试仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(286,'热分析器',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(287,'物理光学仪器',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(288,'燃烧测定仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(289,'流变仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(290,'熔点仪',279,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(291,'样品处理/材料试验',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(292,'凯氏定氮仪',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(293,'微波消解',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(294,'萃取设备',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(295,'凝胶净化',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(296,'氮吹仪',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(297,'干燥机',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(298,'硬度计',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(299,'流动分析仪',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(300,'粒度仪',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(301,'筛分仪',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(302,'热解析仪',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(303,'试验机',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(304,'其它',291,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(305,'实验室家具',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(306,'实验台',305,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(307,'实验桌椅',305,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(308,'通风橱/柜',305,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(309,'整体实验室家具',305,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(310,'储物柜',305,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(311,'其它',305,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(312,'环境检测',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(313,' TOC测定仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(314,' TOC测定仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(315,'BOD测定仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(316,'COD测定仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(317,'溶解氧测定仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(318,'其它水质检测仪器',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(319,' CO2分析仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(320,'甲醛检测仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(321,'测氨仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(322,'臭氧分析仪',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(323,'气体采样器',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(324,'其它气体检测仪器',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(325,' GC(便携)',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(326,'辐射测量仪器',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(327,'微生物检测',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(328,'其它',312,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(329,'临床检验设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(330,'血液分析系统',329,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(331,'免疫分析系统',329,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(332,'尿液分析系统',329,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(333,'细菌分析系统',329,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(334,'血气分析系统',329,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(335,'生化分析系统',329,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(336,'其它',329,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(337,'二手仪器设备',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(338,'二手仪器交易',337,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(339,'二手仪器租用',337,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(341,'其它',337,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(340,'专业软件',337,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(342,'生物软件',1,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(343,'医学软件',342,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(344,'实验室信息化',342,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(902,'其它软件',342,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(345,'生物分子',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(346,'抗生素/抗真菌素',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(347,'维生素',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(348,'氨基酸',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(349,'核苷酸',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(350,'脂',345,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(351,'糖类',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(352,'白三烯',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(353,'前列腺素',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(354,'药物结合物',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(355,'抗氧化剂',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(356,'药理活性化合物',345,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(357,'类固醇激素结合物',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(358,'半抗原反应',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(359,'半抗原载体结合物',345,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(360,'放射性核素',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(361,'血小板活化素',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(362,' tRNA',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(363,'活性染料和化合物',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(364,'亲和素/链霉亲和素',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(365,'基因组和分子生物学探针',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(366,'微生物学探针',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(367,'细胞生物学探针',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(368,'荧光染料和淬灭剂',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(369,'药物标准品',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(370,'其它生物分子',345,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(371,'常用生化试剂',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(372,'EDTA',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(373,'DTT',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(374,'Tris',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(375,'SDS',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(376,'MOPS',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(377,'HEPES',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(378,'水',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(379,'分子生物学缓冲液',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(380,'蛋白生化缓冲液',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(381,'去垢剂',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(382,'染色试剂',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(383,'溶剂',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(384,'酸碱',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(385,'化学试剂',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(386,'其它生化试剂',371,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(387,'酶',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(388,'限制性内切酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(389,'蛋白酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(390,'核酸酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(391,'激酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(392,'聚合酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(393,'连接酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(394,'反转录酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(395,'磷酸酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(396,'酯酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(397,'氧化酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(398,'还原酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(399,'脱氨酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(400,'脱氢酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(401,'转氨酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(402,'转移酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(403,'拓扑异构酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(404,'标记移除酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(405,'糖酵解酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(406,'其它酶',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(407,'批量酶和蛋白产品',387,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(408,'蛋白质/抗原/多肽',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(409,'免疫球蛋白',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(410,'封闭肽',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(411,'人蛋白和抗原',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(412,'小鼠蛋白和抗原',408,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(413,'细菌蛋白和抗原',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(414,'病毒蛋白和抗原',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(415,'植物蛋白和抗原',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(416,'其它蛋白和抗原',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(417,'细胞质蛋白',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(418,'总蛋白',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(419,'膜蛋白',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(420,'核蛋白',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(421,'线粒体蛋白',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(422,'细胞裂解和提取物 ',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(423,'细胞裂解',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(424,'组织提取',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(425,'重组细胞因子',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(426,'其它',408,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(427,'cDNA全长基因',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(428,'RNA',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(429,'cDNA合成',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(430,'cDNA相关试剂',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(431,'cDNA选择和分离',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(432,'cDNA纯化',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(433,'反转录试剂',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(434,'mRNA分离',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(435,'RACE试剂盒',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(436,'SAGE试剂盒',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(437,'其它cDNA相关',427,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(438,'PCR/RT-PCR/qPCR',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(439,'PCR引物',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(440,'PCR试剂',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(441,'PCR对照',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(442,'特异性PCR试剂盒',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(443,'PCR克隆试剂盒',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(444,' RNA',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(445,'RNase检测/去除',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(446,'RT-PCR试剂',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(447,'RT-PCR标准品',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(448,'定量PCR试剂',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(449,'定量PCR标记',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(450,'其它',438,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(451,'载体及构建',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(452,'PCR克隆载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(453,'M13克隆载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(454,'细菌克隆载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(455,'病毒克隆载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(456,'穿梭克隆载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(457,'细菌人工染色体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(458,'细菌表达载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(459,'真核表达载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(460,'昆虫细胞载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(461,'酵母表达载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(462,'体外表达载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(463,'荧光蛋白载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(464,'双杂交载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(465,'信号转导载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(466,'siRNA表达载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(467,'热休克载体 ',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(468,'报告基因载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(469,'亚细胞定位载体 ',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(470,'转座子构建载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(471,'噬菌体展示载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(472,'特异性载体',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(473,'启动子元件',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(474,'Adapter/Linker',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(475,'载体构建试剂',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(476,'其它',451,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(477,'文库及构建',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(478,'cDNA文库',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(479,'基因组文库',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(480,'噬菌体展示文库',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(481,'双杂交cDNA文库',477,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(482,'基因组DNA扩增',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(483,' RNA扩增',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(484,'文库构建试剂',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(485,'Lambda载体切除',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(486,'肽展示系统',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(487,'单杂交系统',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(488,'双杂交系统',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(489,'其它',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(490,'克隆与表达',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(491,'克隆基因',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(492,'克隆试剂盒',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(493,'克隆筛选',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(494,'感受态细胞',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(495,'突变',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(496,'转染',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(497,'转化',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(498,'体外转录/翻译',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(499,'其它',490,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(500,'表达分析',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(501,'Northern印迹分析',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(502,'分支DNA和mRNA定量',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(503,'差别基因表达',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(504,'反义寡核苷酸',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(505,'RACE试剂盒',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(506,' SAGE 试剂盒',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(507,'其它',500,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(508,'核酸/蛋白合成',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(509,'Oligo纯化',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(510,'核酸合成柱',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(511,'核酸合成试剂',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(512,'合成用引物',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(513,'多肽合成柱',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(514,'多肽合成试剂',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(515,'其它',508,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(516,'核酸分析',2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(517,'AFLP分析',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(518,'SNP基因分型',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(519,'线粒体DNA基因分型',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(520,'其它基因分型',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(521,'DNA指纹试剂盒',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(522,'DNA测序试剂',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(523,'核酸电泳凝胶',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(524,'核酸标准品',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(525,'凝胶纯化试剂盒',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(526,'核酸染色',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(527,'转座工具',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(528,'其它',516,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(529,'核酸检测',2,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(530,'N/S印迹分析试剂盒', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(531,'生物素印迹检测', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(532,'DIG印剂检测', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(533,'其它探针标记/检测', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(534,'原位杂交', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(535,'胞内核酸定位', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(536,'RNA检测', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(537,'核酸定量检测', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(538,'核酸蛋白互作检测', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(539,'其它', 529,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(540,'核酸纯化', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(541,'DNA凝胶回收纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(542,'DNA提取纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(543,'PCR产物纯化基因组DNA提取纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(544,'质粒提取纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(545,'BAC纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(546,'特定DNA分离', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(547,'病毒纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(548,'磁珠DNA分离', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(549,'其它DNA纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(550,'RNA提取纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(551,'总RNA分离纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(552,'mRNA分离纯化', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(553,'RNA和蛋白分离', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(554,'RNA定量', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(555,'其它', 540,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(556,'RNAi技术(siRNA,miRNA,shRNA)', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(557,'siRNA', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(558,'反义寡核苷酸', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(559,'RNAi定量', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(560,'RNAi文库', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(561,'RNAi Oligo合成', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(562,'miRNA', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(563,'miRNA分离', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(564,'miRNA分析', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(565,'siRNA酶', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(566,'siRNA标记', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(567,'siRNA纯化', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(568,'siRNA合成', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(569,'siRNA载体', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(570,'siRNA转录', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(571,'siRNA转染', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(572,'siRNA构建试剂盒', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(573,'其它', 556,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(574,'蛋白检测', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(575,'Western印迹', 574,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(576,'报告基因检测', 574,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(577,'蛋白检测试剂盒', 574,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(578,'蛋白酶抑制剂', 574,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(579,'诱饵蛋白/捕获蛋白互作检测', 574,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(580,'其它', 574,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(581,'蛋白纯化', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(582,'蛋白提取', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(583,'蛋白透析', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(584,'蛋白定量', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(585,'蛋白稳定', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(586,'蛋白纯化', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(587,'蛋白浓缩', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(588,'蛋白体分离', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(589,'亲和标记纯化', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(590,'融合标记移除', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(591,'病毒移除', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(592,'脂移除', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(593,'分馏试剂盒', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(594,'磁珠及相关', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(595,'其它', 581,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(596,'蛋白分析', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(597,'PAGE凝胶制备', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(598,'蛋白电泳试剂', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(599,'预制蛋白凝胶', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(600,'蛋白标准品', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(601,'蛋白凝胶染色', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(602,'蛋白印迹检测', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(603,'噬菌体(NMR分析)', 596,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(604,'其它', 596,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(605,'蛋白修饰', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');

insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(606,'蛋白标记', 605,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(607,'载体蛋白结合', 605,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(608,'其它', 605,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(609,'细胞生物学检测', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(610,'细胞凋亡', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(611,'细胞分离', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(612,'细胞增殖', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(613,'细胞因子检测', 609,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(614,'流式细胞检测', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(615,'信号传导检测', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(616,'荧光微球体检测', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(617,'组织学/免疫组织学检测', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(618,'血清/组织/血液产品', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(619,'细胞生物学检测试剂盒', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(620,'其它', 609,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(621,'药物筛选', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(622,'荧光素细胞活力/增殖/毒性检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(623,'细胞标记/跟踪', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(624,'其它细胞活力/增殖/毒性检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(625,'吞噬作用检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(626,'报告基因检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(627,'端粒酶和TRAP检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(628,'细胞衰老检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(629,'细胞扩增', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(630,'干细胞分化', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(631,'酶抑制剂筛选', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(632,'卵母细胞筛选', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(633,'受体结合检测', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(634,'其它', 621,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(635,'免疫检测', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(636,'放射性免疫检测', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(637,'化学发光免疫检测', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(638,'ELISPOT检测荧光偏振免疫检测', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(639,'切向流免疫仪检测', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(640,'免疫层析散射免疫扩散检测', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(641,'ELISA检测试剂盒', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(642,'其它', 635,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(643,'细胞培养', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(644,'血清', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(645,'血清替代物', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(646,'细胞培养基', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(647,'细胞培养添加物培养基/琼脂混合物', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(648,'细胞因子、趋化因子和生长因子琼脂', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(649,'胰酶中和液', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(650,'蛋白胨', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(651,'多聚赖氨酸', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(652,'氨基酸混合物', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(653,'盐溶液', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(654,'细胞分散试剂', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(655,'消泡剂', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(656,'表达诱导', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(657,'细菌检测', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(658,'酵母细胞培养', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(659,'支原体检测', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(660,'支原体消除', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(661,'导向毒素', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(662,'细胞分选', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(663,'其它', 643,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(664,'细胞', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(665,'细胞株', 664,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(666,'感受态细胞', 664,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(667,'新鲜细胞分离', 664,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(668,'受体系(细菌)', 664,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(669,'受体系(酵母)', 664,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(670,'其它', 664,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(671,'干细胞', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(672,'干细胞/祖细胞', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(673,'原代细胞', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(674,'干细胞培养基', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(675,'干细胞分化', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(676,'干细胞扩增', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(677,'干细胞鉴定', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(678,'干细胞培养质控', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(679,'干细胞培养添加物', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(680,'干细胞与iPS', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(681,'干细胞诱导分化', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(682,'3D培养', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(683,'干细胞冻存', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(684,'其它', 671,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(685,'实验动物', 2,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(686,'转基因动物', 685,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(687,'小鼠', 685,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(688,'大鼠', 685,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(689,'模式动物', 685,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(690,'模式植物', 685,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(691,'其它', 685,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(692,'临床检测试剂', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(693,'生化检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(694,'遗传学检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(695,'血液检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(696,'尿液检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(697,'骨髓检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(698,'肿瘤检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(699,'生殖检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(700,'寄生虫检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(701,'传染病检测', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(702,'其它', 692,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(703,'相关检验试剂', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(704,'食品安全检测', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(705,'药品安全检测', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(706,'动物安全检测', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(707,'植物安全检测', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(708,'法医学鉴定', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(709,'抗生素检测', 703,1,'2012-1-1','admin','pc','2015-1-1','admin'); 
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(710,'体外诊断试剂', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(711,'其它', 703,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(712,'抗体', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(713,'蛋白A, G PLUS', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(714,'肿瘤抑制/凋亡抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(715,'信号分子抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(716,'结构蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(717,'磷酸化特异抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(718,'融合蛋白tag抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(719,'非哺乳动物蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(720,'细胞周期蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(721,'转录调节蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(722,'类固醇受体抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(723,'膜受体抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(724,'亚细胞标记抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(725,'同源结构域蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(726,'运输蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(727,'生长因子和激素抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(728,'神经生物抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(729,'激酶和磷酸化抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(730,'GDP/GTP结合蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(731,'合成降解蛋白抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(732,'离子通道抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(733,'淋巴细胞信号抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(734,'细胞粘附因子抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(735,'流式抗体', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(736,'其它', 712,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(737,'抗体制备', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(738,'佐剂', 737,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(739,'抗体片段', 737,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(740,'抗体同种型', 737,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(741,'抗体纯化', 737,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(742,'抗体制备试剂盒', 737,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(743,'其它', 737,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(744,'第二抗体', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(745,'western blot二抗', 744,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(746, '免疫组化二抗', 744,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(747, '其它', 744,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(748, '试剂盒', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(749, 'ELISA试剂盒', 748,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(750, '免疫组化试剂盒 ', 748,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(751, '放射免疫试剂盒', 748,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(752, 'western blot试剂盒', 748,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(753, '流式细胞试剂盒', 748,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(754, '其它', 748,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(755, '抗体相关', 2,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(756, 'siRNA', 755,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(757, '重组蛋白', 755,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(758, '白蛋白', 755,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(759, '试剂', 755,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(760, '其它', 755,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(761, '常用耗材', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(762, '烧杯', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(763, '漏斗', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(764, '量筒', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(765, '广口罐', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(766, '研钵和研杵', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(767, '手套', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(768, '移液管', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(769, '滴定管', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(770, '金属制品', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(771, '封口膜', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(772, '冰盒', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(773, '预制胶', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(774, '其它', 761,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(775, '移液器(Pipette)', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(776, '单道手动移液器', 775,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(777, '单道电动移液器', 775,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(778, '多道手动移液器', 775,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(779, '多道电动移液器', 775,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(780, '正置换移液器', 775,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(781, '瓶口分液器', 775,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(782, '吸头(Tips)', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(783, '滤芯吸头(灭菌)', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(896, '滤芯吸头(未灭菌)', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(897, '普通吸头(灭菌)', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(898, '普通吸头(未灭菌)', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(899, '多道移液器吸头', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(900, '液体工作站吸头', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(901, '其它', 782,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(785, '瓶(Bottle)', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(786, '离心瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(787, '称量瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(788, '存储瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(789, '比重瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(790, '血清瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(791, '滴瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(792, '吸气瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(793, '细胞培养瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(794, '培养基瓶', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(795, '其它', 785,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(796, '瓶(Flask)', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(797, '蓝盖瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(798, '烧瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(799, '平底烧瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(800, '克氏(长颈)烧瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(801, '碘测定烧瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(802, '蒸馏烧瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(803, '容量瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(804, '过滤瓶', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(805, '其它', 796,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(806, '管(Tube&Vial)', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(807, '离心管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(808, '微离心管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(809, '样品储存管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(810, '冻存管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(811, '反应管聚乙烯保存管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(812, '细胞培养管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(813, '自动上样管', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(814, '其它', 806,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(815, 'PCR耗材', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(816, 'PCR管', 815,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(817, 'PCR条板 ', 815,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(818, 'PCR板', 815,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(819, '实时定量PCR毛细管', 815,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(820, '其它', 815,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(821, '微孔板', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(822, '微孔板(96孔)', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(823, '微孔板(384孔)', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(824, '微孔板(包被)', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(825, '不透明微孔板', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(826, '微孔板盖', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(827, '微孔板封口', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(828, '其它', 821,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(829, '色谱柱', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
 insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(830, '空色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(831, '离子交换色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(832, '分子筛色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(833, '正相色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(834, '反相色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(835, '亲和色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(836, '疏水作用色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(837, '特制色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(838, '羟基磷灰石色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(839, '混合模式色谱柱', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(840, '其它', 829,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(841, '色谱介质', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(842, '离子交换色谱介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(843, '分子筛色谱介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(844, '亲和色谱介质疏水作用色谱介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(845, '羟基磷灰石色谱介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(846, '吸附色谱介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(847, '陶瓷化氟代磷灰石介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(848, '活化色谱介质', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(849, '其它', 841,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(850, '细胞/组织培养耗材', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(851, '细胞培养皿', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(852, '多孔组织培养板', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(853, '组织培养池', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(854, '培养瓶', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(855, '细胞培养板', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(856, '立体培养皿/板', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(857, '蓝盖瓶', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(858, '其它', 850,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(859, '分子生物学耗材', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(860, '硝酸纤维素膜', 859,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(861, '尼龙膜', 859,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(862, 'PVDF膜', 859,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(863, '胶片', 859,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(864, '印迹膜/滤纸三明治', 859,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(865, '其它', 859,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(866, '过滤耗材', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(867, '过滤膜', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(868, '过滤板', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(869, '纯水纯化柱', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(870, '透析袋/膜', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(871, '滤纸', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(872, '超滤', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(873, '其它', 866,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(874, '仪器配套专用耗材', 3,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(875, '比色皿/比色杯', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(876, '电击杯', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(877, '电泳设备配件', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(878, '酶标板', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(879, '显微镜用耗材', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(880, '显微镜用耗材', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(881, '其它', 874,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(882, '仪器维修服务', 4,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(905, '仪器维修服务', 882,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(883, '技术培训服务', 4,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(884, '新药技术培训', 883,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(885, 'GMP认证培训', 883,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(886, '实验技术培训', 883,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(887, 'ISO系列标准培训', 883,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(888, '其它培训', 883,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(889, '其它服务', 4,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(890, '厂商OEM', 889,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(891, '组织学服务', 889,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(892, '实验室定制服务', 889,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(893, '冷链物流', 889,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(894, '技术转让', 889,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(895, '其它', 889,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(904,'基因组DNA',477,1,'2012-1-1','admin','pc','2015-1-1','admin');
insert into category(id,name,parent_id,activate,created_Time,created_By,category_type,updated_time,updated_by) values(906, '仪器维修服务', 882,1,'2012-1-1','admin','pc','2015-1-1','admin');
