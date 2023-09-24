package com.gabiley.emoney.SealedClasses


sealed class Screen(val route: String) {
   object DashBoard: Screen(route = "DashBoard")
   object Home: Screen(route = "Home")
   object LastVouchers_Screen: Screen(route = "Last_Vouchers_Screen")

   object BankScreen: Screen(route = "Bank_Screen")
   object LoginScreen: Screen(route = "Login_Screen")

   object SettingScreen: Screen(route = "Setting_Screen")
   object VerificationScreen: Screen(route = "Verification_Screen")
   object Signup: Screen(route = "Signup")

}

