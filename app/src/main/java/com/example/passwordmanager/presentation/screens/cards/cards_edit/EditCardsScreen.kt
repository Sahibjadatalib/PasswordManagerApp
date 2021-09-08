package com.example.passwordmanager.presentation.screens.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.LoginsScreen
import com.example.passwordmanager.domain.models.cardsCategoryOptions
import com.example.passwordmanager.presentation.common_components.*
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.presentation.common_components.TitleField
import com.example.passwordmanager.presentation.screens.cards.cards_edit.EditCardsViewModel
import kotlinx.coroutines.launch

@Composable
fun EditCardsDetails(
    viewModel: EditCardsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    itemId: Int,
) {

    val itemById = viewModel.getItemById(itemId).observeAsState()

    itemById.value?.title?.let {viewModel.title.value = it }
    itemById.value?.category?.let { viewModel.category.value = it }
    itemById.value?.cardNumber?.let { viewModel.cardNumber.value = it }
    itemById.value?.cardHolderName?.let { viewModel.cardHolderName.value = it }
    itemById.value?.pinNumber?.let { viewModel.pinNumber.value = it }
    itemById.value?.cvv?.let { viewModel.cvvNumber.value = it }
    itemById.value?.issueDate?.let { viewModel.issueDate.value = it }
    itemById.value?.expiryDate?.let { viewModel.expiryDate.value = it }


    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LoginsScreen.EditLoginsDetails.label,
                onCancelIconClick = {
                    actions.popUp()
                },
                onDoneIconClick = {
                    viewModel.updateCardsItem(itemId, actions.showSnackBar, actions.popUp)
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
                onTextChange = {viewModel.title.value = it}
            )

            Category(
                categoryList = cardsCategoryOptions,
                selectedCategory = viewModel.category.value,
                setCategory = { viewModel.category.value = it }
            )

            InputField(
                fieldTitle = "Card No.",
                text = viewModel.cardNumber.value,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                onTextChange = { viewModel.cardNumber.value = it },
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
                onTextChange = { viewModel.cardHolderName.value = it },
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
                    onTextChange = { viewModel.pinNumber.value = it },
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
                    onTextChange = { viewModel.cvvNumber.value = it },
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
                onTextChange = { viewModel.issueDate.value = it },
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
                onTextChange = { viewModel.expiryDate.value = it },
                leadingIcon = Icons.Default.CalendarToday,
                placeholderText = "Click to choose a date"
            )

            Spacer(modifier = Modifier.height(64.dp))


        }
    }

}