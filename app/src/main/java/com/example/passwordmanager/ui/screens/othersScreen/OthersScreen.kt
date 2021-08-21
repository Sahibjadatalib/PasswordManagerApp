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
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.viewModel.OthersViewModel
import com.example.passwordmanager.*

@Composable
fun OthersScreen(
    viewModel: OthersViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController,
    currentRoute: String,
    navigateToAllLogins: ()->Unit,
    navigateToAllCards: ()->Unit,
    navigateToAllOthers: ()->Unit,
) {

    mainViewModel.setColorForStatusBar(MaterialTheme.colors.primaryVariant)


    Scaffold(
        topBar = {
//            HomeTopAppBar(
//                topAppBarTitle = OthersScreen.AllOthers.label,
//                onMenuIconClick = {},
//                onSortIconClick = {},
//                onSearchIconClick = {},
//                onSwitchIconClick = {}
//            )
        },
        floatingActionButton = {
            MyFloatingBtn(
                onClick = {navController.navigate(OthersScreen.NewOthersItem.route)}            )
        },
        drawerContent = {
            //MyDrawer()
        },
        bottomBar = {
            MyBottomBar(
                navController = navController,
                currentRoute = currentRoute,
                navigateToAllLogins = navigateToAllLogins,
                navigateToAllCards = navigateToAllCards,
                navigateToAllOthers = navigateToAllOthers,
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