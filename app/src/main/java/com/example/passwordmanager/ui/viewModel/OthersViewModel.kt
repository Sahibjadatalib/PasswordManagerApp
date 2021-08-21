package com.example.passwordmanager.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OthersViewModel @Inject constructor(
    private val repository: RoomsRepository
): ViewModel() {

    val title = mutableStateOf("")
    val category = mutableStateOf("Note")
    val description = mutableStateOf("")
    val macAddress = mutableStateOf("")
    val userName = mutableStateOf("")
    val password = mutableStateOf("")
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

//    fun insertLoginsItem(loginsItems: LoginsItems){
//        viewModelScope.launch {
//            repository.insertLoginsItem(loginsItems)
//        }
//    }
//
//    private val _results = MutableStateFlow<List<LoginsItems>>(emptyList())
//    val results: StateFlow<List<LoginsItems>> = _results
//
//    fun getAllLoginsItems(){
//        viewModelScope.launch {
//            repository.getAllLoginsItems()
//                .catch { exception->
//
//                }
//                .collect {
//                    _results.value = it
//                }
//        }
//    }

}