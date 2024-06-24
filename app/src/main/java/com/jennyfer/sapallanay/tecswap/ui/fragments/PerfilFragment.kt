package com.jennyfer.sapallanay.tecswap.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jennyfer.sapallanay.tecswap.R
import com.jennyfer.sapallanay.tecswap.databinding.FragmentPerfilBinding


class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cargar imagen predeterminada usando Glide
        Glide.with(this)
            .load(R.drawable.icon_profile) // Cambia esto por tu imagen predeterminada
            .apply(RequestOptions.circleCropTransform())
            .into(binding.profileImage)

        // Configurar botón de cerrar sesión
        binding.logoutButton.setOnClickListener {
            val fragmentManager: FragmentManager = parentFragmentManager
            val logoutDialog = LogoutDialogFragment()
            logoutDialog.show(fragmentManager, "logout_dialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}