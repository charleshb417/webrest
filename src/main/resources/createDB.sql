SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `openbaltimore`
--
CREATE DATABASE IF NOT EXISTS `openbaltimore` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `openbaltimore`;

-- --------------------------------------------------------

--
-- Table structure for table `appuser`
--

CREATE TABLE IF NOT EXISTS `appuser` (
  `username` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `userrole` varchar(64) NOT NULL DEFAULT 'ROLE_USER'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appuser`
--

INSERT INTO `appuser` (`username`, `password`, `userrole`) VALUES
('user', '$2a$10$O7y.4uIP96cSu64j00dXc.5HPmwvdidAv4sgxgaM7sR8gUxqGWfLm', 'USER');

--
-- Indexes for table `appuser`
--
ALTER TABLE `appuser`
  ADD PRIMARY KEY (`username`);

--
-- Table structure for table `crime`
--

CREATE TABLE IF NOT EXISTS `crime` (
  `CrimeID` int(11) NOT NULL PRIMARY KEY UNIQUE KEY AUTO_INCREMENT,
  `CrimeDate` date NOT NULL,
  `CrimeCode` varchar(12) NOT NULL,
  `Location` varchar(64) NOT NULL,
  `Description` varchar(128) NOT NULL,
  `Weapon` varchar(128) NOT NULL,
  `District` varchar(12) NOT NULL,
  `Neighborhood` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vacant`
--

CREATE TABLE IF NOT EXISTS `vacant` (
  `ReferenceID` varchar(24) NOT NULL PRIMARY KEY,
  `Block` varchar(8) NOT NULL,
  `Lot` varchar(8) NOT NULL,
  `BuildingAddress` varchar(64) NOT NULL,
  `NoticeDate` date NOT NULL,
  `Neighborhood` varchar(64) NOT NULL,
  `PoliceDistrict` varchar(12) NOT NULL,
  `CouncilDistrict` int(11) NOT NULL,
  `Location` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Load data into Vacant table
--

LOAD DATA LOCAL INFILE 'data/vacants.csv'
IGNORE INTO TABLE vacant 
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(ReferenceID,Block,Lot,BuildingAddress,@a,Neighborhood,PoliceDistrict,CouncilDistrict,Location)
SET NoticeDate = STR_TO_DATE(@a,'%m/%d/%Y');

--
-- Load data into Crime table
--

LOAD DATA LOCAL INFILE 'data/crimes.csv'
IGNORE INTO TABLE crime 
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
(@b,CrimeCode,Location,Description,Weapon,District,Neighborhood)
SET CrimeDate = STR_TO_DATE(@b,'%m/%d/%Y');

--
-- Create Neighborhoods View
--
CREATE OR REPLACE VIEW neighborhood
AS SELECT Neighborhood, COUNT(CrimeID) as 'NumCrimes', 
(SELECT COUNT(ReferenceID) FROM vacant where Neighborhood=crime.Neighborhood) as 'NumVacants' 
FROM crime GROUP BY Neighborhood