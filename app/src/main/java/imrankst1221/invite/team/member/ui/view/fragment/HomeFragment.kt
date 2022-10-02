package imrankst1221.invite.team.member.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>()  {
    override fun setBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClick()
    }

    private fun initOnClick(){
        binding.buttonInvite.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}