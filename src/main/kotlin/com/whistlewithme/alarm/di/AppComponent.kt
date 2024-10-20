package com.whistlewithme.alarm.di

import com.mongodb.client.MongoClient
import dagger.Component
import org.glassfish.grizzly.http.server.HttpServer
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, ConfigModule::class, DatabaseModule::class])
interface AppComponent {

    fun server(): HttpServer
    fun dbserver(): MongoClient

}
