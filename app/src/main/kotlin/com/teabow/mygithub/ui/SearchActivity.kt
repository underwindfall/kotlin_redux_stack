package com.teabow.mygithub.ui

import android.os.Bundle
import com.teabow.mygithub.R
import com.teabow.mygithub.helper.Navigator
import com.teabow.mygithub.redux.actions.GetUser
import com.teabow.mygithub.ui.component.SearchActivityUI
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView
import redux.api.Store
import java.util.concurrent.ExecutorService
import javax.inject.Inject

/**
 * Search activity.
 */
class SearchActivity : BaseActivity(), AnkoLogger {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var executor: ExecutorService

    lateinit var ui: SearchActivityUI
    var subscriber: Store.Subscription? = null

    override fun injectDependencies() {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = SearchActivityUI()
        ui.setContentView(this@SearchActivity)
    }

    override fun onResume() {
        super.onResume()
        subscriber = store.subscribe {
            val user = store.state?.user
            if (user != null) {
                navigator.routeToDashboard(this@SearchActivity)
            }
            else {
                snack("User error")
            }
            resetButton()
        }
    }

    override fun onPause() {
        super.onPause()
        subscriber?.unsubscribe()
    }

    fun searchUser(userName: String) {
        setLoadButton()
        store.dispatch(GetUser(userName))
    }

    fun resetButton() {
        ui.styleButton(resources.getString(R.string.search_label), 1f)
    }

    fun setLoadButton() {
        ui.styleButton(resources.getString(R.string.loading_label), .3f)
    }

}

