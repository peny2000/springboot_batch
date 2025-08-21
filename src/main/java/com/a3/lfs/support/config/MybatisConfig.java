package com.a3.lfs.support.config;

import com.a3.lfs.support.annotation.MonitoringDb;
import com.a3.lfs.support.annotation.PrivacyDb;
import com.a3.lfs.support.annotation.ShopDb;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

public abstract class MybatisConfig{

	protected void configureSqlSessionFactory(SqlSessionFactoryBean factoryBean, DataSource dataSource){

		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		factoryBean.setDataSource(dataSource);
	}
}

@Configuration
@MapperScan(
	basePackages = "com.a3.lfs.mapper.monitoring",
	sqlSessionFactoryRef = "monitoringSessionFactory",
	annotationClass = MonitoringDb.class
)
class MonitoringConfig extends MybatisConfig{

	@Bean
	@Primary
	public SqlSessionFactory monitoringSessionFactory(@Qualifier("monitoringDatasource") DataSource dataSource) throws Exception{

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		configureSqlSessionFactory(sqlSessionFactoryBean, dataSource);
		return sqlSessionFactoryBean.getObject();
	}
}

@Configuration
@MapperScan(
		basePackages = "com.a3.lfs.mapper.privacy",
		sqlSessionFactoryRef = "privacySessionFactory",
		annotationClass = PrivacyDb.class
)
class PrivacyConfig extends MybatisConfig{

	@Bean
	public SqlSessionFactory privacySessionFactory(@Qualifier("privacyDatasource") DataSource dataSource) throws Exception{

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		configureSqlSessionFactory(sqlSessionFactoryBean, dataSource);
		return sqlSessionFactoryBean.getObject();
	}
}

@Configuration
@MapperScan(
		basePackages = "com.a3.lfs.mapper.shop",
		sqlSessionFactoryRef = "shopSessionFactory",
		annotationClass = ShopDb.class
)
class ShopConfig extends MybatisConfig{

	@Bean
	public SqlSessionFactory shopSessionFactory(@Qualifier("shopDatasource") DataSource dataSource) throws Exception{

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		configureSqlSessionFactory(sqlSessionFactoryBean, dataSource);
		return sqlSessionFactoryBean.getObject();
	}
}