package com.teabow.mygithub.ui.component

import android.widget.Button
import android.widget.EditText
import com.google.android.flexbox.FlexboxLayout
import com.teabow.mygithub.*
import com.teabow.mygithub.redux.AppState
import com.teabow.mygithub.ui.SearchActivity
import org.jetbrains.anko.*
import redux.api.Store
import javax.inject.Inject

/**
 * Created by thibaud.bourgeois on 01/02/2017.
 * Search activity ui component.
 */
class SearchActivityUI: AnkoComponent<SearchActivity> {

    @Inject
    lateinit var store: Store<AppState>

    lateinit var userEditText: EditText
    lateinit var loginButton: Button

    override fun createView(ui: AnkoContext<SearchActivity>) = with(ui) {

        GithubApplication.appComponent.inject(this@SearchActivityUI)

        flexLayout {

            padding = dip(30)
            flexDirection = FlexboxLayout.FLEX_DIRECTION_COLUMN
            justifyContent = FlexboxLayout.JUSTIFY_CONTENT_CENTER
            alignItems = FlexboxLayout.ALIGN_ITEMS_CENTER


            imageView {
                imageResource = R.drawable.ic_github
            }.lparams {
                height = 128
            }

            userEditText = editText {
                hint = "User name"
                setText(store.state.user?.login)
            }.lparams {
                width = matchParent
            }

            loginButton = button {
                text = resources.getString(R.string.search_label)
                textColor = ui.owner.color(R.color.white, ui.owner.theme)
                backgroundColor = ui.owner.color(R.color.colorPrimaryDark, ui.owner.theme)
                onClick {
                    ui.owner.searchUser(userEditText.text.toString())
                }
            }.lparams {
                topMargin = 48
            }
        }
    }

    fun styleButton(buttonText: String, buttonAlpha: Float) {
        loginButton.apply {
            text = buttonText
            alpha = buttonAlpha
        }
    }

}