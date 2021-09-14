package com.stalib.passwordmanager.presentation.screens.others.others_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.OthersRoomRepository
import com.stalib.passwordmanager.data.room.entity.OthersItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherListViewModel @Inject constructor(
    private val othersRoomRepository: OthersRoomRepository
): ViewModel() {

    val switch = mutableStateOf(false)
    val searchQuery = mutableStateOf("")

    private val _results = MutableStateFlow<List<OthersItems>>(emptyList())
    val results: StateFlow<List<OthersItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<OthersItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<OthersItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<OthersItems>>(emptyList())
    val resultsForSearch: StateFlow<List<OthersItems>> = _resultsForSearch

    init {

        getAllOthersItems()
        getAllFavoriteOthersItems()

    }


    private fun getAllFavoriteOthersItems() {
        viewModelScope.launch {
            othersRoomRepository.getAllFavoriteEntries()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForFavorites.value = it
                }
        }
    }

    private fun getAllOthersItems() {
        viewModelScope.launch {
            othersRoomRepository.getAllOthersItems()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _results.value = it
                }
        }
    }

    fun getSearchedEntries() {
        viewModelScope.launch {

            othersRoomRepository.getSearchedEntries(searchQuery.value)
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForSearch.value = it
                }
        }
    }


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



}

