package com.codehobby.powerballbatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerballDrawing
{
	private Calendar drawDate = new GregorianCalendar();
	private String drawDatePattern = "MM/dd/yyyy";
	private SimpleDateFormat drawDateFormat = new SimpleDateFormat(drawDatePattern);
	private byte[] whiteBalls = new byte[5];
	private byte powerball;
	private byte powerPlay;
	private static final Logger LOGGER = LoggerFactory.getLogger(PowerballDrawing.class);
	private boolean isPowerPlaySet = false;

	public PowerballDrawing()
	{
	}

	public PowerballDrawing( String newDate,
			String newWhiteBallOne,
			String newWhiteBallTwo,
			String newWhiteBallThree,
			String newWhiteBallFour,
			String newWhiteBallFive,
			String newPowerball,
			String newPowerPlay)
	{
		setDate( newDate );
		setWhiteBallOne( newWhiteBallOne );
		setWhiteBallTwo( newWhiteBallTwo );
		setWhiteBallThree( newWhiteBallThree );
		setWhiteBallFour( newWhiteBallFour );
		setWhiteBallFive( newWhiteBallFive );
		setPowerball( newPowerball );
		setPowerPlay( newPowerPlay );
	}

	public void setDate( String newDate )
	{
		try
		{
			drawDate.setTime( drawDateFormat.parse(newDate) );
		} catch( ParseException pe ) {
			LOGGER.error( "Date parsing exception in PowerballDrawing: " + pe.getMessage() );
			LOGGER.error( "Trying to process date \"" + newDate + "\"" );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(pe.getStackTrace()) );
		}
	}

	public Calendar getDate()
	{
		return drawDate;
	}

	public String getDateString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(drawDate.getTime());
	}

	public void setWhiteBallOne( String newWhiteBallOne )
	{
		try
		{
			whiteBalls[0] = Byte.parseByte( newWhiteBallOne );
		} catch( NumberFormatException nfe ) {
			LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
			LOGGER.error( "Trying to process White Ball One: \"" + newWhiteBallOne + "\" " );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
		}
	}

	public byte getWhiteBallOne()
	{
		return whiteBalls[0];
	}

	public void setWhiteBallTwo( String newWhiteBallTwo )
	{
		try
		{
			whiteBalls[1] = Byte.parseByte( newWhiteBallTwo );
		} catch( NumberFormatException nfe ) {
			LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
			LOGGER.error( "Trying to process White Ball Two: \"" + newWhiteBallTwo + "\" " );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
		}
	}

	public byte getWhiteBallTwo()
	{
		return whiteBalls[1];
	}

	public void setWhiteBallThree( String newWhiteBallThree )
	{
		try
		{
			whiteBalls[2] = Byte.parseByte( newWhiteBallThree );
		} catch( NumberFormatException nfe ) {
			LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
			LOGGER.error( "Trying to process White Ball Three: \"" + newWhiteBallThree + "\" " );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
		}
	}

	public byte getWhiteBallThree()
	{
		return whiteBalls[2];
	}

	public void setWhiteBallFour( String newWhiteBallFour )
	{
		try
		{
			whiteBalls[3] = Byte.parseByte( newWhiteBallFour );
		} catch( NumberFormatException nfe ) {
			LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
			LOGGER.error( "Trying to process White Ball Four: \"" + newWhiteBallFour + "\" " );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
		}
	}

	public byte getWhiteBallFour()
	{
		return whiteBalls[3];
	}

	public void setWhiteBallFive( String newWhiteBallFive )
	{
		try
		{
			whiteBalls[4] = Byte.parseByte( newWhiteBallFive );
		} catch( NumberFormatException nfe ) {
			LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
			LOGGER.error( "Trying to process White Ball Five: \"" + newWhiteBallFive + "\" " );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
		}
	}

	public byte getWhiteBallFive()
	{
		return whiteBalls[4];
	}

	public void setPowerball( String newPowerball )
	{
		try
		{
			powerball = Byte.parseByte( newPowerball );
		} catch( NumberFormatException nfe ) {
			LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
			LOGGER.error( "Trying to process Powerball: \"" + newPowerball + "\" " );
			LOGGER.error( "Stack Trace: " );
			LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
		}
	}

	public byte getPowerball()
	{
		return powerball;
	}

	public void setPowerPlay( String newPowerPlay )
	{
		if( newPowerPlay != null && !"".equals(newPowerPlay))
		{
			isPowerPlaySet = true;
			try
			{
				powerPlay = Byte.parseByte( newPowerPlay );
			} catch( NumberFormatException nfe ) {
				LOGGER.error( "Number format exception in PowerballDrawing: " + nfe.getMessage() );
				LOGGER.error( "Trying to process Power Play: \"" + newPowerPlay + "\" " );
				LOGGER.error( "Stack Trace: " );
				LOGGER.error( Arrays.toString(nfe.getStackTrace()) );
			}
		}
		else
		{
			isPowerPlaySet = false;
			powerPlay = 0;
		}
	}

	public byte getPowerPlay()
	{
		return powerPlay;
	}

	public boolean hasPowerPlay()
	{
		return isPowerPlaySet;
	}
}
