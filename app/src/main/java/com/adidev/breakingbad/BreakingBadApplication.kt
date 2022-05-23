package com.adidev.breakingbad

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.adidev.breakingbad.data.BreakingBadRepository
import com.adidev.breakingbad.data.Character

class BreakingBadApplication : Application() {
    lateinit var appContainer: AppContainer

    inner class AppContainer {
        val breakingBadRepository = BreakingBadRepository()

        val characterList = MutableLiveData<List<Character>>()
        val seasonFilter = mutableListOf<Int>()
    }

    override fun onCreate(){
        super.onCreate()
        appContainer = AppContainer()
    }
}