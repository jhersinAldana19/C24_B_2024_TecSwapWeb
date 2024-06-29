//package com.jennyfer.sapallanay.tecswap.data.repository
//
//import retrofit2.Response
//import com.jennyfer.sapallanay.tecswap.data.model.Producto
//import com.jennyfer.sapallanay.tecswap.data.network.ApiService
//
//class ProductoRepository(private val apiService: ApiService) {
//
//    suspend fun getProductos(): List<Producto> {
//        return apiService.getProductos()
//    }
//
//    suspend fun createProducto(producto: Producto): Response<Producto> {
//        return apiService.createProducto(producto)
//    }
//}