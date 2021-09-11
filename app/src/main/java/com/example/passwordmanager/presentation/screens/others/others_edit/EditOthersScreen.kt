package com.example.passwordmanager.presentation.screens.others

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.domain.models.othersCategoryOptions
import com.example.passwordmanager.presentation.common_components.*
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.presentation.screens.others.others_edit.OtherEditViewModel

@Composable
fun EditOthersDetails(
    viewModel: OtherEditViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    itemId: Int,
) {

    val itemById = viewModel.getItemById(itemId).observeAsState()

    itemById.value?.title?.let { viewModel.title.value = it }
    itemById.value?.category?.let { viewModel.category.value = it }
    itemById.value?.userName?.let { viewModel.userName.value = it }
    itemById.value?.passWord?.let { viewModel.password.value = it }
    itemById.value?.macAddress?.let {viewModel.macAddress.value = it }
    itemById.value?.description?.let { viewModel.description.value = it }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LoginsScreen.EditLoginsDetails.label,
                onCancelIconClick = {
                    actions.popUp()
                },
                onDoneIconClick = {
                    viewModel.updateOthersItem(itemId, actions.showSnackBar, actions.popUp)
                }
            )
        },
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            TitleField(
                text = viewModel.title.value,
                onTextChange = { viewModel.title.value = it }
            )

            Category(
                categoryList = othersCategoryOptions,
                selectedCategory = viewModel.category.value,
                setCategory = { viewModel.category.value = it }
            )

            if(viewModel.category.value == 0){

                InputField(
                    fieldTitle = "Description",
                    text = viewModel.description.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    onTextChange = { viewModel.description.value = it },
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
                    onTextChange = {viewModel.password.value = it},
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
                    onTextChange = { viewModel.macAddress.value = it },
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
                    onTextChange = { viewModel.userName.value = it },
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
                    onTextChange = {viewModel.password.value = it },
                    leadingIcon = Icons.Default.VpnKey,
                    placeholderText = "password"
                )

            }

        }
    }



}
