package io.github.guigol.libraryapi.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;

    @Bean
    public DataSource dataSource() {
        // Configure and return the DataSource bean
        // You can use HikariCP, Apache DBCP, or any other connection pool implementation
        // Example using HikariCP:
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setJdbcUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);

        dataSource.setMaximumPoolSize(10);//maximo de conexões
        dataSource.setMinimumIdle(1); // taanho inicial do pool
        dataSource.setIdleTimeout(100000); // tempo de inatividade antes de fechar a conexão
        dataSource.setMaxLifetime(600000); // tempo máximo de vida da conexão
        dataSource.setPoolName("librarydb-pool"); // nome do pool de conexões
        dataSource.setConnectionTestQuery("SELECT 1"); // consulta para testar a conexão

//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(this.driverClassName);
//        hikariConfig.setJdbcUrl(this.url);
//        hikariConfig.setUsername(this.username);
//        hikariConfig.setPassword(this.password);
//        hikariConfig.setMaximumPoolSize(10);
//        hikariConfig.setMinimumIdle(1);
//        hikariConfig.setIdleTimeout(100000);
//        hikariConfig.setMaxLifetime(600000);
//        hikariConfig.setPoolName("librarydb-pool");
//        hikariConfig.setConnectionTestQuery("SELECT 1");
//
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
}
