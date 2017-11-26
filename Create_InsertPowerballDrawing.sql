DROP PROCEDURE IF EXISTS InsertPowerballDrawing;

DELIMITER $$
CREATE PROCEDURE InsertPowerballDrawing
(IN drawDate date,
IN wbOne int,
IN wbTwo int,
IN wbThree int,
IN wbFour int,
IN wbFive int,
IN powerball int,
IN powerPlay int)
BEGIN
	INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, wbOne, 1);
	INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, wbTwo, 1);
	INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, wbThree, 1);
	INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, wbFour, 1);
	INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, wbFive, 1);
	INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, powerball, 2);
	IF powerPlay <> 0 AND powerPlay IS NOT NULL
	THEN
		INSERT IGNORE INTO `PowerballDrawingInfo`(`DrawDate`, `Number`, `NumberType`) VALUES (drawDate, powerPlay, 3);
	END IF;
END$$
DELIMITER ;
