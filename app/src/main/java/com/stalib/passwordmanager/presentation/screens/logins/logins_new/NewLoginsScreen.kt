package com.stalib.passwordmanager.presentation.screens.logins

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stalib.passwordmanager.LoginsScreen
import com.stalib.passwordmanager.domain.models.loginsCategoryOptions
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.presentation.common_components.Category
import com.stalib.passwordmanager.presentation.common_components.InputField
import com.stalib.passwordmanager.presentation.common_components.NewItemTopAppBar
import com.stalib.passwordmanager.presentation.common_components.TitleField
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.screens.logins.logins_new.NewLoginsViewModel


@Composable
fun NewLoginsScreen(
    viewModel: NewLoginsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    val (title, titleSetter) = viewModel.title
    val (category, categorySetter) = viewModel.category
    val (userName, userNameSetter) = viewModel.userName
    val (password, passwordSetter) = viewModel.password

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LoginsScreen.NewLoginsItem.label,
                onCancelIconClick = { actions.popUp() },
                onDoneIconClick = { viewModel.insertLoginsItem(actions.popUp, actions.showSnackBar) }
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
                text = title,
                onTextChange = titleSetter
            )

            Category(
                categoryList = loginsCategoryOptions,
                selectedCategory = category,
                setCategory = categorySetter
            )

            InputField(
                fieldTitle = "Username",
                text = userName,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                onTextChange = userNameSetter,
                leadingIcon = Icons.Default.Person,
                placeholderText = "username"
            )

            InputField(
                fieldTitle = "Password",
                text = password,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                onTextChange = passwordSetter,
                leadingIcon = Icons.Default.VpnKey,
                placeholderText = "password"
            )

        }
    }



}




