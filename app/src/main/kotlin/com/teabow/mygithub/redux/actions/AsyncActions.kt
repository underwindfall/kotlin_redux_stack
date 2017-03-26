package com.teabow.mygithub.redux.actions

import com.teabow.mygithub.api.GithubApi
import com.teabow.mygithub.redux.AppState
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import redux.api.Dispatcher

/**
 * Created by thibaud.bourgeois on 17/02/2017.
 * Async actions.
 */

/**
 * Async action interface
 */
interface AsyncAction {
    fun defer(dispatcher: Dispatcher, state: AppState): Action?
}

// User
class GetUser(val userName: String): AsyncAction {
    override fun defer(dispatcher: Dispatcher, state: AppState): Action? {
        doAsync {
            val (user, error) = GithubApi.getUser(userName)
            uiThread {
                if (error != null) {
                    dispatcher.dispatch(Action.SetUserError())
                }
                else {
                    dispatcher.dispatch(Action.SetUser(user))
                }
            }
        }
        return null
    }
}

// Repositories
class GetRepositories(val userName: String): AsyncAction {
    override fun defer(dispatcher: Dispatcher, state: AppState): Action? {
        doAsync {
            val (repositories, error) = GithubApi.getPublicRepositories(userName)
            uiThread {
                if (error != null) {
                    dispatcher.dispatch(Action.SetRepositoriesError())
                }
                else {
                    dispatcher.dispatch(Action.SetRepositories(repositories))
                }
            }
        }
        return null
    }
}