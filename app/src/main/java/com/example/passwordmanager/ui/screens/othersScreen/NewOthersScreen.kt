package com.example.passwordmanager.ui.screens.newItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.OthersScreen
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.viewModel.OthersViewModel

@Composable
fun NewOthersScreen(
    mainViewModel: MainViewModel,
    viewModel: OthersViewModel = hiltViewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()

    mainViewModel.setColorForStatusBar(Color.White)


    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = OthersScreen.AllOthers.label,
                onCancelIconClick = {},
                onDoneIconClick = {}
            )
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Text("others")

        }
    }


}


//@Composable
//fun UserNameField(
//    userName: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>
//) {
//    if (isPresentOnScreen.value) {
//
//        InputField(
//            fieldTitle = "Username",
//            text = userName.value,
//            keyboardType = KeyboardType.Text,
//            onTextChange = { userName.value = it },
//            leadingIcon = Icons.Default.PersonOutline,
//            placeholderText = "username",
//        )
//    }
//}
//
//@Composable
//fun PassWordField(
//    password: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Password",
//            text = password.value,
//            keyboardType = KeyboardType.Text,
//            onTextChange = { password.value = it },
//            leadingIcon = Icons.Default.Person,
//            placeholderText = "password",
//        )
//    }
//}
//
//
//@Composable
//fun WebsiteField(
//    website: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Website",
//            text = website.value,
//            keyboardType = KeyboardType.Text,
//            onTextChange = { website.value = it },
//            leadingIcon = Icons.Default.Language,
//            placeholderText = "www.stalib.com",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//}
//
//@Composable
//fun EmailField(
//    email: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Email",
//            text = email.value,
//            keyboardType = KeyboardType.Email,
//            onTextChange = { email.value = it },
//            leadingIcon = Icons.Default.AlternateEmail,
//            placeholderText = "stalib@gmail.com",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//
//        )
//    }
//}
//
//@Composable
//fun NumberField(
//    number: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Number",
//            text = number.value,
//            keyboardType = KeyboardType.Number,
//            onTextChange = { number.value = it },
//            leadingIcon = Icons.Default.Pin,
//            placeholderText = "123...",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//}
//
//@Composable
//fun PinNumberField(
//    pinNumber: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "PIN",
//            text = pinNumber.value,
//            keyboardType = KeyboardType.Number,
//            onTextChange = { pinNumber.value = it },
//            leadingIcon = Icons.Default.Dialpad,
//            placeholderText = "123...",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//}
//
//@Composable
//fun DateDDMMYYYYField(
//    date: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Date[DD/MM/YYYY]",
//            text = date.value,
//            keyboardType = KeyboardType.Number,
//            onTextChange = { date.value = it },
//            leadingIcon = Icons.Default.CalendarToday,
//            placeholderText = "22/07/1999",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//}
//
//@Composable
//fun DateMMYYField(
//    date: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Date[MM/YY]",
//            text = date.value,
//            keyboardType = KeyboardType.Number,
//            onTextChange = { date.value = it },
//            leadingIcon = Icons.Default.CalendarToday,
//            placeholderText = "07/99",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//}
//
//
//@Composable
//fun TextField(
//    text: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "Text",
//            text = text.value,
//            keyboardType = KeyboardType.Text,
//            onTextChange = { text.value = it },
//            leadingIcon = Icons.Default.Title,
//            placeholderText = "hello there...",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//
//
//}
//
//@Composable
//fun MultiLineText(
//    text: MutableState<String>,
//    isPresentOnScreen: MutableState<Boolean>,
//    onMinusClick: () -> Unit
//) {
//    if (isPresentOnScreen.value) {
//        InputField(
//            fieldTitle = "MultiLineText",
//            text = text.value,
//            keyboardType = KeyboardType.Text,
//            onTextChange = { text.value = it },
//            leadingIcon = Icons.Default.TextFields,
//            placeholderText = "i feel lucky today...",
//            minusIcon = Icons.Default.RemoveCircleOutline,
//            onMinusIconClick = { onMinusClick() }
//        )
//    }
//}
