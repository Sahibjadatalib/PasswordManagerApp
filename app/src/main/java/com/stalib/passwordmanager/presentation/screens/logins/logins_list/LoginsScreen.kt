package com.stalib.passwordmanager.presentation.screens.logins

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stalib.passwordmanager.*
import com.stalib.passwordmanager.data.room.entity.LoginsItems
import com.stalib.passwordmanager.domain.models.loginsCategoryOptions
import com.stalib.passwordmanager.presentation.common_components.*
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.screens.logins.logins_list.LoginsListViewModel

@Composable
fun LoginsScreen(
    viewModel: LoginsListViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    currentRoute: String,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    val (searchQuery, searchQuerySetter) = viewModel.searchQuery


    var items = if (viewModel.switch.value) {
        viewModel.resultsForFavorites.collectAsState()
    } else {
        viewModel.results.collectAsState()
    }

    if (viewModel.searchQuery.value.isNotEmpty()) {
        items = viewModel.resultsForSearch.collectAsState()
    }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = LoginsScreen.AllLogins.label,
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.switch.value = it },
                onSettingsIconClick = { actions.navigateToSettings() }
            )
        },
        floatingActionButton = {
            MyFloatingBtn(
                onClick = { actions.navigateToNewLoginsItem() }
            )
        },
        bottomBar = {
            MyBottomBar(
                currentRoute = currentRoute,
                actions = actions
            )

        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,

        ) {

        val scrollState = rememberScrollState()

        val storedItems = viewModel.results.collectAsState()
        if (storedItems.value.isEmpty()) {
            PlaceholderComponent(
                icon = Icons.Default.Language,
                title = "No Logins Added"
            )
        } else {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                SearchBar(
                    text = searchQuery,
                    onTextChange = {
                        searchQuerySetter(it)
                        viewModel.getSearchedEntries()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                val searchedItems = viewModel.resultsForSearch.collectAsState()
                if (searchedItems.value.isEmpty() && searchQuery.isNotEmpty()) {
                    SearchPlaceholder()
                } else {

                    LoginsItemsList(
                        items = items,
                        onItemCardClick = { actions.navigateToLoginsDetails(it) },
                        onStarIconClick = viewModel::updateIsFavorite,
                        onEditIconClick = { actions.navigateToLoginsEdit(it) },
                        onDeleteIconClick = viewModel::deleteLoginsItem
                    )

                    Spacer(modifier = Modifier.height(140.dp))
                }


            }
        }


    }

}


@Composable
fun LoginsItemsList(
    items: State<List<LoginsItems>>,
    onItemCardClick: (Int) -> Unit,
    onStarIconClick: (Int, Boolean) -> Unit,
    onEditIconClick: (Int) -> Unit,
    onDeleteIconClick: (Int) -> Unit
) {

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
                    onItemCardClick = { onItemCardClick(item.itemId) },
                    isFavorite = item.isFavorite,
                    onStarIconsClick = { onStarIconClick(item.itemId, it) },
                    onEditMenuClick = { onEditIconClick(item.itemId) },
                    onDeleteMenuClick = { onDeleteIconClick(item.itemId) }
                )

                Divider()
            }
        }

    }

}




