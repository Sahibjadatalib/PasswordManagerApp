package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.example.passwordmanager.data.room.entity.CardsItems
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.data.room.entity.OthersItems
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.model.othersCategoryOptions
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.theme.Theme

@Composable
fun OthersScreen(
    viewModel: OthersViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    currentRoute: String,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    var items = if (viewModel.switch.value) {
        viewModel.resultsForFavorites.collectAsState()
    } else {
        viewModel.results.collectAsState()
    }

    if(viewModel.searchQuery.value.isNotEmpty()){
        items = viewModel.resultsForSearch.collectAsState()
    }



    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = OthersScreen.AllOthers.label,
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.setSwitch(it) },
                onSettingsIconClick = {actions.navigateToSettings()}
            )
        },
        floatingActionButton = {
            MyFloatingBtn(
                onClick = {actions.navigateToNewOthersItem()})
        },
        bottomBar = {
            MyBottomBar(
                currentRoute = currentRoute,
                actions = actions
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false
    ) {

        val scrollState = rememberScrollState()

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            SearchBar(
                text = viewModel.searchQuery.value,
                onTextChange = {
                    viewModel.setSearchQuery(it)
                    viewModel.getSearchedEntries()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OthersItemsList(
                items = items,
                onItemCardClick = {actions.navigateToOthersDetails(it)},
                onStarIconClick = viewModel::updateIsFavorite,
                onEditIconClick = { actions.navigateToOthersEdit(it) },
                onDeleteIconClick = viewModel::deleteOthersItem
            )

            Spacer(modifier = Modifier.height(140.dp))

        }

    }
}

@Composable
fun OthersItemsList(
    items: State<List<OthersItems>>,
    onItemCardClick: (Int)->Unit,
    onStarIconClick: (Int, Boolean) -> Unit,
    onEditIconClick: (Int) -> Unit,
    onDeleteIconClick: (Int) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth().padding(Theme.paddings.medium),
        elevation = Theme.elevation.medium,
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            items.value.forEach { item ->

                val text = when (othersCategoryOptions[item.category].index) {
                    0 -> item.description.toString()
                    1 -> item.macAddress.toString()
                    else -> item.userName.toString()
                }

                ItemsCard(
                    title = item.title,
                    text = text,
                    itemIcon = othersCategoryOptions[item.category].icon,
                    itemIconColor = othersCategoryOptions[item.category].tintColor,
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