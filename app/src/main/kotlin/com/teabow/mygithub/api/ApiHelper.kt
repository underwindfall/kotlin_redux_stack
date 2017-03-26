package com.teabow.mygithub.api

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by thibaud.bourgeois on 14/01/2017.
 * Api helper.
 */

fun buildGson(): Gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes?): Boolean {
        return f?.declaringClass == RealmObject::class.java
    }

    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }
}).create()

fun <T> GithubApi.callGetRequest(url: String, clazz: Class<T>): Pair<T, FuelError?> {
    val (request, response, result) = url.httpGet().responseString()
    val (data, error) = result
    return Pair(gson.fromJson(data, clazz), error)
}

fun <T> GithubApi.callGetListRequest(url: String, clazz: Class<T>): Pair<List<T>, FuelError?> {
    val (request, response, result) = url.httpGet().responseString()
    val (data, error) = result
    val results: List<T> = gson.fromJson(data, ListOf(clazz))
    return Pair(results, error)
}


class ListOf<T>(private val type: Class<T>) : ParameterizedType {
    override fun getActualTypeArguments() = arrayOf<Type>(type)
    override fun getRawType() = List::class.java
    override fun getOwnerType() = null
}