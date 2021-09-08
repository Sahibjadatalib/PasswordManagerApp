package com.example.passwordmanager.presentation.screens.others.others_details

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
class OtherDetailsViewModel @Inject constructor(
    private val othersRoomRepository: OthersRoomRepository
): ViewModel() {


    fun deleteOthersItem(itemId: Int) {
        viewModelScope.launch {
            try {
                othersRoomRepository.delete(itemId = itemId)
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
                othersRoomRepository.updateIsFavorite(
                    itemId = itemId,
                    isFavorite = isFavorite
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun getItemById(itemId: Int): LiveData<OthersItems> {
        return othersRoomRepository.getItemById(itemId = itemId).asLiveData()
    }


}