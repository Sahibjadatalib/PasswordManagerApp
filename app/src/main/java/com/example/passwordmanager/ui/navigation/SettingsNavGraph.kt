package com.example.passwordmanager.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.Screen
import com.example.passwordmanager.SettingsScreen
import com.example.passwordmanager.WelcomeScreen
import com.example.passwordmanager.ui.screens.settings.SettingsScreen
import com.example.passwordmanager.ui.viewModel.MainViewModel

fun NavGraphBuilder.addSettingsGraph(
    mainViewModel: MainViewModel,
    navController: NavController,
    scaffoldState: ScaffoldState,
    actions: MainActions
){

    navigation(
        route = Screen.SettingsScreenRoot.route,
        startDestination = SettingsScreen.Settings.route
    ){

        addSettings(mainViewModel, navController)

    }

}

private fun NavGraphBuilder.addSettings(
    mainViewModel: MainViewModel,
    navController: NavController
){

    composable(
        route = SettingsScreen.Settings.route,
    ){

        val navigateToSignUpScreen: ()->Unit = {
            navController.navigate(WelcomeScreen.SignUp.route){
                popUpTo(Screen.LoginsScreenRoot.route){
                    inclusive = true
                }
            }
        }

        val popUp: ()->Unit = {
            navController.navigateUp()
        }

        SettingsScreen(
            navigateToSignUpScreen = navigateToSignUpScreen,
            popUp = popUp
        )

    }

}