//package com.jennyfer.sapallanay.tecswap.ui.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.jennyfer.sapallanay.tecswap.data.model.Producto
//import com.jennyfer.sapallanay.tecswap.data.repository.ProductoRepository
//import kotlinx.coroutines.launch
//
//class ExplorarViewModel(private val repository: ProductoRepository) : ViewModel() {
//
//    private val _productos = MutableLiveData<List<Producto>>()
//    val productos: LiveData<List<Producto>> get() = _productos
//
//    fun fetchProductos() {
//        viewModelScope.launch {
//            val productosList = repository.getProductos()
//            _productos.postValue(productosList)
//        }
//    }
//}