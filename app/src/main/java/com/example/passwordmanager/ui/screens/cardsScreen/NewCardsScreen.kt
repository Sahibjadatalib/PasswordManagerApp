package com.example.passwordmanager.ui.screens.newItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.CardsScreen
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.model.cardsCategoryOptions
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.viewModel.CardsViewModel
import kotlinx.coroutines.launch

@Composable
fun NewCardsScreen(
    viewModel: CardsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController,
    popUp: () -> Unit
) {

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
                topAppBarTitle = CardsScreen.NewCardsItem.label,
                onCancelIconClick = { popUp() },
                onDoneIconClick = {
                    viewModel.insertCardsItem(popUp = popUp, showSnackBar = showSnackBar)
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
                categoryList = cardsCategoryOptions,
                selectedCategory = viewModel.category.value,
                setCategory = { viewModel.setCategory(it) }
            )

            InputField(
                fieldTitle = "Card No.",
                text = viewModel.cardNumber.value,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                onTextChange = { viewModel.setCardNumber(it) },
                leadingIcon = Icons.Default.Pin,
                placeholderText = "Card No..."
            )

            InputField(
                fieldTitle = "Cardholder Name",
                text = viewModel.cardHolderName.value,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                onTextChange = { viewModel.setCardHolderName(it) },
                leadingIcon = Icons.Default.Person,
                placeholderText = "Cardholder Name..."
            )

            if (viewModel.category.value == 0 || viewModel.category.value == 1) {

                InputField(
                    fieldTitle = "PIN",
                    text = viewModel.pinNumber.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    onTextChange = { viewModel.setPinNumber(it) },
                    leadingIcon = Icons.Default.Dialpad,
                    placeholderText = "PIN..."
                )

                InputField(
                    fieldTitle = "CVV",
                    text = viewModel.cvvNumber.value,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    onTextChange = { viewModel.setCVVNumber(it) },
                    leadingIcon = Icons.Default.Dialpad,
                    placeholderText = "cvv..."
                )

            }

            InputField(
                fieldTitle = "Issue Date",
                text = viewModel.issueDate.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onTextChange = { viewModel.setIssueDate(it) },
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date",
                isReadableOnly = true
            )

            InputField(
                fieldTitle = "Expiry Date",
                text = viewModel.expiryDate.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onTextChange = { viewModel.setExpiryDate(it) },
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date",
                isReadableOnly = true
            )


        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}