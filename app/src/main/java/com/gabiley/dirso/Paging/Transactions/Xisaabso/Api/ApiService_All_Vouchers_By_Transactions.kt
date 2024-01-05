package com.gabiley.dirso.Paging.Transactions.Xisaabso.Api


import com.gabiley.xisaabso.Classes.Xisaabso.Xisaabso
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService_Xisaabso_All_Vouchers_By_Transactions {
    //https://xisaabso.online/allvouchers?page=1
    @GET("allvouchersbytransactions")
    suspend fun getAll_Vouchers_By_Transactions(
        @Query("page") page: Int,
    ): Xisaabso
}


