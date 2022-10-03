package imrankst1221.invite.team.member.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import imrankst1221.invite.team.member.data.api.ApiService
import imrankst1221.invite.team.member.data.api.SafeApiRequest
import imrankst1221.invite.team.member.data.model.Invites
import imrankst1221.invite.team.member.data.model.InvitesRequest
import imrankst1221.invite.team.member.data.model.Team
import imrankst1221.invite.team.member.utilities.NetworkHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamsRepositoryImp @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val service: ApiService
) : TeamsRepository, SafeApiRequest() {
    private val teamsData = MutableLiveData<Team>()
    private val inviteData = MutableLiveData<Invites>()

    override suspend fun fetchTeams(teamId: String) {
        val response = apiRequest {
            service.getTeams(teamId)
        }
        teamsData.postValue(response)
    }

    override fun onTeamsData(): LiveData<Team> {
        return teamsData
    }

    override suspend fun fetchInvite(teamId: String, role: String) {
        val response = apiRequest {
            service.getInvitesUrl(teamId, InvitesRequest(role = role))
        }
        inviteData.postValue(response)
    }

    override fun onInviteData(): LiveData<Invites> {
        return inviteData
    }

    operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
        val value = this.value ?: arrayListOf()
        value.addAll(values)
        this.value = value
    }
}