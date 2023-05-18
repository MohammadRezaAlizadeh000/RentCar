package com.mra.rentcar.presenter.view.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mra.rentcar.utils.SHOW_LOGOUT
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.MainMenuBottomSheetFragmentBinding


class MainMenuBottomSheetFragment: BottomSheetDialogFragment() {

    var binding: MainMenuBottomSheetFragmentBinding? = null
    var showLogout = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            setOnShowListener {
                val bottomSheetDialog = it as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLogout = arguments?.getBoolean(SHOW_LOGOUT, false) ?: false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainMenuBottomSheetFragmentBinding.inflate(layoutInflater)

        binding?.logoutBtn?.visibility = if (showLogout) View.VISIBLE else View.GONE

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            logoutBtn.setOnClickListener {
                // clear data store and direct user to login page
                findNavController().popBackStack(R.id.loginFragment, false)
            }

            aboutUsBtn.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")))
            }
        }
    }



}