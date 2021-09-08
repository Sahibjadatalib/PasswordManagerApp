package com.example.passwordmanager.presentation.screens.logins.logins_edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginsEditViewModel @Inject constructor(
    private val loginsRoomRepository: LoginsRoomRepository
): ViewModel() {


    val title = mutableStateOf("")
    val category = mutableStateOf(0)
    val userName = mutableStateOf("")
    val password = mutableStateOf("")

    fun updateLoginsItem(
        itemId: Int,
        showSnackBar: (String, String) -> Unit,
        popUp: () -> Unit,
    ) {

        viewModelScope.launch {

            if (title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {


                try {

                    loginsRoomRepository.updateLoginsItem(
                        title = title.value,
                        category = category.value,
                        userName = userName.value,
                        password = password.value,
                        itemId = itemId
                    )

                    showSnackBar("updated", "Dismiss")
                    popUp()

                } catch (e: Exception) {
                    showSnackBar(e.message.toString(), "Dismiss")

                }

            }


        }

    }


    fun getItemById(itemId: Int): LiveData<LoginsItems> {
        return loginsRoomRepository.getItemById(itemId = itemId).asLiveData()
    }
}