package com.teabow.mygithub.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.teabow.mygithub.GithubApplication
import com.teabow.mygithub.dagger.ApplicationComponent
import com.teabow.mygithub.redux.AppState
import redux.api.Store
import javax.inject.Inject

/**
 * Created by thibaud.bourgeois on 27/01/2017.
 * Base activity.
 */
abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var store: Store<AppState>

    protected lateinit var appComponent: ApplicationComponent

    abstract fun injectDependencies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = GithubApplication.appComponent
        injectDependencies()
    }

    protected fun snack(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }
}