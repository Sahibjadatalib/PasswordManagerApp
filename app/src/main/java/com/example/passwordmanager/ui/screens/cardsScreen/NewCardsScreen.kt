package com.example.passwordmanager.ui.screens.newItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.model.CardsCategoryOptions
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.screens.cardsScreen.CardsViewModel

@Composable
fun NewCardsScreen(
    viewModel: CardsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState()

    mainViewModel.setColorForStatusBar(Color.White)



    Scaffold(
        topBar = {
            NewItemTopAppBar(
                topAppBarTitle = LeafScreen.NewCardsItem.label,
                onCancelIconClick = {},
                onDoneIconClick = {}
            )
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            //title field
            TitleField(
                text = viewModel.title.value,
                onTextChange = { viewModel.title.value = it },
                onTitleTrailingIconClick = {}
            )

            //category
            Category(
                categoryList = CardsCategoryOptions,
                selectedCategory = viewModel.category
            )


            AddFieldsBtn(
                onClick = { viewModel.setOpenDialog() }
            )

//            if (viewModel.openDialog.value) {
//                AddFieldDialog(
//                    onDismiss = { viewModel.resetOpenDialog() },
//                    extraField = extraFieldsList
//                )
//
//            }

        }
    }


}