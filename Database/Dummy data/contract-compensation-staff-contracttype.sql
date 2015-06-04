-- Insert data vào bảng contract type
INSERT INTO `mic_data`.`mic_contract_type`(`name`,`description`,`price_per_year`)
VALUES ('Xe trên 50cc có BH cho người trên xe','Xe trên 50cc có BH cho người trên xe','85000');
INSERT INTO `mic_data`.`mic_contract_type`(`name`,`description`,`price_per_year`)
VALUES ('Xe trên 50cc không có BH cho người trên xe','Xe trên 50cc không có BH cho người trên xe','75000');
INSERT INTO `mic_data`.`mic_contract_type`(`name`,`description`,`price_per_year`)
VALUES ('Xe dưới 50cc có BH cho người trên xe','Xe dưới 50cc có BH cho người trên xe','65000');
INSERT INTO `mic_data`.`mic_contract_type`(`name`,`description`,`price_per_year`)
VALUES ('Xe dưới 50cc không có BH cho người trên xe','Xe dưới 50cc không có BH cho người trên xe','55000');
INSERT INTO `mic_data`.`mic_contract_type`(`name`,`description`,`price_per_year`)
VALUES ('Xe mô tô ba bánh, xe gắn máy và các loại xe tương tự','Bảo hiểm dành cho xe ba bánh, xe gắn máy v.v','105000');

-- Insert data vào bảng staff
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NV0001','qwerty','Lê Tuấn Anh','anhlt@gmail.com','0912025544');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NV0C03','123456','Lê Xuân Tiến','globavina.hrd@gmail.com','0983660909');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NVDC32','tienxuanle','Lê Xuân Tiến','conan_3137@yahoo.com','0940984464');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NVESC2','dongphuongminhchau','Võ Thị Minh Châu','chauvtm@gmail.com','0915020617');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NV44C2','huyhoang','Trương Đắc Huy Hoàng','hoangtdh@gmail.com','0912116730');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NV4869','aptx4869','Lê Duy Thanh','tmnthanh0125@yahoo.com','0913002087');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NV44DD','minhcaoto','Vũ Nhật Minh','minhnm@gmail.com','0915250309');
INSERT INTO `mic_data`.`mic_staff`(`staff_code`,`password`,`name`,`email`,`phone`)
VALUES ('NVACDD','luangquang','Trần Đình Quang','quangtd@outlook.com','0912632096');

-- Insert data vào bảng contract
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HD0A33','2014-12-05','2015-12-05','Pending','100000','59Y1-32244','Honda','Air Blade','2 Bánh','Đỏ Đen',
'A02E-0519856','Y-519860','108','2010','150','2',NULL,NULL,NULL,NULL,'NV0001','1','KH0001');
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HD0ES6','2014-03-15','2015-03-15','No Card','108000','52Y1-5433','Honda','Wave','Nữ','Bạc',
'4D2E-4543454','A-432376','87','2006','110','2','/wp-content/uploads/2014/03/52Y1-5433.jpg',NULL,NULL,NULL,
'NV0C03','1','KH0002');
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HD76FF','2012-02-18','2016-02-18','Ready','75000','49H4-45569','Honda','Click','2 Bánh','Trắng',
'AY57-4657656','S-257345','100','2012','100','2','/wp-content/uploads/2012/02/49H4-45569.jpg',NULL,NULL,NULL,
'NV4869','2','KH0003');
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HD33FD','2013-04-09','2016-04-09','Request cancel','85000','54F8-24904','Yamaha','Exiter','2 Bánh','Xanh Trắng',
'G45H-3256544','C-434355','125','2013','135','2',NULL,NULL,NULL,NULL,'NVDC32','2','KH0004');
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HDUA79','2009-05-08','2015-05-08','Expired','79000','77S3-3243','Honda','Future','2 Bánh','Đen Bạc',
'W3FC-4543489','M-902384','98','2007','120','2','/wp-content/uploads/2010/05/77S3-3243.jpg',NULL,NULL,NULL,
'NVACDD','2','KH0005');
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HD453C','2015-04-25','2016-04-25','Ready','110000','54I8-35432','Kawasaki','Z1','3 Bánh','Đen',
'R5443-5646543','C-454376','250','2015','450','2',NULL,NULL,NULL,NULL,'NV44DD','5','KH0006');
INSERT INTO `mic_data`.`mic_contract`(`contract_code`,`start_date`,`expired_date`,`status`,`contract_fee`,`plate`,
`brand`,`model_code`,`vehicle_type`,`color`,`engine`,`chassis`,`capacity`,`year_of_manufacture`,`weight`,
`seat_capacity`,`cert_image`,`cancel_date`,`cancel_reason`,`cancel_note`,`staff_code`,`contract_type_id`,
`customer_code`)
VALUES
('HD6679','2005-02-18','2015-02-18','Cancelled','59000','67G5-3790','SYM','Elegant','2 Bánh','Xanh Dương',
'TR54-6543525','L-435333','50','2004','88','2','/wp-content/uploads/2010/05/67G5-3790.jpg',NULL,NULL,NULL,
'NVESC2','4','KH000A');

-- Insert data vào bảng compensation
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTHFUR','Nguyễn Nhật Duy','457566','A1','0974131489','2','1231 QL 1A, P Bình Trị Đông B, Q Bình Tân',
'51T9-72931','2015-01-30','35 Tôn Đức Thắng, Quận 1, TP Hồ Chí Minh','Đội CSGT Bến Thành','Tai nạn giao thông',
'Chấn động não','Hư hại hoàn toàn','Nguyễn Lương Tùng',NULL,'/wp-content/uploads/2015/04/BTHFUR.jpg',
'2015-04-08',NULL,NULL,NULL,'HD0A33');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTVDSD','Trần Thanh Duy','344907','A2','0972907579','2','167/2 Nguyễn Ảnh Thủ, P Trung Mỹ Tây, Q12',
'52F4-9886','2015-02-05','2 Hồ Tùng Mậu, Quận 1, TP Hồ Chí Minh','Đội CSGT Bến Thành',
'Tai nạn giao thông','Cắt bỏ 2 xương sườn','Hư hại hoàn toàn','Đặng Thị Trúc Thanh','Bồi thường tiền',
'/wp-content/uploads/2015/03/BTVDSD.jpg','2015-03-13',NULL,NULL,NULL,'HD0ES6');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTTNZL','Ngô Nhật Minh','514776','A3','0936053286','4','51/1E Quang Trung, P8, Q Gò Vấp',
'53D3-54503','2014-01-15','428 Võ Văn Kiệt - P. Cô Giang, Quận 1, TP Hồ Chí Minh','Đội CSGT Bến Thành',
'Tai nạn giao thông','Chấn thương sọ não kín','Hư hại hoàn toàn','Phan Huy Bão','Bồi thường tiền cho thương tật',
'/wp-content/uploads/2014/02/BTTNZL.jpg','2014-02-04','2014-08-13','Chấp nhận bồi thường',NULL,'HD33FD');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTQOGA','Trần Đình Huy Bảo','345128','B1','0976734967','5','110 Trường Chinh, P. Tân Hưng Thuận, Q12',
'54C3-59120','2014-02-04','286 CMT8 P6 , Quận 3, TP Hồ Chí Minh','Đội CSGT Nam Sài Gòn','Tai nạn giao thông',
'Lún xương sọ','Hư hại hoàn toàn','Đỗ Cảnh Phụng','Bồi thường tiền','/wp-content/uploads/2014/02/BTQOGA.jpg',
'2014-02-04','2014-12-25','Từ chối bồi thường','Nồng độ cồn vượt quá mức cho phép','HD76FF');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTHCNK','Đặng Vĩnh Tân','940727','A2','0977022810','7','479-481-483 Lạc Long Quân, P5, Q11',
'55C6-8184','2013-09-05','324 - 326 Võ Văn Tần - P.5, Quận 3, TP Hồ Chí Minh','Đội CSGT Nam Sài Gòn',
'Tai nạn giao thông','Gãy xẹp thân 1 đốt sống','Hư hại hoàn toàn','Tạ Thị Bạch Yến','Bồi thường tiền',
'/wp-content/uploads/2013/09/BTHCNK.jpg','2013-09-07','2014-01-17','Chấp nhận bồi thường',
'Không bồi thường cho tài sản','HDUA79');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BT5LD8','Đào Tiến Mạnh','186498','B1','0984200935','2','76 CMT8, Phường 6, Quận 3',
'67D7-15233','2015-01-21','223A Phan Văn Trị , P11, Q Bình Thạnh, TP Hồ Chí Minh','Đội CSGT Bình Triệu',
'Tai nạn giao thông','Gãy xương cánh chậu 1 bên','Hư hại hoàn toàn','Trần Ngọc Mỹ Hiền','Bồi thường tiền',
'/wp-content/uploads/2015/04/BT5LD8.jpg','2015-04-11',NULL,NULL,NULL,'HD6679');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BT1J1V','Dương Hải Đăng','380881','B2','01253212277','2','261E Nguyễn Văn Trỗi, Q Phú Nhuận',
'68A1-38486','2015-01-03','614-616 Nguyễn Đình Chiểu - P3, Quận 7, TP Hồ Chí Minh','Đội CSGT Nam Sài Gòn',
'Tai nạn giao thông','Đứt gân Achille','Hư hại hoàn toàn','Nguyễn Thị Lý',NULL,NULL,'2015-01-23',NULL,NULL,NULL,
'HD453C');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BT2QDI','Nguyễn Hữu Đoàn','552054','A2','0976054844','2','69 Bùi Thị Xuân, P.PNL, Q1',
'69F5-87983','2014-09-03','18, CMT8, Q1, HCM, Quận 1, TP Hồ Chí Minh','Đội CSGT Bến Thành','Tai nạn giao thông',
'Cắt toàn bộ một bên phổi','Hư hại hoàn toàn','Nguyễn Bảo Anh','Bồi thường tiền cho thương tật',
'/wp-content/uploads/2014/09/BT2QDI.jpg','2014-09-16','2014-12-26','Chấp nhận bồi thường',NULL,'HDUA79');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTWO52','Nguyễn Ngọc Hưng','802430','C1','0979680065','4','266 Lý Thường Kiệt, P14, Q10',
'76Y9-5822','2014-01-13','2A Phó Đức Chính - P.Nguyễn Thái Bình, Quận 1, TP Hồ Chí Minh','Đội CSGT Chợ Lớn',
'Tai nạn giao thông','Cắt đoạn dạ dày','Hư hại hoàn toàn','Huỳnh Minh Chánh','Bồi thường tiền cho thương tật',
'/wp-content/uploads/2014/05/BTWO52.jpg','2014-05-15','2014-09-16','Chấp nhận bồi thường',NULL,'HD0A33');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTI14D','Nguyễn Văn Chính','597020','A2','01686979170','4','158 Đường số 19, P Bình Trị Đông B, Q Bình Tân',
'77S9-63531','2014-06-15','45 Đinh Tiên Hoàng - P. Bến Nghé, Quận 1, TP Hồ Chí Minh','Đội CSGT Bến Thành',
'Tai nạn giao thông','Chết','Hư hại hoàn toàn','Đoàn Thị Ngọc Thủy','Bồi thường tiền cho thương tật',NULL,
'2014-10-09','2015-02-11','Từ chối bồi thường',NULL,'HD33FD');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTNTID','Phạm Anh Dũng','544739','B1','01686915981','7','188 Hậu Giang, P6, Q6',
'78V3-94295','2013-09-27','21 Trường Sơn, P.4, Q. Tân Bình, Q Bình Tân, TP Hồ Chí Minh','Đội CSGT Chợ Lớn',
'Tai nạn giao thông','Mất cả 5 ngón chân','Hư hại hoàn toàn','Nguyễn Thị Mai Duyên','Bồi thường tiền',
'/wp-content/uploads/2013/12/BTNTID.jpg','2013-12-21','2014-05-30','Từ chối bồi thường',NULL,'HD6679');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BTYFH8','Phan Thanh Dũng','633685','B2','0974131489','5','497 Hòa Hảo, P7, Q10',
'79S4-91663','2015-03-27','34 Hoàng Việt, P4, Q Tân Bình, TP Hồ Chí Minh','Đội CSGT Tân Sơn Nhất',
'Tai nạn giao thông','Gãy 2 xương cẳng chân','Hư hại hoàn toàn','Trần Thị Bích Liên','Bồi thường tiền',NULL,
'2015-05-16',NULL,NULL,NULL,'HD33FD');
INSERT INTO `mic_data`.`mic_compensation`(`conpensation_code`,`driver_name`,`license_number`,`license_type`,`driver_phone`,`vehicle_capacity`,
`driver_address`,`plate`,`accident_date`,`accident_place`,`control_department`,`description`,`human_damage`,
`asset_damage`,`observer`,`compensation_note`,`attachment`,`created_date`,`resolve_date`,`desicion`,
`resolve_note`,`contract_code`)
VALUES ('BT0LBH','Phùng Mạnh Ninh','475255','A2','0975861551','7','6 Bà Hom, P13, Q6',
'23Z1-38425','2013-12-10','117 Âu cơ - P. Phú Trung, Q Tân Phú, TP Hồ Chí Minh','Đội CSGT Phú Lâm',
'Tai nạn giao thông','Hỏng hoàn toàn chức năng nhai và nói, hô hấp','Hư hại hoàn toàn','Huỳnh Mỹ Thảo',
'Bồi thường tiền cho thương tật','/wp-content/uploads/2014/05/BT0LBH.jpg','2014-05-28','2014-09-30',
'Chấp nhận bồi thường',NULL,'HD76FF');