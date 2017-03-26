package com.teabow.mygithub.helper

import android.app.Activity
import android.content.Intent
import com.teabow.mygithub.ui.DashboardActivity

/**
 * Created by thibaud.bourgeois on 16/02/2017.
 * Navigator for UI routing.
 */
class Navigator {

    fun routeToDashboard(activity: Activity) {
        val intent = Intent(activity, DashboardActivity::class.java)
        activity.startActivity(intent)
    }

}