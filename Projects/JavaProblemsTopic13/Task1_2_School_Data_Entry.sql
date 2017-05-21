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
-- Dumping data for table School.addresses: ~7 rows (approximately)
DELETE FROM `addresses`;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` (`id`, `country`, `city`, `street`, `number`, `floor`, `apartment_no`) VALUES
	(1, 'Bulgaria', 'Sofia', 'aaa', '1', 1, 1),
	(2, 'Bulgaria', 'Sofia', 'bbb', '2', 2, 2),
	(3, 'Bulgaria', 'Sofia', 'ccc', '3', 3, 3),
	(4, 'Bulgaria', 'Sofia', 'ddd', '4', 4, 4),
	(5, 'Bulgaria', 'Sofia', 'eee', '5', 5, 5),
	(6, 'Bulgaria', 'Sofia', 'fff', '6', 6, 6),
	(7, 'Bulgaria', 'Plovdiv', 'ggg', '7', 7, 7),
	(8, 'Bulgaria', 'Varna', 'hhh', '8', 8, 8);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping data for table School.disciplines: ~4 rows (approximately)
DELETE FROM `disciplines`;
/*!40000 ALTER TABLE `disciplines` DISABLE KEYS */;
INSERT INTO `disciplines` (`id`, `name`) VALUES
	(1, 'Mathematics'),
	(2, 'Geography'),
	(3, 'Physics'),
	(4, 'Informatics');
/*!40000 ALTER TABLE `disciplines` ENABLE KEYS */;

-- Dumping data for table School.disciplines_studied: ~8 rows (approximately)
DELETE FROM `disciplines_studied`;
/*!40000 ALTER TABLE `disciplines_studied` DISABLE KEYS */;
INSERT INTO `disciplines_studied` (`id`, `student_id`, `discipline_id`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 2, 2),
	(5, 2, 4),
	(6, 3, 3),
	(7, 4, 1),
	(8, 4, 2);
/*!40000 ALTER TABLE `disciplines_studied` ENABLE KEYS */;

-- Dumping data for table School.disciplines_taught: ~6 rows (approximately)
DELETE FROM `disciplines_taught`;
/*!40000 ALTER TABLE `disciplines_taught` DISABLE KEYS */;
INSERT INTO `disciplines_taught` (`id`, `teacher_id`, `discipline_id`) VALUES
	(1, 1, 1),
	(2, 1, 4),
	(3, 2, 1),
	(4, 2, 3),
	(5, 3, 3),
	(6, 4, 2);
/*!40000 ALTER TABLE `disciplines_taught` ENABLE KEYS */;

-- Dumping data for table School.students: ~4 rows (approximately)
DELETE FROM `students`;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` (`id`, `name`, `enrollment_date`, `address_id`) VALUES
	(1, 'Georgi Georgiev', '2000-10-22', 5),
	(2, 'Stoyan Ivanov', '2010-11-18', 6),
	(3, 'Mariya Grozdanova', '2008-05-27', 7),
	(4, 'Stefaniya Petkova', '2007-01-12', 8);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;

-- Dumping data for table School.teachers: ~4 rows (approximately)
DELETE FROM `teachers`;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` (`id`, `name`, `email`, `salary`, `address_id`) VALUES
	(1, 'Peter Ivanov', 'pivanov@gmail.com', 1921.5, 1),
	(2, 'Georgi Stoimenov', 'stoimenov@gmail.com', 1787.25, 2),
	(3, 'Nataliya Yordanova', 'nyyoo@abv.bg', 1888.21, 3),
	(4, 'Simeon Prodanov', 'sprod@yahoo.com', 1341.6, 4);
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

