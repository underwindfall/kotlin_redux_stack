package com.teabow.mygithub.redux.middlewares

import com.teabow.mygithub.redux.AppState
import com.teabow.mygithub.redux.actions.AsyncAction
import redux.api.Dispatcher
import redux.api.Store
import redux.api.enhancer.Middleware

/**
 * Created by thibaud.bourgeois on 16/02/2017.
 * Thunk middleware for async actions support.
 */

fun <S: Any> createThunkMiddleware() = Middleware { store: Store<S>, next, action ->

    val result: Any?

    when (action) {
        is AsyncAction -> result = action.defer(store as Dispatcher, store.state as AppState)
        else -> result = next.dispatch(action)
    }

    result
}
