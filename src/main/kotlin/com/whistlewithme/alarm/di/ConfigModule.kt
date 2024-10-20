package com.whistlewithme.alarm.di

import com.whistlewithme.alarm.resources.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    @Provides
    @Named(SERVER_HOST)
    fun provideSeverHost(): String {
        return SERVER_HOST
    }

    @Provides
    @Named(SERVER_PORT)
    fun provideServerPort(): String {
        return SERVER_PORT
    }

    @Provides
    @Named(DB_NAME)
    fun provideDbName(): String{
        return DB_NAME
    }

    @Provides
    @Named(DB_HOST)
    fun provideDbHost(): String{
        return DB_HOST
    }

    @Provides
    @Named(DB_COLLECTION)
    fun provideDbCollection(): String{
        return DB_COLLECTION
    }

    @Provides
    @Named(DB_PORT)
    fun provideDbPort(): String{
        return DB_PORT
    }
}
