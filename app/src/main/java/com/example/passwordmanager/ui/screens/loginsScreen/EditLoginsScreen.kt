package com.example.passwordmanager.ui.screens.loginsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.viewModel.LoginsViewModel
import com.example.passwordmanager.ui.viewModel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun EditLoginsDetails(
    viewModel: LoginsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController,
    navigateToAllLogins: () -> Unit,
    itemId: Int,
    popUp: ()->Unit
) {

    val itemById = viewModel.getItemById(itemId).observeAsState()

    itemById.value?.title?.let { viewModel.setTitle(it) }
    itemById.value?.category?.let { viewModel.setCategory(it) }
    itemById.value?.userName?.let { viewModel.setUserName(it) }
    itemById.value?.passWord?.let { viewModel.setPassword(it) }

    val scrollState = rememberScrollState()

    mainViewModel.setColorForStatusBar(Color.White)

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val showSnackBar: (String, String) -> Unit = { message, action ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message, action)
        }
    }

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LoginsScreen.EditLoginsDetails.label,
                onCancelIconClick = {
                    popUp()
                },
                onDoneIconClick = {
                    viewModel.updateLoginsItem(itemId,showSnackBar, popUp)
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
                onTextChange = { viewModel.setTitle(it) }
            )

            Category(
                categoryList = loginsCategoryOptions,
                selectedCategory = viewModel.category.value,
                setCategory = {viewModel.setCategory(it)}
            )

            InputField(
                fieldTitle = "Username",
                onMinusIconClick = { /*TODO*/ },
                text = viewModel.userName.value,
                keyboardType = KeyboardType.Text,
                onTextChange = { viewModel.setUserName(it) },
                leadingIcon = Icons.Default.Person,
                placeholderText = "username"
            )

            InputField(
                fieldTitle = "Password",
                onMinusIconClick = { /*TODO*/ },
                text = viewModel.password.value,
                keyboardType = KeyboardType.Password,
                onTextChange = { viewModel.setPassword(it) },
                leadingIcon = Icons.Default.VpnKey,
                placeholderText = "password"
            )

        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}




