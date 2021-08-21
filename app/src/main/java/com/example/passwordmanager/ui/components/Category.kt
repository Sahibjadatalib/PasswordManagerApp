package com.example.passwordmanager.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.model.Category
import com.example.passwordmanager.model.loginsCategoryOptions


@Composable
fun Category(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    selectedCategory: Int,
    setCategory: (Int)->Unit

) {

    val expended = remember { mutableStateOf(false) }
    val rotateAnimation by animateFloatAsState(targetValue = if (expended.value) 180f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp

    ) {

        Column {
            Row(
                modifier = modifier
                    .clickable(onClick = { expended.value = !expended.value })
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = modifier.weight(7f),
                    text = "Category",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    modifier = modifier.weight(3f),
                    text = categoryList[selectedCategory].title,
                    color = MaterialTheme.colors.primary,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )

                IconButton(
                    modifier = modifier
                        .rotate(rotateAnimation)
                        .weight(1f),
                    onClick = { expended.value = !expended.value }
                ) {
                    Icon(Icons.Default.ExpandMore, "")
                }
            }

            if (expended.value) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {


                    Divider(color = Color.LightGray, thickness = 1.dp)

                    for (category in categoryList) {
                        OptionsRow(
                            modifier = modifier,
                            category = category,
                            categoryList = categoryList,
                            selectedCategory = selectedCategory,
                            setCategory = setCategory,
                            expended = expended
                        )
                        Divider(color = Color.LightGray, thickness = 1.dp)
                    }
                }

            }
        }

    }
}


@Composable
fun OptionsRow(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    category: Category,
    selectedCategory: Int,
    setCategory: (Int)->Unit,
    expended: MutableState<Boolean>
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                setCategory(category.index)
                expended.value = false
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Card(
            modifier = modifier
                .width(32.dp)
                .height(32.dp)
                .weight(1f),
            elevation = 5.dp
        ) {
            Icon(
                modifier = modifier,
                tint = category.tintColor,
                imageVector = categoryList[category.index].icon, contentDescription = ""
            )

        }
        Spacer(modifier = modifier.width(16.dp))
        Text(
            modifier = modifier.weight(8f),
            text = category.title,
            fontSize = MaterialTheme.typography.body1.fontSize,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        if (selectedCategory == categoryList.indexOf(category)) {
            Icon(
                modifier = modifier.weight(1f),
                imageVector = Icons.Default.Done, contentDescription = ""
            )
        }
    }

}