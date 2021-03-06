package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.presentation.theme.Theme

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
    onEditMenuClick: () -> Unit,
    onDeleteMenuClick: () -> Unit
) {

    var favorite by remember { mutableStateOf(isFavorite) }

    Row(
        modifier = modifier
            .clickable {
                onItemCardClick()
            }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Surface(
            modifier = Modifier.wrapContentSize().padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            color = itemIconColor.copy(alpha = 0.25f)
        ) {

            Icon(
                modifier = Modifier.padding(8.dp).size(32.dp),
                tint = itemIconColor,
                imageVector = itemIcon,
                contentDescription = ""
            )


        }



        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(7f)
        ) {
            Text(
                text = title,
                style = Theme.typography.subtitle1.copy(fontWeight = FontWeight.W500),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                softWrap = true
            )

            Text(
                text = text,
                style = Theme.typography.subtitle1.copy(fontWeight = FontWeight.Light),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                softWrap = true

            )

        }


        IconButton(onClick = {
            favorite = !favorite
            onStarIconsClick(favorite)
        }) {
            Icon(
                tint = if(isFavorite) Theme.colors.primary else Color.Gray,
                imageVector = Icons.Default.Favorite,
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
    onDeleteMenuClick: () -> Unit,
    onEditMenuClick: () -> Unit
) {

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

            DropdownMenuItem(onClick = {
                onEditMenuClick()
                expanded = false
            }) {
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

            DropdownMenuItem(onClick = {
                onDeleteMenuClick()
                expanded = false
            }) {
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