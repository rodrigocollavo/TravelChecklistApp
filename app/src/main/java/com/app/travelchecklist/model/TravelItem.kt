package com.app.travelchecklist.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class TravelItem : RealmObject() {
    @PrimaryKey
    var travelItemID: String = UUID.randomUUID().toString()

    var name: String? = null
    var completed: Boolean = false
}