package com.gabiley.xisaabso.Paging.Customer.Api


import com.gabiley.xisaabso.Classes.Customer_data.CustomerData
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService_CustomerData {
    //https://xisaabso.online/allvouchers?page=1
    @GET("getallcustomer")
    suspend fun getAll_CustomerData(
        @Query("page") page: Int,
    ): CustomerData
}

