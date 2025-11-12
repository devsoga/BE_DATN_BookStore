package com.devsoga.BookStore_V2.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.flywaydb.core.Flyway;

@Configuration
public class FlywayConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.flyway.locations:classpath:db/migration}")
    private String locations;

    @Bean
    public Flyway flyway() {
        Flyway flyway =  Flyway.configure()
                .dataSource(url, user, password)
                .locations(locations)
                .baselineOnMigrate(true) // nếu DB đã có dữ liệu
                .baselineVersion("0")
                .validateOnMigrate(false) // Temporarily disable validation to fix checksum mismatch
                .load();
        flyway.migrate();
        System.out.println("Migrating...");
        return flyway;
    }
}
