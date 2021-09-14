package com.stalib.passwordmanager.ui.screens.cardsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.common.transformToCardNumber
import com.stalib.passwordmanager.domain.models.cardsCategoryOptions
import com.stalib.passwordmanager.presentation.theme.Theme


@Composable
fun DebitCard(
    cardName: String,
    cardCategory: Int,
    cardNumber: String,
    cardHolderName: String,
    issueDate: String,
    expiryDate: String,
    cvv: String
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        color = cardsCategoryOptions[cardCategory].tintColor,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,

            ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = cardName,
                    style = Theme.typography.h5.copy(fontFamily = FontFamily.Cursive),
                    color = Color.White
                )

                Card(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Transparent)
                        .padding(4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        tint = cardsCategoryOptions[cardCategory].tintColor,
                        modifier = Modifier.size(12.dp),
                        imageVector = cardsCategoryOptions[cardCategory].icon,
                        contentDescription = ""
                    )
                }


            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = transformToCardNumber(text = cardNumber),
                style = Theme.typography.h5.copy(fontFamily = FontFamily.Cursive),
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "CardHolder",
                        style = Theme.typography.subtitle1.copy(fontFamily = FontFamily.Cursive),
                        color = Color.White
                    )

                    Text(
                        text = cardHolderName,
                        style = Theme.typography.subtitle1.copy(fontFamily = FontFamily.Cursive),
                        color = Color.White
                    )

                }

                Column {

                    Text(
                        text = "Issue",
                        style = Theme.typography.subtitle1.copy(fontFamily = FontFamily.Cursive),
                        color = Color.White
                    )

                    Text(
                        text = issueDate,
                        style = Theme.typography.subtitle1.copy(fontFamily = FontFamily.Cursive),
                        color = Color.White
                    )

                }

                Column {

                    Text(
                        text = "Expiry",
                        style = Theme.typography.subtitle1.copy(fontFamily = FontFamily.Cursive),
                        color = Color.White
                    )

                    Text(
                        text = expiryDate,
                        style = Theme.typography.subtitle1.copy(fontFamily = FontFamily.Cursive),
                        color = Color.White
                    )

                }
            }

        }

    }

}