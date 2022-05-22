package com.adidev.breakingbad.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adidev.breakingbad.BreakingBadApplication

class ViewModelFactory(val application: BreakingBadApplication): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) -> {
                return CharacterDetailViewModel(
                    application.appContainer.breakingBadRepository
                ) as T
            }
            (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) -> {
                return CharacterListViewModel(
                    application.appContainer.breakingBadRepository
                ) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}