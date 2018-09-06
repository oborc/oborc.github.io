DROP TABLE IF EXISTS `organization`;
--
-- Table structure for table `organization`
--


CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `company_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;