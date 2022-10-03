package imrankst1221.invite.team.member.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.databinding.FragmentInviteMembersBinding

class InviteMembersFragment : BaseFragment<FragmentInviteMembersBinding>(){
    override fun setBinding(): FragmentInviteMembersBinding =
        FragmentInviteMembersBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}