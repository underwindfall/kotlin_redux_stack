package com.teabow.mygithub.helper

import io.realm.RealmList
import io.realm.RealmModel
import java.util.*

/**
 * Created by thibaud.bourgeois on 17/02/2017.
 * Converters helpers.
 */

fun <T: RealmModel> convertListToRealmList(list: List<T>): RealmList<T> {
    val realmList = RealmList<T>()
    list.map {
        realmList.add(it)
    }
    return realmList
}

fun <T: RealmModel> convertRealmListToList(realmList: RealmList<T>): List<T> {
    val list = ArrayList<T>()
    realmList.map {
        list.add(it)
    }
    return list
}
