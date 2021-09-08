package com.example.passwordmanager.presentation.navigation

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.passwordmanager.Screen
import com.example.passwordmanager.SettingsScreen
import com.example.passwordmanager.presentation.screens.settings.SettingsScreen
import com.example.passwordmanager.MainViewModel

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