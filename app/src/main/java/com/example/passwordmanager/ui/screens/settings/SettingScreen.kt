package com.example.passwordmanager.ui.screens.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.model.dangerZoneSettings
import com.example.passwordmanager.model.helpsAndAboutSettings
import com.example.passwordmanager.model.securitySettings
import com.example.passwordmanager.ui.components.DefaultSnackbar
import com.example.passwordmanager.ui.components.SettingsTopAppBar
import com.example.passwordmanager.ui.screens.settings.components.*
import com.example.passwordmanager.ui.viewModel.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateToSignUpScreen: ()->Unit,
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

    val suggestionsIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf("stalib420@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Suggestions")
        putExtra(Intent.EXTRA_TEXT, "i want to suggest you...")
        type = "message/rfc822"
    }

    val contactUsIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf("stalib420@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Contact")
        putExtra(Intent.EXTRA_TEXT, "hey, I want to contact you...")
        type = "message/rfc822"
    }

    val reportBugIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf("stalib420@gmail.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Bug Report")
        putExtra(Intent.EXTRA_TEXT, "i want to report a bug...")
        type = "message/rfc822"
    }

//    val rateAppIntent = Intent(Intent.ACTION_VIEW).apply {
//        data = try {
//            Uri.parse("market://details?id=$packageName")
//        }catch (e: ActivityNotFoundException){
//            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
//        }
//    }

    val privacyPolicyIntent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("http://www.google.com")
    }


    Scaffold(
        topBar = {
            SettingsTopAppBar(
                onBackBtnClick = popUp
            )
        }
    ) {

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            AppDetails()

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
                    onchangeHint = {viewModel.setHint(it)},
                    onSaveClick = {
                        viewModel.changeHint(showSnackBar = showSnackBar)
                        viewModel.setChangeHintDialog(false)
                    },
                    currentHint = viewModel.storedHint.value
                )
            }

            if(viewModel.deleteDataDialog.value){
                DeleteDataDialog(onDismiss = { viewModel.setDeleteDataDialog(false) }) {
                    viewModel.deleteAllData(showSnackBar)
                }
            }

            if(viewModel.resetAppDialog.value){
                ResetAppDialog(
                    onDismiss = { viewModel.setResetAppDialog(false) },
                    onConfirmClick = {
                        viewModel.resetApp(showSnackBar,navigateToSignUpScreen)
                    }
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