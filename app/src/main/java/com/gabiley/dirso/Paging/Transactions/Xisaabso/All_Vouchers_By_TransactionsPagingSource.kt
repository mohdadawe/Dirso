package com.gabiley.dirso.Paging.Transactions.Xisaabso


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gabiley.xisaabso.Classes.Xisaabso.XisaabsoData
import com.gabiley.dirso.Paging.Transactions.Xisaabso.Api.ApiService_Xisaabso_All_Vouchers_By_Transactions
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException


class Xisaabso_All_Vouchers_By_TransactionsPagingSource (
    private val apiserviceXisaabso_All_Vouchers_By_Transactions:
    ApiService_Xisaabso_All_Vouchers_By_Transactions
    ): PagingSource<Int, XisaabsoData>() {
    override fun getRefreshKey(state: PagingState<Int, XisaabsoData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, XisaabsoData> {
        return try {
            val position = params.key ?: 1

            delay(250)

            val response = apiserviceXisaabso_All_Vouchers_By_Transactions
                .getAll_Vouchers_By_Transactions(page = position)
            LoadResult.Page(
                data = response.data,
                //remoteData,
                prevKey = if(position == 1) null
                          else position - 1,
                nextKey = if(position == response.last_page) null
                          else position + 1

                /*
                prevKey = if(position == 1) null
                          else position.minus(1),
                nextKey = if(response.data.isEmpty()) null
                          else position.plus(1)
                */

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

