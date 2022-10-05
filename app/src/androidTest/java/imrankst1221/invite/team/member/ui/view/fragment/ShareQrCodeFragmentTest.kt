package imrankst1221.invite.team.member.ui.view.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.utilities.EspressoTestsMatchers.noDrawable
import imrankst1221.invite.team.member.utilities.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith


@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class ShareQrCodeFragmentTest {
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
     * Test QR Code with permission Coach
     * Run the app on Debug mode
     * Mock data file name: invite_mock_case_1.json
     */
    @Test
    fun testQrCodeWith_permissionCoach() {
        val invitePermission = "Coach"
        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.spinnerInvitePermission)).perform(click())

        val textViewTitle = onView(
            allOf(withId(R.id.textViewTitle), withText(invitePermission), isDisplayed())
        )
        textViewTitle.perform(click())

        onView(withId(R.id.buttonShareQrCode)).perform(click())
        onView(withId(R.id.imageViewQrCode)).check(matches(isDisplayed()))
        // view contain QR image
        onView(withId(R.id.imageViewQrCode)).check(matches(not(noDrawable())))
    }

    /**
     * Test QR Code with permission Player
     * Run the app on Debug mode
     * Mock data file name: invite_mock_case_1.json
     */
    @Test
    fun testQrCodeWith_permissionPlayer() {
        val invitePermission = "Player"
        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.spinnerInvitePermission)).perform(click())

        val textViewTitle = onView(
            allOf(withId(R.id.textViewTitle), withText(invitePermission), isDisplayed())
        )
        textViewTitle.perform(click())

        onView(withId(R.id.buttonShareQrCode)).perform(click())
        onView(withId(R.id.imageViewQrCode)).check(matches(isDisplayed()))
        // view contain QR image
        onView(withId(R.id.imageViewQrCode)).check(matches(not(noDrawable())))
    }

    /**
     * Test QR Code with permission Supporter
     * Run the app on Debug mode
     * Mock data file name: invite_mock_case_1.json
     */
    @Test
    fun testQrCodeWith_permissionSupporter() {
        val invitePermission = "Supporter"
        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.spinnerInvitePermission)).perform(click())

        val textViewTitle = onView(
            allOf(withId(R.id.textViewTitle), withText(invitePermission), isDisplayed())
        )
        textViewTitle.perform(click())

        onView(withId(R.id.buttonShareQrCode)).perform(click())
        onView(withId(R.id.imageViewQrCode)).check(matches(isDisplayed()))
        // view contain QR image
        onView(withId(R.id.imageViewQrCode)).check(matches(not(noDrawable())))
    }

}