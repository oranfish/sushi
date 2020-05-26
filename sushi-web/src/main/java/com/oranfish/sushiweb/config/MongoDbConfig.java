package com.oranfish.sushiweb.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@EnableConfigurationProperties(MongoDbProperties.class)
@PropertySource(value="file:/Users/admin/Documents/env/snapshot/common/common/mongodb.log.properties", encoding="utf-8")
@Configuration
public class MongoDbConfig {

    @Bean
    public MongoDbFactory mongoDbFactory(MongoDbProperties mongoDbProperties){
        String[] hostPortArray = mongoDbProperties.getHostport().split(":");

        MongoClient mongoClient = new MongoClient(new ServerAddress(hostPortArray[0], Integer.parseInt(hostPortArray[1])),
                MongoCredential.createCredential(mongoDbProperties.getUsername(), mongoDbProperties.getDbname(), mongoDbProperties.getPassword().toCharArray()),
                MongoClientOptions.builder()
                        .minHeartbeatFrequency(25)
                        .heartbeatSocketTimeout(3000)
                        .connectionsPerHost(mongoDbProperties.getConnectionsPerHost())
                        .threadsAllowedToBlockForConnectionMultiplier(mongoDbProperties.getThreadsAllowedToBlockForConnectionMultiplier())
                        .connectTimeout(mongoDbProperties.getConnectTimeout())
                        .maxWaitTime(mongoDbProperties.getMaxWaitTime())
                        .socketTimeout(mongoDbProperties.getSocketTimeout())
                        .build());

        //注册一个MongoDbFactory，连接到指定数据库
        return new SimpleMongoDbFactory(mongoClient, mongoDbProperties.getDbname());
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate getMongoTemplate(MongoDbFactory mongoDbFactory)
    {
        return new MongoTemplate(mongoDbFactory);
    }

}
