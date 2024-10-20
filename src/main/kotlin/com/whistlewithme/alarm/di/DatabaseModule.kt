package com.whistlewithme.alarm.di

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.whistlewithme.alarm.resources.DB_HOST
import com.whistlewithme.alarm.resources.DB_NAME
import com.whistlewithme.alarm.resources.DB_PORT
import javax.inject.Named


@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMongoClient(
        @Named(DB_HOST) host: String,
        @Named(DB_PORT) port: Int
    ): MongoClient {
        return MongoClients.create("mongodb://${host}:${port}")
    }


    @Provides
    fun provideShipmentDatabase(
        mongoClient: MongoClient,
        @Named(DB_NAME) databaseName: String
    ): MongoDatabase {
        return mongoClient.getDatabase(databaseName)
    }
}