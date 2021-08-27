package com.example.passwordmanager.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.repository.OthersRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OthersViewModel @Inject constructor(
    private val repository: OthersRoomRepository
): ViewModel() {

    private val _results = MutableStateFlow<List<LoginsItems>>(emptyList())
    val results: StateFlow<List<LoginsItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<LoginsItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<LoginsItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<LoginsItems>>(emptyList())
    val resultsForSearch: StateFlow<List<LoginsItems>> = _resultsForSearch

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


    }
}