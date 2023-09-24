
package com.gabiley.emoney.Screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.gabiley.emoney.DataStore.StoreAccountNum



@Composable
fun bankScreen(navController: NavController) {
   Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top,
      modifier = Modifier
         .fillMaxSize()
      //.background(color = Color.White)
   ) {
      val context = LocalContext.current
      val REQUEST_PHONE_CALL = 1

      val scope = rememberCoroutineScope()
      // we instantiate the saveEmail class
      val AccNum = StoreAccountNum(context)
      val SavedAccountNum = AccNum.getAccountNum.collectAsState(initial = "")


      var money by remember{
         mutableStateOf("")
      }

      var AccountNum by remember {
         mutableStateOf("")
      }

      Spacer(modifier = Modifier.padding(20.dp))

      //Spacer(modifier = Modifier.height(40.dp))
      Text(text = "Darasalaam Bank", fontSize = 43.sp)

      Spacer(modifier = Modifier.padding(20.dp))

      ////////////////////////////////////////////////
      ////////////////////////////////////////////////

      OutlinedTextField(
         textStyle = TextStyle(fontSize = 24.sp),
         keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
         ),
         value = money,
         onValueChange = { money = it},
         label = { Text("Enter Phone") },
         maxLines = 1
      )

      Spacer(modifier = Modifier.padding(20.dp))

      ////////////////////////////////////////////////
      ////////////////////////////////////////////////

      Button(onClick = {
         if(ActivityCompat.checkSelfPermission(
               context,
               Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
               context as Activity,
               arrayOf(Manifest.permission.CALL_PHONE),
               REQUEST_PHONE_CALL
            )
         }
         else {
            //navController.navigate(Screen.BankScreen.route)
            Toast.makeText(context, money, Toast.LENGTH_SHORT).show()
            val dialedNum = "*889*${SavedAccountNum.value}*${money}%23"

            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + dialedNum)
            ContextCompat.startActivity(context, callIntent, null)
            //Toast.makeText(context, Total.toString(), Toast.LENGTH_SHORT).show()
         }

      }) {
         Text("Dir", fontSize = 23.sp)
      }

      Spacer(modifier = Modifier.padding(20.dp))



   }
}

