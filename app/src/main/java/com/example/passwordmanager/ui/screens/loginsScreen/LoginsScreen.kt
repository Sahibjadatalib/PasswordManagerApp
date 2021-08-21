package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.*
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.viewModel.LoginsViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginsScreen(
    viewModel: LoginsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController,
    currentRoute: String,
    navigateToAllLogins: () -> Unit,
    navigateToAllCards: () -> Unit,
    navigateToAllOthers: () -> Unit,
    navigateToLoginsDetails: (Int) -> Unit,
    navigateToNewItem: () -> Unit,
    popUp: () -> Unit
) {


    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val items = if (viewModel.switch.value) {
        viewModel.resultsForFavorites.collectAsState()
    } else {
        viewModel.results.collectAsState()
    }


    val scrollState = rememberScrollState()

    val showSnackBar: (String, String) -> Unit = { message, action ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message, action)
        }
    }


    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = LoginsScreen.AllLogins.label,
                onMenuIconClick = {},
                onSortIconClick = {},
                onSearchIconClick = {},
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.setSwitch(it) }
            )
        },
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        floatingActionButton = {
            MyFloatingBtn(
                onClick = { navigateToNewItem() }
            )
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
        isFloatingActionButtonDocked = false,

        ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 48.dp)
                    .verticalScroll(scrollState)
            ) {

                items.value.forEach { item ->
                    ItemsCard(
                        title = item.title,
                        text = item.userName!!,
                        itemIcon = loginsCategoryOptions[item.category].icon,
                        itemIconColor = loginsCategoryOptions[item.category].tintColor,
                        onItemCardClick = {
                            item.itemId?.let { id -> navigateToLoginsDetails(id) }
                        },
                        isFavorite = item.isFavorite,
                        onStarIconsClick = {
                            item.itemId?.let { id -> viewModel.updateIsFavorite(id, it) }
                        }
                    )


                }

            }


        }


    }


    Box(modifier = Modifier.fillMaxSize()) {
        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}


