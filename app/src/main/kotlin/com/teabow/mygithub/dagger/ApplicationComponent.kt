package com.teabow.mygithub.dagger

import com.teabow.mygithub.ui.DashboardActivity
import com.teabow.mygithub.ui.SearchActivity
import com.teabow.mygithub.ui.component.SearchActivityUI
import dagger.Component
import javax.inject.Singleton

/**
 * Created by thibaud.bourgeois on 27/01/2017.
 * Application component.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(activity: DashboardActivity)
    fun inject(activity: SearchActivity)
    fun inject(ui: SearchActivityUI)

}