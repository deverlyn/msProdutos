package com.fiap.msProdutos.config;


import com.fiap.msProdutos.infra.persistence.produto.ProdutoEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    @Bean
    public Job processarProduto(JobRepository jobRepository, Step step) {
        return new JobBuilder("processorProduto", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<ProdutoEntity> reader,
                     ItemWriter<ProdutoEntity> writer) {
        return new StepBuilder("step", jobRepository)
                .<ProdutoEntity, ProdutoEntity>chunk(30, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<ProdutoEntity> reader() {
        BeanWrapperFieldSetMapper<ProdutoEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ProdutoEntity.class);
        fieldSetMapper.setDistanceLimit(0);
        return new FlatFileItemReaderBuilder<ProdutoEntity>()
                .name("produtoItemReader")
                .resource(new ClassPathResource("produtos.csv"))
                .delimited()
                .delimiter(",")
                .names("id", "nome", "descricao", "quantidade")
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean
    public ItemWriter<ProdutoEntity> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ProdutoEntity>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("INSERT INTO produto (id, nome, descricao, quantidade) VALUES (:id, :nome, :descricao, :quantidade)")
                .beanMapped()
                .build();
    }



}