package com.stalib.passwordmanager.presentation.screens.cards

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
import com.stalib.passwordmanager.CardsScreen
import com.stalib.passwordmanager.domain.models.cardsCategoryOptions
import com.stalib.passwordmanager.presentation.common_components.*
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.screens.cards.cards_new.NewCardsViewModel

@Composable
fun NewCardsScreen(
    viewModel: NewCardsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    val (title, titleSetter) = viewModel.title
    val (category, categorySetter) = viewModel.category
    val (cardNum, cardNumSetter) = viewModel.cardNumber
    val (cardHolderName, cardHolderNameSetter) = viewModel.cardHolderName
    val (pin, pinSetter) = viewModel.pinNumber
    val (cvv, cvvSetter) = viewModel.cvvNumber
    val (issueDate, issueDateSetter) = viewModel.issueDate
    val (expiryDate, expiryDateSetter) = viewModel.expiryDate


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
                text = title,
                onTextChange = titleSetter
            )

            Category(
                categoryList = cardsCategoryOptions,
                selectedCategory = category,
                setCategory = categorySetter
            )

            InputField(
                fieldTitle = "Card No.",
                text = cardNum,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                onTextChange = cardNumSetter,
                leadingIcon = Icons.Default.Pin,
                placeholderText = "Card No..."
            )

            InputField(
                fieldTitle = "Cardholder Name",
                text = cardHolderName,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                onTextChange = cardHolderNameSetter,
                leadingIcon = Icons.Default.Person,
                placeholderText = "Cardholder Name..."
            )

            if (category == 0 || category == 1) {

                InputField(
                    fieldTitle = "PIN",
                    text = pin,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    onTextChange = pinSetter,
                    leadingIcon = Icons.Default.Dialpad,
                    placeholderText = "PIN..."
                )

                InputField(
                    fieldTitle = "CVV",
                    text = cvv,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    onTextChange = cvvSetter,
                    leadingIcon = Icons.Default.Dialpad,
                    placeholderText = "cvv..."
                )

            }

            PickerInputField(
                fieldTitle = "Issue Date",
                text = issueDate,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onTextChange = issueDateSetter,
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date"
            )

            PickerInputField(
                fieldTitle = "Expiry Date",
                text = expiryDate,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                onTextChange = expiryDateSetter,
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date"
            )

            Spacer(modifier = Modifier.height(64.dp))


        }
    }
}