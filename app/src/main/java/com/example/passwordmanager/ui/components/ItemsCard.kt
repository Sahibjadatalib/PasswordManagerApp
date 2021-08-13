package com.example.passwordmanager.ui.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil.compose.rememberImagePainter
import com.example.passwordmanager.R

@Composable
fun ItemsCard(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    itemIcon: String,
    onStarIconsClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {

        Divider(color = Color.LightGray, thickness = 1.dp)
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = modifier
                    .width(48.dp)
                    .height(48.dp)
                    .weight(2f),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Yellow
            ) {
//                Icon(
//                    modifier = modifier.padding(4.dp),
//                    tint = Color.Green,
//                    imageVector = Image(itemIcon), contentDescription = ""
//                )

                Image(painter = rememberImagePainter(itemIcon),
                    contentDescription = "")
                
                //Text(text = itemIcon)
            }

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
                onClick = { onStarIconsClick() }) {
                Icon(
                    imageVector = Icons.Outlined.StarBorder, contentDescription = ""
                )
            }

            IconButton(
                modifier = modifier.weight(1f),
                onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            }
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}