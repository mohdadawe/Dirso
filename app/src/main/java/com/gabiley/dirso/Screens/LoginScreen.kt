
package com.gabiley.dirso.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.dirso.DataStore.StoreAccountNum
import com.gabiley.dirso.Screen
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {
   Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Top,
      modifier = Modifier
         .fillMaxSize()
         .padding(30.dp)
   ) {
      val context = LocalContext.current
      var username by rememberSaveable {
         mutableStateOf("")
      }
      val maxLength = 9
      //val SharedSession = loginViewModel.person

      val scope = rememberCoroutineScope()
      // we instantiate the saveEmail class
      val person = StoreAccountNum(context)

      val Result_Username = person.getUsername.collectAsState(initial = "")

      //Spacer(modifier = Modifier.height(70.dp))

      /*
      if (Result_Username.value?.isEmpty() == true) {
         Text(
            text = "Empty",
            fontSize = 33.sp
         )
      }
      */

      if (Result_Username.value?.isEmpty() == false) {
         Text(
            text = Result_Username.value.toString(),
            fontSize = 33.sp
         )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text(text = "Login", fontSize = 33.sp)

      Spacer(modifier = Modifier.height(60.dp))

      val pattern = remember { Regex("[0-9]*") }

      OutlinedTextField(
         textStyle = TextStyle(fontSize = 24.sp),
         value = username,
         onValueChange = {
            //username = it
            if (it.length <= maxLength) {
               if (it.matches(pattern)) {
                  username = it
               }
            }
         },
         label = { Text("Enter Your username") },
         maxLines = 1,
         keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
         )
      )

      Spacer(modifier = Modifier.height(40.dp))

      /////////////////////////////////////////////////////
      /////////////////////////////////////////////////////
      /////////////////////////////////////////////////////

      //CircularProgressIndicator()

      Button(modifier = Modifier.fillMaxWidth(),
         onClick = {
         if (username.isNotBlank()) {
            //val mediaPlayer = MediaPlayer.create(context, R.raw.)
            //mediaPlayer.stat()

            val queue = Volley.newRequestQueue(context)
            val sr: StringRequest = object : StringRequest(Method.POST,
               "https://xisaabso.online/UsernameLoginEmoney",
               Response.Listener { response ->
                  Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()

                  if (response.toString() == "This username is not exist") {
                     Toast.makeText(context, "Ma Jiro Qofkani", Toast.LENGTH_LONG).show()
                  }
                  else if (response.toString() == "Your Account is Expired") {
                     Toast.makeText(context, "EXPIRED ACCOUNT", Toast.LENGTH_LONG).show()
                  }
                  else {
                     scope.launch {
                        person.saveUsername(username)
                     }
                     navController.navigate(Screen.VerificationScreen.route)
                  }
               },
               Response.ErrorListener {
                  //your error
               }) {
               override fun getParams(): Map<String, String> {
                  val params: MutableMap<String, String> = HashMap()
                  params["Username"] = username
                  return params
               }
               //@Throws(AuthFailureError::class)
               //override fun getHeaders(): Map<String, String> {
                  //val params: MutableMap<String, String> = HashMap()
                  //params["Content-Type"] = "application/x-www-form-urlencoded"
                  //return params
               //}
            }
            queue.add(sr)
         }
         else {
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
         }

      }) {
         Text(text = "Login", fontSize = 23.sp)
      }

      Spacer(modifier = Modifier.height(20.dp))

      Button(modifier = Modifier.fillMaxWidth(),
         onClick = {
         navController.navigate(Screen.Signup.route)
      }) {
         Text("Sign up", fontSize = 23.sp)
      }


   }
}

///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////







