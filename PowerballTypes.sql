--
-- Database: `PowerballBatch`
--

-- --------------------------------------------------------

--
-- Table structure for table `PowerballTypes`
--

CREATE TABLE IF NOT EXISTS `PowerballTypes` (
  `TypeID` int(11) NOT NULL,
  `Description` varchar(20) NOT NULL
);

--
-- Dumping data for table `PowerballTypes`
--

INSERT INTO `PowerballTypes` (`TypeID`, `Description`) VALUES
(1, 'White Ball'),
(2, 'Powerball'),
(3, 'Power Play');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `PowerballTypes`
--
ALTER TABLE `PowerballTypes`
 ADD PRIMARY KEY (`TypeID`);
