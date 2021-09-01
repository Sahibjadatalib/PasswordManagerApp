package com.example.passwordmanager.ui.screens.welcomeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.ui.components.DefaultSnackbar
import com.example.passwordmanager.ui.screens.welcomeScreen.components.HintDialog
import com.example.passwordmanager.ui.screens.welcomeScreen.components.SignInputField
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.example.passwordmanager.ui.viewModel.WelcomeViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    viewModel: WelcomeViewModel = hiltViewModel(),
    navigateToLoginsScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val showSnackBar: (String, String) -> Unit = { message, action ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message, action)
        }
    }

    if (viewModel.storedMasterPassword.value.isEmpty()) {
        navigateToSignUpScreen()
    } else {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                textAlign = TextAlign.Center,
                text = "Welcome Back",
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                textAlign = TextAlign.Center,
                text = "Please enter your master password",
                style = MaterialTheme.typography.body1.copy(fontSize = 20.sp)
            )

            Spacer(modifier = Modifier.height(120.dp))

            IconButton(onClick = { viewModel.setHintDialog(true) }) {
                Icon(
                    tint = MaterialTheme.colors.primary,
                    imageVector = Icons.Default.Help, contentDescription = ""
                )
            }

            SignInputField(
                value = viewModel.masterPassword.value,
                onValueChange = { viewModel.setMasterPassword(it) },
                placeholder = "Master Password"
            )

            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp
            ) {
                Button(
                    modifier = modifier,
                    onClick = {
                        viewModel.checkMasterPassword(navigateToLoginsScreen, showSnackBar)
                    }
                ) {
                    Text(
                        modifier = modifier.padding(4.dp),
                        text = "Continue",
                        style = MaterialTheme.typography.button.copy(fontSize = 16.sp)

                    )
                }
            }


        }

        Box(modifier = Modifier.fillMaxSize()) {
            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

        if (viewModel.hintDialog.value) {

            HintDialog(
                onDismiss = { viewModel.setHintDialog(false) },
                hint = viewModel.storedHint.value
            )

        }

    }


}