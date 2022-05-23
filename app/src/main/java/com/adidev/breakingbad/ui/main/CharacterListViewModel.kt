package com.adidev.breakingbad.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adidev.breakingbad.data.BreakingBadRepository
import com.adidev.breakingbad.data.Character
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val breakingBadRepository: BreakingBadRepository,
    private val characterList: MutableLiveData<List<Character>>,
    private val seasonFilter: MutableList<Int>
) : ViewModel() {
    val visibleList = MutableLiveData<List<Character>>()

    val keyMap = mapOf(
        "Season 1" to 1,
        "Season 2" to 2,
        "Season 3" to 3,
        "Season 4" to 4,
        "Season 5" to 5
    )

    init {
        fetchCharacters()
    }

    fun fetchCharacters() =
        viewModelScope.launch {
            if (characterList.value == null){
                characterList.value = breakingBadRepository.fetchCharacters()
            }
            filterBySeason()
        }

    fun filter(text: String){
        val filtered = mutableListOf<Character>()

        for (character in characterList.value!!){
            if (character.name.lowercase().contains(text.lowercase()) && character.appearance.containsAll(seasonFilter)){
                filtered.add(character)
            }
        }

        visibleList.value = filtered
    }

    fun filterBySeason(){
        val filtered = mutableListOf<Character>()

        for (character in characterList.value!!){
            if (character.appearance.containsAll(seasonFilter)){
                filtered.add(character)
            }
        }

        visibleList.value = filtered
    }
}