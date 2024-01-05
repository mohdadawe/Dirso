package com.gabiley.dirso.Paging.Transactions.Xisaabso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gabiley.xisaabso.Classes.Xisaabso.XisaabsoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class Xisaabso_All_Vouchers_By_TransactionsViewModel @Inject constructor(
    private val xisaabso_All_Vouchers_By_TransactionsRepo:
    Xisaabso_All_Vouchers_By_TransactionsRepo
) : ViewModel() {
    fun getDDDDD(): Flow<PagingData<XisaabsoData>> =
        xisaabso_All_Vouchers_By_TransactionsRepo
            .getXisaabso_All_Vouchers_By_TransactionsStream()
            .cachedIn(viewModelScope)
    //val pagingData = allVouchersRepo.getAllVouchersStream()
}

