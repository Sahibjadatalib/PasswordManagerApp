package com.stalib.passwordmanager.presentation.screens.others

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
import com.stalib.passwordmanager.domain.models.othersCategoryOptions
import com.stalib.passwordmanager.presentation.common_components.*
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.screens.others.others_new.OthersNewViewModel

@Composable
fun NewOthersScreen(
    mainViewModel: MainViewModel,
    viewModel: OthersNewViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    val (title, titleSetter) = viewModel.title
    val (category, categorySetter) = viewModel.category
    val (userName, userNameSetter) = viewModel.userName
    val (password, passwordSetter) = viewModel.password
    val (description, descriptionSetter) = viewModel.description
    val (macAddress, macAddressSetter) = viewModel.macAddress

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
                text = title,
                onTextChange = titleSetter
            )

            Category(
                categoryList = othersCategoryOptions,
                selectedCategory = category,
                setCategory = categorySetter
            )


            if(viewModel.category.value == 0){

                MultilineInputField(
                    fieldTitle = "Description",
                    text = description,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    onTextChange = descriptionSetter,
                    leadingIcon = Icons.Default.Note,
                    placeholderText = "Type here your note",
                    maxLine = 100,
                    singleLine = false
                )

            }

            if(viewModel.category.value == 1){

                InputField(
                    fieldTitle = "Password",
                    text = password,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    ),
                    onTextChange = passwordSetter,
                    leadingIcon = Icons.Default.VpnKey,
                    placeholderText = "password"
                )

                InputField(
                    fieldTitle = "MAC Address",
                    text = macAddress,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    onTextChange = macAddressSetter,
                    leadingIcon = Icons.Default.Computer,
                    placeholderText = "mac address"
                )

            }

            if(viewModel.category.value == 2){

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
}


