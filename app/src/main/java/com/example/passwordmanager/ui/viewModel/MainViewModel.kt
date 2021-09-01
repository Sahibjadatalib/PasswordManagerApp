package com.example.passwordmanager.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.dataStore.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
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

    private val _storedHint = mutableStateOf("")
    val storedHint: State<String> = _storedHint

    private val _storedMasterPassword: MutableState<String?> = mutableStateOf(null)
    val storedMasterPassword: State<String?> = _storedMasterPassword

    init {
        retrieveMasterPassword()
        retrievePasswordHint()
    }


    fun retrieveMasterPassword(){
        viewModelScope.launch {
            val result = preferenceStorage.masterPassword.firstOrNull()
            result?.let {
                _storedMasterPassword.value = result
            }
        }
    }

    fun retrievePasswordHint(){
        viewModelScope.launch {
            val result = preferenceStorage.passwordHint.firstOrNull()
            result?.let {
                _storedHint.value = result
            }
        }
    }


}