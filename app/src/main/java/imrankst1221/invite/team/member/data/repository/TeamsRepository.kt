package imrankst1221.invite.team.member.data.repository

import androidx.lifecycle.LiveData
import imrankst1221.invite.team.member.data.model.Invites
import imrankst1221.invite.team.member.data.model.Team
import imrankst1221.invite.team.member.utilities.SingleLiveData

interface TeamsRepository {
    suspend fun fetchTeams(teamId: String)
    fun onTeamsData(): SingleLiveData<Team>
    suspend fun fetchInvite(teamId: String, role: String, isQrNavigation: Boolean)
    fun onInviteData(): SingleLiveData<Invites>
}