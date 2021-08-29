package com.example.passwordmanager.ui.navigation

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
    navController: NavController
) {

    navigation(
        route = Screen.WelcomeScreenRoot.route,
        startDestination = WelcomeScreen.SignIn.route
    ) {

        addSignUpGraph(mainViewModel, navController)
        addSignInGraph(mainViewModel, navController)

    }

}

private fun NavGraphBuilder.addSignUpGraph(
    mainViewModel: MainViewModel,
    navController: NavController
) {

    composable(
        route = WelcomeScreen.SignUp.route
    ) {

        val navigateToLoginsScreen: () -> Unit = {
            navController.navigate(Screen.LoginsScreenRoot.route) {
                popUpTo(Screen.WelcomeScreenRoot.route) {
                    inclusive = true
                }
            }
        }

        SignUpScreen(
            navigateToLoginsScreen = navigateToLoginsScreen
        )
    }
}

private fun NavGraphBuilder.addSignInGraph(
    mainViewModel: MainViewModel,
    navController: NavController
) {

    composable(
        route = WelcomeScreen.SignIn.route
    ) {

        val navigateToLoginsScreen: () -> Unit = {
            navController.navigate(Screen.LoginsScreenRoot.route) {
                popUpTo(Screen.WelcomeScreenRoot.route) {
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

        SignInScreen(
            navigateToLoginsScreen = navigateToLoginsScreen,
            navigateToSignUpScreen = navigateToSignUpScreen
        )


    }
}



