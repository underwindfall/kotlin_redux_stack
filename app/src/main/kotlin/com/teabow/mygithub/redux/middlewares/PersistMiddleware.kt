package com.teabow.mygithub.redux.middlewares

import com.teabow.mygithub.redux.AppState
import io.realm.Realm
import redux.api.Store
import redux.api.enhancer.Middleware

/**
 * Created by thibaud.bourgeois on 14/01/2017.
 * Persist middleware
 */

fun<S: Any> createPersistMiddleware(realm: Realm) = Middleware { store: Store<S>, next, action ->

    val result = next.dispatch(action)
    val newState: AppState = store.state as AppState

    if (newState.user?.id != -1) {
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(newState)
        }
    }
    result
}
