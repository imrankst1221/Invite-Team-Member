package imrankst1221.invite.team.member.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import imrankst1221.invite.team.member.data.repository.TeamsRepositoryImp
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject internal constructor(
    private val teamRepository: TeamsRepositoryImp
) : ViewModel() {
    suspend fun fetchTeams(teamId: String) = teamRepository.fetchTeams(teamId)
    fun onTeamsData() = teamRepository.onTeamsData()
    suspend fun fetchInvite(teamId: String, role: String, isQrNavigation: Boolean) =
        teamRepository.fetchInvite(teamId, role, isQrNavigation)

    fun onInviteData() = teamRepository.onInviteData()
}