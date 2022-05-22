package com.adidev.breakingbad.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.adidev.breakingbad.data.BreakingBadRepository
import com.adidev.breakingbad.data.Character
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(private val breakingBadRepository: BreakingBadRepository) : ViewModel() {
    val characterList = MutableLiveData<List<Character>>()

    init {
        fetchCharacters()
    }
    fun fetchCharacters() =
        viewModelScope.launch {
            characterList.value = breakingBadRepository.fetchCharacters()
        }
}