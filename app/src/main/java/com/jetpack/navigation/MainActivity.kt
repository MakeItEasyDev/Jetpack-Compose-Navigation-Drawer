package com.jetpack.navigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpack.navigation.home.Drawer
import com.jetpack.navigation.home.HomeScreen
import com.jetpack.navigation.navigation.Screen
import com.jetpack.navigation.ui.theme.NavigationDrawerTheme
import com.jetpack.navigation.ui.theme.Purple500
import com.jetpack.navigation.utils.Constant
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerTheme {
                NavigationDrawer()
            }
        }
    }
}

@Composable
fun NavigationDrawer() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    var topBar: @Composable () -> Unit = {
        TopAppBar(
            title = {
                Text(text = "Make It Easy")
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                    Icon(Icons.Filled.Menu, contentDescription = "")
                }
            },
            actions = {
                IconButton(onClick = {
                    Toast.makeText(context, "Clicked the menu", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "")
                }
            },
            backgroundColor = Purple500,
            elevation = AppBarDefaults.TopAppBarElevation
        )
    }

    val drawer: @Composable () -> Unit = {
        Drawer { title, route ->
            scope.launch {
                scaffoldState.drawerState.close()
            }
            Constant.title = title

            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()

            navController.navigate(route = route)
        }
    }

    Scaffold(
        topBar = {
            topBar()
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            drawer()
        },
        drawerGesturesEnabled = true
    ) { innerPadding ->
        NavigationHost(navController = navController)
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.HomeScreen.route
    ) {
        composable(Screen.DrawerScreen.HomeScreen.route) {
            HomeScreen(value = Constant.title)
        }
    }
}



















