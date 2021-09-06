package com.example.passwordmanager.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.Screen
import com.example.passwordmanager.WelcomeScreen
import com.example.passwordmanager.ui.screens.welcomeScreen.SignInScreen
import com.example.passwordmanager.ui.screens.welcomeScreen.SignUpScreen
import com.example.passwordmanager.ui.viewModel.MainViewModel

fun NavGraphBuilder.addWelcomeGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {


    navigation(
        route = Screen.WelcomeScreenRoot.route,
        startDestination = WelcomeScreen.SignIn.route
    ) {

        addSignUpGraph(mainViewModel,scaffoldState,actions)
        addSignInGraph(mainViewModel,scaffoldState,actions)

    }

}

private fun NavGraphBuilder.addSignUpGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    composable(
        route = WelcomeScreen.SignUp.route
    ) {

        SignUpScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions
        )
    }
}

private fun NavGraphBuilder.addSignInGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    composable(
        route = WelcomeScreen.SignIn.route
    ) {

        SignInScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions,
        )


    }
}



