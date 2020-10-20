package com.example.androidsampletest

import android.app.Application
import com.example.androidsampletest.network.MyApi
import com.example.androidsampletest.network.NetworkConnectionInterceptor
import com.example.androidsampletest.repo.UserRepo
import com.example.androidsampletest.ui.UserViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ApplicationTest: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ApplicationTest))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { UserRepo(instance()) }
        bind() from provider { UserViewModelFactory(instance()) }
        /*bind() from provider { ReceiverRepo(instance(),instance()) }
        bind() from provider { ReceiverViewModelFactory(instance()) }*/


    }
}