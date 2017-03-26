package com.teabow.mygithub.redux

import com.teabow.mygithub.redux.middlewares.createLoggerMiddleware
import com.teabow.mygithub.redux.middlewares.createPersistMiddleware
import com.teabow.mygithub.redux.middlewares.createThunkMiddleware
import com.teabow.mygithub.redux.reducers.mainReducer
import io.realm.Realm
import redux.api.Store
import redux.applyMiddleware
import redux.createStore

/**
 * Created by thibaud.bourgeois on 12/01/2017.
 * App state object.
 */
class AppStore {

    fun create(realm: Realm, initialState: AppState): Store<AppState> {
        return createStore<AppState>(
                mainReducer,
                initialState,
                applyMiddleware(
                        createLoggerMiddleware(),
                        createThunkMiddleware(),
                        createPersistMiddleware<AppState>(realm)
                )
        )
    }

}