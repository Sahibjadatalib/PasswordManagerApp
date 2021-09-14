package com.stalib.passwordmanager.presentation.navigation

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.stalib.passwordmanager.Screen
import com.stalib.passwordmanager.SettingsScreen
import com.stalib.passwordmanager.presentation.screens.settings.SettingsScreen
import com.stalib.passwordmanager.MainViewModel

fun NavGraphBuilder.addSettingsGraph(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
){

    navigation(
        route = Screen.SettingsScreenRoot.route,
        startDestination = SettingsScreen.Settings.route
    ){

        addSettings(mainViewModel,scaffoldState,actions)

    }

}

private fun NavGraphBuilder.addSettings(
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
){

    composable(
        route = SettingsScreen.Settings.route,
    ){

        SettingsScreen(
            mainViewModel = mainViewModel,
            scaffoldState = scaffoldState,
            actions = actions
        )

    }

}