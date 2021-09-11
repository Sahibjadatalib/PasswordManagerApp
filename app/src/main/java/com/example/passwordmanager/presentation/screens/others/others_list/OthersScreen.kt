package com.example.passwordmanager.presentation.screens.others

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.*
import com.example.passwordmanager.data.room.entity.OthersItems
import com.example.passwordmanager.domain.models.othersCategoryOptions
import com.example.passwordmanager.presentation.common_components.*
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.presentation.screens.others.others_list.OtherListViewModel
import com.example.passwordmanager.presentation.theme.Theme

@Composable
fun OthersScreen(
    viewModel: OtherListViewModel = hiltViewModel(),
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

    if(viewModel.searchQuery.value.isNotEmpty()){
        items = viewModel.resultsForSearch.collectAsState()
    }



    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = OthersScreen.AllOthers.label,
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.switch.value = it },
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

        val storedItems = viewModel.results.collectAsState()
        if(storedItems.value.isEmpty()){
            PlaceholderComponent(
                title = "No Others Added",
                description = "Click on + icon to add new others item"
            )
        }
        else{

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
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
                if(searchedItems.value.isEmpty() && searchQuery.isNotEmpty()){
                    SearchPlaceholder()
                }
                else{

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