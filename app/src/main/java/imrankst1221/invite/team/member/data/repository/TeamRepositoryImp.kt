package imrankst1221.invite.team.member.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import imrankst1221.invite.team.member.data.model.Team
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImp @Inject constructor() : TeamRepository {
    private val teamData = MutableLiveData<Team>()

    override suspend fun fetchTeams(teamId: String) {
        TODO("Not yet implemented")
    }

    override fun onTeamData(): LiveData<Team> {
        return teamData
    }

    operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
        val value = this.value ?: arrayListOf()
        value.addAll(values)
        this.value = value
    }
}