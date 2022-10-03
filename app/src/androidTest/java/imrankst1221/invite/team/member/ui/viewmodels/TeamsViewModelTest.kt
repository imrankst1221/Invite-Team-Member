package imrankst1221.invite.team.member.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import imrankst1221.invite.team.member.data.repository.TeamsRepositoryImp
import imrankst1221.invite.team.member.utilities.Constants
import imrankst1221.invite.team.member.utilities.MainCoroutineRule
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.currentMember
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isAvailableMemberSlots
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isAvailableSupporterSlots
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isSupporterLimitZero
import imrankst1221.invite.team.member.utilities.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class TeamsViewModelTest {
    private lateinit var viewModel: TeamsViewModel
    private var hiltRule = HiltAndroidRule(this)
    private val coroutineRule = MainCoroutineRule()
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var repository: TeamsRepositoryImp

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = TeamsViewModel(repository)
    }

    /**
     * Run the app on Debug mode
     * Mock data read successfully from okhttp3.Interceptor
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun mockDataReadTest_team_mock_case_1() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[0]
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.id).isEqualTo(id)
    }

    /**
     * Run the app on Debug mode
     * Test current member count
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun testCurrentMemberCount_team_mock_case_1() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[0]
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.currentMember()).isEqualTo(
            (data.members?.total ?: 0) - (data.members?.supporters ?: 0)
        )
    }

    /**
     * Run the app on Debug mode
     * Test member slots not available
     * Mock data file name: team_mock_case_2.json
     */
    @Test
    fun testMemberSlotsNotAvailable_team_mock_case_2() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[1]
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.isAvailableMemberSlots()).isFalse()
    }


    /**
     * Run the app on Debug mode
     * Test Supporters are not available for the Team
     * Mock data file name: team_mock_case_3.json
     */
    @Test
    fun testSupporterAreNotAvailable_team_mock_case_3() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[2]
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.isSupporterLimitZero()).isTrue()
    }

    /**
     * Run the app on Debug mode
     * Test Supporters are available but there are no open slots
     * Mock data file name: team_mock_case_4.json
     */
    @Test
    fun testSupportersSlotsNotAvailable_team_mock_case_4() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[3]
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.isAvailableSupporterSlots()).isFalse()
    }

    /**
     * Run the app on Debug mode
     * Test Supporters and member both are available
     * Mock data file name: team_mock_case_1.json
     */
    @Test
    fun testSupportersSlotsNotAvailable_team_mock_case_1() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[0]
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.isAvailableMemberSlots()).isTrue()
        assertThat(data.isAvailableSupporterSlots()).isTrue()
    }


    /**
     * Run the app on Debug mode
     * Mock data read team invitation URL
     * Mock data file name: invite_mock_case_1.json
     */
    @Test
    fun testTeamInvitesUrl_invite_mock_case_1() = runBlocking {
        val id = Constants.DEMO_TEAM_IDS[0]
        val role = Constants.TEAM_MEMBER_ROLE.getValue("Player Coach")
        viewModel.fetchInvite(id, role)
        val data = viewModel.onInviteData().getOrAwaitValue()
        assertThat(data.url).isNotEmpty()
    }

}