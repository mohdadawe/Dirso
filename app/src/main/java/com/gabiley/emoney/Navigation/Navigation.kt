package com.gabiley.emoney.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabiley.emoney.DataStore.StoreAccountNum
import com.gabiley.emoney.SealedClasses.Screen
import com.gabiley.emoney.ViewModelClass.MyViewModel
import com.gabiley.emoney.Screens.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Nav(myViewModel: MyViewModel) {
    val context = LocalContext.current
    val navController = rememberNavController()

    //val scope = rememberCoroutineScope()
    val AccNum = StoreAccountNum(context)
    val SavedAccountNum = AccNum.getAccountNum.collectAsState(initial = "")
    val SavedUsername   = AccNum.getUsername.collectAsState(initial = "")
    val Result_Verfiy   = AccNum.getVerficationCode.collectAsState(initial = "");


    //val userNameViewModel: UsernameViewModel = viewModel()
    //val passwordViewModel: PasswordViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination =
        if(Result_Verfiy.value == "true")
            Screen.Home.route
        else
            Screen.LoginScreen.route
        /*
        if(SavedUsername.value!!.isEmpty() && Result_Verfiy.value!!.isEmpty())
           Screen.LoginScreen.route
        */

    ) {
        composable(route = Screen.Home.route) {
            ScreenA(navController, myViewModel)
        }
        composable(route = Screen.VerificationScreen.route) {
            VerifyScreen(navController)
        }
        composable(route = Screen.BankScreen.route) {
            bankScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.SettingScreen.route) {
            SettingScreen(navController)
        }
        composable(route = Screen.Signup.route) {
            SignupScreen(navController, myViewModel)
        }

    }

}

