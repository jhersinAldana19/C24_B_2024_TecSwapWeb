package com.jennyfer.sapallanay.tecswap.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jennyfer.sapallanay.tecswap.R
import com.jennyfer.sapallanay.tecswap.data.model.LoginRequest
import com.jennyfer.sapallanay.tecswap.data.network.ApiService
import com.jennyfer.sapallanay.tecswap.data.network.RetrofitClient
import com.jennyfer.sapallanay.tecswap.data.repository.AuthRepository
import com.jennyfer.sapallanay.tecswap.databinding.FragmentLoginTabBinding
import com.jennyfer.sapallanay.tecswap.ui.activities.LoginActivity
import com.jennyfer.sapallanay.tecswap.ui.activities.MainActivity2
import com.jennyfer.sapallanay.tecswap.ui.viewmodel.AuthViewModel
import com.jennyfer.sapallanay.tecswap.ui.viewmodel.AuthViewModelFactory

class LoginTabFragment : Fragment() {

    private var _binding: FragmentLoginTabBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_tab, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = RetrofitClient.getInstance(requireContext()).create(ApiService::class.java)
        val repository = AuthRepository(apiService)
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginButton.setOnClickListener {
            val email = viewModel.email.value?.trim()
            val password = viewModel.password.value?.trim()

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                val request = LoginRequest(email, password)
                viewModel.loginUser(request, requireContext()) { response ->
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Bienvenid@!", Toast.LENGTH_SHORT).show()
                        navigateToMainActivity()
                    } else {
                        Toast.makeText(requireContext(), "Logueo fallido", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        val signUpText = getString(R.string.no_account_signup)
        val spannableString = SpannableString(signUpText)
        val start = signUpText.indexOf("Regístrate")
        val end = start + "Regístrate".length

        val linkColor = ContextCompat.getColor(requireContext(), R.color.link_color)
        spannableString.setSpan(
            ForegroundColorSpan(linkColor),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.signupText.text = spannableString

        binding.signupText.setOnClickListener {
            (activity as LoginActivity).navigateToSignup()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity2::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


