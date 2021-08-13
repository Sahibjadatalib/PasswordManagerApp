package com.example.passwordmanager.ui.screens.loginsScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.NewLoginsScreen
import com.example.passwordmanager.ui.screens.home.LoginsScreen


fun NavGraphBuilder.addLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    navigation(
        route = Screen.LoginsScreenRoot.route,
        startDestination = LeafScreen.AllLogins.route
    ) {
        addAllLoginsGraph(mainViewModel, navController)
        addNewLoginsGraph(mainViewModel, navController)
        addIconsLibraryGraph(mainViewModel, navController)
    }


}

private fun NavGraphBuilder.addAllLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    composable(
        route = LeafScreen.AllLogins.route
    ) {

        LoginsScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}

private fun NavGraphBuilder.addNewLoginsGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LeafScreen.NewLoginsItem.route
    ) {

        val navigateToAllLogins: () -> Unit = {
            navController.navigate(LeafScreen.AllLogins.route)
        }

        val navigateToIconsLibrary: () -> Unit = {
            navController.navigate(LeafScreen.LoginsIconsLibrary.route)
        }

        NewLoginsScreen(
            mainViewModel = mainViewModel,
            navController = navController,
            navigateToAllLogins = navigateToAllLogins,
            navigateToIconsLibrary = navigateToIconsLibrary
        )


    }
}


private fun NavGraphBuilder.addIconsLibraryGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LeafScreen.LoginsIconsLibrary.route
    ) {

        LoginsIconsLibrary(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}

