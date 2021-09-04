package com.example.passwordmanager.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.model.dangerZoneSettings
import com.example.passwordmanager.model.generalSettings
import com.example.passwordmanager.model.helpsAndAboutSettings
import com.example.passwordmanager.model.securitySettings
import com.example.passwordmanager.ui.components.DefaultSnackbar
import com.example.passwordmanager.ui.components.SettingsTopAppBar
import com.example.passwordmanager.ui.screens.settings.components.*
import com.example.passwordmanager.ui.viewModel.SettingsViewModel
import com.example.passwordmanager.util.contactUsIntent
import com.example.passwordmanager.util.privacyPolicyIntent
import com.example.passwordmanager.util.reportBugIntent
import com.example.passwordmanager.util.suggestionsIntent
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit,
    popUp: () -> Unit
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val showSnackBar: (String, String) -> Unit = { message, action ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message, action)
        }
    }

    Scaffold(
        topBar = {
            SettingsTopAppBar(
                onBackBtnClick = popUp
            )
        },
    ) {

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            AppDetails()

            SettingsSection(
                header = "General",
                content = {

                    SwitchableSettingsRow(
                        item = generalSettings[0],
                        switchState = viewModel.themeSwitch.value,
                        onSwitchClick = { viewModel.changeTheme(it) }
                    )
                }
            )


            SettingsSection(
                header = "Help & About",
                content = {
                    SettingsRow(
                        item = helpsAndAboutSettings[0],
                        onClick = { startActivity(context, contactUsIntent, null) }
                    )
                    SettingsRow(
                        item = helpsAndAboutSettings[1],
                        onClick = { startActivity(context, suggestionsIntent, null) }
                    )
                    SettingsRow(
                        item = helpsAndAboutSettings[2],
                        onClick = { startActivity(context, reportBugIntent, null) }
                    )
                    SettingsRow(
                        item = helpsAndAboutSettings[3],
                        onClick = { startActivity(context, privacyPolicyIntent, null) }
                    )
                    SettingsRow(
                        item = helpsAndAboutSettings[4],
                        onClick = { startActivity(context, privacyPolicyIntent, null) }
                    )
                }
            )

            SettingsSection(
                header = "Security",
                content = {

                    SettingsRow(
                        item = securitySettings[0],
                        onClick = {
                            viewModel.setChangePassDialog(true)
                        }
                    )
                    SettingsRow(
                        item = securitySettings[1],
                        onClick = {
                            viewModel.setChangeHintDialog(true)
                        }
                    )
                }
            )

            SettingsSection(
                header = "Danger Zone",
                content = {

                    SettingsRow(
                        item = dangerZoneSettings[0],
                        onClick = {
                            viewModel.setDeleteDataDialog(true)
                        }
                    )
                    SettingsRow(
                        item = dangerZoneSettings[1],
                        onClick = {
                            viewModel.setResetAppDialog(true)
                        }
                    )
                }
            )




        }

    }


    if (viewModel.changePassDialog.value) {
        ChangePasswordDialog(
            onDismiss = { viewModel.setChangePassDialog(false) },
            currentPassword = viewModel.currentPass.value,
            onCurrentPasswordChange = { viewModel.setCurrentPass(it) },
            newPassword = viewModel.newPass.value,
            onNewPasswordChange = { viewModel.setNewPass(it) },
            confirmNewPassword = viewModel.confirmNewPass.value,
            onConfirmNewPassword = { viewModel.setConfirmNewPass(it) },
            onSaveClick = {
                viewModel.changePassword(showSnackBar = showSnackBar)
            }
        )
    }

    if (viewModel.changeHintDialog.value) {
        ChangeHintDialog(
            onDismiss = { viewModel.setChangeHintDialog(false) },
            hint = viewModel.hint.value,
            onchangeHint = { viewModel.setHint(it) },
            onSaveClick = {
                viewModel.changeHint(showSnackBar = showSnackBar)
                viewModel.setChangeHintDialog(false)
            },
            currentHint = viewModel.storedHint.value
        )
    }

    if (viewModel.deleteDataDialog.value) {
        DeleteDataDialog(onDismiss = { viewModel.setDeleteDataDialog(false) }) {
            viewModel.deleteAllData(showSnackBar)
        }
    }

    if (viewModel.resetAppDialog.value) {
        ResetAppDialog(
            onDismiss = { viewModel.setResetAppDialog(false) },
            onConfirmClick = {
                viewModel.resetApp(showSnackBar, navigateToSignUpScreen)
            }
        )
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