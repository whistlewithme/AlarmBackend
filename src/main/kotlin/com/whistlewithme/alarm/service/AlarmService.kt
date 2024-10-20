package com.whistlewithme.alarm.service

import com.whistlewithme.alarm.model.Alarm
import com.whistlewithme.alarm.repositories.AlarmRepository
import java.util.UUID
import javax.inject.Inject
import javax.ws.rs.core.Response

class AlarmService @Inject constructor(
    private val alarmRepository: AlarmRepository
) {

    fun createAlarm(alarm: Alarm) :Alarm{
        alarm.uuid = UUID.randomUUID().toString()
        alarmRepository.addAlarm(alarm)
        return alarm
    }

    fun getAlarms() : List<Alarm>{
        val alarms = alarmRepository.getAllAlarms()
        return alarms
    }

    fun deleteAlarm(uuid: String) : Response{
        val deleted = alarmRepository.deleteAlarm(uuid)
        return if (deleted != null) {
            Response.noContent().build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }

    fun updateAlarm(uuid: String, updatedAlarm: Alarm): Response{
        val updated = alarmRepository.updateAlarm(uuid, updatedAlarm)
        return if (updated != null) {
            Response.ok(updatedAlarm).build()
        } else {
            Response.status(Response.Status.NOT_FOUND).build()
        }
    }
}
