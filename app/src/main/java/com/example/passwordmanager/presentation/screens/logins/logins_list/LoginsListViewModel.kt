package com.example.passwordmanager.presentation.screens.logins.logins_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginsListViewModel @Inject constructor(
    private val loginsRoomRepository: LoginsRoomRepository
): ViewModel() {


    val switch = mutableStateOf(false)
    val searchQuery = mutableStateOf("")

    private val _results = MutableStateFlow<List<LoginsItems>>(emptyList())
    val results: StateFlow<List<LoginsItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<LoginsItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<LoginsItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<LoginsItems>>(emptyList())
    val resultsForSearch: StateFlow<List<LoginsItems>> = _resultsForSearch


    init {

        getAllLoginsItems()
        getAllFavoriteLoginsItems()

    }

    private fun getAllFavoriteLoginsItems() {
        viewModelScope.launch {
            loginsRoomRepository.getAllFavoriteEntries()
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
            loginsRoomRepository.getAllLoginsItems()
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

            loginsRoomRepository.getSearchedEntries(searchQuery.value)
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForSearch.value = it
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

    fun deleteLoginsItem(itemId: Int) {
        viewModelScope.launch {
            try {
                loginsRoomRepository.deleteLoginsItem(itemId = itemId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}