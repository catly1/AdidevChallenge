package com.adidev.breakingbad.ui.main

import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adidev.breakingbad.BreakingBadApplication
import com.adidev.breakingbad.data.BreakingBadRepository
import com.adidev.breakingbad.data.Character
import getOrAwaitValue
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.MatcherAssert.assertThat
import org.robolectric.Shadows

@RunWith(AndroidJUnit4::class)
class CharacterListViewModelTest : TestCase() {

    val fakeRepository = BreakingBadRepository()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testFetchCharacters() {
        val appContainer = ApplicationProvider.getApplicationContext<BreakingBadApplication>().appContainer
        val characterListViewModel = CharacterListViewModel(BreakingBadRepository(), appContainer.characterList, appContainer.seasonFilter)
//


//        val observer = Observer<List<Character>>{}
//        try {
//            characterListViewModel.visibleList.observeForever(observer)
//
//            characterListViewModel.fetchCharacters()
//            Shadows.shadowOf(Looper.getMainLooper()).idle()
//            val value = characterListViewModel.visibleList
//            println("value is: " + value.value)
//        } finally {
//            appContainer.characterList.removeObserver(observer)
//        }
        characterListViewModel.fetchCharacters()

        appContainer.characterList.postValue(listOf(Character("test","test", listOf(),"test","test","test",
            listOf())))
        val value = appContainer.characterList.getOrAwaitValue()
        println(value.first())
//        assertThat(value, not(nullValue()))
    }
}