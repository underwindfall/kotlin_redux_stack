package com.teabow.mygithub.dagger

import android.app.Application
import android.content.Context
import com.teabow.mygithub.helper.Navigator
import com.teabow.mygithub.model.User
import com.teabow.mygithub.redux.AppState
import com.teabow.mygithub.redux.AppStore
import dagger.Module
import dagger.Provides
import io.realm.Realm
import redux.api.Store
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by thibaud.bourgeois on 27/01/2017.
 * Application module.
 */

@Module
class ApplicationModule(val application: Application) {

    @Singleton
    @Provides
    fun providesContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun providesNavigator(): Navigator = Navigator()

    @Singleton
    @Provides
    fun providesRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Singleton
    @Provides
    fun providesStore(realm: Realm): Store<AppState> {
        val storedStates = realm.where(AppState::class.java).findAll()
        val initialPersistState = if (storedStates.size > 0) storedStates.last() else null
        val initialState = initialPersistState ?: AppState(User(), null)
        return AppStore().create(realm, initialState)
    }

    @Singleton
    @Provides
    fun providesExecutorService(): ExecutorService {
        return Executors.newScheduledThreadPool(4)
    }

}