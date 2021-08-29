package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.viewModel.OthersViewModel
import com.example.passwordmanager.*
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.model.othersCategoryOptions

@Composable
fun OthersScreen(
    viewModel: OthersViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController,
    currentRoute: String,
    navigateToAllLogins: ()->Unit,
    navigateToAllCards: ()->Unit,
    navigateToAllOthers: ()->Unit,
    navigateToOthersDetails: (Int)->Unit,
    navigateToNewItem: ()->Unit,
    navigateToOthersEdit: (Int)->Unit,
    popUp: ()->Unit
) {

    mainViewModel.setColorForStatusBar(MaterialTheme.colors.primaryVariant)


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
                topAppBarTitle = OthersScreen.AllOthers.label,
                onMenuIconClick = {},
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.setSwitch(it) }
            )
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

                            val text = when (othersCategoryOptions[item.category].index) {
                                0 -> {
                                    item.description.toString()
                                }
                                1 -> {
                                    item.macAddress.toString()
                                }
                                else -> {
                                    item.userName.toString()
                                }
                            }

                            ItemsCard(
                                title = item.title,
                                text = text,
                                itemIcon = othersCategoryOptions[item.category].icon,
                                itemIconColor = othersCategoryOptions[item.category].tintColor,
                                onItemCardClick = {
                                    item.itemId?.let { id -> navigateToOthersDetails(id) }
                                },
                                isFavorite = item.isFavorite,
                                onStarIconsClick = {
                                    item.itemId?.let { id -> viewModel.updateIsFavorite(id, it) }
                                },
                                onEditMenuClick = { item.itemId?.let { id -> navigateToOthersEdit(id) } },
                                onDeleteMenuClick = {
                                    item.itemId?.let { id ->
                                        viewModel.deleteOthersItem(
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




}