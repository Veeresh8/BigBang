@file:Suppress("USELESS_CAST")

package com.droid.bigbang

import com.droid.bigbang.data.DataRepository
import com.droid.bigbang.data.DataRepositoryImpl
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// GLOBAL DEPENDENCIES
val applicationModule = module {
    single { Gson() }
    factory { PhotoViewController() }
    factory<DataRepository> { DataRepositoryImpl() }
}

// VIEW-MODEL
val photoViewModel = module {
    viewModel { PhotoViewModel(dataRepository = get()) }
}
