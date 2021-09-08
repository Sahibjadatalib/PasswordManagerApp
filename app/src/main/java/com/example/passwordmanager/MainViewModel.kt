package com.example.passwordmanager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.Screen
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): ViewModel() {

    val topAppBarTitle = mutableStateOf("")

    val colorForStatusBar = mutableStateOf(Color(0xFF0039cb))

    fun setColorForStatusBar(color:Color){
        colorForStatusBar.value = color
    }

    val storedAppTheme = mutableStateOf(false)
    val storedMasterPassword = mutableStateOf("")
    val storedPasswordHint = mutableStateOf("")

    val currentScreen = mutableStateOf(Screen.WelcomeScreenRoot.route)


    init {
        getMasterPassword()
        getPasswordHint()
        getAppTheme()
    }


    fun setCurrentScreen(screen: String){
        currentScreen.value = screen
    }


    private fun getMasterPassword(){
        viewModelScope.launch {
            preferenceStorage.masterPassword
                .catch {

                }
                .collect {
                    storedMasterPassword.value = it
                }
        }
    }

    private fun getPasswordHint(){
        viewModelScope.launch {
            preferenceStorage.passwordHint
                .catch {

                }
                .collect {
                    storedPasswordHint.value = it
                }
        }
    }

    private fun getAppTheme(){
        viewModelScope.launch {
            preferenceStorage.appTheme
                .catch {

                }
                .collect {
                    storedAppTheme.value = it
                }
        }
    }


}