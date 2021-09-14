package com.stalib.passwordmanager.presentation.screens.cards.cards_new

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.CardsRoomRepository
import com.stalib.passwordmanager.data.room.entity.CardsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCardsViewModel @Inject constructor(
    private val repository: CardsRoomRepository
): ViewModel() {

    val title = mutableStateOf("")
    val category = mutableStateOf(0)
    val cardNumber = mutableStateOf("")
    val cardHolderName = mutableStateOf("")
    val pinNumber = mutableStateOf("")
    val cvvNumber = mutableStateOf("")
    val issueDate = mutableStateOf("")
    val expiryDate = mutableStateOf("")


    fun insertCardsItem(
        popUp: () -> Unit,
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            if (title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                if(category.value != 0 && category.value != 1){
                    cvvNumber.value = ""
                    pinNumber.value = ""
                }

                val cardsItems = CardsItems(
                    title = title.value,
                    category = category.value,
                    cardNumber = cardNumber.value,
                    cardHolderName = cardHolderName.value,
                    pinNumber = pinNumber.value,
                    issueDate = issueDate.value,
                    cvv = cvvNumber.value,
                    expiryDate = expiryDate.value
                )

                try {

                    repository.insertItem(cardsItems = cardsItems)
                    showSnackBar("Card saved successfully", "Dismiss")
                    popUp()

                } catch (e: Exception) {
                    showSnackBar(e.message.toString(), "Dismiss")

                }

            }


        }
    }


}