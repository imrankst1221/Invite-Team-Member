package imrankst1221.invite.team.member.ui.view

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import imrankst1221.invite.team.member.R
import imrankst1221.invite.team.member.base.BaseActivity
import imrankst1221.invite.team.member.ui.viewmodels.TeamsViewModel
import imrankst1221.invite.team.member.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun setBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    val teamsViewModel: TeamsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initOnClick()
    }

    private fun initView(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initOnClick(){
        binding.buttonToolbarBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun setToolbarTitle(title: String){
        binding.textViewToolbarTitle.text = title
    }

    fun hideBackButton(){
        binding.buttonToolbarBack.visibility = View.GONE
    }

    fun showBackButton(){
        binding.buttonToolbarBack.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}