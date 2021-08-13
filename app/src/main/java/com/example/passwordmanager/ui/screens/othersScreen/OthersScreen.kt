package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.Screen
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.screens.othersScreen.OthersViewModel

@Composable
fun OthersScreen(
    viewModel: OthersViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController
) {

    mainViewModel.setColorForStatusBar(MaterialTheme.colors.primaryVariant)


    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = LeafScreen.AllOthers.label,
                onMenuIconClick = {},
                onSortIconClick = {},
                onSearchIconClick = {}
            )
        },
        floatingActionButton = {
            MyFloatingBtn(
                navController = navController,
                onClick = {navController.navigate(LeafScreen.NewOthersItem.route)}            )
        },
        drawerContent = {
            //MyDrawer()
        },
        bottomBar = {
            MyBottomBar(
                navController = navController
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(text = "others screen")

        }
    }


}