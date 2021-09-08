package com.example.passwordmanager.presentation.screens.settings

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import com.example.passwordmanager.data.repository.CardsRoomRepository
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.repository.OthersRoomRepository
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

    val storedHint = mutableStateOf("")
    val storedTheme = mutableStateOf(false)

    init {
        getCurrentHint()
        retrieveTheme()
    }

    fun changePassword(
        showSnackBar: (String, String) -> Unit
    ) {

        viewModelScope.launch {

            try {

                if (newPasswordField.value.isEmpty()) {
                    showSnackBar("master password cannot be empty", "Dismiss")
                } else {
                    preferenceStorage.setMasterPassword(newPasswordField.value)
                    changePasswordDialog.value = false
                    showSnackBar("master password changed", "Dismiss")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBar(e.message.toString(), "Dismiss")
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
                showSnackBar("password hint changed", "Dismiss")
            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBar(e.message.toString(), "Dismiss")
            }

        }
    }

    fun changeTheme(switchState: Boolean){

        viewModelScope.launch {

            try {
                themeSwitch.value = switchState
                preferenceStorage.setAppTheme(themeSwitch.value)
            }catch (e:Exception){
                e.printStackTrace()
            }

        }

    }

    private fun retrieveTheme(){

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
                showSnackBar("All data deleted","Dismiss")
            } catch (e: Exception) {
                showSnackBar("deletion failed","Dismiss")
            }

        }
    }


    fun resetApp(
        showSnackBar: (String, String) -> Unit,
        navigateToSignUpScreen: ()->Unit
    ) {
        viewModelScope.launch {

            try {

                loginsRoomRepository.deleteAllLogins()
                othersRoomRepository.deleteAllOthers()
                cardsRoomRepository.deleteAllCards()
                preferenceStorage.setMasterPassword("")
                preferenceStorage.setPasswordHint("")
                changePasswordDialog.value = false
                showSnackBar("reset done","Dismiss")
                Log.e(TAG, "resetApp: reset app done successfully")
                navigateToSignUpScreen()

            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBar(e.message.toString(),"Dismiss")
                Log.e(TAG, "resetApp: reset app failed")
            }

        }
    }

}