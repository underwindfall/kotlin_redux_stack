package com.teabow.mygithub.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.google.android.flexbox.FlexboxLayout
import com.teabow.mygithub.R
import com.teabow.mygithub.color
import com.teabow.mygithub.flexLayout
import com.teabow.mygithub.helper.convertRealmListToList
import com.teabow.mygithub.model.User
import com.teabow.mygithub.redux.actions.GetRepositories
import com.teabow.mygithub.ui.adapter.RepositoriesListAdapter
import com.teabow.mygithub.ui.divider.SimpleDivider
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import redux.api.Store

class DashboardActivity : BaseActivity() {

    var user: User? = null
    lateinit var progressView: ProgressBar
    lateinit var recyclerView: RecyclerView

    var subscriber: Store.Subscription? = null

    override fun injectDependencies() {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = store.state.user

        relativeLayout {

            progressView = progressBar().lparams {
                centerInParent()
            }

            val titleView = flexLayout {

                id = 100

                padding = dip(30)
                flexDirection = FlexboxLayout.FLEX_DIRECTION_COLUMN
                alignItems = FlexboxLayout.ALIGN_ITEMS_CENTER
                justifyContent = FlexboxLayout.JUSTIFY_CONTENT_CENTER

                textView {
                    text = String.format(resources.getString(R.string.dashboard_title), user?.login)
                    textColor = this@DashboardActivity.color(R.color.colorAccent, theme)
                    textSize = resources.getDimension(R.dimen.large_text_size)
                }

                textView {
                    text = user?.name
                    textColor = this@DashboardActivity.color(R.color.colorPrimary, theme)
                    textSize = resources.getDimension(R.dimen.small_text_size)
                }

            }.lparams {
                width = matchParent
            }

            recyclerView = recyclerView {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(SimpleDivider(this@DashboardActivity))
            }.lparams {
                below(titleView)
                width = matchParent
                setMargins(48, 0, 48, 0)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        store.dispatch(GetRepositories(user?.login!!))
    }

    override fun onResume() {
        super.onResume()
        subscriber = store.subscribe {
            val repositories = store.state?.repositories
            if (repositories != null) {
                progressView.visibility = View.GONE
                recyclerView.adapter = RepositoriesListAdapter(convertRealmListToList(repositories))
            }
            else {
                snack("Repositories error")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        subscriber?.unsubscribe()
    }

}
