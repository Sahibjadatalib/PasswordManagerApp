package com.example.passwordmanager.ui.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginsViewModel @Inject constructor(
    private val repository: LoginsRoomRepository
) : ViewModel() {


    private val _results = MutableStateFlow<List<LoginsItems>>(emptyList())
    val results: StateFlow<List<LoginsItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<LoginsItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<LoginsItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<LoginsItems>>(emptyList())
    val resultsForSearch: StateFlow<List<LoginsItems>> = _resultsForSearch

    private val _switch = mutableStateOf(false)
    var switch: State<Boolean> = _switch
    fun setSwitch(bool: Boolean) {
        _switch.value = bool
    }

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    fun setSearchQuery(newText: String) {
        _searchQuery.value = newText
    }

    init {

        getAllLoginsItems()
        getAllFavoriteLoginsItems()

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

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName
    fun setUserName(newText: String) {
        _userName.value = newText
    }

    private val _password = mutableStateOf("")
    val password: State<String> = _password
    fun setPassword(newText: String) {
        _password.value = newText
    }


    fun deleteLoginsItem(itemId: Int) {
        viewModelScope.launch {
            try {
                repository.deleteLoginsItem(itemId = itemId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertLoginsItem(
        popUp: () -> Unit,
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            if (_title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val loginsItem = LoginsItems(
                    title = _title.value,
                    category = _category.value,
                    userName = _userName.value,
                    passWord = _password.value
                )

                try {

                    repository.insertLoginsItem(loginsItems = loginsItem)

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

    fun updateLoginsItem(
        itemId: Int,
        showSnackBar: (String, String) -> Unit,
        popUp: () -> Unit,
    ) {

        viewModelScope.launch {

            if (_title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val loginsItem = LoginsItems(
                    title = _title.value,
                    category = _category.value,
                    userName = _userName.value,
                    passWord = _password.value
                )

                try {

                    repository.updateLoginsItem(
                        title = _title.value,
                        category = _category.value,
                        userName = _userName.value,
                        password = _password.value,
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


    private fun getAllFavoriteLoginsItems() {
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
            repository.getAllLoginsItems()
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

            repository.getSearchedEntries(_searchQuery.value)
                .catch { exception ->
                    exception.printStackTrace()
                }
                .collect {
                    _resultsForSearch.value = it
                }
        }
    }

    fun getItemById(itemId: Int): LiveData<LoginsItems> {
        return repository.getItemById(itemId = itemId).asLiveData()
    }

}