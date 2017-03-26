package com.teabow.mygithub.redux

import com.teabow.mygithub.model.Repository
import com.teabow.mygithub.model.User
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by thibaud.bourgeois on 14/01/2017.
 * State.
 */
@RealmClass
open class AppState(): RealmObject() {

    @PrimaryKey
    open var id = 0

    open var user: User? = null
    open var repositories: RealmList<Repository>? = null

    constructor(user: User?, repositories: RealmList<Repository>?) : this() {
        this.user = user
        this.repositories = repositories
    }

}