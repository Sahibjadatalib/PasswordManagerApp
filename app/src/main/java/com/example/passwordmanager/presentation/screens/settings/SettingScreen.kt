package com.example.passwordmanager.presentation.screens.settings

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passwordmanager.presentation.common_components.SettingsTopAppBar
import com.example.passwordmanager.presentation.navigation.MainActions
import com.example.passwordmanager.ui.screens.settings.components.*
import com.example.passwordmanager.MainViewModel
import com.example.passwordmanager.domain.models.*
import com.example.passwordmanager.presentation.screens.settings.components.DangerZoneSection
import com.example.passwordmanager.presentation.screens.settings.components.GeneralSection
import com.example.passwordmanager.presentation.screens.settings.components.HelpAndAboutSection
import com.example.passwordmanager.presentation.screens.settings.components.SecuritySection
import com.example.passwordmanager.presentation.theme.Theme
import com.example.passwordmanager.util.contactUsIntent
import com.example.passwordmanager.util.privacyPolicyIntent
import com.example.passwordmanager.util.reportBugIntent
import com.example.passwordmanager.util.suggestionsIntent

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    scaffoldState: ScaffoldState,
    actions: MainActions
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val (currPass, currPassSetter) = viewModel.currPasswordField
    val (newPass, newPassSetter) = viewModel.newPasswordField
    val (cnfNewPass, cnfNewPassSetter) = viewModel.cnfNewPasswordField
    val (newHint, newHintSetter) = viewModel.newHintField

    Scaffold(
        topBar = { SettingsTopAppBar(onBackBtnClick = actions.popUp) },
    ) {

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            AppDetails()

            Spacer(modifier = Modifier.height(8.dp))

            GeneralSection(
                header = "General",
                list = generalSettings,
                switchState = viewModel.themeSwitch.value,
                onSwitchClick = { viewModel.changeTheme(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            HelpAndAboutSection(
                header = "Help&About",
                list = helpsAndAboutSettings,
                onContactUsClick = { startActivity(context, contactUsIntent, null) },
                onSentSuggestion = { startActivity(context, suggestionsIntent, null) },
                onReportBug = { startActivity(context, reportBugIntent, null) },
                onPrivacy = { startActivity(context, privacyPolicyIntent, null) },
                onRateApp = { startActivity(context, privacyPolicyIntent, null) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            SecuritySection(
                header = "Security",
                list = securitySettings,
                onChangePassClick = { viewModel.changePasswordDialog.value = true },
                onChangeHintClick = {viewModel.changeHintDialog.value = true}
            )

            Spacer(modifier = Modifier.height(8.dp))

            DangerZoneSection(
                header = "Danger Zone",
                list = dangerZoneSettings,
                onDeleteClick = { viewModel.deleteDataDialog.value = true },
                onResetClick = {viewModel.resetAppDialog.value = true}
            )

            Spacer(modifier = Modifier.height(8.dp))


        }


        if (viewModel.changePasswordDialog.value) {
            ChangePasswordDialog(
                onDismiss = { viewModel.changePasswordDialog.value = false },
                currentPassword = currPass,
                onCurrentPasswordChange = currPassSetter,
                newPassword = newPass,
                onNewPasswordChange = newPassSetter,
                confirmNewPassword = cnfNewPass,
                onConfirmNewPassword = cnfNewPassSetter,
                onSaveClick = { viewModel.changePassword(showSnackBar = actions.showSnackBar) }
            )
        }

        if (viewModel.changeHintDialog.value) {
            ChangeHintDialog(
                onDismiss = { viewModel.changeHintDialog.value = false },
                hint = newHint,
                onchangeHint = newHintSetter,
                onSaveClick = { viewModel.changeHint(showSnackBar = actions.showSnackBar) },
                currentHint = viewModel.storedHint.value
            )
        }

        if (viewModel.deleteDataDialog.value) {
            DeleteDataDialog(
                onDismiss = { viewModel.deleteDataDialog.value = false},
                onConfirmClick = {viewModel.deleteAllData(actions.showSnackBar)}
            )
        }

        if (viewModel.resetAppDialog.value) {
            ResetAppDialog(
                onDismiss = { viewModel.resetAppDialog.value = false },
                onConfirmClick = {viewModel.resetApp(actions.showSnackBar, actions.navigateToSignUpScreen)}
            )
        }

    }

}








