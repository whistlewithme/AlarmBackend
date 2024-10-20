package com.whistlewithme.alarm.repositories

import com.whistlewithme.alarm.model.Alarm
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.whistlewithme.alarm.resources.DB_COLLECTION
import org.bson.Document
import javax.inject.Inject
import javax.inject.Named

class AlarmRepository @Inject constructor(
    database: MongoDatabase,
    @Named(DB_COLLECTION) collectionName: String
) {
    private var collection: MongoCollection<Document> = database.getCollection(collectionName)

    fun addAlarm(alarm: Alarm): Alarm {
        val document = Document("uuid", alarm.uuid)
            .append("time", alarm.time)
            .append("label", alarm.label)

        this.collection.insertOne(document)
        return alarm
    }

    fun getAllAlarms(): List<Alarm> {
        return collection.find().map {
            Alarm(it.getString("uuid"), it.getString("time"), it.getString("label"))
        }.toList()
    }

    fun deleteAlarm(uuid: String): Alarm? {
        // Find the alarm before deletion
        val alarmToDelete = collection.find(Document("uuid", uuid)).firstOrNull()?.let {
            Alarm(it.getString("uuid"), it.getString("time"), it.getString("label"))
        }

        val result = collection.deleteOne(Document("uuid", uuid))
        return if (result.deletedCount > 0) {
            alarmToDelete
        } else {
            null
        }
    }

    fun updateAlarm(uuid: String, updatedAlarm: Alarm): Alarm? {
        val updatedDocument = Document("time", updatedAlarm.time)
            .append("label", updatedAlarm.label)

        val result = collection.updateOne(Document("uuid", uuid), Document("\$set", updatedDocument))

        return if (result.matchedCount > 0) {
            updatedAlarm.uuid = uuid
            updatedAlarm
        } else {
            null
        }
    }
}
