package com.example.passwordmanager.ui.screens.othersScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.screens.NewLoginsScreen
import com.example.passwordmanager.ui.screens.home.LoginsScreen
import com.example.passwordmanager.ui.screens.home.OthersScreen
import com.example.passwordmanager.ui.screens.newItem.NewOthersScreen

fun NavGraphBuilder.addOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    navigation(
        route = Screen.OthersScreenRoot.route,
        startDestination = LeafScreen.AllOthers.route
    ) {
        addAllOthersGraph(mainViewModel, navController)
        addNewOthersGraph(mainViewModel, navController)
        addIconsLibraryGraph(mainViewModel, navController)
    }


}

private fun NavGraphBuilder.addAllOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {

    composable(
        route = LeafScreen.AllOthers.route
    ) {

        OthersScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}

private fun NavGraphBuilder.addNewOthersGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LeafScreen.NewOthersItem.route
    ) {

        NewOthersScreen(
            mainViewModel = mainViewModel,
            navController = navController
        )


    }
}


private fun NavGraphBuilder.addIconsLibraryGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(
        route = LeafScreen.OthersIconsLibrary.route
    ) {

//        IconLibraryScreen(
//            mainViewModel = mainViewModel,
//            navController = navController
//        )

    }
}
