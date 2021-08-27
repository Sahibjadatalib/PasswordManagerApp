package com.example.passwordmanager.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repository.CardsRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.room.entity.CardsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val repository: CardsRoomRepository
): ViewModel() {


    private val _results = MutableStateFlow<List<CardsItems>>(emptyList())
    val results: StateFlow<List<CardsItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<CardsItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<CardsItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<CardsItems>>(emptyList())
    val resultsForSearch: StateFlow<List<CardsItems>> = _resultsForSearch

    private val _switch = mutableStateOf(false)
    var switch: State<Boolean> = _switch
    fun setSwitch(newText: Boolean) {
        _switch.value = newText
    }

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    fun setSearchQuery(newText: String){
        _searchQuery.value = newText
    }

    init {

        getAllLoginsItems()
        getAllFavoriteCardsItems()

    }




    private val _title = mutableStateOf("")
    var title: State<String> = _title
    fun setTitle(newText: String) {
        _title.value = newText
    }

    private val _category = mutableStateOf(0)
    val category: State<Int> = _category
    fun setCategory(newText: Int) {
        _category.value = newText
    }

    private val _cardNumber = mutableStateOf("")
    val cardNumber: State<String> = _cardNumber
    fun setCardNumber(newText: String) {
        _cardNumber.value = newText
    }

    private val _cardHolderName = mutableStateOf("")
    var cardHolderName: State<String> = _cardHolderName
    fun setCardHolderName(newText: String) {
        _cardHolderName.value = newText
    }



    private val _pinNumber = mutableStateOf("")
    var pinNumber: State<String> = _pinNumber
    fun setPinNumber(newText: String) {
        _pinNumber.value = newText
    }

    private val _cvvNumber = mutableStateOf("")
    var cvvNumber: State<String> = _cvvNumber
    fun setCVVNumber(newText: String) {
        _cvvNumber.value = newText
    }

    private val _issueDate = mutableStateOf("")
    var issueDate: State<String> = _issueDate
    fun setIssueDate(newText: String) {
        _issueDate.value = newText
    }

    private val _expiryDate = mutableStateOf("")
    var expiryDate: State<String> = _expiryDate
    fun setExpiryDate(newText: String) {
        _expiryDate.value = newText
    }


    fun deleteCardsItem(itemId: Int) {
        viewModelScope.launch {
            try {
                repository.delete(itemId = itemId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertCardsItem(
        popUp: () -> Unit,
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            if (_title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val cardsItems = CardsItems(
                    title = _title.value,
                    category = _category.value,
                    cardNumber = _cardNumber.value,
                    cardHolderName = _cardHolderName.value,
                    pinNumber = _pinNumber.value,
                    cvv = _cvvNumber.value,
                    issueDate = _issueDate.value,
                    expiryDate = _expiryDate.value
                )

                try {

                    repository.insertItem(cardsItems = cardsItems)

                    showSnackBar("saved", "Dismiss")
                    popUp()

                } catch (e: Exception) {
                    showSnackBar(e.message.toString(), "Dismiss")

                }

            }


        }
    }

    fun updateIsFavorite(
        itemId: Int,
        isFavorite: Boolean
    ) {
        viewModelScope.launch {

            try {
                repository.updateIsFavorite(
                    itemId = itemId,
                    isFavorite = isFavorite
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateCardsItem(
        itemId: Int,
        showSnackBar: (String, String) -> Unit,
        popUp: () -> Unit,
    ) {

        viewModelScope.launch {

            if (_title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val cardsItems = CardsItems(
                    title = _title.value,
                    category = _category.value,
                    cardNumber = _cardNumber.value,
                    cardHolderName = _cardHolderName.value,
                    pinNumber = _pinNumber.value,
                    cvv = _cvvNumber.value,
                    issueDate = _issueDate.value,
                    expiryDate = _expiryDate.value
                )

                try {

                    repository.updateItem(
                        title = _title.value,
                        category = _category.value,
                        cardNumber = _cardNumber.value,
                        cardHolderName = _cardHolderName.value,
                        pinNumber = _pinNumber.value,
                        cvv = _cvvNumber.value,
                        issueDate = _issueDate.value,
                        expiryDate = _expiryDate.value,
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




    private fun getAllFavoriteCardsItems(){
        viewModelScope.launch {
            repository.getAllFavoriteEntries()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForFavorites.value = it
                }
        }
    }

    private fun getAllLoginsItems() {
        viewModelScope.launch {
            repository.getAllCardsItems()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _results.value = it
                }
        }
    }

    fun getSearchedEntries(){
        viewModelScope.launch {

            repository.getSearchedEntries(_searchQuery.value)
                .catch { exception->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForSearch.value = it
                }
        }
    }

    fun getItemById(itemId: Int): LiveData<CardsItems> {
        return repository.getItemById(itemId = itemId).asLiveData()
    }



}