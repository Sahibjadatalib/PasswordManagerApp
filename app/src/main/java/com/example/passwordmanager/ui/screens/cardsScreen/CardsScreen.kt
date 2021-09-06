package com.example.passwordmanager.ui.screens.home

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
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.viewModel.CardsViewModel
import com.example.passwordmanager.*
import com.example.passwordmanager.data.room.entity.CardsItems
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.model.cardsCategoryOptions
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.navigation.MainActions

@Composable
fun CardsScreen(
    viewModel: CardsViewModel = hiltViewModel(),
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
                topAppBarTitle = CardsScreen.AllCards.label,
                switchState = viewModel.switch.value,
                onSwitchIconClick = { viewModel.setSwitch(it) },
                onSettingsIconClick = {actions.navigateToSettings()}
            )
        },
        floatingActionButton = {
            MyFloatingBtn(
                onClick = { actions.navigateToNewCardsItem() })
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

            CardsItemsList(
                items = items,
                onItemCardClick = {actions.navigateToCardsDetails(it)},
                onStarIconClick = viewModel::updateIsFavorite,
                onEditIconClick = { actions.navigateToCardsEdit(it) },
                onDeleteIconClick = viewModel::deleteCardsItem
            )

            Spacer(modifier = Modifier.height(140.dp))

        }

    }
}


@Composable
fun CardsItemsList(
    items: State<List<CardsItems>>,
    onItemCardClick: (Int)->Unit,
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
                    text = item.cardNumber!!,
                    itemIcon = cardsCategoryOptions[item.category].icon,
                    itemIconColor = cardsCategoryOptions[item.category].tintColor,
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