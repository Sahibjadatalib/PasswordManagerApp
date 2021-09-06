package com.example.passwordmanager.ui.screens.newItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.CardsScreen
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.model.cardsCategoryOptions
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.navigation.MainActions
import com.example.passwordmanager.ui.viewModel.CardsViewModel
import kotlinx.coroutines.launch

@Composable
fun NewCardsScreen(
    viewModel: CardsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = CardsScreen.NewCardsItem.label,
                onCancelIconClick = { actions.popUp() },
                onDoneIconClick = { viewModel.insertCardsItem(popUp = actions.popUp, showSnackBar = actions.showSnackBar) }
            )
        }
    ) {

        val scrollState = rememberScrollState()

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

            PickerInputField(
                fieldTitle = "Issue Date",
                text = viewModel.issueDate.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onTextChange = { viewModel.setIssueDate(it) },
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date"
            )

            PickerInputField(
                fieldTitle = "Expiry Date",
                text = viewModel.expiryDate.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onTextChange = { viewModel.setExpiryDate(it) },
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date"
            )

            Spacer(modifier = Modifier.height(64.dp))


        }
    }
}