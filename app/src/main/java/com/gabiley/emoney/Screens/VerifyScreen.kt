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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.emoney.DataStore.StoreAccountNum
import kotlinx.coroutines.launch


@Composable
fun VerifyScreen(navController: NavController) {
   Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier
         .fillMaxSize()
   ) {

      val context = LocalContext.current
      var verifyCode by remember {
         mutableStateOf("")
      }
      val maxLength = 10
      //val SharedSession = loginViewModel.person

      val scope = rememberCoroutineScope()
      // we instantiate the saveEmail class
      val person = StoreAccountNum(context)
      val Result_Username = person.getUsername.collectAsState(initial = "")
      val Result_Verfiy   = person.getVerficationCode.collectAsState(initial = "");
      //val Result_Expiry   = person.getExpiryAccount.collectAsState(initial = "")


      Spacer(modifier = Modifier.height(20.dp))

      Text(text = Result_Verfiy.value.toString(), fontSize = 33.sp)

      Text(text = "Verfication", fontSize = 33.sp)

      Spacer(modifier = Modifier.height(20.dp))

      OutlinedTextField(
         textStyle = TextStyle(fontSize = 24.sp),
         value = verifyCode,
         onValueChange = { verifyCode = it },
         label = { Text("Enter Your username") },
         maxLines = 1,
         keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
         )
      )

      Spacer(modifier = Modifier.height(20.dp))

      /////////////////////////////////////////////////////
      /////////////////////////////////////////////////////
      /////////////////////////////////////////////////////
      Button(onClick = {
         if (verifyCode.isNotBlank()) {
            val queue = Volley.newRequestQueue(context)
            val sr: StringRequest = object : StringRequest(Method.POST,
               "https://xisaabso.online/UsernameLoginEmoneyVerify",
               Response.Listener { response ->
                  //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()


                  if(response.toString() == "Login code is wrong") {
                     Toast.makeText(context, "incorrect login code", Toast.LENGTH_LONG).show()
                  }
                  else if(response.toString() == "Yes") {
                     Toast.makeText(context, "Masha Allah!!!!", Toast.LENGTH_LONG).show()

                     scope.launch {
                        person.saveVerficationCode("true")
                     }

                  }


               },
               Response.ErrorListener {
                  //your error
               }) {
               override fun getParams(): Map<String, String> {
                  val params: MutableMap<String, String> = HashMap()
                  params["Username"]   = Result_Username.value.toString()
                  params["Verify_URL"] = verifyCode
                  return params
               }
               /*
               @Throws(AuthFailureError::class)
               override fun getHeaders(): Map<String, String> {
                  val params: MutableMap<String, String> = HashMap()
                  params["Content-Type"] = "application/x-www-form-urlencoded"
                  return params
               }
               */
            }
            queue.add(sr)

         } else {
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
         }

      }) {
         Text(text = "Soo Saar", fontSize = 23.sp)
      }

      Spacer(modifier = Modifier.height(60.dp))
      //Text(text = Result_Username.toString(), fontSize = 33.sp)
      //Text(text = Result_AccountType.toString(), fontSize = 33.sp)

   }
}


