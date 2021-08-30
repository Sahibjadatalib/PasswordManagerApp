package com.example.passwordmanager.ui.screens.cardsScreen.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.model.Category
import com.example.passwordmanager.model.cardsCategoryOptions
import java.util.*


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
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
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
                text = cardNumber,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
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
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                    Text(
                        text = cardHolderName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                }

                Column {

                    Text(
                        text = "Issue",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                    Text(
                        text = issueDate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                }

                Column {

                    Text(
                        text = "Expiry",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                    Text(
                        text = expiryDate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                }
            }

        }

    }

}