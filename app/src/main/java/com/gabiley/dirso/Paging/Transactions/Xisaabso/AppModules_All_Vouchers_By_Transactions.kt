package com.gabiley.dirso.Paging.Transactions.Xisaabso

import com.gabiley.dirso.Paging.Transactions.Xisaabso.Api.ApiService_Xisaabso_All_Vouchers_By_Transactions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@InstallIn(SingletonComponent::class)
@Module
object AppModules_All_Vouchers_By_Transactions {
    //https://xisaabso.online/allvouchers?page=1
    private const val BASE_URL = "https://xisaabso.online/"

    @Provides
    fun provideAPiService_All_Vouchers_By_Transactions(): ApiService_Xisaabso_All_Vouchers_By_Transactions {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService_Xisaabso_All_Vouchers_By_Transactions::class.java)
    }

    @Provides
    fun provideAll_Vouchers_By_TransactionsRepo(
        apiserviceXisaabso_All_Vouchers_By_Transactions: ApiService_Xisaabso_All_Vouchers_By_Transactions
    ): Xisaabso_All_Vouchers_By_TransactionsRepo {
        return Xisaabso_All_Vouchers_By_TransactionsRepo(
            apiserviceXisaabso_All_Vouchers_By_Transactions
        )
    }

}