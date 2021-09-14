package com.stalib.passwordmanager.presentation.screens.cards.cards_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.CardsRoomRepository
import com.stalib.passwordmanager.data.room.entity.CardsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsDetailsViewModel @Inject constructor(
    private val cardsRoomRepository: CardsRoomRepository
): ViewModel() {


    fun getItemById(itemId: Int): LiveData<CardsItems> {
        return cardsRoomRepository.getItemById(itemId = itemId).asLiveData()
    }

    fun deleteCardsItem(itemId: Int) {
        viewModelScope.launch {
            try {
                cardsRoomRepository.delete(itemId = itemId)
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
                cardsRoomRepository.updateIsFavorite(
                    itemId = itemId,
                    isFavorite = isFavorite
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}