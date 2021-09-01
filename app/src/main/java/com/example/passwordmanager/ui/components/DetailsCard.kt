package com.example.passwordmanager.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.ui.theme.AmberA700

@Composable
fun DetailsCard(
    modifier: Modifier = Modifier,
    category: String,
    itemIcon: ImageVector,
    itemIconTint: Color,
    passStrength: Int,
    isFavorite: Boolean,
    onStarIconClick: (Boolean) -> Unit
) {

    var favorite by remember { mutableStateOf(isFavorite) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {

        Row(
            modifier = modifier.padding(bottom = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tint = itemIconTint,
                    modifier = Modifier.size(64.dp),
                    imageVector = itemIcon, contentDescription = ""
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = category)

            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 30.dp)
                    .height(50.dp)
                    .width(1.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PasswordStrength(percent = 1f, number = passStrength)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Pass Strength")
            }

            Divider(
                color = Color.LightGray,
                modifier = Modifier.padding(bottom = 30.dp)
                    .height(50.dp)
                    .width(1.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                IconButton(onClick = {
                    favorite = !favorite
                    onStarIconClick(favorite)
                }) {

                    Icon(
                        modifier = Modifier.size(64.dp),
                        tint = if(favorite) AmberA700 else Color.Gray,
                        imageVector = Icons.Rounded.Star, contentDescription = ""
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = if(favorite) "Favorite" else "Not Favorite")


            }
        }

    }

}


@Composable
fun PasswordStrength(
    percent: Float,
    number: Int,
    radius: Dp = 25.dp,
    strokeWidth: Dp = 4.dp
) {

    var animationPlayed by remember { mutableStateOf(false) }

    val currPercent by animateFloatAsState(
        targetValue = if (animationPlayed) percent else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0,
            easing = LinearEasing
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier.size(radius * (2f)),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.size(radius * (2f))
        ) {

            drawArc(
                color = Color.Green,
                startAngle = 270f,
                sweepAngle = 360 * currPercent,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }

        Text(
            text = (currPercent * number).toInt().toString(),
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }

}