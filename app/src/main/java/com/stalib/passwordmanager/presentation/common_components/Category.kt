package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.domain.models.Category
import com.stalib.passwordmanager.presentation.theme.Theme


@Composable
fun Category(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    selectedCategory: Int,
    setCategory: (Int)->Unit

) {

    val expended = remember { mutableStateOf(false) }
    val rotateAnimation by animateFloatAsState(targetValue = if (expended.value) 180f else 0f)

    Surface(
        color = Theme.colors.surface,
        contentColor = contentColorFor(backgroundColor = Theme.colors.surface),
        modifier = modifier.fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing))
            .padding(Theme.paddings.medium),
        shape = Theme.shapes.medium,
        elevation = Theme.elevation.medium,
    ) {

        Column {
            Row(
                modifier = modifier.clickable(onClick = { expended.value = !expended.value }).padding(
                    Theme.paddings.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = modifier.weight(5f),
                    text = "Category",
                    style = Theme.typography.h6
                )

                Text(
                    modifier = modifier.weight(3f),
                    text = categoryList[selectedCategory].title,
                    color = MaterialTheme.colors.primary,
                    style = Theme.typography.h6,
                    textAlign = TextAlign.End
                )

                IconButton(
                    modifier = modifier.rotate(rotateAnimation).weight(1f),
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

                    val lastElement = categoryList.last()

                    categoryList.forEach { category->

                        OptionsRow(
                            modifier = modifier,
                            category = category,
                            categoryList = categoryList,
                            selectedCategory = selectedCategory,
                            setCategory = setCategory,
                            expended = expended
                        )

                        if(category!=lastElement){
                            Divider(color = Color.LightGray, thickness = 1.dp)
                        }

                    }
                }

            }
        }

    }
}


@Composable
private fun OptionsRow(
    modifier: Modifier = Modifier,
    categoryList: List<Category>,
    category: Category,
    selectedCategory: Int,
    setCategory: (Int)->Unit,
    expended: MutableState<Boolean>
) {


    Row(
        modifier = modifier.fillMaxWidth()
            .clickable {
                setCategory(category.index)
                expended.value = false
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {


        Icon(
            modifier = modifier,
            tint = category.tintColor,
            imageVector = categoryList[category.index].icon, contentDescription = ""
        )

        Spacer(modifier = modifier.width(16.dp))
        Text(
            modifier = modifier.weight(8f),
            text = category.title,
            style = Theme.typography.subtitle1
        )
        if (selectedCategory == categoryList.indexOf(category)) {
            Icon(
                modifier = modifier.weight(1f),
                imageVector = Icons.Default.Done, contentDescription = ""
            )
        }
    }



}