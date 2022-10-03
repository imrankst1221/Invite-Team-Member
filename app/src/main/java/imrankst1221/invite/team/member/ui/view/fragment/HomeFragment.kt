package imrankst1221.invite.team.member.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.databinding.FragmentHomeBinding
import imrankst1221.invite.team.member.utilities.Constants

class HomeFragment : BaseFragment<FragmentHomeBinding>()  {
    override fun setBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    var mockTeamsPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClick()
    }

    private fun initOnClick(){
        binding.buttonInvite.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("teamId", Constants.DEMO_TEAM_IDS[mockTeamsPosition])
            }
            if(mockTeamsPosition >= Constants.DEMO_TEAM_IDS.size){
                mockTeamsPosition = 0
            }else{
                mockTeamsPosition++
            }

            findNavController().navigate(
                R.id.action_FirstFragment_to_SecondFragment,
                bundle
            )
        }
    }
}