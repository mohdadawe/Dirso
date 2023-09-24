package com.gabiley.emoney.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabiley.emoney.DataStore.StoreAccountNum
import com.gabiley.emoney.SealedClasses.Screen
import kotlinx.coroutines.launch



@Composable
fun SettingScreen(navController: NavController) {
   Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top,
      modifier = Modifier
         .fillMaxSize()
      //.background(color = Color.White)
   ) {
      val context = LocalContext.current

      val scope = rememberCoroutineScope()
      val AccNum = StoreAccountNum(context)
      val SavedAccountNum = AccNum.getAccountNum.collectAsState(initial = "")

      var AccountNum by remember {
         mutableStateOf("")
      }


      Spacer(modifier = Modifier.padding(20.dp))

      //Spacer(modifier = Modifier.height(40.dp))
      Text(text = "Settings", fontSize = 43.sp)

      OutlinedTextField(
         textStyle = TextStyle(fontSize = 24.sp),
         keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
         ),
         value = AccountNum,
         onValueChange = { AccountNum = it},
         label = { Text("Enter Account Num") },
         maxLines = 1
      )

      ////////////////////////////////////////////////
      ////////////////////////////////////////////////

      Button(onClick = {
         //if(AccountNum.isNotBlank()) {
            scope.launch {
               AccNum.saveAccountNum(AccountNum)

               navController.navigate(Screen.Home.route) {
                  popUpTo(Screen.Home.route) {
                     // Sida caadiga ah waa (true)
                     inclusive = true
                  }
               }
            }
            Toast.makeText(
               context,
               "Khaanada Account-ku wuu madhan yahay",
               Toast.LENGTH_SHORT
            ).show()
      }) {
         Text("Save", fontSize = 23.sp)
      }

      Spacer(modifier = Modifier.padding(20.dp))

      Text(
         text = "Account Num: ${AccountNum}",
         fontSize = 25.sp
      )

      Text(
         text = SavedAccountNum.value.toString(),
         fontSize = 33.sp
      )

   }
}

