package ru.dream.checkingsleep.config;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "${packages.common.mongo.repository}" })
@EntityScan(basePackages = { "${packages.common.mongo.entity}" })
public class MongoConfig {
    @Autowired
    MongoMappingContext mongoMappingContext;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.mongo")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean
    @Primary
    public MongoClient mongo() {
        return new MongoClient(mongoProperties().getHost(), mongoProperties().getPort());
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() throws Exception {
        MongoDbFactory factory = new SimpleMongoDbFactory(mongo(), mongoProperties().getDatabase());
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));  // remove _class

        return new MongoTemplate(factory, converter);
    }
}
