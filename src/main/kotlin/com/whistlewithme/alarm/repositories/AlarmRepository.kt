package com.whistlewithme.alarm.repositories

import com.whistlewithme.alarm.model.Alarm
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.whistlewithme.alarm.resources.DB_COLLECTION
import org.bson.Document
import javax.inject.Inject
import javax.inject.Named

class AlarmRepository @Inject constructor(
    private val mongoClient: MongoClient,
    database: MongoDatabase,
    @Named(DB_COLLECTION) collectionName: String
) {
    private var collection: MongoCollection<Document>
    init {
        collection = database.getCollection(collectionName)
    }

    fun addAlarm(alarm: Alarm) {
        val document = Document("id", alarm.id)
            .append("time", alarm.time)
            .append("label", alarm.label)
        collection.insertOne(document)
    }

    fun getAllAlarms(): List<Alarm> {
        return collection.find().map {
            Alarm(it.getString("id"), it.getString("time"), it.getString("label"))
        }.toList()
    }

}
