package com.teabow.mygithub.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by thibaud.bourgeois on 14/01/2017.
 * Github models
 */
@RealmClass
open class User: RealmObject() {

    @PrimaryKey
    open var id: Int = -1

    open var login: String = ""

    open var name: String = ""

    @SerializedName("html_url")
    open var url: String = ""

    @SerializedName("public_repos")
    open var publicRepos: Int = 0

}

@RealmClass
open class Repository: RealmObject() {

    @PrimaryKey
    open var id: Int = -1

    open var name: String = ""

    open var owner: User? = null

    @SerializedName("html_url")
    open var url: String = ""

    open var description: String = ""

    open var language: String = ""
}
