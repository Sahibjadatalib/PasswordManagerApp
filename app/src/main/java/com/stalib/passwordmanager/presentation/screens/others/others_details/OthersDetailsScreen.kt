package com.stalib.passwordmanager.presentation.screens.others

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stalib.passwordmanager.data.room.entity.OthersItems
import com.stalib.passwordmanager.presentation.common_components.DetailsTopAppBar
import com.stalib.passwordmanager.presentation.common_components.ItemDetailRow
import com.stalib.passwordmanager.presentation.common_components.PasswordStrength
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.theme.Theme
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.domain.models.othersCategoryOptions
import com.stalib.passwordmanager.presentation.screens.others.others_details.OtherDetailsViewModel

@Composable
fun OthersDetailsScreen(
    viewModel: OtherDetailsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    itemId: Int
) {

    val itemById = viewModel.getItemById(itemId).observeAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            itemById.value?.let { item ->
                DetailsTopAppBar(
                    topAppBarTitle = item.title,
                    onDeleteIconClick = {
                        viewModel.deleteOthersItem(itemId)
                        actions.popUp()
                    },
                    onBackIconClick = { actions.popUp() },
                    onEditIconClick = { actions.navigateToOthersEdit(itemId) }
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            itemById.value?.let { item ->
                OthersItemHeader(
                    item = item,
                    onStarIconClick = { viewModel.updateIsFavorite(itemId, it) }
                )
            }

            itemById.value?.let { item->
                FieldsDetails(
                    description = item.description.toString(),
                    userName = item.userName.toString(),
                    password = item.passWord.toString(),
                    macAddress = item.macAddress.toString()
                )
            }



        }


    }

}

@Composable
fun OthersItemHeader(
    modifier: Modifier = Modifier,
    item: OthersItems,
    onStarIconClick: (Boolean) -> Unit
) {

    var favorite by remember { mutableStateOf(item.isFavorite) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Theme.paddings.medium),
        shape = Theme.shapes.medium,
        elevation = Theme.elevation.medium
    ) {

        Row(
            modifier = modifier.padding(Theme.paddings.medium),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tint = othersCategoryOptions[item.category].tintColor,
                    modifier = Modifier.size(48.dp),
                    imageVector = othersCategoryOptions[item.category].icon,
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = othersCategoryOptions[item.category].title,
                    style = Theme.typography.subtitle1
                )

            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .height(50.dp)
                    .width(1.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item.passWord?.length?.let { PasswordStrength(percent = 1f, number = it) }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pass Strength",
                    style = Theme.typography.subtitle1
                )
            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .height(50.dp)
                    .width(1.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(onClick = {
                    favorite = !favorite
                    onStarIconClick(favorite)
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        tint = if(item.isFavorite) Theme.colors.primary else Color.Gray,
                        imageVector = Icons.Default.Favorite,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (favorite) "Favorite" else "Not Favorite",
                    style = Theme.typography.subtitle1
                )


            }
        }

    }

}

@Composable
fun FieldsDetails(
    description: String,
    userName: String,
    password: String,
    macAddress: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(Theme.paddings.medium),
        shape = RoundedCornerShape(8.dp),
        elevation = Theme.elevation.medium
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {


            if (description.isNotEmpty()) {
                ItemDetailRow(icon = Icons.Default.Note, title = "Description", text = description)
                Divider()
            }

            if (userName.isNotEmpty()) {
                ItemDetailRow(icon = Icons.Default.Person, title = "Username", text = userName)
                Divider()
            }

            if (password.isNotEmpty()) {
                ItemDetailRow(icon = Icons.Default.VpnKey, title = "Password", text = password)
                Divider()
            }

            if (macAddress.isNotEmpty()) {
                ItemDetailRow(icon = Icons.Default.Computer, title = "MAC Address", text = macAddress)
                Divider()
            }


        }


    }
}