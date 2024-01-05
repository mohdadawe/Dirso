package com.gabiley.xisaabso.Paging.Customer

import com.gabiley.xisaabso.Paging.Customer.Api.ApiService_CustomerData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



@InstallIn(SingletonComponent::class)
@Module
object AppModules_CustomerData {
    //https://xisaabso.online/allvouchers?page=1
    private const val BASE_URL = "https://xisaabso.online/"

    @Provides
    fun provideAPiService_CustomerData(): ApiService_CustomerData {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService_CustomerData::class.java)
    }

    @Provides
    fun provideAll_CustomerDataRepo(
        apiserviceCustomerdata: ApiService_CustomerData
    ): Xisaabso_CustomerDataRepo {
        return Xisaabso_CustomerDataRepo(
            apiserviceCustomerdata
        )
    }

}