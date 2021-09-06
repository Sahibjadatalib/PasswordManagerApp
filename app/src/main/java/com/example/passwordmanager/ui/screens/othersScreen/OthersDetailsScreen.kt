package com.example.passwordmanager.ui.screens.othersScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.data.room.entity.OthersItems
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.model.othersCategoryOptions
import com.example.passwordmanager.ui.components.DetailsCard
import com.example.passwordmanager.ui.components.DetailsTopAppBar
import com.example.passwordmanager.ui.components.ItemDetailRow
import com.example.passwordmanager.ui.components.PasswordStrength
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.theme.AmberA700
import com.example.passwordmanager.ui.theme.Theme
import com.example.passwordmanager.ui.viewModel.LoginsViewModel
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.viewModel.OthersViewModel

@Composable
fun OthersDetailsScreen(
    viewModel: OthersViewModel = hiltViewModel(),
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
                    tint = loginsCategoryOptions[item.category].tintColor,
                    modifier = Modifier.size(64.dp),
                    imageVector = loginsCategoryOptions[item.category].icon,
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = loginsCategoryOptions[item.category].title,
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