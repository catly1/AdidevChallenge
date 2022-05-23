package com.adidev.breakingbad.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adidev.breakingbad.BreakingBadApplication
import com.adidev.breakingbad.CoroutineDispatcherRule
import com.adidev.breakingbad.data.BreakingBadRepository
import getOrAwaitValue
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.MatcherAssert.assertThat


@RunWith(AndroidJUnit4::class)
class CharacterListViewModelTest : TestCase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesDispatcherRule = CoroutineDispatcherRule()
    @Test
    fun testFetchCharacters() {
        //Set
        val appContainer = ApplicationProvider.getApplicationContext<BreakingBadApplication>().appContainer

        //Call
        val characterListViewModel = CharacterListViewModel(BreakingBadRepository(), appContainer.characterList, appContainer.seasonFilter)

        //Result
        val value = appContainer.characterList.getOrAwaitValue()
        println(value.first())
        assertThat(value, not(nullValue()))
    }
}