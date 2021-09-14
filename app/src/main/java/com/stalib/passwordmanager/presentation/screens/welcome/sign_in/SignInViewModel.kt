package com.stalib.passwordmanager.presentation.screens.welcome.sign_in

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.dataStore.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): ViewModel() {

    val storedPassword = mutableStateOf("")
    val storedHint = mutableStateOf("")
    val passwordField = mutableStateOf("")
    val hintDialog = mutableStateOf(false)

    init {
        retrieveMasterPassword()
        retrievePasswordHint()
    }

    fun checkMasterPassword(
        navigateToLoginsScreen: ()->Unit,
        showSnackBar: (String, String)->Unit
    ){
        if(passwordField.value == storedPassword.value){
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
                    storedPassword.value = it
                }
        }
    }

    private fun retrievePasswordHint(){
        viewModelScope.launch {
            preferenceStorage.passwordHint
                .catch {

                }
                .collect {
                    storedHint.value = it
                }
        }
    }


}