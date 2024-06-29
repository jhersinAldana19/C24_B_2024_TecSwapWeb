package com.jennyfer.sapallanay.tecswap.ui.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jennyfer.sapallanay.tecswap.R
import com.jennyfer.sapallanay.tecswap.data.model.RegisterRequest
import com.jennyfer.sapallanay.tecswap.data.network.ApiService
import com.jennyfer.sapallanay.tecswap.data.network.RetrofitClient
import com.jennyfer.sapallanay.tecswap.data.repository.AuthRepository
import com.jennyfer.sapallanay.tecswap.databinding.FragmentSignupTabBinding
import com.jennyfer.sapallanay.tecswap.ui.activities.LoginActivity
import com.jennyfer.sapallanay.tecswap.ui.viewmodel.AuthViewModel
import com.jennyfer.sapallanay.tecswap.ui.viewmodel.AuthViewModelFactory

class SignupTabFragment : Fragment() {

    private var _binding: FragmentSignupTabBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = RetrofitClient.getInstance(requireContext()).create(ApiService::class.java)
        val repository = AuthRepository(apiService)
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(AuthViewModel::class.java)

        binding.signupButton.setOnClickListener {
            val name = binding.signupName.text.toString().trim()
            val lastname = binding.signupLastname.text.toString().trim()
            val phone = binding.signupPhone.text.toString().trim()
            val email = binding.signupEmail.text.toString().trim()
            val password = binding.signupPassword.text.toString().trim()
            val confirmPassword = binding.signupConfirmPassword.text.toString().trim()
            val carrera = binding.signupCarrera.text.toString().trim()

            if (name.isNotEmpty() && lastname.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && confirmPassword.isNotEmpty() && carrera.isNotEmpty()) {

                if (password == confirmPassword) {
                    val request = RegisterRequest(name, lastname, phone, email, password, carrera)
                    viewModel.registerUser(request, requireContext()) { response ->
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
                            navigateToLogin()
                        } else {
                            Toast.makeText(requireContext(), "Registro fallido", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        setupSpannableString()
        binding.loginText.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun setupSpannableString() {
        val loginText = getString(R.string.already_have_account_login)
        val spannableString = SpannableString(loginText)
        val start = loginText.indexOf("Inicia sesión")
        val end = start + "Inicia sesión".length

        if (start >= 0 && end >= 0) {
            val linkColor = ContextCompat.getColor(requireContext(), R.color.link_color)
            spannableString.setSpan(
                ForegroundColorSpan(linkColor),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.loginText.text = spannableString
    }

    private fun navigateToLogin() {
        (activity as LoginActivity).navigateToLogin()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



