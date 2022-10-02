package imrankst1221.invite.team.member.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import imrankst1221.invite.team.member.data.repository.TeamRepositoryImp
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject internal constructor(
        private val teamRepository: TeamRepositoryImp
    ) : ViewModel() {
    suspend fun fetchTeams(teamId: String) = teamRepository.fetchTeams(teamId)
    suspend fun onTeamData() = teamRepository.onTeamData()
}