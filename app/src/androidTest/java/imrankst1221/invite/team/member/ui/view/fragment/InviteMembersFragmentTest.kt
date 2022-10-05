package imrankst1221.invite.team.member.ui.view.fragment

import android.content.Context
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.utilities.MainCoroutineRule
import imrankst1221.invite.team.member.utilities.UtilMethods
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
class InviteMembersFragmentTest {
    private var hiltRule = HiltAndroidRule(this)
    private val coroutineRule = MainCoroutineRule()
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var mContext: Context

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            mContext = activity.applicationContext
        }
    }

    @After
    fun tearDown() {
    }

    /**
     * Run the app on Debug mode
     * Test text view has value
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun testTextViewValue() {
        val currentMember = "83"
        val currentMemberLimit = "100"
        val currentSupporter = "6"
        val currentSupporterLimit = "20"
        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.textViewCurrentMember)).check(matches(withText(currentMember)))
        onView(withId(R.id.textViewMemberLimit)).check(matches(withText(currentMemberLimit)))
        onView(withId(R.id.textViewSupporters)).check(matches(withText(currentSupporter)))
        onView(withId(R.id.textViewSupporterLimit)).check(matches(withText(currentSupporterLimit)))
    }

    /**
     * Run the app on Debug mode
     * Test Supporters available for the Team
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun testSupporterAvailableForTeam() {
        onView(withId(R.id.buttonInvite)).perform(click())
        onView(withId(R.id.layoutCurrentSupporter))
            .check(matches(isDisplayed()))
    }

    /**
     * Run the app on Debug mode
     * Test Supporters are not available for the Team
     * Mock data file name: team_mock_case_3.json
     */
    @Test
    fun testSupporterAreNotAvailableForTeam() {
        onView(withId(R.id.buttonInvite)).perform(click())
        pressBack()
        onView(withId(R.id.buttonInvite)).perform(click())
        pressBack()
        onView(withId(R.id.buttonInvite)).perform(click())

        onView(withId(R.id.layoutCurrentSupporter))
            .check(matches(not(isDisplayed())))
    }

    /**
     * Run the app on Debug mode
     * Test invite URL copy successfully into clip board
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun testInviteLinkCopyIntoClipBoard() {
        val shareUrl = "https://example.com/ti/eyJpbnZpdGVJZ"
        UtilMethods.copyIntoClipBoard(mContext, "")
        onView(withId(R.id.buttonInvite)).perform(click())

        onView(withId(R.id.buttonCopyLink)).perform(click())
        Truth.assertThat(UtilMethods.getFromClipBoard(mContext)).isNotEmpty()
        Truth.assertThat(UtilMethods.getFromClipBoard(mContext)).isEqualTo(shareUrl)
    }

    /**
     * Run the app on Debug mode
     * Test selected permission not valid
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun testSelectedPermissionNotValid() {
        val shareUrl = "https://example.com/ti/eyJpbnZpdGVJZ"
        UtilMethods.copyIntoClipBoard(mContext, "")
        onView(withId(R.id.buttonInvite)).perform(click())
        pressBack()
        onView(withId(R.id.buttonInvite)).perform(click())

        // share QR code
        onView(withId(R.id.buttonShareQrCode)).perform(click())
        onView(withId(R.id.buttonShareQrCode)).check(matches(isDisplayed()))

        // copy url
        onView(withId(R.id.buttonCopyLink)).perform(click())
        Truth.assertThat(UtilMethods.getFromClipBoard(mContext)).isNotEqualTo(shareUrl)
    }


}