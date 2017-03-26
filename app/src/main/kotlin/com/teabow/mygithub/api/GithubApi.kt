package com.teabow.mygithub.api

import com.github.kittinunf.fuel.core.FuelError
import com.teabow.mygithub.model.Repository
import com.teabow.mygithub.model.User

/**
 * Created by thibaud.bourgeois on 13/01/2017.
 * Github api.
 */

object GithubApi {

    private val BASE_URL = "https://api.github.com"
    val gson = buildGson()

    // API CALLS

    fun getUser(userName: String): Pair<User, FuelError?> {
        return callGetRequest("$BASE_URL/users/$userName", User::class.java)
    }

    fun getPublicRepositories(userName: String): Pair<List<Repository>, FuelError?> {
        return callGetListRequest("$BASE_URL/users/$userName/repos", Repository::class.java)
    }

}
