package com.jennyfer.sapallanay.tecswap.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jennyfer.sapallanay.tecswap.databinding.FragmentLogoutDialogBinding


class LogoutDialogFragment : DialogFragment() {

    private var _binding: FragmentLogoutDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentLogoutDialogBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.positiveButton.setOnClickListener {
            logout()
            dismiss()
        }

        binding.negativeButton.setOnClickListener {
            dismiss()
        }

        return builder.create()
    }

    private fun logout() {
        // Aquí puedes manejar la lógica de cerrar sesión
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}