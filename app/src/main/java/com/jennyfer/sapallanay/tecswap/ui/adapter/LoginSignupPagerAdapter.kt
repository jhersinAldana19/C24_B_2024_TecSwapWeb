package com.jennyfer.sapallanay.tecswap.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jennyfer.sapallanay.tecswap.ui.fragments.LoginTabFragment
import com.jennyfer.sapallanay.tecswap.ui.fragments.SignupTabFragment

class LoginSignupPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginTabFragment()
            1 -> SignupTabFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}