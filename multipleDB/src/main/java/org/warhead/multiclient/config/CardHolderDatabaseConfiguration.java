package org.warhead.multiclient.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.warhead.multiclient.domain.cardholder.CreditCardHolder;

import com.zaxxer.hikari.HikariDataSource;

@EnableJpaRepositories(basePackages = "org.warhead.multiclient.repository.cardholder",
transactionManagerRef = "cardholderTransactionManager",
entityManagerFactoryRef = "cardholderEntityManagerFactory")
@Configuration
public class CardHolderDatabaseConfiguration {

	@Bean
	@ConfigurationProperties("spring.cardholder.datasource")
	public DataSourceProperties cardHolderDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.cardholder.datasource.hikari")
	public DataSource cardHolderDataSource(
			@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties) {
		
	return cardHolderDataSourceProperties.initializeDataSourceBuilder()
			.type(HikariDataSource.class)
			.build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean cardholderEntityManagerFactory(
			@Qualifier("cardHolderDataSource") DataSource cardHolderDataSource,
			EntityManagerFactoryBuilder builder) {
		
		  Properties props = new Properties();
	        props.put("hibernate.hbm2ddl.auto", "validate");
	        props.put("hibernate.physical_naming_strategy",
	                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
	        
		LocalContainerEntityManagerFactoryBean lcemfb = builder.dataSource(cardHolderDataSource)
				.packages(CreditCardHolder.class)
				.build();
		
		lcemfb.setJpaProperties(props);
		return lcemfb;
	}
	
	@Bean
	PlatformTransactionManager cardholderTransactionManager(
			@Qualifier("cardholderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardholderEntityManagerFactory) {
		return new JpaTransactionManager(cardholderEntityManagerFactory.getObject());
	}
}
