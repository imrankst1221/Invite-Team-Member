package imrankst1221.invite.team.member.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.findNavController
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.databinding.FragmentHomeBinding
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.utilities.Constants

class HomeFragment : BaseFragment<FragmentHomeBinding>(), LifecycleObserver {
    override fun setBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    var mockTeamsPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClick()
    }

    override fun onStart() {
        super.onStart()
        initView()
    }
    private fun initView() {
        (activity as MainActivity?)?.setToolbarTitle(getString(R.string.app_name))
        (activity as MainActivity?)?.hideBackButton()
        updateTestCase()
    }

    private fun initOnClick() {
        binding.buttonInvite.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("teamId", Constants.DEMO_TEAM_IDS[mockTeamsPosition])
            }
            updateTestCase()
            if (mockTeamsPosition >= Constants.DEMO_TEAM_IDS.size - 1) {
                mockTeamsPosition = 0
            } else {
                mockTeamsPosition++
            }

            findNavController().navigate(
                R.id.action_HomeFragment_to_InviteMembersFragment,
                bundle
            )
        }
    }

    private fun updateTestCase() {
        binding.textViewTestCase.text = "Test case ${mockTeamsPosition + 1}"
    }
}