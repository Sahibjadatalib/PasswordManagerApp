package com.example.passwordmanager.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.passwordmanager.CardsScreen
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.OthersScreen
import com.example.passwordmanager.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActions(
    navController: NavController,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {

    val showSnackBar: (String, String) -> Unit = { message, action ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message, action)
        }
    }

    val navigateToAllLogins: ()->Unit = {
        navController.navigate(LoginsScreen.AllLogins.route){
            popUpTo(LoginsScreen.AllLogins.route)
            launchSingleTop = true
        }
    }

    val navigateToAllCards: ()->Unit = {
        navController.navigate(CardsScreen.AllCards.route){
            popUpTo(LoginsScreen.AllLogins.route)
            launchSingleTop = true
        }
    }

    val navigateToAllOthers: ()->Unit = {
        navController.navigate(OthersScreen.AllOthers.route){
            popUpTo(LoginsScreen.AllLogins.route)
            launchSingleTop = true
        }
    }


    val navigateToLoginsDetails: (Int)->Unit = {itemId->
        navController.navigate(LoginsScreen.LoginsDetails.createRoute(itemId.toString())){
            popUpTo(LoginsScreen.AllLogins.route)
        }
    }

    val navigateToNewItem: ()->Unit = {
        navController.navigate(LoginsScreen.NewLoginsItem.route){
            popUpTo(LoginsScreen.AllLogins.route)
        }
    }

    val navigateToLoginsEdit: (Int)->Unit = {itemId->
        navController.navigate(LoginsScreen.EditLoginsDetails.createRoute(itemId.toString())){
            popUpTo(LoginsScreen.AllLogins.route)
        }
    }

    val navigateToSettings: ()->Unit = {
        navController.navigate(SettingsScreen.Settings.route){
            popUpTo(SettingsScreen.Settings.route){
                inclusive = true
            }
        }
    }

    val popUp: ()->Unit = {
        navController.navigateUp()
    }

}