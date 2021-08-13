package com.example.passwordmanager.ui.screens.cardsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.room.entity.LoginsItems
import com.example.passwordmanager.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val repository: RoomsRepository
): ViewModel() {


    val title = mutableStateOf("")
    val category = mutableStateOf("Credit")
    val cardNumber = mutableStateOf("")
    val cardHolderName = mutableStateOf("")
    val pinNumber = mutableStateOf("")
    val cvvNumber = mutableStateOf("")
    val issueDate = mutableStateOf("")
    val expiryDate = mutableStateOf("")
    val website = mutableStateOf("")
    val email = mutableStateOf("")
    val number = mutableStateOf("")
    val pinNumberExtra = mutableStateOf("")
    val dateDDMMYYYY = mutableStateOf("")
    val dateMMYY = mutableStateOf("")
    val text = mutableStateOf("")
    val multiLineText = mutableStateOf("")

    val openDialog = mutableStateOf(false)
    fun setOpenDialog(){
        openDialog.value = true
    }
    fun resetOpenDialog(){
        openDialog.value = false
    }

    fun insertLoginsItem(loginsItems: LoginsItems){
        viewModelScope.launch {
            repository.insertLoginsItem(loginsItems)
        }
    }

    private val _results = MutableStateFlow<List<LoginsItems>>(emptyList())
    val results: StateFlow<List<LoginsItems>> = _results

    fun getAllLoginsItems(){
        viewModelScope.launch {
            repository.getAllLoginsItems
                .catch { exception->

                }
                .collect {
                    _results.value = it
                }
        }
    }

}