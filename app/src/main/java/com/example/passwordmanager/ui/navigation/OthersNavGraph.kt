package com.example.passwordmanager.ui.screens.othersScreen

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.example.passwordmanager.*
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.screens.home.OthersScreen
import com.example.passwordmanager.ui.screens.loginsScreen.EditLoginsDetails
import com.example.passwordmanager.ui.screens.loginsScreen.LoginsDetailsScreen
import com.example.passwordmanager.ui.screens.newItem.NewOthersScreen

fun NavGraphBuilder.addOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    navigation(
        route = Screen.OthersScreenRoot.route,
        startDestination = OthersScreen.AllOthers.route
    ) {
        addAllOthersGraph(mainViewModel,scaffoldState,actions)
        addNewOthersGraph(mainViewModel,scaffoldState,actions)
        addEditOthersGraph(mainViewModel,scaffoldState,actions)
        addOthersDetailsGraph(mainViewModel,scaffoldState,actions)
    }


}

private fun NavGraphBuilder.addAllOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    composable(
        route = OthersScreen.AllOthers.route
    ) {

        val currentRoute = it.destination.route

        if (currentRoute != null) {
            OthersScreen(
                mainViewModel = mainViewModel,
                currentRoute = currentRoute,
                scaffoldState = scaffoldState,
                actions = actions
            )
        }

    }
}

private fun NavGraphBuilder.addNewOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = OthersScreen.NewOthersItem.route
    ) {

        NewOthersScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions
        )


    }
}

private fun NavGraphBuilder.addOthersDetailsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = OthersScreen.OthersDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")

        OthersDetailsScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions,
            itemId = itemId ?: 0
        )

    }
}

private fun NavGraphBuilder.addEditOthersGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = OthersScreen.EditOthersDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditOthersDetails(
                mainViewModel = mainViewModel,
                scaffoldState = scaffoldState,
                actions = actions,
                itemId = itemId
            )
        }


    }
}



