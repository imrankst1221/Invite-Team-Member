package imrankst1221.invite.team.member.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.currentMember
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isAvailableMemberSlots
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isAvailableSupporterSlots
import imrankst1221.invite.team.member.data.extensions.TeamExtensions.isSupporterLimitZero
import imrankst1221.invite.team.member.data.model.InvitePermission
import imrankst1221.invite.team.member.data.model.Invites
import imrankst1221.invite.team.member.data.model.Team
import imrankst1221.invite.team.member.databinding.FragmentInviteMembersBinding
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.ui.view.adapter.InvitePermissionAdapter
import imrankst1221.invite.team.member.ui.viewmodels.TeamsViewModel
import imrankst1221.invite.team.member.utilities.Constants
import imrankst1221.invite.team.member.utilities.Coroutines
import imrankst1221.invite.team.member.utilities.UtilMethods


class InviteMembersFragment : BaseFragment<FragmentInviteMembersBinding>() {
    override fun setBinding(): FragmentInviteMembersBinding =
        FragmentInviteMembersBinding.inflate(layoutInflater)

    lateinit var mContext: Context
    lateinit var teamsViewModel: TeamsViewModel
    lateinit var teamId: String
    private var invitePermissions: ArrayList<InvitePermission> = arrayListOf()
    private val args: InviteMembersFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamsViewModel = (activity as MainActivity).teamsViewModel
        mContext = (activity as MainActivity).applicationContext
        teamId = args.teamId

        initView()
        initOnClick()
        fetchTeams()
        setupObserver()
    }

    private fun initView(){
        (activity as MainActivity?)?.setToolbarTitle(getString(R.string.invite_members_fragment_label))
        (activity as MainActivity?)?.showBackButton()
    }

    private fun initOnClick() {
        binding.buttonCopyLink.setOnClickListener {
            fetchInvite(false)
        }

        binding.buttonShareQrCode.setOnClickListener {
            fetchInvite(true)
        }
    }

    private fun updateUi(team: Team){
        binding.textViewCurrentMember.text = team.currentMember().toString()
        binding.textViewMemberLimit.text = team.plan?.memberLimit.toString()

        if (team.isSupporterLimitZero()) {
            binding.layoutCurrentSupporter.visibility = View.GONE
        } else {
            binding.layoutCurrentSupporter.visibility = View.VISIBLE
            binding.textViewSupporters.text = team.members?.supporters.toString()
            binding.textViewSupporterLimit.text = team.plan?.supporterLimit.toString()
        }

        invitePermissions = arrayListOf()
        Constants.TEAM_MEMBER_ROLE.keys.forEachIndexed { index, key ->
            if (key == Constants.supporter) {
                if (!team.isSupporterLimitZero()) {
                    invitePermissions.add(InvitePermission(key, team.isAvailableSupporterSlots()))
                }
            } else {
                invitePermissions.add(InvitePermission(key, team.isAvailableMemberSlots()))
            }
        }

        binding.spinnerInvitePermission.adapter = InvitePermissionAdapter(invitePermissions)
    }

    private fun shareLink(invite: Invites){
        if(invite.isQrNavigation){
            val bundle = Bundle().apply {
                putSerializable("shareUrl", invite.url ?: "")
            }
            findNavController().navigate(
                R.id.action_InviteMembersFragment_to_shareQrCode,
                bundle
            )
        }else{
            UtilMethods.copyIntoClipBoard(mContext, invite.url ?: "")
            UtilMethods.showLongToast(mContext, getString(R.string.message_clip_board_copy_success))
        }
    }

    private fun fetchTeams() = Coroutines.main {
        if (::teamId.isInitialized) {
            teamsViewModel.fetchTeams(teamId)
        }
    }

    private fun fetchInvite(isQrNavigation: Boolean) = Coroutines.main {
        val selectedPermission = binding.spinnerInvitePermission.selectedItem as InvitePermission
        if(selectedPermission.isEnabled){
            val role = Constants.TEAM_MEMBER_ROLE[selectedPermission.name] ?: ""
            if (::teamId.isInitialized) {
                teamsViewModel.fetchInvite(teamId, role, isQrNavigation)
            }
        }else {
            UtilMethods.showLongToast(mContext, getString(R.string.message_permission_incorrect))
        }
    }

    private fun setupObserver() = Coroutines.main {
        teamsViewModel.onTeamsData().observe(viewLifecycleOwner) { team ->
            if(team != null) {
                activity?.runOnUiThread {
                    updateUi(team)
                }
            }
        }

        teamsViewModel.onInviteData().observe(viewLifecycleOwner) { invite ->
            if(invite != null) {
                activity?.runOnUiThread {
                    shareLink(invite)
                }
            }
        }
    }
}