package com.petception.beans;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@Configuration
public class DaoConfiguration {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoDatabase mongoDatabase;

    @Bean
    @Qualifier("petInfoCollection")
    public MongoCollection<Document> getPetInfoCollection()
    {
       return mongoDatabase.getCollection("petInfo");
    }

    @Bean
    @Qualifier("userInfoCollection")
    public MongoCollection<Document> userInfoCollection()
    {
        return mongoDatabase.getCollection("user");
    }

    @Bean
    public MongoClient getMongoClient()
    {
        return new MongoClient();
    }

    @Bean
    public MongoDatabase getMongoDatabase()
    {
        return mongoClient.getDatabase("petception");
    }



}
