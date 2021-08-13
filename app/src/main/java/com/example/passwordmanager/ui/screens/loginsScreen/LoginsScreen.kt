package com.example.passwordmanager.ui.screens.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.ui.components.*
import com.example.passwordmanager.ui.screens.loginsScreen.LoginsViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginsScreen(
    viewModel: LoginsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navController: NavController
) {


    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val items = viewModel.results.collectAsState()
    val scrollState = rememberScrollState()

    val showSnackBar: (String,String)->Unit = { message,action->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message,action)
        }
    }


    Scaffold(
        topBar = {
            HomeTopAppBar(
                topAppBarTitle = LeafScreen.AllLogins.label,
                onMenuIconClick = {},
                onSortIconClick = {},
                onSearchIconClick = {}
            )
        },
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        floatingActionButton = {
            MyFloatingBtn(
                navController = navController,
                onClick = {navController.navigate(LeafScreen.NewLoginsItem.route)}
            )
        },
        drawerContent = {
            //MyDrawer()
        },
        bottomBar = {
            MyBottomBar(
                navController = navController
            )

        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,

        ) {

        Box(modifier = Modifier.fillMaxSize()){

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 48.dp)
                    .verticalScroll(scrollState)
            ) {

                items.value.forEach { item ->
                    ItemsCard(
                        title = item.title,
                        text = item.userName!!,
                        itemIcon = item.itemIcon!!,
                        onStarIconsClick = {
                            viewModel.deleteLoginsItem(item)
                            showSnackBar("deleted", "dismissss")
                        }
                    )


                }

            }


        }


    }


    Box(modifier = Modifier.fillMaxSize()){
        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }




}


