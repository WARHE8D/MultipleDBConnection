package org.warhead.multiclient.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.warhead.multiclient.domain.pan.CreditCardPan;

import com.zaxxer.hikari.HikariDataSource;

@EnableJpaRepositories(basePackages = "org.warhead.multiclient.repository.pan",
transactionManagerRef = "panTransactionManager",
entityManagerFactoryRef = "panEntityManagerFactory")
@Configuration
public class PanDatabaseConfiguration {
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.pan.datasource")
	public DataSourceProperties panDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean
	@ConfigurationProperties("spring.pan.datasource.hikari")
	public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties) {
		return panDataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(
			@Qualifier("panDataSource") DataSource panDataSource,
			EntityManagerFactoryBuilder builder) {
		
		Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "validate");
        props.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        
		LocalContainerEntityManagerFactoryBean lcemf = builder.dataSource(panDataSource)
				.packages(CreditCardPan.class)
				.build();
		lcemf.setJpaProperties(props);
		return lcemf;
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager panTransactionManager(
			@Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory) {
		return new JpaTransactionManager(panEntityManagerFactory.getObject());
	}
}
