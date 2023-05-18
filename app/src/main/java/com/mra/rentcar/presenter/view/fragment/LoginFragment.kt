package com.mra.rentcar.presenter.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mra.rentcar.data.model.UserDataLoginInputModel
import com.mra.rentcar.presenter.view.BaseFragment
import com.mra.rentcar.presenter.viewmodel.LoginViewModel
import com.mra.rentcar.utils.*
import com.mra.testrantecarapp.R
import com.mra.testrantecarapp.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideLogoutBtn()

        binding?.apply {
            loginBtn.setOnClickListener { loginClickListener() }

            headerImage.showWithGlide("https://wahadventures.com/wp-content/uploads/2021/08/Ways-to-Make-Money-Renting-Out-Your-Car-1024x576.jpg")

            usernameET.onTextChangeListener { if (it.isNotEmpty()) usernameETLayout.removeError() else usernameETLayout.removeError() }
            passwordET.onTextChangeListener { if (it.isNotEmpty()) passwordETLayout.removeError() else passwordETLayout.removeError() }
        }

        observeUserLoginResult()
    }

    private fun loginClickListener() {
        binding?.apply {
            val username = usernameET.text.toString()
            val password = passwordET.text.toString()

            checkValidation()

            if (username.isNotEmpty() && password.isNotEmpty())
                loginUserRequest(createUserLoginData(username, password))
        }
    }

    private fun checkValidation() {
        binding?.apply {
            val username = usernameET.text.toString()
            val password = passwordET.text.toString()

            if (username.isNotEmpty()) usernameETLayout.removeError()
            else usernameETLayout.callError(resources.getString(R.string.fieldEmpty))

            if (password.isNotEmpty()) passwordETLayout.removeError()
            else passwordETLayout.callError(resources.getString(R.string.fieldEmpty))
        }
    }

    private fun createUserLoginData(username: String, password: String): UserDataLoginInputModel {
        return UserDataLoginInputModel(username, password)
    }

    private fun loginUserRequest(userLoginDataLoginInputModel: UserDataLoginInputModel) {
        viewModel.userLogin(userLoginDataLoginInputModel)
    }

    private fun observeUserLoginResult() {
        repeatOnLifeCycle(viewModel.userLoginResultFlow) { result ->
            result.data?.let {
                if (it)
                    navigateToCarsList()

                viewModel.clearUserLoginFlow()
            }

            result.message?.let {
                toast(it)
                viewModel.clearUserLoginFlow()
            }
        }
    }

    private fun navigateToCarsList() {
        findNavController().navigate(R.id.action_loginFragment_to_carsListFragment)
    }
}