package com.stalib.passwordmanager.ui.screens.loginsScreen

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.stalib.passwordmanager.*
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.Screen
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.screens.logins.EditLoginsDetails
import com.stalib.passwordmanager.presentation.screens.logins.LoginsDetailsScreen
import com.stalib.passwordmanager.presentation.screens.logins.NewLoginsScreen
import com.stalib.passwordmanager.presentation.screens.logins.LoginsScreen


fun NavGraphBuilder.addLoginsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    navigation(
        route = Screen.LoginsScreenRoot.route,
        startDestination = LoginsScreen.AllLogins.route
    ) {

        addAllLoginsGraph(mainViewModel,scaffoldState, actions)
        addNewLoginsGraph(mainViewModel,scaffoldState, actions)
        addLoginsDetailsGraph(mainViewModel,scaffoldState, actions)
        addEditLoginsGraph(mainViewModel,scaffoldState, actions)

    }


}

private fun NavGraphBuilder.addAllLoginsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    composable(
        route = LoginsScreen.AllLogins.route
    ) {

        val currentRoute = it.destination.route

        if (currentRoute != null) {
            LoginsScreen(
                mainViewModel = mainViewModel,
                currentRoute = currentRoute,
                scaffoldState = scaffoldState,
                actions = actions
            )
        }

    }
}

private fun NavGraphBuilder.addNewLoginsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = LoginsScreen.NewLoginsItem.route

    ) {

        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            NewLoginsScreen(
                mainViewModel = mainViewModel,
                scaffoldState = scaffoldState,
                actions = actions
            )
        }


    }
}


private fun NavGraphBuilder.addLoginsDetailsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = LoginsScreen.LoginsDetails.route,
        arguments = listOf(
            navArgument("itemId"){type = NavType.IntType}
        )
    ) {backStackEntry->

        val itemId = backStackEntry.arguments?.getInt("itemId")


            LoginsDetailsScreen(
                mainViewModel = mainViewModel,
                scaffoldState = scaffoldState,
                actions = actions,
                itemId = itemId ?: 0
            )

    }
}

private fun NavGraphBuilder.addEditLoginsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {
    composable(
        route = LoginsScreen.EditLoginsDetails.route,
        arguments = listOf(
            navArgument("itemId"){
                type = NavType.IntType
            }
        )
    ) {


        val itemId = it.arguments?.getInt("itemId")

        if (itemId != null) {
            EditLoginsDetails(
                mainViewModel = mainViewModel,
                scaffoldState = scaffoldState,
                actions = actions,
                itemId = itemId,
            )
        }


    }
}




