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
