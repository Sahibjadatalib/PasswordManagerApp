package com.stalib.passwordmanager.presentation.screens.cards.cards_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.CardsRoomRepository
import com.stalib.passwordmanager.data.room.entity.CardsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsListViewModel @Inject constructor(
    private val cardsRoomRepository: CardsRoomRepository
): ViewModel() {


    val switchState = mutableStateOf(false)
    val searchQuery = mutableStateOf("")

    private val _results = MutableStateFlow<List<CardsItems>>(emptyList())
    val results: StateFlow<List<CardsItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<CardsItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<CardsItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<CardsItems>>(emptyList())
    val resultsForSearch: StateFlow<List<CardsItems>> = _resultsForSearch


    init {

        getAllCardsItems()
        getAllFavoriteCardsItems()

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


    private fun getAllFavoriteCardsItems(){
        viewModelScope.launch {
            cardsRoomRepository.getAllFavoriteEntries()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForFavorites.value = it
                }
        }
    }

    private fun getAllCardsItems() {
        viewModelScope.launch {
            cardsRoomRepository.getAllCardsItems()
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

            cardsRoomRepository.getSearchedEntries(searchQuery.value)
                .catch { exception->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForSearch.value = it
                }
        }
    }






}