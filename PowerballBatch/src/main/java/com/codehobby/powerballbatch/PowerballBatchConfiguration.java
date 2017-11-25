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
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.UrlResource;

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
	public FlatFileItemReader<PowerballDrawing> reader()
	{
		FlatFileItemReader<PowerballDrawing> reader = new FlatFileItemReader<PowerballDrawing>();
		String powerballURL = "http://www.powerball.com/powerball/winnums-text.txt";
		try
		{
			//Trying UrlResource instead of ClassPathResource in the Spring example.
			reader.setResource( new UrlResource(new URL(powerballURL)) );
		} catch (MalformedURLException e) {
			LOGGER.error( "Malformed URL exception: " + e.getMessage() );
			LOGGER.error( "URL: \"" + powerballURL + "\"" );
			LOGGER.error( Arrays.toString(e.getStackTrace()) );
		}
		reader.setLineMapper(new DefaultLineMapper<PowerballDrawing>() {{
			setLineTokenizer( new DelimitedLineTokenizer() {{
				setNames( new String[] {"date", 
						"whiteBallOne", 
						"whiteBallTwo", 
						"whiteBallThree", 
						"whiteBallFour", 
						"whiteBallFive",
						"powerball",
						"powerPlay"});
				setFieldSetMapper( new BeanWrapperFieldSetMapper<PowerballDrawing>() {{
					setTargetType( PowerballDrawing.class );
				}});
			}});
		}});
		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<PowerballDrawing> writer()
	{
		JdbcBatchItemWriter<PowerballDrawing> writer = new JdbcBatchItemWriter<PowerballDrawing>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PowerballDrawing>());
		writer.setSql("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?);");
		writer.setDataSource(datasource);
		ItemPreparedStatementSetter<PowerballDrawing> pbSetter = new PowerballDrawingPreparedStatementSetter();
		writer.setItemPreparedStatementSetter(pbSetter);
		return writer;
	}

	@Bean
	public Job importDrawingJob(JobCompletionNotificationListener listener)
	{
		return jobBuilderFactory.get("importDrawingJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();
	}

	@Bean
	public Step step1()
	{
		return stepBuilderFactory.get("step1")
				.<PowerballDrawing, PowerballDrawing> chunk(10)
				.reader(reader())
				.writer(writer())
				.build();
	}
}
