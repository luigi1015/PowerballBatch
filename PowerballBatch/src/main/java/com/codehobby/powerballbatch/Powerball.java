package com.codehobby.powerballbatch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Powerball
{
	public enum PowerballType
	{
		WHITE_BALL, POWERBALL, POWER_PLAY
	}

	private int number;
	private PowerballType type;
	private Calendar date;

	public Powerball( int newNumber, PowerballType newType, Calendar newDate )
	{
		number = newNumber;
		type = newType;
		date = newDate;
	}

	public void setNumber( int newNumber )
	{
		number = newNumber;
	}

	public int getNumber()
	{
		return number;
	}

	public void setType( PowerballType newType )
	{
		type = newType;
	}

	public PowerballType getType()
	{
		return type;
	}

	public void setDate( Calendar newDate )
	{
		date = newDate;
	}

	public Calendar getDate()
	{
		return date;
	}

	/**
	 * Returns a string in the format "number type MM-dd-yyyy".
	 * 
	 * @return A string in the format "number type MM-dd-yyyy"
	 */
	public String toString()
	{
		StringBuilder toStringSB = new StringBuilder();

		//The number
		toStringSB.append( number );
		toStringSB.append( " " );

		//The type
		switch( type )
		{
			case WHITE_BALL:
				toStringSB.append( "White Ball" );
				break;

			case POWERBALL:
				toStringSB.append( "Powerball" );
				break;

			case POWER_PLAY:
				toStringSB.append( "Power Play" );
				break;

			default:
				toStringSB.append( "Unkown Type" );
		}
		toStringSB.append( " " );

		//The date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		toStringSB.append( dateFormat.format(date.getTime()) );

		return toStringSB.toString();
	}
}
