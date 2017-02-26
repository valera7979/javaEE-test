
CREATE TABLE `user` (
	 ID INT(8) NOT NULL AUTO_INCREMENT, 
	`name` varchar(25) NOT NULL,
	`age` int NOT NULL,
	`isAdmin` bit NOT NULL,
	`createdDate` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
);


INSERT INTO `user` (`age`,`isAdmin`,`name`) VALUES
('20',0,'Carmen Padberg'),
('41',1,'Dr. Sallie Kub'),
('42',0,'Wilford Kris'),
('66',0,'Estell Hansen'),
('73',0,'Prof. Nathanial Altenwerth DDS'),
('39',0,'Cloyd Kassulke'),
('31',0,'Luther Towne V'),
('71',0,'Florida Jacobi III'),
('15',0,'Quinton Russel'),
('46',0,'Dorthy Kovacek'),
('53',0,'Deanna Orn I'),
('29',0,'Ashlee Kris MD'),
('72',0,'Wilfrid Gutmann'),
('58',1,'Ms. Karlee Homenick'),
('29',0,'Maribel Wintheiser'),
('22',0,'Angus Waters'),
('57',0,'Maye Kirlin'),
('51',0,'Nicholas Monahan'),
('61',0,'Keven Schneider Sr.'),
('19',0,'Gabriel Lang MD'),
('28',0,'Mr. Dario Aufderhar'),
('42',0,'Prof. Zetta Cronin Sr.'),
('40',0,'Coralie Auer'),
('76',0,'Jack Runolfsson'),
('20',0,'Lisandro Beahan'),
('42',0,'Mckenzie Tromp'),
('11',0,'Camila Gleason II'),
('38',0,'Ambrose Prosacco V'),
('26',0,'Josephine Sanford'),
('42',0,'Jayme Weber');