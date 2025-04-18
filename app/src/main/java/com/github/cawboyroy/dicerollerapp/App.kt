package com.github.cawboyroy.dicerollerapp

import android.app.Application

class App : Application() {

    lateinit var viewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel()
    }
}

04.05.2022 compose+mvvm+livedata test