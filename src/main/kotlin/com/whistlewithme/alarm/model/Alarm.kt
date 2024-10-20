package com.whistlewithme.alarm.model

data class Alarm(
    var uuid: String?,
    val time: String,
    val label: String?
){
    constructor() : this("", "", "")
}
