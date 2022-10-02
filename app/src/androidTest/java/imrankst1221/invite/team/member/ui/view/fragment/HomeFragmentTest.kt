package imrankst1221.invite.team.member.ui.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.utilities.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {
    private var hiltRule = HiltAndroidRule(this)
    private val coroutineRule = MainCoroutineRule()
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testInviteMemberFragmentNavigation() {
        onView(withId(R.id.button_invite)).perform(click())
        onView(withId(R.id.textview_second)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.button_invite)).check(matches(isDisplayed()))
    }
}