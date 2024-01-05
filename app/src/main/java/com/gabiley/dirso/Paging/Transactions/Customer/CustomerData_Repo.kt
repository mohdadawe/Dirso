package com.gabiley.xisaabso.Paging.Customer

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gabiley.xisaabso.Paging.Customer.Api.ApiService_CustomerData

class Xisaabso_CustomerDataRepo(
    private val apiserviceCustomerdata: ApiService_CustomerData
) {
    fun getXisaabso_CustomerDataStream() = Pager(
        config = PagingConfig(
            pageSize = 5,
            /*prefetchDistance = 5,
            enablePlaceholders = false,
            initialLoadSize = 5*/
        ),

        pagingSourceFactory = {
            Xisaabso_CustomerDataPagingSource(
                apiserviceCustomerdata
            )
        }
    ).flow

}

