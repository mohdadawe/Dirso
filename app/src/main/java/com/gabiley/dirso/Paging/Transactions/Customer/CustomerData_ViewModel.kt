package com.gabiley.xisaabso.Paging.Customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gabiley.xisaabso.Classes.Customer_data.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class Xisaabso_CustomerDataViewModel @Inject constructor(
    private val xisaabsoCustomerdatarepo: Xisaabso_CustomerDataRepo
) : ViewModel() {
    fun getDDDDD(): Flow<PagingData<Data>> =
        xisaabsoCustomerdatarepo
            .getXisaabso_CustomerDataStream()
            .cachedIn(viewModelScope)
            //.getXisaabso_All_Vouchers_By_TransactionsStream()
            //.cachedIn(viewModelScope)
    //val pagingData = allVouchersRepo.getAllVouchersStream()
}

