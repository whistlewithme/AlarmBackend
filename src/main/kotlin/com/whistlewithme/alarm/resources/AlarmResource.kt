package com.whistlewithme.alarm.resources

import com.whistlewithme.alarm.model.Alarm
import com.whistlewithme.alarm.repositories.AlarmRepository
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.inject.Inject

@Path("/alarms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AlarmResource @Inject constructor(private val alarmRepository: AlarmRepository) {

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createAlarm(alarm: Alarm): Alarm {
        alarmRepository.addAlarm(alarm)
        return alarm
    }

    @GET
    fun getAlarms(): Response {
        val alarms = alarmRepository.getAllAlarms()
        return Response.ok(alarms).build()
    }

    // Implement delete and update methods as necessary
}
