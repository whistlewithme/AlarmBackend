package com.whistlewithme.alarm

import com.whistlewithme.alarm.di.ConfigModule
import com.whistlewithme.alarm.di.DaggerAppComponent


fun main() {
    val configModule = ConfigModule()
    val appComponent = DaggerAppComponent.builder().configModule(configModule).build()
    val httpServer = appComponent.server()
    httpServer.start()

}
