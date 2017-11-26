--
-- Database: `PowerballBatch`
--

-- --------------------------------------------------------

--
-- Table structure for table `PowerballDrawingInfo`
--

CREATE TABLE IF NOT EXISTS `PowerballDrawingInfo` (
  `DrawDate` date NOT NULL,
  `Number` int(11) NOT NULL,
  `NumberType` int(11) NOT NULL
);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `PowerballDrawingInfo`
--
ALTER TABLE `PowerballDrawingInfo`
 ADD UNIQUE KEY `DrawDate` (`DrawDate`,`Number`,`NumberType`);
