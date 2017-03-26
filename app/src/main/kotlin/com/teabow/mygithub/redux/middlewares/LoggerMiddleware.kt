package com.teabow.mygithub.redux.middlewares

import android.util.Log
import redux.api.Store
import redux.api.enhancer.Middleware

/**
 * Created by thibaud.bourgeois on 28/02/2017.
 * Logger middleware.
 */

fun <S: Any> createLoggerMiddleware() = Middleware { store: Store<S>, next, action ->

    val result = next.dispatch(action)
    Log.d("Logger", "Dispatching $action, state = ${store.state}")

    result
}
