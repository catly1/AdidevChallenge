package com.adidev.breakingbad.ui.main

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adidev.breakingbad.MainActivity
import com.adidev.breakingbad.R
import com.adidev.breakingbad.util.CountingIdlingResourceSingleton
import org.hamcrest.CoreMatchers.allOf
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CharacterListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun clickFilterButtonOpenDialog(){
        onView(withId(R.id.filter))
            .perform(ViewActions.click())

        onView(withId(R.id.checkBox)).check(matches(isDisplayed()))
    }



    @Test
    fun clickListItemGoToDetailFragment(){
        onView(withText("Walter White"))
            .perform(ViewActions.click())

        onView(withId(R.id.characterNickname)).check(matches(isDisplayed()))
    }



    @Test
    fun searchAndDisplayResult(){
        onView(withId(R.id.actionSearch))
            .perform(ViewActions.click())

        onView(isAssignableFrom(EditText::class.java)).perform(ViewActions.typeText("fring"))

        onView(withId(R.id.characterName)).check(matches(withText("Gustavo Fring")))
    }

    @Test
    fun testFilterAndCheckInDetailFragmentForSeason(){
        onView(withId(R.id.filter))
            .perform(ViewActions.click())

        onView(withId(R.id.checkBox3))
            .perform(ViewActions.click())

        onView(withId(R.id.checkBox5))
            .perform(ViewActions.click(), ViewActions.pressBack())

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollTo<CharacterListAdapter.ItemViewHolder>(
            hasDescendant(withText("Duane Chow")))).perform(ViewActions.click())

        onView(withId(R.id.characterSeasonAppearance)).check(matches(withText("3, 5")))
    }
}