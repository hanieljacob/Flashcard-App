package com.example.flashcardapp

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun accessSecondActivity(){
        onView(withId(R.id.usernameEditText)).perform(typeText("cs501"))
        onView(withId(R.id.passwordEditText)).perform(typeText("123"))
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.operand1)).check(matches(isDisplayed()))
        onView(withId(R.id.horizontalLine1)).check(matches(isDisplayed()))
        onView(withId(R.id.operator)).check(matches(isDisplayed()))
        onView(withId(R.id.operand2)).check(matches(isDisplayed()))
        onView(withId(R.id.horizontalLine2)).check(matches(isDisplayed()))
        onView(withId(R.id.generateButton)).check(matches(isDisplayed()))
        onView(withId(R.id.yourAnswerTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.answerEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.submitButton2)).check(matches(isDisplayed()))
    }

}