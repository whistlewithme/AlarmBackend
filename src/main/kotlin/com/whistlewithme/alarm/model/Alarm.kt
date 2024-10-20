package com.whistlewithme.alarm.model

data class Alarm(
    val name: String,
    val time: String,
    val label: String
){
    constructor() : this("", "", "")
}
