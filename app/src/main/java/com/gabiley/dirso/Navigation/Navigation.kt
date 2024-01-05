package com.gabiley.dirso.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabiley.dirso.DataStore.StoreAccountNum
import com.gabiley.dirso.Screen
import com.gabiley.dirso.ViewModelClass.MyViewModel
import com.gabiley.dirso.Screens.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Nav(myViewModel: MyViewModel) {
    val context = LocalContext.current
    val navController = rememberNavController()

    //val scope = rememberCoroutineScope()
    val AccNum          = StoreAccountNum(context)
    val SavedAccountNum = AccNum.getAccountNum.collectAsState(initial = "")
    val SavedUsername   = AccNum.getUsername.collectAsState(initial = "")
    val Result_Verfiy   = AccNum.getVerficationCode.collectAsState(initial = "");


    //val userNameViewModel: UsernameViewModel = viewModel()
    //val passwordViewModel: PasswordViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination =
        if(Result_Verfiy.value == "true")
            Screen.MainScreen.route
            //Screen.SubscriptionScreen.route
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
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }

        composable(route = Screen.Customers.route) {
            Customers(navController)
        }

        composable(route = Screen.AddCustomer.route) {
            AddCustomer_with_WithTopBar(navController)
        }

        composable(route = Screen.SubscriptionScreen.route) {
            SubscriptionScreen(navController)
        }




    }

}

