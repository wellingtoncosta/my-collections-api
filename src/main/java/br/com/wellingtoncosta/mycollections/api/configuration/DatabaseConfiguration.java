package br.com.wellingtoncosta.mycollections.api.configuration;

import br.com.wellingtoncosta.mycollections.api.Application;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan(basePackageClasses = Application.class)
public class DatabaseConfiguration {

    @Bean @Lazy public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(dataSourceProperties.getDriverClassName());
        config.setJdbcUrl(dataSourceProperties.getUrl());
        config.setUsername(dataSourceProperties.getUsername());
        config.setPassword(dataSourceProperties.getPassword());

        return new HikariDataSource(config);
    }

}
