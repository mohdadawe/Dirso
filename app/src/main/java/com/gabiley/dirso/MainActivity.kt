package com.gabiley.dirso

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.gabiley.dirso.Drawer.AppBar
import com.gabiley.dirso.Drawer.DrawerBody
import com.gabiley.dirso.Drawer.DrawerHeader
import com.gabiley.dirso.Drawer.MenuItem
import com.gabiley.dirso.Navigation.Nav
import com.gabiley.dirso.ViewModelClass.MyViewModel
import com.gabiley.dirso.ui.theme.EMoneyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMateria@AndroidEntryPointlScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EMoneyTheme {
                //val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    //scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    //scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    //drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    /*
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
                    */

                ) {
                    Nav(myViewModel = MyViewModel())
                }
            }
        }
    }
}

