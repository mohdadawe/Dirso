package com.gabiley.dirso.Screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gabiley.dirso.DataStore.StoreAccountNum
import com.gabiley.dirso.Screen
import com.gabiley.dirso.connection.ConnectionState
import com.gabiley.dirso.connection.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SubscriptionScreen(navController: NavController) {
    val options = listOf(
        "Iibso 3 Bilood",
        "Iibso 6 Bilood",
        "Iibso 1 Sano"
    )
    val context = LocalContext.current
    val loading = remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }

    val scope  = rememberCoroutineScope()
    val person = StoreAccountNum(context)

    val subscriptionFee = person.getSubscriptionFee.collectAsState(initial = "")

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted
        }
        else {
            // Permission Denied
        }
    }


    if(loading.value) {
        AlertDialog(
            onDismissRequest = { /* openDialog.value = false */ },
            title = {
                Text("Furasho Account")
            },
            text = {
                if(selectedOption == options[0]){
                    Text(
                        "Ma Hubta Inaad Furato Account Shaqeenaya Muddo (3 Bilood) oo Qiimihiisu yahay SLSH ${subscriptionFee.value}",
                        fontSize = 19.sp
                    )
                }
                else if(selectedOption == options[1]){
                    Text(
                        "Ma Hubta Inaad Furato Account Shaqeenaya Muddo (6 Bilood) oo Qiimihiisu yahay SLSH ${subscriptionFee.value}",
                        fontSize = 19.sp
                    )
                }
                else if(selectedOption == options[2]){
                    Text(
                        "Ma Hubta Inaad Furato Account Shaqeenaya Muddo (1 Sano) oo Qiimihiisu yahay SLSH ${subscriptionFee.value}",
                        fontSize = 19.sp
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    loading.value = false

                    when (PackageManager.PERMISSION_GRANTED) {
                        //Check permission
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) -> {

                            // Instantiate the RequestQueue.
                            val queue = Volley.newRequestQueue(context)
                            val url = "https://xisaabso.online/Exchange"

                            // Request a string response from the provided URL.
                            val stringRequest = StringRequest(
                                Request.Method.GET, url,
                                { response ->

                                    val somDataMerchant = 463434

                                    if(selectedOption == options[0]){
                                        val money = 3
                                        val sendingMoney = "" + response.toInt() * money
                                        //val total = myViewModel.mobile.toInt()
                                        val dialedNum = "*223*$somDataMerchant*$sendingMoney%23"
                                        val callIntent = Intent(Intent.ACTION_CALL)
                                        callIntent.data = Uri.parse("tel:$dialedNum")
                                        ContextCompat.startActivity(context, callIntent, null)

                                        scope.launch {
                                            person.saveSubscriptionFee(sendingMoney)
                                        }

                                    }
                                    else if(selectedOption == options[1]){
                                        //val num   = "463434".toInt()
                                        val money = 6
                                        val sendingMoney = "" + response.toInt() * money
                                        //val total = myViewModel.mobile.toInt()
                                        val dialedNum = "*223*$somDataMerchant*$sendingMoney%23"

                                        val callIntent = Intent(Intent.ACTION_CALL)
                                        callIntent.data = Uri.parse("tel:$dialedNum")
                                        ContextCompat.startActivity(context, callIntent, null)

                                        scope.launch {
                                            person.saveSubscriptionFee(sendingMoney)
                                        }
                                    }
                                    else if(selectedOption == options[2]){
                                        //val num   = "463434".toInt()
                                        val money = 9
                                        val sendingMoney = "" + response.toInt() * money
                                        //val total = myViewModel.mobile.toInt()
                                        val dialedNum = "*223*$somDataMerchant*$sendingMoney%23"

                                        val callIntent = Intent(Intent.ACTION_CALL)
                                        callIntent.data = Uri.parse("tel:$dialedNum")
                                        ContextCompat.startActivity(context, callIntent, null)

                                        scope.launch {
                                            person.saveSubscriptionFee(sendingMoney)
                                        }
                                    }

                                    //Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                                    // Display the first 500 characters of the response string.
                                    //textView.text = "Response is: ${response.substring(0, 500)}"
                                },
                                { /*textView.text = "That didn't work!"*/ })

                            // Add the request to the RequestQueue.
                            queue.add(stringRequest)
                        }
                        else -> {
                            // Asking for permission
                            launcher.launch(Manifest.permission.CALL_PHONE)
                        }
                    }

                }) {
                    Text(text = "Haa")
                }
            },
            dismissButton = {
                Button(onClick = {
                    loading.value = false
                }) {
                    Text(text = "May")
                }
            }
        )
    }



    if (isConnected) {
        Column(modifier = Modifier
            //.background(Color.Magenta)
            .fillMaxSize()
            .padding(30.dp)
        ) {
            Text(
                text = "Payment Screen",
                fontSize = 33.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                //.background(Color.Red)
            )

            Spacer(modifier = Modifier.height(40.dp))

            options.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        //.background(Color.Green)
                        .selectable(
                            selected = selectedOption == it,
                            onClick = {
                                selectedOption = it
                                //Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                loading.value = true

                                if(selectedOption == options[0]){
                                    // Instantiate the RequestQueue.
                                    val queue = Volley.newRequestQueue(context)
                                    val url = "https://xisaabso.online/Exchange"

                                    // Request a string response from the provided URL.
                                    val stringRequest = StringRequest(
                                        Request.Method.GET, url,
                                        { response ->
                                            val money = 3
                                            val sendingMoney = "" + response.toInt() * money

                                            scope.launch {
                                                person.saveSubscriptionFee(sendingMoney)
                                            }

                                            //Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                                            // Display the first 500 characters of the response string.
                                            //textView.text = "Response is: ${response.substring(0, 500)}"
                                        },
                                        { /*textView.text = "That didn't work!"*/ })

                                    // Add the request to the RequestQueue.
                                    queue.add(stringRequest)


                                }
                                else if(selectedOption == options[1]){
                                    // Instantiate the RequestQueue.
                                    val queue = Volley.newRequestQueue(context)
                                    val url = "https://xisaabso.online/Exchange"

                                    // Request a string response from the provided URL.
                                    val stringRequest = StringRequest(
                                        Request.Method.GET, url,
                                        { response ->
                                            val money = 6
                                            val sendingMoney = "" + response.toInt() * money

                                            scope.launch {
                                                person.saveSubscriptionFee(sendingMoney)
                                            }

                                            //Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                                            // Display the first 500 characters of the response string.
                                            //textView.text = "Response is: ${response.substring(0, 500)}"
                                        },
                                        { /*textView.text = "That didn't work!"*/ })

                                    // Add the request to the RequestQueue.
                                    queue.add(stringRequest)


                                }
                                else if(selectedOption == options[2]){
                                    // Instantiate the RequestQueue.
                                    val queue = Volley.newRequestQueue(context)
                                    val url = "https://xisaabso.online/Exchange"

                                    // Request a string response from the provided URL.
                                    val stringRequest = StringRequest(
                                        Request.Method.GET, url,
                                        { response ->
                                            val money = 9
                                            val sendingMoney = "" + response.toInt() * money

                                            scope.launch {
                                                person.saveSubscriptionFee(sendingMoney)
                                            }

                                            //Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                                            // Display the first 500 characters of the response string.
                                            //textView.text = "Response is: ${response.substring(0, 500)}"
                                        },
                                        { /*textView.text = "That didn't work!"*/ })

                                    // Add the request to the RequestQueue.
                                    queue.add(stringRequest)


                                }
                            }
                        )
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        modifier = Modifier.scale(1.2f),
                        selected = selectedOption == it,
                        onClick = null
                    )
                    Text(
                        text = it,
                        fontSize = 24.sp,
                        modifier = Modifier
                            //.background(Color.DarkGray)
                            .padding(start = 10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.MainScreen.route)
                }) {
                Text(text = "Go To", fontSize = 22.sp)
            }

        }
    }
    else {
    // Show UI for No Internet Connectivity
    //Toast.makeText(context, "Not connected", Toast.LENGTH_SHORT).show()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Not Connected")
        }
    }
}



