package com.whistlewithme.alarm.di

import com.whistlewithme.alarm.resources.*
import dagger.Module
import dagger.Provides
import javax.inject.Named
import com.whistlewithme.alarm.helper.AppContext

@Module
class ConfigModule {

    @Provides
    @Named(SERVER_HOST)
    fun provideSeverHost(): String {
        return AppContext.getProp(SERVER_HOST).toString()
    }

    @Provides
    @Named(SERVER_PORT)
    fun provideServerPort(): Int {
        return AppContext.getProp(SERVER_PORT)?.toInt() ?: 8080
    }

    @Provides
    @Named(DB_NAME)
    fun provideDbName(): String{
        return AppContext.getProp(DB_NAME).toString()
    }

    @Provides
    @Named(DB_HOST)
    fun provideDbHost(): String{
        return AppContext.getProp(DB_HOST).toString()
    }

    @Provides
    @Named(DB_COLLECTION)
    fun provideDbCollection(): String{
        return AppContext.getProp(DB_COLLECTION).toString()
    }

    @Provides
    @Named(DB_PORT)
    fun provideDbPort(): Int{
        return AppContext.getProp(DB_PORT)?.toInt() ?: 27017
    }
}
