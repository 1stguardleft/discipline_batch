package com.lstguardleft.discipline_batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@SpringBootApplication
public class DisciplineBatchApplication {

	@Autowired
	private DataSource batchDataSource;

	@Bean
	public DataSourceTransactionManager transactionManager(){
		System.out.println("Hello World DataSourceTransactionManager");
		return new DataSourceTransactionManager(batchDataSource);
	}

	@Bean
	public Tasklet helloWorldTasklet(){
		System.out.println("Hello World helloWorldTasklet");
		return (StepContribution contribution, ChunkContext chunkContext) -> {
			String name = (String) chunkContext.getStepContext().getJobParameters().get("name");
			System.out.println(String.format("Helloooooooooooooooo %s World", name));
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Step step(JobRepository jobRepository){
		System.out.println("Hello World step");
		StepBuilder stepBuilderOne = new StepBuilder("Step1", jobRepository);
		return stepBuilderOne.tasklet(helloWorldTasklet(), transactionManager()).build();
	}

	@Bean
	public Job job(JobRepository jobRepository){
		System.out.println("Hello World job");
		return new JobBuilder("job", jobRepository).start(step(jobRepository)).build();
	}

	public static void main(String[] args) {
		System.out.println("Hello World main");
		SpringApplication.run(DisciplineBatchApplication.class, args);
	}
}