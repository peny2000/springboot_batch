package com.a3.lfs.support.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatasourceConfig{

	@Bean(destroyMethod = "close")
	@ConfigurationProperties(locations = "classpath:db_config.yml", prefix = "monitoring.datasource")
	@Primary
	public DataSource monitoringDatasource(){

		return DataSourceBuilder.create().build();
	}

	@Bean
	public PlatformTransactionManager monitoringTxManager(@Qualifier("monitoringDatasource") DataSource dataSource){

		return new DataSourceTransactionManager(dataSource);
	}


	@Bean(destroyMethod = "close")
	@ConfigurationProperties("privacy.datasource")
	public DataSource privacyDatasource(){

		return DataSourceBuilder.create().build();
	}

	@Bean
	public PlatformTransactionManager privacyTxManager(@Qualifier("privacyDatasource") DataSource dataSource){

		return new DataSourceTransactionManager(dataSource);
	}


	@Bean(destroyMethod = "close")
	@ConfigurationProperties(locations = "classpath:db_config.yml", prefix = "shop.datasource")
	public DataSource shopDatasource(){

		return DataSourceBuilder.create().build();
	}
}