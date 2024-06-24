package com.jennyfer.sapallanay.tecswap.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jennyfer.sapallanay.tecswap.databinding.ActivityLoginBinding
import com.jennyfer.sapallanay.tecswap.ui.adapter.LoginSignupPagerAdapter

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = LoginSignupPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Iniciar Sesión"
                1 -> "Registrarse"
                else -> null
            }
        }.attach()

    }
}