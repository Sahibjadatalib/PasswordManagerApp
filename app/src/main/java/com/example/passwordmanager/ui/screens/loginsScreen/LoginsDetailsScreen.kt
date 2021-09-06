package com.example.passwordmanager.ui.screens.loginsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.data.room.entity.LoginsItems
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
import kotlinx.coroutines.flow.collect

@Composable
fun LoginsDetailsScreen(
    viewModel: LoginsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    itemId: Int
) {

    val itemById = viewModel.getItemById(itemId = itemId).observeAsState()

    Scaffold(
        topBar = {
            itemById.value?.let { item ->
                DetailsTopAppBar(
                    topAppBarTitle = item.title,
                    onDeleteIconClick = {
                        viewModel.deleteLoginsItem(itemId)
                        actions.popUp()
                    },
                    onBackIconClick = { actions.popUp() },
                    onEditIconClick = { actions.navigateToLoginsEdit(itemId) }
                )
            }
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            itemById.value?.let { item ->
                LoginsHeader(
                    item = item,
                    onStarIconClick = { viewModel.updateIsFavorite(itemId, it) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            itemById.value?.let { item ->
                FieldsDetails(
                    userName = item.userName.toString(),
                    password = item.passWord.toString()
                )
            }

        }

    }

}

@Composable
fun LoginsHeader(
    modifier: Modifier = Modifier,
    item: LoginsItems,
    onStarIconClick: (Boolean) -> Unit
) {

    var favorite by remember { mutableStateOf(item.isFavorite) }

    Surface(
        modifier = modifier.fillMaxWidth().wrapContentHeight().padding(Theme.paddings.medium),
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
                        modifier = Modifier.size(64.dp),
                        tint = if (favorite) AmberA700 else Color.Gray,
                        imageVector = Icons.Rounded.Star, contentDescription = ""
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
    userName: String,
    password: String
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

            if (userName.isNotEmpty()) {
                ItemDetailRow(icon = Icons.Default.Person, title = "Username", text = userName)
                Divider()
            }
            if (password.isNotEmpty()) {
                ItemDetailRow(icon = Icons.Default.VpnKey, title = "Password", text = password)
            }


        }


    }
}
