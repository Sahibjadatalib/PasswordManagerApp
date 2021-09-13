package com.example.passwordmanager.presentation.screens.logins.logins_new

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLoginsViewModel @Inject constructor(
    private val loginsRoomRepository: LoginsRoomRepository
): ViewModel() {


    val title = mutableStateOf("")
    val category = mutableStateOf(0)
    val userName = mutableStateOf("")
    val password = mutableStateOf("")

    fun insertLoginsItem(
        popUp: () -> Unit,
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            if (title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val loginsItem = LoginsItems(
                    title = title.value,
                    category = category.value,
                    userName = userName.value,
                    passWord = password.value
                )

                try {

                    loginsRoomRepository.insertLoginsItem(loginsItems = loginsItem)

                    showSnackBar("Saved successfully", "Dismiss")
                    popUp()

                } catch (e: Exception) {
                    showSnackBar(e.message.toString(), "Dismiss")

                }

            }


        }
    }


}