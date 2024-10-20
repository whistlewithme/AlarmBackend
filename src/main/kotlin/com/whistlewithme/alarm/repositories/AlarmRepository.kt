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
        val document = Document("name", alarm.name)
            .append("time", alarm.time)
            .append("label", alarm.label)
        this.collection.insertOne(document)
        return alarm
    }

    fun getAllAlarms(): List<Alarm> {
        return collection.find().map {
            Alarm(it.getString("name"), it.getString("time"), it.getString("label"))
        }.toList()
    }

}
