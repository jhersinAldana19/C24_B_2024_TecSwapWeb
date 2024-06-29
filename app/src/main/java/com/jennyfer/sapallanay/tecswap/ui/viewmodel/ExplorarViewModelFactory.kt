//package com.jennyfer.sapallanay.tecswap.ui.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.jennyfer.sapallanay.tecswap.data.repository.ProductoRepository
//
//class ExplorarViewModelFactory(private val repository: ProductoRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ExplorarViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ExplorarViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}