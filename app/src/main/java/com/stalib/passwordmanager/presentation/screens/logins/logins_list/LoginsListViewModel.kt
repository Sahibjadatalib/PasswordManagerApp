package com.stalib.passwordmanager.presentation.screens.logins.logins_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stalib.passwordmanager.data.repository.LoginsRoomRepository
import com.stalib.passwordmanager.data.room.entity.LoginsItems
import com.stalib.passwordmanager.domain.use_case.logins_use_cases.GetLoginsItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginsListViewModel @Inject constructor(
    private val loginsRoomRepository: LoginsRoomRepository,
    private val getLoginsItemsUseCase: GetLoginsItemsUseCase
): ViewModel() {

    private val _states = mutableStateOf(LoginsItemsStates())
    val states: State<LoginsItemsStates> = _states


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
            getLoginsItemsUseCase()
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _results.value = it
                }
        }
    }

//    private fun getAllLoginsItems() {
//        viewModelScope.launch {
//            loginsRoomRepository.getAllLoginsItems()
//                .catch { exception ->
//                    exception.printStackTrace()
//                }
//                .collect {
//                    _results.value = it
//                }
//        }
//    }

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