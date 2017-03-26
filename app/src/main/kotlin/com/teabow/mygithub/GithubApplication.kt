package com.teabow.mygithub

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.teabow.mygithub.dagger.ApplicationComponent
import com.teabow.mygithub.dagger.ApplicationModule
import com.teabow.mygithub.dagger.DaggerApplicationComponent
import io.realm.Realm

/**
 * Created by thibaud.bourgeois on 12/01/2017.
 * Github application.
 */

class GithubApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initLeakCanary()
            initStetho()
        }
        initRealm()
        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun initRealm() {
        Realm.init(this)
    }

    fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    fun initStetho() {
        Stetho.initializeWithDefaults(this);
    }

}