package com.jennyfer.sapallanay.tecswap.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jennyfer.sapallanay.tecswap.R
import com.jennyfer.sapallanay.tecswap.databinding.ActivityLoginBinding
import com.jennyfer.sapallanay.tecswap.ui.fragments.LoginTabFragment
import com.jennyfer.sapallanay.tecswap.ui.fragments.SignupTabFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(LoginTabFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun navigateToSignup() {
        loadFragment(SignupTabFragment())
    }

    fun navigateToLogin() {
        loadFragment(LoginTabFragment())
    }
}

