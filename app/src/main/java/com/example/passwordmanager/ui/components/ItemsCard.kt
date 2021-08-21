package com.example.passwordmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.theme.AmberA200

@Composable
fun ItemsCard(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    itemIcon: ImageVector,
    itemIconColor: Color,
    isFavorite: Boolean,
    onStarIconsClick: (Boolean) -> Unit,
    onItemCardClick: () -> Unit
) {

    var favorite by remember { mutableStateOf(isFavorite) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemCardClick() },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {

        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            Icon(
                modifier = Modifier.size(48.dp),
                tint = itemIconColor,
                imageVector = itemIcon,
                contentDescription = ""
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(7f)
            ) {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    softWrap = true
                )

                Text(
                    text = text,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    softWrap = true

                )

            }

            IconButton(
                modifier = modifier.weight(1f),
                onClick = {
                    favorite = !favorite
                    onStarIconsClick(favorite)
                }) {

                Icon(
                    tint = if(isFavorite) AmberA200 else Color.Gray,
                    imageVector = Icons.Rounded.StarOutline,
                    contentDescription = ""
                )

            }

            IconButton(
                modifier = modifier.weight(1f),
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = ""
                )
            }
        }
    }
}