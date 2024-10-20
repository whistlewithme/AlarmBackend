package com.whistlewithme.alarm.di

import com.whistlewithme.alarm.resources.AlarmResource
import com.whistlewithme.alarm.resources.SERVER_HOST
import com.whistlewithme.alarm.resources.SERVER_PORT
import dagger.Module
import dagger.Provides
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import javax.inject.Named
import javax.inject.Singleton
import javax.ws.rs.core.UriBuilder

@Module
class HttpModule {

    @Provides
    fun provideRestApplicationBootstrap(alarmResource: AlarmResource): ResourceConfig {
        return ResourceConfig()
            .register(alarmResource)
    }

    @Provides
    @Singleton
    fun provideHttpServer(
        @Named(SERVER_HOST) host: String,
        @Named(SERVER_PORT) port: Int,
        application: ResourceConfig
    ): HttpServer {
        val uri = UriBuilder.fromUri(host).port(port).build()
        return GrizzlyHttpServerFactory.createHttpServer(uri, application, false)
    }
}
