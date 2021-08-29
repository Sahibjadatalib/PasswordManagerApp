package com.example.passwordmanager.ui.screens.othersScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.model.othersCategoryOptions
import com.example.passwordmanager.ui.components.DetailsCard
import com.example.passwordmanager.ui.components.DetailsTopAppBar
import com.example.passwordmanager.ui.viewModel.LoginsViewModel
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.viewModel.OthersViewModel

@Composable
fun OthersDetailsScreen(
    viewModel: OthersViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navigateToAllOthers: () -> Unit,
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
                        viewModel.deleteOthersItem(itemId)
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
                            category = othersCategoryOptions[category].title,
                            itemIcon = othersCategoryOptions[category].icon,
                            itemIconTint = othersCategoryOptions[category].tintColor,
                            passStrength = passwordLen,
                            isFavorite = isFavorite,
                            onStarIconClick = {
                                viewModel.updateIsFavorite(itemId = itemId,isFavorite = it)}
                        )
                    }
                }
            }

            FieldsDetails(
                description = itemById.value?.description.toString(),
                userName = itemById.value?.userName.toString(),
                password = itemById.value?.passWord.toString(),
                macAddress = itemById.value?.macAddress.toString()
            )

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
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 0.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            if(description.isNotEmpty()){
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val scrollState = rememberScrollState()

                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Note, contentDescription = ""
                    )

                    Column(
                        modifier = Modifier.verticalScroll(scrollState)
                    ) {
                        Text(
                            text = "Description",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = description)
                    }

                }

                Divider()
            }

            if(userName.isNotEmpty()){

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

            if(password.isNotEmpty()){

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

                Divider()
            }

            if(macAddress.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Computer, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "Mac Address",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = macAddress)
                    }

                }

                Divider()
            }


        }


    }
}