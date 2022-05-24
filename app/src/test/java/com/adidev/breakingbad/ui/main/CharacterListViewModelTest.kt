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
        CharacterListViewModel(BreakingBadRepository(), appContainer.characterList, appContainer.seasonFilter)

        //Result
        val value = appContainer.characterList.getOrAwaitValue()
        assertThat(value, not(nullValue()))
    }

    @Test
    fun testFilter() {
        //Set
        val appContainer = ApplicationProvider.getApplicationContext<BreakingBadApplication>().appContainer
        val viewModel = CharacterListViewModel(BreakingBadRepository(), appContainer.characterList, appContainer.seasonFilter)

        //call
        appContainer.characterList.getOrAwaitValue()
        viewModel.filter("white")

        //result
        assertEquals(true, viewModel.visibleList.getOrAwaitValue().all { it.name.contains("white", true) })
    }

    @Test
    fun testFilterBySeason() {
        //Set
        val appContainer = ApplicationProvider.getApplicationContext<BreakingBadApplication>().appContainer
        val viewModel = CharacterListViewModel(BreakingBadRepository(), appContainer.characterList, appContainer.seasonFilter)

        //call
        appContainer.characterList.getOrAwaitValue()
        appContainer.seasonFilter.add(3)
        viewModel.filterBySeason()

        //result
        assertEquals(true, viewModel.visibleList.getOrAwaitValue().all{it.appearance.contains(3)})
    }

}