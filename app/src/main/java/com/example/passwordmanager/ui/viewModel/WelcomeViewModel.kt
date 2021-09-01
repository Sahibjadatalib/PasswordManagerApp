package com.example.passwordmanager.ui.viewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import com.example.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): ViewModel() {


    private val _isDisclaimerDialog = mutableStateOf(false)
    val isDisclaimerDialog: State<Boolean> = _isDisclaimerDialog
    fun setIsDisclaimerDialog(newValue: Boolean){
        _isDisclaimerDialog.value = newValue
    }

    
    private val _masterPassword = mutableStateOf("")
    val masterPassword: State<String> = _masterPassword
    fun setMasterPassword(newValue: String){
        _masterPassword.value = newValue
    }
    
    
    private val _confirmMasterPassword = mutableStateOf("")
    val confirmMasterPassword: State<String> = _confirmMasterPassword
    fun setConfirmMasterPassword(newValue: String){
        _confirmMasterPassword.value = newValue
    }


    private val _passwordHint = mutableStateOf("")
    val passwordHint: State<String> = _passwordHint
    fun setPasswordHint(newValue: String){
        _passwordHint.value = newValue
    }


    private val _hintDialog = mutableStateOf(false)
    val hintDialog: State<Boolean> = _hintDialog
    fun setHintDialog(newValue: Boolean){
        _hintDialog.value = newValue
    }

    private val _storedHint = MutableStateFlow("")
    val storedHint: StateFlow<String> = _storedHint

    private var _storedMasterPassword = MutableStateFlow("")
    val storedMasterPassword: StateFlow<String> = _storedMasterPassword


    init {
        retrieveMasterPassword()
        retrievePasswordHint()
    }


    fun saveMasterPassword(
        navigateToLoginsScreen: ()->Unit,
        showSnackBar: (String, String)->Unit
    ){
        viewModelScope.launch {

            if(_masterPassword.value == _confirmMasterPassword.value
                && _masterPassword.value.isNotEmpty()
            ){
                preferenceStorage.setMasterPassword(_masterPassword.value)
                preferenceStorage.setPasswordHint(_passwordHint.value)
                navigateToLoginsScreen()
            }
            else{
                showSnackBar("Password mismatch!", "Dismiss")
            }

        }
    }



    fun checkMasterPassword(
        navigateToLoginsScreen: ()->Unit,
        showSnackBar: (String, String)->Unit
    ){
        if(_masterPassword.value == _storedMasterPassword.value){
            navigateToLoginsScreen()
        }
        else{
            showSnackBar("Wrong Password!", "Dismiss")
        }
    }



    private fun retrieveMasterPassword(){
        viewModelScope.launch {
            preferenceStorage.masterPassword
                .catch {

                }
                .collect {
                    _storedMasterPassword.value = it
                }
        }
    }

    private fun retrievePasswordHint(){
        viewModelScope.launch {
            preferenceStorage.passwordHint
                .catch {

                }
                .collect {
                    _storedHint.value = it
                }
        }
    }
    

}