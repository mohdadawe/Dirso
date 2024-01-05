package com.gabiley.xisaabso.Paging.Customer

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gabiley.xisaabso.Classes.Customer_data.Data
import com.gabiley.xisaabso.Paging.Customer.Api.ApiService_CustomerData
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException


class Xisaabso_CustomerDataPagingSource (
    private val apiserviceCustomerdata: ApiService_CustomerData
    ): PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val position = params.key ?: 1
            delay(500)

            val response = apiserviceCustomerdata
                .getAll_CustomerData(page = position)
            LoadResult.Page(
                data = response.data,
                //remoteData,
                prevKey = if(position == 1) null
                          else position - 1,
                nextKey = if(position == response.last_page) null
                          else position + 1
            )
        }
        catch (e:IOException){
            LoadResult.Error(e)
        }
        catch (e:HttpException){
            LoadResult.Error(e)
        }

    }
}

