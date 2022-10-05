package imrankst1221.invite.team.member.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.zxing.WriterException
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.base.BaseFragment
import imrankst1221.invite.team.member.databinding.FragmentShareQrCodeBinding
import imrankst1221.invite.team.member.ui.view.MainActivity
import imrankst1221.invite.team.member.utilities.UtilMethods

class ShareQrCodeFragment :  BaseFragment<FragmentShareQrCodeBinding>() {
    override fun setBinding(): FragmentShareQrCodeBinding =
        FragmentShareQrCodeBinding.inflate(layoutInflater)

    lateinit var mContext: Context
    lateinit var shareUrl: String
    private val args: ShareQrCodeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = (activity as MainActivity).applicationContext
        shareUrl = args.shareUrl

        initView()
    }

    private fun initView(){
        (activity as MainActivity?)?.setToolbarTitle(getString(R.string.my_qr_code_label))
        (activity as MainActivity?)?.showBackButton()

        try {
            val bitmap = UtilMethods.encodeAsBitmap(shareUrl)
            binding.imageViewQrCode.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}