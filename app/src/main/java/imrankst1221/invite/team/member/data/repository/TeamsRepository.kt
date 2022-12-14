package imrankst1221.invite.team.member.data.repository

import androidx.lifecycle.LiveData
import imrankst1221.invite.team.member.data.model.Invites
import imrankst1221.invite.team.member.data.model.Team

interface TeamsRepository {
    suspend fun fetchTeams(teamId: String)
    fun onTeamsData(): LiveData<Team>
    suspend fun fetchInvite(teamId: String, role: String)
    fun onInviteData(): LiveData<Invites>
}