package imrankst1221.invite.team.member.ui.view.fragment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.R

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author imran.choudhury
 * 2/10/22
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testInviteMemberFragmentNavigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button_invite)).perform(click())
        onView(withId(R.id.textview_second)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.button_invite)).check(matches(isDisplayed()))
    }
}