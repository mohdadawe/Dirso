package com.gabiley.dirso.Screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.dirso.Notification.Notfication
import com.gabiley.dirso.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomer_with_WithTopBar(navController: NavController) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Customer")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Customers.route) {
                                popUpTo(Screen.Customers.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                //backgroundColor = Color.Transparent,
                //elevation = 0.dp
            )
        },
        sheetContent = {},
    ) {
        AddCustomer(navController)
    }

}



@Composable
fun AddCustomer(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.Red)
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Add Customer", fontSize = 27.sp)

        Spacer(modifier = Modifier.height(10.dp))

        val context    = LocalContext.current
        val firstName  = rememberSaveable { mutableStateOf("") }
        val secondName = rememberSaveable { mutableStateOf("") }
        val phoneNum   = rememberSaveable { mutableStateOf("") }

        val maxLengthNumber = 9
        val maxLengthText   = 20

        val patternText   = remember { Regex("[a-z]*") }
        val patternNumber = remember { Regex("[0-9]*") }
        val openDialog    = remember { mutableStateOf(false) }

        OutlinedTextField(
            textStyle = TextStyle(fontSize = 24.sp),
            value = firstName.value,
            onValueChange = {
                if (it.length <= maxLengthText) {
                    if (it.matches(patternText)) {
                        firstName.value = it
                    }
                }
            },
            label = { Text("Enter Your username") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            textStyle = TextStyle(fontSize = 24.sp),
            value = secondName.value,
            onValueChange = {
                if (it.length <= maxLengthText) {
                    if (it.matches(patternText)) {
                        secondName.value = it
                    }
                }
            },
            label = { Text("Enter Your username") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            textStyle = TextStyle(fontSize = 24.sp),
            value = phoneNum.value,
            onValueChange = {
                if (it.length <= maxLengthNumber) {
                    if (it.matches(patternNumber)) {
                        phoneNum.value = it
                    }
                }
            },
            label = { Text("Enter Your Phone") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        if(openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    //openDialog.value = false
                },
                title = {
                    Text("Hubin Diwaan Galin")
                },
                text = {
                    Text("Ma Hubta Inaad Diwaan Galisid: ${firstName.value} ${secondName.value}")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            //Toast.makeText(context, "${firstName.value} ${secondName.value} ${phoneNum.value}", Toast.LENGTH_LONG).show()
                            val queue = Volley.newRequestQueue(context)
                            val url = "https://xisaabso.online/addCustomer_xisaabso"

                            // Request a string response from the provided URL.
                            val sr: StringRequest = @RequiresApi(Build.VERSION_CODES.O)
                            object : StringRequest(
                                Method.POST, url,
                                Response.Listener {
                                    //Toast.makeText(context, it, Toast.LENGTH_LONG).show()

                                    when (it) {
                                        "not Registered" -> {
                                            Toast.makeText(context, "Maad Diwaan galin", Toast.LENGTH_LONG).show()
                                        }
                                        "successful Registered" -> {
                                            openDialog.value = false
                                            //delay(500)
                                            //navController.navigate(Screen.Customers_Screen.route)

                                            navController.navigate(Screen.Customers.route) {
                                                popUpTo(Screen.Customers.route) {
                                                    inclusive = true
                                                }
                                                val noti = Notfication(
                                                    context,
                                                    "Diwaan Galin Cusub",
                                                    "Waxad Diwaan Galisay: ${firstName.value} ${secondName.value}"
                                                )
                                                noti.firNotification()
                                            }


                                            /*
                                            scope.launch {
                                                person.saveMoneyValue("")
                                                person.save_KaJar("false")
                                            }
                                            */
                                        }
                                    }
                                },
                                Response.ErrorListener {
                                    //your error
                                }) {
                                override fun getParams(): Map<String, String> {
                                    val params: MutableMap<String, String> = HashMap()

                                    params["FirstName"]  = firstName.value
                                    params["SecondName"] = secondName.value
                                    params["PhoneNum"]   = phoneNum.value
                                    return params

                                }
                                //@Throws(AuthFailureError::class)
                                //override fun getHeaders(): Map<String, String> {
                                //val params: MutableMap<String, String> = HashMap()
                                //params["Content-Type"] = "application/x-www-form-urlencoded"
                                //return params
                                //}
                            }
                            // Add the request to the RequestQueue.
                            queue.add(sr)


                        }
                    ) {
                        Text("Confirm Button")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false

                            //scope.launch {
                            //person.save_KaJar("false")
                            //}

                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }


        Button(onClick = {
            if(firstName.value.isBlank()) {
                Toast.makeText(context, "firstName Field is empty", Toast.LENGTH_SHORT).show()
            }
            else if(secondName.value.isBlank()) {
                Toast.makeText(context, "secondName Field is empty", Toast.LENGTH_SHORT).show()
            }

            else if(phoneNum.value.isBlank()) {
                Toast.makeText(context, "phoneNum Field is empty", Toast.LENGTH_SHORT).show()
            }

            else if(phoneNum.value.length < 9) {
                Toast.makeText(context, "phoneNum Field length must not less than 9 characters", Toast.LENGTH_SHORT).show()
            }

            else {
                openDialog.value = true
            }
        }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = "Diwaan Gali",
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }

}

