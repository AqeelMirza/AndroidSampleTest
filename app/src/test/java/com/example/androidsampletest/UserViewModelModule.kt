package com.example.androidsampletest

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val UserViewModelModule = Kodein.Module("UserViewModel") {
    bind<() -> Boolean>() with singleton { { true } }
}