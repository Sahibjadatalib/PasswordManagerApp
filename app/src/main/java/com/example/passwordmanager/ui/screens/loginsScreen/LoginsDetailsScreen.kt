package com.example.passwordmanager.ui.screens.loginsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.ui.components.DetailsCard
import com.example.passwordmanager.ui.components.DetailsTopAppBar
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

    val itemById = viewModel.getItemById(itemId).observeAsState()


    Scaffold(
        topBar = {
            itemById.value?.title?.let {
                DetailsTopAppBar(
                    topAppBarTitle = it,
                    onDeleteIconClick = {
                        viewModel.deleteLoginsItem(itemId)
                        popUp()
                    },
                    onBackIconClick = {popUp()},
                    onEditIconClick = { navigateToEditScreen(itemId) }
                )
            }
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            itemById.value?.category?.let { category ->
                itemById.value?.passWord?.length?.let { passwordLen ->
                    itemById.value?.isFavorite?.let { isFavorite ->
                        DetailsCard(
                            category = loginsCategoryOptions[category].title,
                            itemIcon = loginsCategoryOptions[category].icon,
                            itemIconTint = loginsCategoryOptions[category].tintColor,
                            passStrength = passwordLen,
                            isFavorite = isFavorite,
                            onStarIconClick = {
                                viewModel.updateIsFavorite(itemId = itemId,isFavorite = it)}
                        )
                    }
                }
            }

            itemById.value?.userName?.let { it1 ->
                itemById.value?.passWord?.let { it2 ->
                    FieldsDetails(
                        userName = it1,
                        password = it2
                    )
                }
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
            .padding(8.dp),
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier.padding(16.dp),
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

            Row(
                modifier = Modifier.padding(16.dp),
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