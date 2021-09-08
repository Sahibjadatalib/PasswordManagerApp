package com.example.passwordmanager.presentation.screens.logins

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.domain.models.loginsCategoryOptions
import com.example.passwordmanager.presentation.common_components.DetailsTopAppBar
import com.example.passwordmanager.presentation.common_components.ItemDetailRow
import com.example.passwordmanager.presentation.common_components.PasswordStrength
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.presentation.theme.Theme
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.presentation.screens.logins.logins_details.LoginsDetailsViewModel

@Composable
fun LoginsDetailsScreen(
    viewModel: LoginsDetailsViewModel = hiltViewModel(),
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
                    modifier = Modifier.size(48.dp),
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
