package com.example.passwordmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import kotlin.math.exp

@Composable
fun ItemsCard(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    itemIcon: ImageVector,
    itemIconColor: Color,
    isFavorite: Boolean,
    onStarIconsClick: (Boolean) -> Unit,
    onItemCardClick: () -> Unit,
    onEditMenuClick: ()-> Unit,
    onDeleteMenuClick: ()->Unit
) {

    var favorite by remember { mutableStateOf(isFavorite) }

    Row(
        modifier = modifier.clickable {
            onItemCardClick()
        }.padding(16.dp),
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
                tint = if (isFavorite) AmberA200 else Color.Gray,
                imageVector = Icons.Rounded.StarOutline,
                contentDescription = ""
            )

        }

        PopUpMenu(
            onDeleteMenuClick = onDeleteMenuClick,
            onEditMenuClick = onEditMenuClick
        )



    }

}

@Composable
fun PopUpMenu(
    modifier: Modifier = Modifier,
    onDeleteMenuClick: ()->Unit,
    onEditMenuClick: ()->Unit
){

    var expanded by remember { mutableStateOf(false) }

    IconButton(
        modifier = modifier,
        onClick = { expanded = !expanded }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = ""
        )


        DropdownMenu(
            modifier = modifier.width(170.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {

            DropdownMenuItem(onClick = { onEditMenuClick() }) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Edit")
                    Icon(
                        tint = MaterialTheme.colors.primary,
                        imageVector = Icons.Default.Edit,
                        contentDescription = ""
                    )
                }
            }

            Divider()

            DropdownMenuItem(onClick = { onDeleteMenuClick() }) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Delete")
                    Icon(
                        tint = Color.Red,
                        imageVector = Icons.Default.Delete,
                        contentDescription = ""
                    )
                }
            }

        }
    }


}