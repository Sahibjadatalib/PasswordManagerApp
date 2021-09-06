package com.example.passwordmanager.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.passwordmanager.*
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

    val navigateToAllLoginsFromWelcome: ()->Unit = {
        navController.navigate(LoginsScreen.AllLogins.route){
            popUpTo(Screen.WelcomeScreenRoot.route){
                inclusive = true
            }
            launchSingleTop = true
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

    val navigateToNewLoginsItem: ()->Unit = {
        navController.navigate(LoginsScreen.NewLoginsItem.route){
            popUpTo(LoginsScreen.AllLogins.route)
        }
    }

    val navigateToLoginsEdit: (Int)->Unit = {itemId->
        navController.navigate(LoginsScreen.EditLoginsDetails.createRoute(itemId.toString())){
            popUpTo(LoginsScreen.AllLogins.route)
        }
    }


    //cards
    val navigateToCardsDetails: (Int)->Unit = {itemId->
        navController.navigate(CardsScreen.CardsDetails.createRoute(itemId.toString())){
            popUpTo(CardsScreen.AllCards.route)
        }
    }

    val navigateToNewCardsItem: ()->Unit = {
        navController.navigate(CardsScreen.NewCardsItem.route){
            popUpTo(CardsScreen.AllCards.route)
        }
    }

    val navigateToCardsEdit: (Int)->Unit = {itemId->
        navController.navigate(CardsScreen.EditCardsDetails.createRoute(itemId.toString())){
            popUpTo(CardsScreen.AllCards.route)
        }
    }

    //others
    val navigateToOthersDetails: (Int)->Unit = {itemId->
        navController.navigate(OthersScreen.OthersDetails.createRoute(itemId.toString())){
            popUpTo(OthersScreen.AllOthers.route)
        }
    }

    val navigateToNewOthersItem: ()->Unit = {
        navController.navigate(OthersScreen.NewOthersItem.route){
            popUpTo(OthersScreen.AllOthers.route)
        }
    }

    val navigateToOthersEdit: (Int)->Unit = {itemId->
        navController.navigate(OthersScreen.EditOthersDetails.createRoute(itemId.toString())){
            popUpTo(OthersScreen.AllOthers.route)
        }
    }

    val navigateToSettings: ()->Unit = {
        navController.navigate(SettingsScreen.Settings.route){
            popUpTo(SettingsScreen.Settings.route){
                inclusive = true
            }
        }
    }

    val navigateToSignUpScreen: () -> Unit = {
        navController.navigate(WelcomeScreen.SignUp.route) {
            popUpTo(Screen.WelcomeScreenRoot.route) {
                inclusive = true
            }
        }
    }

    val popUp: ()->Unit = {
        navController.navigateUp()
    }

}