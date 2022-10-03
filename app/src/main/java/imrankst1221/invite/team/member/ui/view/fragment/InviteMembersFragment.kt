package imrankst1221.invite.team.member.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.navigation.fragment.navArgs
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.currentMember
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isAvailableMemberSlots
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isAvailableSupporterSlots
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isSupporterLimitZero
import imrankst1221.invite.team.member.data.model.InvitePermission
import imrankst1221.invite.team.member.databinding.FragmentInviteMembersBinding
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.ui.view.adapter.InvitePermissionAdapter
import imrankst1221.invite.team.member.ui.viewmodels.TeamsViewModel
import imrankst1221.invite.team.member.utilities.Constants
import imrankst1221.invite.team.member.utilities.Coroutines

class InviteMembersFragment : BaseFragment<FragmentInviteMembersBinding>() {
    override fun setBinding(): FragmentInviteMembersBinding =
        FragmentInviteMembersBinding.inflate(layoutInflater)

    lateinit var teamsViewModel: TeamsViewModel
    lateinit var teamId: String
    private var invitePermissions: ArrayList<InvitePermission> = arrayListOf()
    private val args: InviteMembersFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamsViewModel = (activity as MainActivity).teamsViewModel
        teamId = args.teamId

        initView()
        fetchTeams()
        setupObserver()
    }

    private fun initView() {

    }

    private fun fetchTeams() = Coroutines.main {
        if (::teamId.isInitialized) {
            teamsViewModel.fetchTeams(teamId)
        }
    }

    private fun setupObserver() = Coroutines.main {
        teamsViewModel.onTeamsData().observe(viewLifecycleOwner) { team ->
            binding.textViewCurrentMember.text = team.currentMember().toString()
            binding.textViewMemberLimitTitle.text = team.plan?.memberLimit.toString()

            if (team.isSupporterLimitZero()) {
                binding.layoutCurrentSupporter.visibility = View.GONE
            } else {
                binding.layoutCurrentSupporter.visibility = View.VISIBLE
                binding.textViewSupporters.text = team.members?.supporters.toString()
                binding.textViewSupporterLimit.text = team.plan?.supporterLimit.toString()
            }

            invitePermissions = arrayListOf()
s            Constants.TEAM_MEMBER_ROLE.keys.forEachIndexed { index, key ->
                if (key == "Supporter") {
                    if (!team.isSupporterLimitZero()) {
                        invitePermissions.add(InvitePermission(key, team.isAvailableSupporterSlots()))
                    }
                } else {
                    invitePermissions.add(InvitePermission(key, team.isAvailableMemberSlots()))
                }
            }

            binding.spinnerInvitePermission.adapter = InvitePermissionAdapter(invitePermissions)
        }

        teamsViewModel.onInviteData().observe(viewLifecycleOwner) {

        }
    }
}