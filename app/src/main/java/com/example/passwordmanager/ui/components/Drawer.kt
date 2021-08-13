package com.example.passwordmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.R

//@Composable
//fun MyDrawer(
//    modifier: Modifier = Modifier
//) {
//
//
//    Column(
//        modifier
//            .fillMaxSize()
//            .padding(16.dp, 16.dp)
//    ) {
//        Card(
//            elevation = 5.dp,
//            shape = RoundedCornerShape(5.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.password),
//                contentDescription = "image"
//            )
//
//        }
//
//        screensFromDrawer.forEach { screen ->
//            Spacer(modifier = Modifier.height(8.dp))
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { }
//                        .padding(8.dp)
//                ) {
//                    Icon(imageVector = screen.icon, contentDescription = "image")
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(text = screen.label)
//                }
//            }
//
//        }
//
//    }
//}
