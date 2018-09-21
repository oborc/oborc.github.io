package com.example.demo.configuration;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@ConfigurationProperties(prefix = "application.yml")
public class FlywayInitConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private Flyway flyway;

    private Logger logger = LoggerFactory.getLogger(FlywayInitConfiguration.class);

    @Bean("flywayInitializer")
    FlywayMigrationInitializer flywayInitializer() {
        createDatabase();
        return new FlywayMigrationInitializer(flyway, null);
    }

    private void createDatabase() {
        try {
            String url = dataSourceProperties.getUrl();
            String username = dataSourceProperties.getUsername();
            String password = dataSourceProperties.getPassword();
            logger.info("database is: {}", url);
            String dbName = url.split("//|/|\\?")[2];
            String queryStr = url.split("//|/|\\?")[3];
            //may be more than one host
            String hostStr = url.split("//|/|\\?")[1];
            String [] hosts = hostStr.split(",");
            String newUrl = "http://" + hosts[0] + "?" + queryStr ;
            URL buildUrl = new URL(newUrl);
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + buildUrl.getHost() + ":" + buildUrl.getPort(), username, password);
            Statement statement = connection.createStatement();
            statement.execute(String.format("CREATE DATABASE IF NOT EXISTS `%s` CHARACTER SET utf8", dbName));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
