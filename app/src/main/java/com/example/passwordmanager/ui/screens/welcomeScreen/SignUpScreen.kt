package com.example.passwordmanager.ui.screens.welcomeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Announcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.ui.components.DefaultSnackbar
import com.example.passwordmanager.ui.screens.welcomeScreen.components.SignInputField
import com.example.passwordmanager.ui.screens.welcomeScreen.components.DisclaimerDialog
import com.example.passwordmanager.ui.viewModel.WelcomeViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = hiltViewModel(),
    navigateToLoginsScreen: () -> Unit
) {


    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val showSnackBar: (String, String) -> Unit = { message, action ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message, action)
        }
    }

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
            text = "Welcome",
            style = MaterialTheme.typography.h4
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            textAlign = TextAlign.Center,
            text = "Let's create your master password",
            style = MaterialTheme.typography.body1.copy(fontSize = 20.sp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        TextButton(
            modifier = modifier
                .padding(8.dp),
            onClick = { viewModel.setIsDisclaimerDialog(true) }
        ) {

            Row {
                Icon(imageVector = Icons.Default.Announcement, contentDescription = "")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Disclaimer")
            }
        }

        if(viewModel.isDisclaimerDialog.value){

            DisclaimerDialog(
                onDismiss = {viewModel.setIsDisclaimerDialog(false)}
            )

        }


        SignInputField(value = viewModel.masterPassword.value,
            onValueChange = {viewModel.setMasterPassword(it)},
            placeholder = "Master Password"
        )

        SignInputField(value = viewModel.confirmMasterPassword.value,
            onValueChange = {viewModel.setConfirmMasterPassword(it)},
            placeholder = "Confirm Master Password"
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
                    viewModel.saveMasterPassword(navigateToLoginsScreen, showSnackBar)
                }
            ) {
                Text(
                    modifier = modifier.padding(4.dp),
                    text = "Complete Setup",
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

}