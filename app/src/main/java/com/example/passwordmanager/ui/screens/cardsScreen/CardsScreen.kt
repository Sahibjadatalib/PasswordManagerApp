package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.HomeTopAppBar
import com.example.passwordmanager.ui.components.MyBottomBar
import com.example.passwordmanager.ui.components.MyFloatingBtn
import com.example.passwordmanager.ui.viewModel.CardsViewModel
import com.example.passwordmanager.*

@Composable
fun CardsScreen(
    viewModel: CardsViewModel = hiltViewModel(),
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
//                topAppBarTitle = CardsScreen.AllCards.label,
//                onMenuIconClick = {},
//                onSortIconClick = {},
//                onSearchIconClick = {},
//                onSwitchIconClick = {}
//            )
        },
        floatingActionButton = {
            MyFloatingBtn(
                onClick = {navController.navigate(CardsScreen.NewCardsItem.route)}            )
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

            Text(text = "cards screen")

        }
    }

}