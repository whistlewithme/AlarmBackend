package com.whistlewithme.alarm

import com.whistlewithme.alarm.di.ConfigModule
import com.whistlewithme.alarm.di.DaggerAppComponent
import com.whistlewithme.alarm.helper.AppContext
import com.whistlewithme.alarm.resources.SERVER_HOST
import com.whistlewithme.alarm.resources.SERVER_PORT


fun main(args: Array<String>) {
    AppContext.init(args)
    val configModule = ConfigModule()
    val appComponent = DaggerAppComponent.builder().configModule(configModule).build()
    val httpServer = appComponent.server()
    httpServer.start()

}
