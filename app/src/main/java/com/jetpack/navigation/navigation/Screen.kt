package com.jetpack.navigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String) {

    sealed class DrawerScreen(route: String, title: String, val icon: ImageVector
    ): Screen(route, title) {
        object HomeScreen: DrawerScreen("HomeScreen", "Home", Icons.Filled.Home)
        object MakeItEasyOne: DrawerScreen("HomeScreen", "Make It Easy One", Icons.Filled.Favorite)
        object MakeItEasyTwo: DrawerScreen("HomeScreen", "Make It Easy Two", Icons.Filled.Favorite)
        object MakeItEasyThree: DrawerScreen("HomeScreen", "Make It Easy Three", Icons.Filled.Favorite)
    }
}

val screenFromDrawer = listOf(
    Screen.DrawerScreen.HomeScreen,
    Screen.DrawerScreen.MakeItEasyOne,
    Screen.DrawerScreen.MakeItEasyTwo,
    Screen.DrawerScreen.MakeItEasyThree
)