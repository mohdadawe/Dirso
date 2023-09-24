package com.gabiley.emoney.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreAccountNum(private val context: Context) {
    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userEmail")
        val ACCOUNT_NUM_KEY  = stringPreferencesKey("accountNum")
        val USERNAME_KEY     = stringPreferencesKey("username")
        val ACCOUNT_TYPE_KEY = stringPreferencesKey("accountType")
        val PHONE_NUM_KEY    = stringPreferencesKey("Session_PhoneNum")
        val EXPIRY_ACCOUNT   = stringPreferencesKey("expiry_account")
        val VERIFICATION_KEY = stringPreferencesKey(("verification_code"))
        val MAX_SENDING_MONEY_USD = stringPreferencesKey("max_sending_money_usd")
    }

    //get the saved MaxSendingMoneyUSD
    val getMaxSendingMoneyUSD: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[MAX_SENDING_MONEY_USD] ?: ""
        }

    //save MaxSendingMoneyUSD into datastore
    suspend fun saveMaxSendingMoneyUSD(sendingMoneyUSD: String) {
        context.dataStore.edit { preferences ->
            preferences[MAX_SENDING_MONEY_USD] = sendingMoneyUSD
        }
    }



    //get the saved AccountNum
    val getAccountNum: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[ACCOUNT_NUM_KEY] ?: ""
        }

    //save AccountNum into datastore
    suspend fun saveAccountNum(accountNum: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCOUNT_NUM_KEY] = accountNum
        }
    }

    //get the saved AccountNum
    val getUsername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USERNAME_KEY] ?: ""
        }

    //save AccountNum into datastore
    suspend fun saveUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    //get the saved AccountNum
    val getAccountType: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[ACCOUNT_TYPE_KEY] ?: ""
        }

    //save AccountNum into datastore
    suspend fun saveAccountType(accountType: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCOUNT_TYPE_KEY] = accountType
        }
    }

    //get the saved PhoneNum
    val getPhoneNum: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PHONE_NUM_KEY] ?: ""
        }

    //save PhoneNum into datastore
    suspend fun savePhoneNum(phoneNum: String) {
        context.dataStore.edit { preferences ->
            preferences[PHONE_NUM_KEY] = phoneNum
        }
    }

    //get the saved ExpiryAccount
    val getExpiryAccount: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[EXPIRY_ACCOUNT] ?: ""
        }

    //save ExpiryAccount into datastore
    suspend fun saveExpiryAccount(expiryAccount: String) {
        context.dataStore.edit { preferences ->
            preferences[EXPIRY_ACCOUNT] = expiryAccount
        }
    }

    //get the saved VerficationCode
    val getVerficationCode: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[VERIFICATION_KEY] ?: ""
        }

    //save VerficationCode into datastore
    suspend fun saveVerficationCode(verficationCode: String) {
        context.dataStore.edit { preferences ->
            preferences[VERIFICATION_KEY] = verficationCode
        }
    }



}

