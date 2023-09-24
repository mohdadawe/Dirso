package com.gabiley.emoney.Screens


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.gabiley.emoney.SealedClasses.Screen
import com.gabiley.emoney.ViewModelClass.MyViewModel


@Composable
fun SignupScreen(
    navController: NavController,
    myViewModel: MyViewModel
) {
    val maxLength = 9

    val context = LocalContext.current
    val REQUEST_PHONE_CALL = 1

    val scope = rememberCoroutineScope()
    val AccNum = StoreAccountNum(context)

    val SavedUsername = AccNum.getUsername.collectAsState(initial = "")
    val ExpiryAccount = AccNum.getExpiryAccount.collectAsState(initial ="")
    val Result_Verify = AccNum.getVerficationCode.collectAsState(initial = "")
    //Toast.makeText(context, ExpiryAccount.value, Toast.LENGTH_SHORT).show()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 0.dp)
        //.background(Color.Green)

    ) {
        Spacer(modifier = Modifier.height(40.dp))

        //Text(text = SavedUsername.value.toString(), fontSize = 33.sp)
        //Text(text = Result_Verify.value.toString(), fontSize = 33.sp)

        Text(text = "Sign Up", fontSize = 33.sp)

        Spacer(modifier = Modifier.height(40.dp))

        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            //.background(Color.LightGray)
        ) {

            val pattern = remember { Regex("[0-9]*") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 24.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                value = myViewModel.mobile,
                onValueChange = {
                    if(it.length <= maxLength) {
                        if(it.matches(pattern)) {
                            myViewModel.mobile = it
                        }

                    }
                },
                label = { Text("Gali Numberka") },
                maxLines = 1,
                shape = RoundedCornerShape(10.dp),
            )

        }


        Spacer(modifier = Modifier.height(30.dp))

        ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (myViewModel.mobile.isNotBlank()) {
                    //Toast.makeText(context, selectedMoney.value, Toast.LENGTH_SHORT).show()

                    /////////////////////////////////////////////////
                    /////////////////////////////////////////////////
                    val person = StoreAccountNum(context)

                    val queue = Volley.newRequestQueue(context)
                    val sr: StringRequest = object : StringRequest(Method.POST,
                        "https://adeega.xisaabso.online/Api/Emoney/Registration.php",
                        Response.Listener { response ->
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()

                            if (response.toString() == "This username is exist") {
                                Toast.makeText(
                                    context,
                                    "Horaa loo diwaan galiyey",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else if (response.toString() == "Not Registered") {
                                Toast.makeText(
                                    context,
                                    "Not Registered",
                                    Toast.LENGTH_LONG
                                ).show()
                                //scope.launch {
                                    //person.saveVerficationCode("false")
                                //}
                            }
                            else if (response.toString() == "Successfully Registered") {
                                Toast.makeText(
                                    context,
                                    "Successfully Registered",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate(Screen.LoginScreen.route){
                                    popUpTo(Screen.LoginScreen.route) {
                                        // Sida caadiga ah waa (true)
                                        inclusive = false
                                    }
                                }
                            }
                        },
                        Response.ErrorListener {
                            //your error
                        }) {
                        override fun getParams(): Map<String, String> {
                            val params: MutableMap<String, String> = HashMap()
                            params["Username"] = myViewModel.mobile
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

                    //Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()

                }
                else {
                    Toast.makeText(context, "Madhan yahay", Toast.LENGTH_SHORT).show()
                }

            }) {
            Text("Sign up", fontSize = 23.sp)
        }
    }
}
