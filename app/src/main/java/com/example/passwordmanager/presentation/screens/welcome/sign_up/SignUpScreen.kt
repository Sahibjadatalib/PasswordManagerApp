package com.example.passwordmanager.presentation.screens.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Announcement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.ui.screens.welcomeScreen.components.SignInputField
import com.example.passwordmanager.ui.screens.welcomeScreen.components.DisclaimerDialog
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.presentation.screens.welcome.sign_up.SignUpViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp).verticalScroll(scrollState),
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

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(
            modifier = modifier
                .padding(8.dp),
            onClick = { viewModel.disclaimerDialog.value=true }
        ) {

            Row {
                Icon(imageVector = Icons.Default.Announcement, contentDescription = "")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Disclaimer")
            }
        }

        if(viewModel.disclaimerDialog.value){

            DisclaimerDialog(
                onDismiss = {viewModel.disclaimerDialog.value = false}
            )

        }


        SignInputField(value = viewModel.passwordField.value,
            onValueChange = {viewModel.passwordField.value = it},
            placeholder = "Master Password"
        )

        SignInputField(value = viewModel.cnfPasswordField.value,
            onValueChange = {viewModel.cnfPasswordField.value = it},
            placeholder = "Confirm Master Password"
        )

        SignInputField(value = viewModel.hintField.value,
            onValueChange = {viewModel.hintField.value= it},
            placeholder = "hint (optional)"
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
                    viewModel.saveMasterPassword(actions.navigateToAllLoginsFromWelcome, actions.showSnackBar)
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
}