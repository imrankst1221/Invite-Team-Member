package imrankst1221.invite.team.member.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import imrankst1221.invite.team.member.data.repository.TeamsRepositoryImp
import imrankst1221.invite.team.member.utilities.MainCoroutineRule
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
class TeamsViewModelTest{
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

    @Test
    fun testTeams_dataStore() = runBlocking {
        val id = "57994f271ca5dd20847b910c"
        viewModel.fetchTeams(id)
        val data = viewModel.onTeamsData().getOrAwaitValue()
        assertThat(data.id).isEqualTo(id)
    }

}