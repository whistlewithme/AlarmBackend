package com.whistlewithme.alarm.resources

import com.whistlewithme.alarm.model.Alarm
import com.whistlewithme.alarm.service.AlarmService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.inject.Inject

@Path("/alarms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AlarmResource @Inject constructor(
    private val alarmService: AlarmService
) {

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createAlarm(alarm: Alarm): Alarm {
        alarmService.createAlarm(alarm)
        return alarm
    }

    @GET
    fun getAlarms(): Response {
        val alarms = alarmService.getAlarms()
        return Response.ok(alarms).build()
    }

    @DELETE
    @Path("/{uuid}")
    fun deleteAlarm(@PathParam("uuid") uuid: String): Response {
        val response = alarmService.deleteAlarm(uuid)
        return response
    }

    @PUT
    @Path("/{uuid}")
    fun updateAlarm(@PathParam("uuid") uuid: String, updatedAlarm: Alarm): Response {
        val response= alarmService.updateAlarm(uuid, updatedAlarm)
        return response
    }

}
