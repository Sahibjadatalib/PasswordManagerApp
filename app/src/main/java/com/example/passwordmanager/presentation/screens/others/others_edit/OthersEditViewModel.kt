package com.example.passwordmanager.presentation.screens.others.others_edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repository.OthersRoomRepository
import com.example.passwordmanager.data.room.entity.OthersItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherEditViewModel @Inject constructor(
    private val othersRoomRepository: OthersRoomRepository
): ViewModel() {

    val title = mutableStateOf("")
    val category = mutableStateOf(0)
    val userName = mutableStateOf("")
    val password = mutableStateOf("")
    val description = mutableStateOf("")
    val macAddress = mutableStateOf("")

    fun getItemById(itemId: Int): LiveData<OthersItems> {
        return othersRoomRepository.getItemById(itemId = itemId).asLiveData()
    }


    fun updateOthersItem(
        itemId: Int,
        showSnackBar: (String, String) -> Unit,
        popUp: () -> Unit,
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


                try {

                    othersRoomRepository.updateItem(
                        title = title.value,
                        category = category.value,
                        userName = userName.value,
                        password = password.value,
                        macAddress = macAddress.value,
                        description = description.value,
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
}