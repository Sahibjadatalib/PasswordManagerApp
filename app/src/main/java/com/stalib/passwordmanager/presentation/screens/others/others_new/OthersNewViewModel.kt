package com.stalib.passwordmanager.presentation.screens.others.others_new

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.OthersRoomRepository
import com.stalib.passwordmanager.data.room.entity.OthersItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OthersNewViewModel @Inject constructor(
    private val othersRoomRepository: OthersRoomRepository
): ViewModel() {

    val title = mutableStateOf("")
    val category = mutableStateOf(0)
    val userName = mutableStateOf("")
    val password = mutableStateOf("")
    val description = mutableStateOf("")
    val macAddress = mutableStateOf("")

    fun insertOthersItem(
        popUp: () -> Unit,
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            if (title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                when(category.value){
                    0 -> {
                        userName.value = ""
                        password.value = ""
                        macAddress.value = ""
                    }
                    1 -> {
                        userName.value = ""
                        description.value = ""
                    }
                    2 -> {
                        description.value = ""
                        macAddress.value = ""
                    }
                }

                val othersItems = OthersItems(
                    title = title.value,
                    category = category.value,
                    userName = userName.value,
                    passWord = password.value,
                    macAddress = macAddress.value,
                    description = description.value
                )

                try {

                    othersRoomRepository.insertItem(othersItems = othersItems)

                    showSnackBar("Saved successfully", "Dismiss")
                    popUp()

                } catch (e: Exception) {
                    showSnackBar(e.message.toString(), "Dismiss")

                }

            }


        }
    }
}