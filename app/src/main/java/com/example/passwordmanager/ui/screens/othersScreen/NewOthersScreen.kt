package com.example.passwordmanager.ui.screens.newItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.OthersScreen
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.model.othersCategoryOptions
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.viewModel.OthersViewModel
import kotlinx.coroutines.launch

@Composable
fun NewOthersScreen(
    mainViewModel: MainViewModel,
    viewModel: OthersViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LoginsScreen.NewLoginsItem.label,
                onCancelIconClick = { actions.popUp() },
                onDoneIconClick = { viewModel.insertOthersItem(actions.popUp, actions.showSnackBar) }
            )
        }
    ) {

        val scrollState = rememberScrollState()

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            TitleField(
                text = viewModel.title.value,
                onTextChange = { viewModel.setTitle(it) }
            )

            Category(
                categoryList = othersCategoryOptions,
                selectedCategory = viewModel.category.value,
                setCategory = {viewModel.setCategory(it)}
            )


            if(viewModel.category.value == 0){

                MultilineInputField(
                    fieldTitle = "Description",
                    text = viewModel.description.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    onTextChange = { viewModel.setDescription(it) },
                    leadingIcon = Icons.Default.Note,
                    placeholderText = "Type here your note",
                    maxLine = 100,
                    singleLine = false
                )

            }

            if(viewModel.category.value == 1){

                InputField(
                    fieldTitle = "Password",
                    text = viewModel.password.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    ),
                    onTextChange = { viewModel.setPassword(it) },
                    leadingIcon = Icons.Default.VpnKey,
                    placeholderText = "password"
                )

                InputField(
                    fieldTitle = "MAC Address",
                    text = viewModel.macAddress.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    onTextChange = { viewModel.setMacAddress(it) },
                    leadingIcon = Icons.Default.Computer,
                    placeholderText = "mac address"
                )

            }

            if(viewModel.category.value == 2){

                InputField(
                    fieldTitle = "Username",
                    text = viewModel.userName.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    onTextChange = { viewModel.setUserName(it) },
                    leadingIcon = Icons.Default.Person,
                    placeholderText = "username"
                )


                InputField(
                    fieldTitle = "Password",
                    text = viewModel.password.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    onTextChange = { viewModel.setPassword(it) },
                    leadingIcon = Icons.Default.VpnKey,
                    placeholderText = "password"
                )

            }
        }
    }
}


