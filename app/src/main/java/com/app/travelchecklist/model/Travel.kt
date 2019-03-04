package com.app.travelchecklist.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Travel : RealmObject() {
    @PrimaryKey
    var TravelID: String = UUID.randomUUID().toString()

    var country: String? = null
    var city: String? = null
    var from: Date? = null
    var items: RealmList<TravelItem>? = null
}