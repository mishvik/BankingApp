package com.ibm.enterprise.db.mongo;

import java.net.UnknownHostException;

import com.ibm.enterprise.common.ApplicationProperties;
import com.mongodb.DB;
import com.mongodb.MongoClient;
 
public class MongoDBSingleton {
 
 private static MongoDBSingleton mDbSingleton;
  
 private static MongoClient mongoClient;
     
 private static DB db ;
    
 private static final String dbHost = ApplicationProperties.getInstance().getMongodbHost();
 private static final int dbPort = ApplicationProperties.getInstance().getMongodbPort();
 private static final String dbName = ApplicationProperties.getInstance().getMongodbDatabase();
 
 private MongoDBSingleton(){};
  
 public static MongoDBSingleton getInstance(){
  if(mDbSingleton == null){
   mDbSingleton = new MongoDBSingleton();
  }
  return mDbSingleton;
 } 
  
 public DB getTestdb(){
  if(mongoClient == null){
   try {
    mongoClient = new MongoClient(dbHost , dbPort);
   } catch (UnknownHostException e) {
    return null;
   }
  }
  if(db == null)
   db = mongoClient.getDB(dbName);
  return db;
 }
}