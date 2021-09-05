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
import com.example.passwordmanager.ui.components.PasswordStrength
import com.example.passwordmanager.ui.theme.AmberA700
import com.example.passwordmanager.ui.theme.Theme
import com.example.passwordmanager.ui.viewModel.LoginsViewModel
import com.example.passwordmanager.ui.viewModel.MainViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun LoginsDetailsScreen(
    viewModel: LoginsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navigateToAllLogins: () -> Unit,
    navigateToEditScreen: (Int) -> Unit,
    popUp: () -> Unit,
    itemId: Int
) {

    val itemById = viewModel.getItemById(itemId = itemId).observeAsState()

    Scaffold(
        topBar = {
            itemById.value?.title?.let {
                DetailsTopAppBar(
                    topAppBarTitle = it,
                    onDeleteIconClick = {
                        viewModel.deleteLoginsItem(itemId)
                        popUp()
                    },
                    onBackIconClick = { popUp() },
                    onEditIconClick = { navigateToEditScreen(itemId) }
                )
            }
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            itemById.value?.let { item ->
                LoginsHeader(
                    item = item,
                    onStarIconClick = {
                        viewModel.updateIsFavorite(
                            itemId = itemId,
                            isFavorite = it
                        )
                    })
            }

            FieldsDetails(
                userName = itemById.value?.userName.let { it.toString() },
                password = itemById.value?.passWord.let { it.toString() }
            )

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
                Text(text = loginsCategoryOptions[item.category].title)

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
                Text(text = "Pass Strength")
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
                Text(text = if (favorite) "Favorite" else "Not Favorite")


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
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 0.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            if (userName.isNotEmpty()) {

                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Person, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "Username",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = userName)
                    }


                }

                Divider()

            }

            if (password.isNotEmpty()) {

                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.VpnKey, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "Password",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = password)
                    }

                }

            }


        }


    }
}