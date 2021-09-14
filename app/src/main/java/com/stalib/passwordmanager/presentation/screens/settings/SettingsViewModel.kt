package com.stalib.passwordmanager.presentation.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.dataStore.PreferenceStorage
import com.stalib.passwordmanager.data.repository.CardsRoomRepository
import com.stalib.passwordmanager.data.repository.LoginsRoomRepository
import com.stalib.passwordmanager.data.repository.OthersRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val loginsRoomRepository: LoginsRoomRepository,
    private val cardsRoomRepository: CardsRoomRepository,
    private val othersRoomRepository: OthersRoomRepository,
    private val preferenceStorage: PreferenceStorage
) : ViewModel() {

    val currPasswordField = mutableStateOf("")
    val newPasswordField = mutableStateOf("")
    val cnfNewPasswordField = mutableStateOf("")
    val newHintField = mutableStateOf("")

    val changePasswordDialog = mutableStateOf(false)
    val changeHintDialog = mutableStateOf(false)
    val deleteDataDialog = mutableStateOf(false)
    val resetAppDialog = mutableStateOf(false)

    val themeSwitch = mutableStateOf(false)

    val storedPassword = mutableStateOf("")
    val storedHint = mutableStateOf("")
    val storedTheme = mutableStateOf(false)

    init {
        getCurrentPassword()
        getCurrentHint()
        retrieveTheme()
    }

    fun changePassword(
        showSnackBar: (String, String) -> Unit
    ) {

        viewModelScope.launch {

            try {

                if (newPasswordField.value.isNotEmpty() &&
                    newPasswordField.value == cnfNewPasswordField.value &&
                    currPasswordField.value == storedPassword.value
                ) {
                    preferenceStorage.setMasterPassword(newPasswordField.value)
                    changePasswordDialog.value = false
                    showSnackBar("Password changed successfully", "Dismiss")
                } else {
                    when {
                        newPasswordField.value.isEmpty() -> {
                            showSnackBar("Master password cannot be empty", "Dismiss")
                        }
                        newPasswordField.value != cnfNewPasswordField.value -> {
                            showSnackBar("Password mismatch", "Dismiss")
                        }
                        currPasswordField.value != newPasswordField.value -> {
                            showSnackBar("Password mismatch!","Dismiss")
                        }
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBar(e.message.toString(), "Dismiss")
            }

        }
    }

    private fun getCurrentPassword() {

        viewModelScope.launch {

            preferenceStorage.masterPassword
                .catch {

                }
                .collect {
                    storedPassword.value = it
                }

        }

    }

    private fun getCurrentHint() {

        viewModelScope.launch {

            preferenceStorage.passwordHint
                .catch {

                }
                .collect {
                    storedHint.value = it
                }

        }

    }

    fun changeHint(
        showSnackBar: (String, String) -> Unit
    ) {

        viewModelScope.launch {

            try {
                preferenceStorage.setPasswordHint(newHintField.value)
                storedHint.value = newHintField.value
                changeHintDialog.value = false
                showSnackBar("Hint changed successfully", "Dismiss")
            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBar(e.message.toString(), "Dismiss")
            }

        }
    }

    fun changeTheme(switchState: Boolean) {

        viewModelScope.launch {

            try {
                themeSwitch.value = switchState
                preferenceStorage.setAppTheme(themeSwitch.value)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    private fun retrieveTheme() {

        viewModelScope.launch {

            preferenceStorage.appTheme
                .catch {

                }
                .collect {
                    themeSwitch.value = it
                    storedTheme.value = it
                }

        }

    }


    fun deleteAllData(
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            try {
                loginsRoomRepository.deleteAllLogins()
                othersRoomRepository.deleteAllOthers()
                cardsRoomRepository.deleteAllCards()
                showSnackBar("All data deleted", "Dismiss")
            } catch (e: Exception) {
                showSnackBar("Deletion failed!", "Dismiss")
            }

        }
    }


    fun resetApp(
        showSnackBar: (String, String) -> Unit,
        navigateToSignUpScreen: () -> Unit
    ) {
        viewModelScope.launch {

            try {

                loginsRoomRepository.deleteAllLogins()
                othersRoomRepository.deleteAllOthers()
                cardsRoomRepository.deleteAllCards()
                preferenceStorage.setMasterPassword("")
                preferenceStorage.setPasswordHint("")
                preferenceStorage.setAppTheme(false)
                changePasswordDialog.value = false
                showSnackBar("Reset done successfully", "Dismiss")
                navigateToSignUpScreen()

            } catch (e: Exception) {
                e.printStackTrace()
                changePasswordDialog.value = false
                showSnackBar(e.message.toString(), "Dismiss")
            }

        }
    }

}