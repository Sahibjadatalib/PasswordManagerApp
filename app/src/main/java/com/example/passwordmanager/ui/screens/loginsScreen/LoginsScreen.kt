package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    navigateToLoginsEdit: (Int) -> Unit,
    navigateToNewItem: () -> Unit,
    navigateToSettings: ()->Unit,
    popUp: () -> Unit
) {


    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var items = if (viewModel.switch.value) {
        viewModel.resultsForFavorites.collectAsState()
    } else {
        viewModel.results.collectAsState()
    }

    if(viewModel.searchQuery.value.isNotEmpty()){
        items = viewModel.resultsForSearch.collectAsState()
    }

    val scrollState = rememberScrollState()


    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = LoginsScreen.AllLogins.label,
                onMenuIconClick = {},
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.setSwitch(it) },
                onSettingsIconClick = {navigateToSettings()}
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

                SearchBar(
                    text = viewModel.searchQuery.value,
                    onTextChange = {
                        viewModel.setSearchQuery(it)
                        viewModel.getSearchedEntries()
                    }
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp)

                ) {

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
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
                                },
                                onEditMenuClick = { item.itemId?.let { id -> navigateToLoginsEdit(id) } },
                                onDeleteMenuClick = {
                                    item.itemId?.let { id ->
                                        viewModel.deleteLoginsItem(
                                            id
                                        )
                                    }
                                }
                            )

                            Divider()


                        }
                    }

                }



                Spacer(modifier = Modifier.height(90.dp))

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





