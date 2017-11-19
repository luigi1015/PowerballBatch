package com.codehobby.powerballbatch;

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
}
