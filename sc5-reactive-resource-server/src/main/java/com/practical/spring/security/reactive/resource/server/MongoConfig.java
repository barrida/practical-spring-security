//package com.practical.spring.security.reactive.resource.server;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
///**
// * @author suleyman.yildirim
// */
//@EnableReactiveMongoRepositories(basePackageClasses = {MongoConfig.class})
//public class MongoConfig extends AbstractReactiveMongoConfiguration {
//
//    @Value("${spring.data.mongo.uri}")
//    String mongoUri;
//
//    @Override
//    public MongoClient reactiveMongoClient() {
//        return MongoClients.create(mongoUri);
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return "product";
//    }
//
//}
//
