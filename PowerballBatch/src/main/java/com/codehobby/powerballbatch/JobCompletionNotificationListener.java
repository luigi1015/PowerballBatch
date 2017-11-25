package com.codehobby.powerballbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport
{
	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	//private final JdbcTemplate jdbcTemplate;
/*
	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate newJdbcTemplate)
	{
		jdbcTemplate = newJdbcTemplate;
	}
*/
	@Override
	public void afterJob(JobExecution jobExecution)
	{
		if(jobExecution.getStatus() == BatchStatus.COMPLETED)
		{
			LOGGER.info( "Job finished!" );
			//Could do something with jdbcTemplate to check the results, but leaving that for later.
		}
	}
}
