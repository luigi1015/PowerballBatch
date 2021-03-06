package com.codehobby.powerballbatch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.EmptyResultDataAccessException;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:config.properties")
@PropertySource("classpath:application.properties")
public class PowerballBatchConfiguration
{
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource datasource;

	private static final Logger LOGGER = LoggerFactory.getLogger(PowerballBatchConfiguration.class);
	
	@Bean
	public FlatFileItemReader<String> reader() throws MalformedURLException
	{
		FlatFileItemReader<String> reader = new FlatFileItemReader<String>();
		String powerballURL = "http://www.powerball.com/powerball/winnums-text.txt";
		try
		{
			//Trying UrlResource instead of ClassPathResource in the Spring example.
			URL pbURL = new URL(powerballURL);
			UrlResource pbURLResource = new UrlResource(pbURL);
			reader.setResource( pbURLResource );
		} catch( MalformedURLException mue ) {
			LOGGER.error( "Malformed URL exception: " + mue.getMessage() );
			LOGGER.error( "URL: \"" + powerballURL + "\"" );
			LOGGER.error( Arrays.toString(mue.getStackTrace()) );
			throw mue;
		}
		reader.setLineMapper( new PassThroughLineMapper() );
		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<PowerballDrawing> writer()
	{
		JdbcBatchItemWriter<PowerballDrawing> writer = new JdbcBatchItemWriter<PowerballDrawing>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PowerballDrawing>());
		writer.setSql("CALL InsertPowerballDrawing( ?, ?, ?, ?, ?, ?, ?, ?);");
		writer.setDataSource(datasource);
		ItemPreparedStatementSetter<PowerballDrawing> pbSetter = new PowerballDrawingPreparedStatementSetter();
		writer.setItemPreparedStatementSetter(pbSetter);
		return writer;
	}

	@Bean
	public DrawingItemProcessor processor()
	{
		return new DrawingItemProcessor();
	}

	@Bean
	public Job importDrawingJob(JobCompletionNotificationListener listener) throws MalformedURLException
	{
		return jobBuilderFactory.get("importDrawingJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(importDrawingInfoStep())
				.end()
				.build();
	}

	@Bean
	public Step importDrawingInfoStep() throws MalformedURLException
	{
		return stepBuilderFactory.get("Import Powerball Drawings Info Step")
				.<String, PowerballDrawing> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
}
