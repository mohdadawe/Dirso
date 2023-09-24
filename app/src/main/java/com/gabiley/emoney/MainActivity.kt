package com.gabiley.emoney

import android.annotation.SuppressLint
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.gabiley.emoney.Drawer.AppBar
import com.gabiley.emoney.Drawer.DrawerBody
import com.gabiley.emoney.Drawer.DrawerHeader
import com.gabiley.emoney.Drawer.MenuItem
import com.gabiley.emoney.Navigation.Nav
import com.gabiley.emoney.ViewModelClass.MyViewModel
import com.gabiley.emoney.ui.theme.EMoneyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    /*
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private var isREAD_PHONE_NUMBERS_PermissionGranted = false
    private var is_CALL_PHONE_PermissionGranted        = false
    private var is_READ_PHONE_STATE_PermissionGranted  = false

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestPermission() {
        is_CALL_PHONE_PermissionGranted = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        is_READ_PHONE_STATE_PermissionGranted = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED

        isREAD_PHONE_NUMBERS_PermissionGranted = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.READ_PHONE_NUMBERS
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest: MutableList<String> = ArrayList()

        if(!is_CALL_PHONE_PermissionGranted) {
            permissionRequest.add(android.Manifest.permission.CALL_PHONE)
        }

        if(!isREAD_PHONE_NUMBERS_PermissionGranted) {
            permissionRequest.add(android.Manifest.permission.READ_PHONE_NUMBERS)
        }

        if(!is_READ_PHONE_STATE_PermissionGranted) {
            permissionRequest.add(android.Manifest.permission.MODIFY_PHONE_STATE)
        }

        if(permissionRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }
    */

    //@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    //@RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            isREAD_PHONE_NUMBERS_PermissionGranted = permissions[android.Manifest.permission.READ_PHONE_NUMBERS] ?: isREAD_PHONE_NUMBERS_PermissionGranted
            is_CALL_PHONE_PermissionGranted        = permissions[android.Manifest.permission.CALL_PHONE] ?: is_CALL_PHONE_PermissionGranted
            is_READ_PHONE_STATE_PermissionGranted  = permissions[android.Manifest.permission.READ_PHONE_STATE] ?: is_READ_PHONE_STATE_PermissionGranted

            // Create obj of TelephonyManager and ask for current telephone service
            val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
            val phoneNumber = telephonyManager.line1Number

            Log.d("welcome", phoneNumber.toString())

            //phone_number!!.text = phoneNumber

            Toast.makeText(applicationContext, phoneNumber, Toast.LENGTH_LONG).show()
        }

        requestPermission()
        */
        setContent {
            EMoneyTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {
                                println("Clicked on ${it.title}")
                            }
                        )
                    }
                ) {
                    Nav(myViewModel = MyViewModel())
                }
            }
        }
    }
}

