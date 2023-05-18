package com.mra.rentcar.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.mra.rentcar.utils.SHOW_LOGOUT
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    private var showLogout = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        hideLogoutBtn()

        navController = findNavController(R.id.navHost)

        binding?.moreBtn?.setOnClickListener {
            navController?.navigate(
                R.id.open_main_menu_bottom_sheet,
                bundleOf(SHOW_LOGOUT to showLogout)
            )
        }
    }

    fun hideLogoutBtn() {
        showLogout = false
    }

    fun showLogoutBtn() {
        showLogout = true
    }
}