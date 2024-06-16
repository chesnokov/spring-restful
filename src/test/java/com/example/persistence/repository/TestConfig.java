package com.example.persistence.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Profile("testing")
@Configuration
public class TestConfig {
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testingEntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManager =
				new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource);
		entityManager.setPackagesToScan("com.example.persistence.model");
		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManager.setJpaProperties(testingAdditionalProperties());

		return entityManager;
	}

	@Bean
	public DataSource testingDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setName("userdb")
				.addScript("/userdb.sql")
				.build();
	}

	Properties testingAdditionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.ddl-auto", "update");
		properties.setProperty("show_sql", "true");
		return properties;
	}

}
