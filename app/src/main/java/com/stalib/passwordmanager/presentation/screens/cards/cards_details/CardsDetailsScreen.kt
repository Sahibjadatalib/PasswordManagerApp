package com.stalib.passwordmanager.presentation.screens.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stalib.passwordmanager.ui.screens.cardsScreen.components.DebitCard
import com.stalib.passwordmanager.presentation.common_components.DetailsTopAppBar
import com.stalib.passwordmanager.presentation.common_components.ItemDetailRow
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.theme.Theme
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.common.transformToCardNumber
import com.stalib.passwordmanager.presentation.screens.cards.cards_details.CardsDetailsViewModel

@Composable
fun CardsDetailsScreen(
    viewModel: CardsDetailsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    itemId: Int
) {

    val itemById = viewModel.getItemById(itemId).observeAsState()

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            itemById.value?.let {item->
                DetailsTopAppBar(
                    topAppBarTitle = item.title,
                    onDeleteIconClick = {
                        viewModel.deleteCardsItem(itemId)
                        actions.popUp()
                    },
                    onBackIconClick = {actions.popUp()},
                    onEditIconClick = { actions.navigateToCardsEdit(itemId) }
                )
            }
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {

            itemById.value?.let { item->
                DebitCard(
                    cardName = item.title,
                    cardCategory = item.category,
                    cardNumber = item.cardNumber.toString(),
                    cardHolderName = item.cardHolderName.toString(),
                    issueDate = item.issueDate.toString(),
                    expiryDate = item.expiryDate.toString(),
                    cvv = item.cvv
                )
            }

            itemById.value?.let {item->

                FieldsDetails(
                    cardNumber = item.cardNumber.toString(),
                    cardHolderName = item.cardHolderName.toString(),
                    pin = item.pinNumber.toString(),
                    cvv = item.cvv.toString(),
                    issueDate = item.issueDate.toString(),
                    expiryDate = item.expiryDate.toString()
                )
            }

        }
    }
}

@Composable
fun FieldsDetails(
    cardNumber: String,
    cardHolderName: String,
    pin: String,
    cvv: String,
    issueDate: String,
    expiryDate: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(Theme.paddings.medium),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            if(cardNumber.isNotEmpty()){
                ItemDetailRow(icon = Icons.Default.Pin, title = "Card No.", text = transformToCardNumber(cardNumber))
                Divider()
            }

            if(cardHolderName.isNotEmpty()){
                ItemDetailRow(icon = Icons.Default.Person, title = "CardHolder name", text = cardHolderName)
                Divider()
            }

            if(pin.isNotEmpty()){
                ItemDetailRow(icon = Icons.Default.Dialpad, title = "PIN", text = pin)
                Divider()
            }

            if(cvv.isNotEmpty()){
                ItemDetailRow(icon = Icons.Default.Dialpad, title = "CVV", text = cvv)
                Divider()
            }

            if(issueDate.isNotEmpty()){
                ItemDetailRow(icon = Icons.Default.CalendarToday, title = "Issue Date", text = issueDate)
                Divider()
            }

            if(expiryDate.isNotEmpty()){
                ItemDetailRow(icon = Icons.Default.CalendarToday, title = "Expiry Date", text = expiryDate)
                Divider()
            }
        }


    }
}