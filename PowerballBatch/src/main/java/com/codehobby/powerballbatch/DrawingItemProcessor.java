package com.codehobby.powerballbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class DrawingItemProcessor implements ItemProcessor<String, PowerballDrawing>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DrawingItemProcessor.class);

	public PowerballDrawing process( final String pbDrawingLine )
	{
		if( pbDrawingLine.startsWith("Draw Date") )
		{
			return null;
		}
		else
		{
			String delimiters = "[ ]+";
			int maxExpectedTokens = 8;
			int minExpectedTokens = 7;
			String[] tokens = pbDrawingLine.split(delimiters, maxExpectedTokens);
			if( tokens.length < minExpectedTokens )
			{
				LOGGER.error( "In processing one of the lines of the text file, got " + tokens.length + " tokens instead of the expected " + minExpectedTokens + " to " + maxExpectedTokens);
				LOGGER.error( "Line: \"" + pbDrawingLine + "\"");
				return null;
			}
			else
			{
				PowerballDrawing pbdLine = new PowerballDrawing();
				pbdLine.setDate( tokens[0] );
				pbdLine.setWhiteBallOne( tokens[1] );
				pbdLine.setWhiteBallTwo( tokens[2] );
				pbdLine.setWhiteBallThree( tokens[3] );
				pbdLine.setWhiteBallFour( tokens[4] );
				pbdLine.setWhiteBallFive( tokens[5] );
				pbdLine.setPowerball( tokens[6] );
				if( tokens.length >= 8 && !"".equals(tokens[7]) )
				{
					pbdLine.setPowerPlay( tokens[7] );
				}
				return pbdLine;
			}
		}
	}
}
