package com.gabiley.dirso.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabiley.dirso.Screen
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {

    val drawerStateV = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope        = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerStateV,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Payment") },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.SubscriptionScreen.route)
                    }
                )
                // ...other drawer items
            }
        },
        //modifier = Modifier.background(Color.Red)
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        //Text(text = "Main")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                if(drawerStateV.isClosed)
                                    scope.launch {
                                        drawerStateV.open()
                                    }
                                else
                                    scope.launch {
                                        drawerStateV.close()
                                    }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                )
            },
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    //.background(Color.Cyan)
                    .padding(it)
                    //.padding(20.dp)
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Network Provider", fontSize = 33.sp)

                Spacer(modifier = Modifier.height(50.dp))

                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                    //.background(Color.Cyan)
                ) {
                    Card(
                        modifier = Modifier
                            .width(170.dp)
                            .height(90.dp)
                            ////.background(Color.White)
                            //.shadow(1.dp, shape = RoundedCornerShape(1.dp))
                            .clickable {
                                navController.navigate(Screen.Home.route)
                            },
                        RoundedCornerShape(7.dp),
                        //elevation = 5.dp
                        //RoundedCornerShape(7.dp),
                        //elevation = 7.dp
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Telesom"
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "Telesom",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .width(170.dp)
                            .height(90.dp)
                            .clickable {
                                navController.navigate(Screen.Customers.route)
                            },
                        RoundedCornerShape(7.dp),
                        //elevation = 5.dp
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Somtel"
                            )

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "Somtel",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } // END Column
            }
        }
    }







}