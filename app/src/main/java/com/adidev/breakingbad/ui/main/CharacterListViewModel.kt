package com.adidev.breakingbad.ui.main

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.adidev.breakingbad.data.BreakingBadRepository
import com.adidev.breakingbad.data.Character
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(private val breakingBadRepository: BreakingBadRepository) : ViewModel() {
    lateinit var characterList : List<Character>
    val visibleList = MutableLiveData<List<Character>>()

    init {
        fetchCharacters()
    }

    fun fetchCharacters() =
        viewModelScope.launch {
            characterList = breakingBadRepository.fetchCharacters()
            visibleList.value = characterList
        }

    fun filter(text: String){
        val filtered = mutableListOf<Character>()

        for (character in characterList){
            if (character.name.lowercase().contains(text.lowercase())){
                filtered.add(character)
            }
        }

        visibleList.value = filtered
    }
}