package com.whistlewithme.alarm

import com.whistlewithme.alarm.di.ConfigModule
import com.whistlewithme.alarm.di.DaggerAppComponent
import com.whistlewithme.alarm.helper.AppContext


fun main(args: Array<String>) {
    AppContext.init(args)
    val configModule = ConfigModule()
    val appComponent = DaggerAppComponent.builder().configModule(configModule).build()
    val httpServer = appComponent.server()
    httpServer.start()
    val mongo = appComponent.dbserver()
    mongo.startSession()
}
