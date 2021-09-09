package com.example.passwordmanager.presentation.screens.logins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.domain.models.loginsCategoryOptions
import com.example.passwordmanager.presentation.common_components.Category
import com.example.passwordmanager.presentation.common_components.InputField
import com.example.passwordmanager.presentation.common_components.NewItemTopAppBar
import com.example.passwordmanager.presentation.common_components.TitleField
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.presentation.screens.logins.logins_edit.LoginsEditViewModel

@Composable
fun EditLoginsDetails(
    viewModel: LoginsEditViewModel = hiltViewModel(),
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

    val scrollState = rememberScrollState()

    mainViewModel.setColorForStatusBar(Color.White)

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LoginsScreen.EditLoginsDetails.label,
                onCancelIconClick = {
                    actions.popUp()
                },
                onDoneIconClick = {
                    viewModel.updateLoginsItem(itemId, actions.showSnackBar, actions.popUp)
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

            TitleField(
                text = viewModel.title.value,
                onTextChange = { viewModel.title.value = it }
            )

            Category(
                categoryList = loginsCategoryOptions,
                selectedCategory = viewModel.category.value,
                setCategory = { viewModel.category.value = it }
            )

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
                onTextChange = { viewModel.password.value = it },
                leadingIcon = Icons.Default.VpnKey,
                placeholderText = "password"
            )

        }
    }


}



