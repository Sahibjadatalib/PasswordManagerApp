package com.example.passwordmanager.presentation.screens.welcome.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): ViewModel() {

    val passwordField = mutableStateOf("")
    val cnfPasswordField = mutableStateOf("")
    val hintField = mutableStateOf("")
    val disclaimerDialog = mutableStateOf(false)

    fun saveMasterPassword(
        navigateToLoginsScreen: ()->Unit,
        showSnackBar: (String, String)->Unit
    ){
        viewModelScope.launch {

            if(passwordField.value == cnfPasswordField.value
                && passwordField.value.isNotEmpty()
            ){
                preferenceStorage.setMasterPassword(passwordField.value)
                preferenceStorage.setPasswordHint(passwordField.value)
                navigateToLoginsScreen()
            }
            else{
                showSnackBar("Password mismatch!", "Dismiss")
            }

        }
    }

}