package imrankst1221.invite.team.member.data.repository

import androidx.lifecycle.LiveData
import imrankst1221.invite.team.member.data.model.Team

interface TeamRepository {
  suspend fun fetchTeams(teamId: String)
  fun onTeamData(): LiveData<Team>
}