package com.example.passwordmanager.ui.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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


    private val _currentPass = mutableStateOf("")
    val currentPass: State<String> = _currentPass
    fun setCurrentPass(newValue: String) {
        _currentPass.value = newValue
    }


    private val _newPass = mutableStateOf("")
    val newPass: State<String> = _newPass
    fun setNewPass(newValue: String) {
        _newPass.value = newValue
    }


    private val _confirmNewPass = mutableStateOf("")
    val confirmNewPass: State<String> = _confirmNewPass
    fun setConfirmNewPass(newValue: String) {
        _confirmNewPass.value = newValue
    }


    private val _hint = mutableStateOf("")
    val hint: State<String> = _hint
    fun setHint(newValue: String) {
        _hint.value = newValue
    }


    private val _storedHint = MutableStateFlow("")
    val storedHint: StateFlow<String> = _storedHint


    private val _changePassDialog = mutableStateOf(false)
    val changePassDialog: State<Boolean> = _changePassDialog
    fun setChangePassDialog(newValue: Boolean) {
        _changePassDialog.value = newValue
    }


    private val _changeHintDialog = mutableStateOf(false)
    val changeHintDialog: State<Boolean> = _changeHintDialog
    fun setChangeHintDialog(newValue: Boolean) {
        _changeHintDialog.value = newValue
    }


    private val _deleteDataDialog = mutableStateOf(false)
    val deleteDataDialog: State<Boolean> = _deleteDataDialog
    fun setDeleteDataDialog(newValue: Boolean) {
        _deleteDataDialog.value = newValue
    }


    private val _resetAppDialog = mutableStateOf(false)
    val resetAppDialog: State<Boolean> = _resetAppDialog
    fun setResetAppDialog(newValue: Boolean) {
        _resetAppDialog.value = newValue
    }


    private val _themeSwitch = mutableStateOf(false)
    val themeSwitch: State<Boolean> = _themeSwitch

    private val _storedAppTheme = MutableStateFlow(false)
    val storedAppTheme: StateFlow<Boolean> = _storedAppTheme



    init {
        getCurrentHint()
        retrieveTheme()
    }

    fun changePassword(
        showSnackBar: (String, String) -> Unit
    ) {

        viewModelScope.launch {

            try {

                if (_newPass.value.isEmpty()) {
                    showSnackBar("master password cannot be empty", "Dismiss")
                } else {
                    preferenceStorage.setMasterPassword(_newPass.value)
                    _changePassDialog.value = false
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
                    _storedHint.value = it
                }

        }

    }

    fun changeHint(
        showSnackBar: (String, String) -> Unit
    ) {

        viewModelScope.launch {

            try {
                preferenceStorage.setPasswordHint(_hint.value)
                _storedHint.value = _hint.value
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
                _themeSwitch.value = switchState
                preferenceStorage.setAppTheme(_themeSwitch.value)
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
                    _themeSwitch.value = it
                    _storedAppTheme.value = it
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
                Log.e(TAG, "deleteData: delete data done successfully")
            } catch (e: Exception) {
                e.printStackTrace()
                showSnackBar("deletion failed","Dismiss")
                Log.e(TAG, "deleteData: delete data failed")
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
                _changePassDialog.value = false
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