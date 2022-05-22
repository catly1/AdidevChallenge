package com.adidev.breakingbad

import android.app.Application
import com.adidev.breakingbad.data.BreakingBadRepository

class BreakingBadApplication : Application() {
    lateinit var appContainer: AppContainer

    inner class AppContainer {
        val breakingBadRepository = BreakingBadRepository()
    }

    override fun onCreate(){
        super.onCreate()
        appContainer = AppContainer()
    }
}