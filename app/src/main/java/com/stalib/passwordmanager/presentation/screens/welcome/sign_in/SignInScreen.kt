package com.stalib.passwordmanager.presentation.screens.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stalib.passwordmanager.R
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.ui.screens.welcomeScreen.components.HintDialog
import com.stalib.passwordmanager.ui.screens.welcomeScreen.components.SignInputField
import com.stalib.passwordmanager.presentation.theme.Theme
import com.stalib.passwordmanager.MainViewModel
import com.stalib.passwordmanager.presentation.screens.welcome.sign_in.SignInViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    if (viewModel.storedPassword.value.isEmpty()) {
        actions.navigateToSignUpScreen()
    } else {

        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(Theme.paddings.medium)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.welcome_back),
                style = Theme.typography.h4
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.enter_password),
                style = Theme.typography.subtitle1
            )

            Spacer(modifier = Modifier.height(100.dp))

            IconButton(onClick = { viewModel.hintDialog.value = true }) {
                Icon(
                    tint = Theme.colors.primary,
                    imageVector = Icons.Default.Help, contentDescription = ""
                )
            }

            if (viewModel.hintDialog.value) {

                HintDialog(
                    onDismiss = { viewModel.hintDialog.value = false },
                    hint = viewModel.storedHint.value
                )

            }

            SignInputField(
                value = viewModel.passwordField.value,
                onValueChange = { viewModel.passwordField.value = it },
                placeholder = stringResource(R.string.textfield_hint_master_password)
            )

            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(Theme.paddings.medium),
                shape = Theme.shapes.medium,
                elevation = Theme.elevation.medium
            ) {
                Button(
                    modifier = modifier,
                    onClick = {
                        viewModel.checkMasterPassword(
                            actions.navigateToAllLoginsFromWelcome,
                            actions.showSnackBar
                        )
                    }
                ) {
                    Text(
                        modifier = modifier.padding(Theme.paddings.medium),
                        text = stringResource(R.string.continue_btn),
                        style = Theme.typography.button

                    )
                }
            }
        }


    }
}