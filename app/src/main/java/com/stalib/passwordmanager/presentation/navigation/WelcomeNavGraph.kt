package com.stalib.passwordmanager.presentation.navigation

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.stalib.passwordmanager.Screen
import com.stalib.passwordmanager.WelcomeScreen
import com.stalib.passwordmanager.presentation.screens.welcome.SignInScreen
import com.stalib.passwordmanager.presentation.screens.welcome.SignUpScreen
import com.stalib.passwordmanager.MainViewModel

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



