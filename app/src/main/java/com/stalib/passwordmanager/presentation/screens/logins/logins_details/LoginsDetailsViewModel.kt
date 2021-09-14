package com.stalib.passwordmanager.presentation.screens.logins.logins_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.LoginsRoomRepository
import com.stalib.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginsDetailsViewModel @Inject constructor(
    private val loginsRoomRepository: LoginsRoomRepository
): ViewModel() {

    fun getItemById(itemId: Int): LiveData<LoginsItems> {
        return loginsRoomRepository.getItemById(itemId = itemId).asLiveData()
    }

    fun deleteLoginsItem(itemId: Int) {
        viewModelScope.launch {
            try {
                loginsRoomRepository.deleteLoginsItem(itemId = itemId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateIsFavorite(
        itemId: Int,
        isFavorite: Boolean
    ) {
        viewModelScope.launch {

            try {
                loginsRoomRepository.updateIsFavorite(
                    itemId = itemId,
                    isFavorite = isFavorite
                )


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}