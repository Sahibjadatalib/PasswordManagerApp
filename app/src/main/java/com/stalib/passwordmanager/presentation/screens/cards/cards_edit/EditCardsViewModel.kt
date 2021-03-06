package com.stalib.passwordmanager.presentation.screens.cards.cards_edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.stalib.passwordmanager.data.repository.CardsRoomRepository
import com.stalib.passwordmanager.data.room.entity.CardsItems
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

                if(category.value != 0 && category.value != 1){
                    cvvNumber.value = ""
                    pinNumber.value = ""
                }

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

                    showSnackBar("Card edited successfully", "Dismiss")
                    popUp()

                } catch (e: Exception) {
                    showSnackBar(e.message.toString(), "Dismiss")

                }

            }


        }

    }

}