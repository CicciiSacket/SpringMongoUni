package com.aitho;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages= "com.aitho")
public class ApplicationConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    @Override
    public MongoClient mongoClient() {

        ConnectionString connectionString = new ConnectionString(env.getProperty("spring.data.mongodb.uri"));

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.aitho");
    }
}

