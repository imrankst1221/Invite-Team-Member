package imrankst1221.invite.team.member.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.navigation.fragment.navArgs
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.databinding.FragmentInviteMembersBinding
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.ui.viewmodels.TeamsViewModel
import imrankst1221.invite.team.member.utilities.Coroutines

class InviteMembersFragment : BaseFragment<FragmentInviteMembersBinding>(){
    override fun setBinding(): FragmentInviteMembersBinding =
        FragmentInviteMembersBinding.inflate(layoutInflater)

    lateinit var teamsViewModel: TeamsViewModel
    lateinit var teamId: String

    private val args: InviteMembersFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamsViewModel = (activity as MainActivity).teamsViewModel
        teamId = args.teamId

        initView()
        fetchTeams()
        setupObserver()
    }

    private fun initView(){

    }

    private fun fetchTeams() = Coroutines.main {
        if (::teamId.isInitialized) {
            teamsViewModel.fetchTeams(teamId)
        }
    }

    private fun setupObserver() = Coroutines.main {
        teamsViewModel.onTeamsData().observe(viewLifecycleOwner){

        }

        teamsViewModel.onInviteData().observe(viewLifecycleOwner){

        }
    }
}