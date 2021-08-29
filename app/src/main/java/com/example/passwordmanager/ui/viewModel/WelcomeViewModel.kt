package com.example.passwordmanager.ui.viewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
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


    private val _storedMasterPassword: MutableState<String?> = mutableStateOf(null)
    val storedMasterPassword: State<String?> = _storedMasterPassword


    init {
        retrieveMasterPassword()
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

    fun retrieveMasterPassword(){
        viewModelScope.launch {
            val result = preferenceStorage.masterPassword.firstOrNull()
            result?.let {
                _storedMasterPassword.value = result
            }
        }
    }
    

}