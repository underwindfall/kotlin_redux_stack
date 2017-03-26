package com.teabow.mygithub.redux.actions

import com.teabow.mygithub.model.Repository
import com.teabow.mygithub.model.User

/**
 * Created by thibaud.bourgeois on 14/01/2017.
 * Simple actions.
 */
sealed class Action {
    // User
    class SetUser(val user: User): Action()
    class SetUserError: Action()
    // Repositories
    class SetRepositories(val repositories: List<Repository>): Action()
    class SetRepositoriesError: Action()
}
