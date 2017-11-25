package com.codehobby.powerballbatch;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class PowerballDrawingPreparedStatementSetter implements ItemPreparedStatementSetter<PowerballDrawing>
{
	public void setValues(PowerballDrawing pbdrawing, PreparedStatement preparedStatement) throws SQLException
	{
		preparedStatement.setString( 1, pbdrawing.getDateString() );
		preparedStatement.setInt( 2, pbdrawing.getWhiteBallOne() );
		preparedStatement.setInt( 3, pbdrawing.getWhiteBallTwo() );
		preparedStatement.setInt( 4, pbdrawing.getWhiteBallThree() );
		preparedStatement.setInt( 5, pbdrawing.getWhiteBallFour() );
		preparedStatement.setInt( 6, pbdrawing.getWhiteBallFive() );
		preparedStatement.setInt( 7, pbdrawing.getPowerball() );
		preparedStatement.setInt( 8, pbdrawing.getPowerPlay() );
	}
}
