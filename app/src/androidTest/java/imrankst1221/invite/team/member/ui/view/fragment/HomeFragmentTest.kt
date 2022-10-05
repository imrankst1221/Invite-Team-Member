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
import org.hamcrest.CoreMatchers.not

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

    /**
     * Run the app on Debug mode
     * On invite button click check the navigation
     */
    @Test
    fun testInviteMemberFragmentNavigation() {
        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.textViewCurrentMemberTitle)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.buttonInvite)).check(matches(isDisplayed()))
    }

    /**
     * Run the app on Debug mode
     * Toolbar back button visibility test
     */
    @Test
    fun testToolbarBackButtonVisibility() {
        onView(withId(R.id.buttonToolbarBack)).check(matches(not(isDisplayed())))

        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.buttonToolbarBack)).check(matches(isDisplayed()))

        onView(withId(R.id.buttonShareQrCode)).perform(click())
        onView(withId(R.id.buttonToolbarBack)).check(matches(isDisplayed()))

        pressBack()
        pressBack()
        onView(withId(R.id.buttonToolbarBack)).check(matches(not(isDisplayed())))
    }
}