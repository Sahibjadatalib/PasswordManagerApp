package com.example.passwordmanager.ui.screens.cardsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.model.cardsCategoryOptions
import com.example.passwordmanager.model.loginsCategoryOptions
import com.example.passwordmanager.ui.components.DebitCard
import com.example.passwordmanager.ui.components.DetailsCard
import com.example.passwordmanager.ui.components.DetailsTopAppBar
import com.example.passwordmanager.ui.viewModel.CardsViewModel
import com.example.passwordmanager.ui.viewModel.LoginsViewModel
import com.example.passwordmanager.ui.viewModel.MainViewModel
import java.lang.StringBuilder

@Composable
fun CardsDetailsScreen(
    viewModel: CardsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navigateToAllLogins: () -> Unit,
    navigateToEditScreen: (Int) -> Unit,
    popUp: () -> Unit,
    itemId: Int
) {

    val itemById = viewModel.getItemById(itemId).observeAsState()

    val scrollState = rememberScrollState()


    Scaffold(
        topBar = {
            itemById.value?.title?.let {
                DetailsTopAppBar(
                    topAppBarTitle = it,
                    onDeleteIconClick = {
                        viewModel.deleteCardsItem(itemId)
                        popUp()
                    },
                    onBackIconClick = {popUp()},
                    onEditIconClick = { navigateToEditScreen(itemId) }
                )
            }
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState),
        ) {

            itemById.value?.category?.let { category ->
                DebitCard(
                    cardName = itemById.value?.title.toString(),
                    cardCategory = category,
                    cardNumber = itemById.value?.cardNumber.toString(),
                    cardHolderName = itemById.value?.cardHolderName.toString(),
                    issueDate = itemById.value?.issueDate.toString(),
                    expiryDate = itemById.value?.expiryDate.toString(),
                    cvv = itemById.value?.cvv.toString()
                )
            }



            FieldsDetails(
                cardNumber = itemById.value?.cardNumber.toString(),
                cardHolderName = itemById.value?.cardHolderName.toString(),
                pin = itemById.value?.pinNumber.toString(),
                cvv = itemById.value?.cvv.toString(),
                issueDate = itemById.value?.issueDate.toString(),
                expiryDate = itemById.value?.expiryDate.toString()
            )

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
            .padding(top = 0.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {





        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            if(cardNumber.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Pin, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "Card No.",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cardNumber)
                    }

                }

                Divider()

            }

            if(cardHolderName.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Person, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "CardHolder Name",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cardHolderName)
                    }

                }

                Divider()

            }

            if(pin.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Dialpad, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "PIN",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = pin)
                    }

                }

                Divider()

            }

            if(cvv.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.Dialpad, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "CVV",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cvv)
                    }

                }

                Divider()

            }

            if(issueDate.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.CalendarToday, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "Issue Date",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = issueDate)
                    }

                }

                Divider()

            }

            if(expiryDate.isNotEmpty()){

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp),
                        imageVector = Icons.Default.CalendarToday, contentDescription = ""
                    )

                    Column {
                        Text(
                            text = "Expiry Date",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = expiryDate)
                    }

                }

                Divider()

            }






        }


    }
}