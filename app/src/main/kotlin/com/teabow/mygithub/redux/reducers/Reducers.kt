package com.teabow.mygithub.redux.reducers

import com.teabow.mygithub.helper.convertListToRealmList
import com.teabow.mygithub.redux.AppState
import com.teabow.mygithub.redux.actions.Action
import redux.api.Reducer

/**
 * Created by thibaud.bourgeois on 28/01/2017.
 * Reducers.
 */

val mainReducer = Reducer { state: AppState, action: Any ->
    when (action) {
        is Action.SetUser -> AppState(action.user, state.repositories)
        is Action.SetUserError -> AppState(null, state.repositories)
        is Action.SetRepositories -> AppState(state.user, convertListToRealmList(action.repositories))
        is Action.SetRepositoriesError -> AppState(state.user, null)
        else -> state
    }
}