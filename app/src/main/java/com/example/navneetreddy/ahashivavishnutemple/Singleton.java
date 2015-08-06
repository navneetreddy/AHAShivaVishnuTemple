package com.example.navneetreddy.ahashivavishnutemple;

import com.mongodb.client.MongoDatabase;

/**
 * Created by NavneetReddy on 8/5/15.
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private static MongoDatabase database;


    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    private Singleton() {}

    public void setDatabase(MongoDatabase db) {
        database = db;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
}