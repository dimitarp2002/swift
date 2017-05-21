-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-0ubuntu0.16.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Dumping data for table AddressDB.addresses: ~4 rows (approximately)
DELETE FROM `addresses`;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` (`id`, `street_id`, `number`, `apartmentNo`) VALUES
	(1, 1, '33', '11'),
	(2, 2, '34', '12'),
	(3, 3, '33', '12'),
	(4, 4, '33', '17');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping data for table AddressDB.countries: ~4 rows (approximately)
DELETE FROM `countries`;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` (`id`, `name`) VALUES
	(1, 'Bulgaria'),
	(2, 'Romania'),
	(3, 'Greece'),
	(4, 'Macedonia');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;

-- Dumping data for table AddressDB.municipalities: ~4 rows (approximately)
DELETE FROM `municipalities`;
/*!40000 ALTER TABLE `municipalities` DISABLE KEYS */;
INSERT INTO `municipalities` (`id`, `name`, `postal_code`, `populated_area_id`) VALUES
	(1, 'Triaditsa', '1030', 1),
	(2, 'Bucharest_center', '2000', 2),
	(3, 'Solun_center', '3000', 3),
	(4, 'Ohrid', '4000', 4);
/*!40000 ALTER TABLE `municipalities` ENABLE KEYS */;

-- Dumping data for table AddressDB.populated_areas: ~4 rows (approximately)
DELETE FROM `populated_areas`;
/*!40000 ALTER TABLE `populated_areas` DISABLE KEYS */;
INSERT INTO `populated_areas` (`id`, `name`, `type_id`, `region_id`) VALUES
	(1, 'Sofia', 3, 1),
	(2, 'Bucharest', 3, 3),
	(3, 'Solun', 2, 6),
	(4, 'Ohrid', 1, 8);
/*!40000 ALTER TABLE `populated_areas` ENABLE KEYS */;

-- Dumping data for table AddressDB.populated_area_types: ~3 rows (approximately)
DELETE FROM `populated_area_types`;
/*!40000 ALTER TABLE `populated_area_types` DISABLE KEYS */;
INSERT INTO `populated_area_types` (`id`, `name`) VALUES
	(1, 'village'),
	(2, 'town'),
	(3, 'city');
/*!40000 ALTER TABLE `populated_area_types` ENABLE KEYS */;

-- Dumping data for table AddressDB.regions: ~8 rows (approximately)
DELETE FROM `regions`;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` (`id`, `name`, `country_id`) VALUES
	(1, 'South', 1),
	(2, 'North', 1),
	(3, 'South', 2),
	(4, 'North', 2),
	(5, 'South', 3),
	(6, 'North', 3),
	(7, 'East', 4),
	(8, 'West', 4);
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;

-- Dumping data for table AddressDB.streets: ~4 rows (approximately)
DELETE FROM `streets`;
/*!40000 ALTER TABLE `streets` DISABLE KEYS */;
INSERT INTO `streets` (`id`, `name`, `municipality_id`) VALUES
	(1, 'aaa', 1),
	(2, 'bbb', 2),
	(3, 'sss', 3),
	(4, 'ooo', 4);
/*!40000 ALTER TABLE `streets` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

