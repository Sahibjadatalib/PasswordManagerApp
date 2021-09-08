package com.example.passwordmanager.presentation.screens.cards.cards_edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.passwordmanager.data.repository.CardsRoomRepository
import com.example.passwordmanager.data.room.entity.CardsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCardsViewModel @Inject constructor(
    private val cardsRoomRepository: CardsRoomRepository,
) : ViewModel() {


    val title = mutableStateOf("")
    val category = mutableStateOf(0)
    val cardNumber = mutableStateOf("")
    val cardHolderName = mutableStateOf("")
    val pinNumber = mutableStateOf("")
    val cvvNumber = mutableStateOf("")
    val issueDate = mutableStateOf("")
    val expiryDate = mutableStateOf("")

    fun getItemById(itemId: Int): LiveData<CardsItems> {
        return cardsRoomRepository.getItemById(itemId = itemId).asLiveData()
    }


    fun updateCardsItem(
        itemId: Int,
        showSnackBar: (String, String) -> Unit,
        popUp: () -> Unit,
    ) {

        viewModelScope.launch {

            if (title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                try {

                    cardsRoomRepository.updateItem(
                        title = title.value,
                        category = category.value,
                        cardNumber = cardNumber.value,
                        cardHolderName = cardHolderName.value,
                        pinNumber = pinNumber.value,
                        cvv = cvvNumber.value,
                        issueDate = issueDate.value,
                        expiryDate = expiryDate.value,
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