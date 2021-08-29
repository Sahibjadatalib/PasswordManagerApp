package com.example.passwordmanager.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.repository.LoginsRoomRepository
import com.example.passwordmanager.data.repository.OthersRoomRepository
import com.example.passwordmanager.data.room.entity.LoginsItems
import com.example.passwordmanager.data.room.entity.OthersItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OthersViewModel @Inject constructor(
    private val repository: OthersRoomRepository
) : ViewModel() {

    private val _results = MutableStateFlow<List<OthersItems>>(emptyList())
    val results: StateFlow<List<OthersItems>> = _results

    private val _resultsForFavorites = MutableStateFlow<List<OthersItems>>(emptyList())
    val resultsForFavorites: StateFlow<List<OthersItems>> = _resultsForFavorites

    private val _resultsForSearch = MutableStateFlow<List<OthersItems>>(emptyList())
    val resultsForSearch: StateFlow<List<OthersItems>> = _resultsForSearch

    private val _switch = mutableStateOf(false)
    var switch: State<Boolean> = _switch
    fun setSwitch(newText: Boolean) {
        _switch.value = newText
    }

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    fun setSearchQuery(newText: String) {
        _searchQuery.value = newText
    }

    init {

        getAllOthersItems()
        getAllFavoriteOthersItems()

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

    private val _description = mutableStateOf("")
    val description: State<String> = _description
    fun setDescription(newText: String) {
        _description.value = newText
    }

    private val _macAddress = mutableStateOf("")
    val macAddress: State<String> = _macAddress
    fun setMacAddress(newText: String) {
        _macAddress.value = newText
    }


    fun deleteOthersItem(itemId: Int) {
        viewModelScope.launch {
            try {
                repository.delete(itemId = itemId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertOthersItem(
        popUp: () -> Unit,
        showSnackBar: (String, String) -> Unit
    ) {
        viewModelScope.launch {

            if (_title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val othersItems = OthersItems(
                    title = _title.value,
                    category = _category.value,
                    userName = _userName.value,
                    passWord = _password.value,
                    macAddress = _macAddress.value,
                    description = _description.value
                )

                try {

                    repository.insertItem(othersItems = othersItems)

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

    fun updateOthersItem(
        itemId: Int,
        showSnackBar: (String, String) -> Unit,
        popUp: () -> Unit,
    ) {

        viewModelScope.launch {

            if (_title.value.isEmpty()) {
                showSnackBar("Title field cannot be empty!", "Dismiss")
            } else {

                val othersItems = OthersItems(
                    title = _title.value,
                    category = _category.value,
                    userName = _userName.value,
                    passWord = _password.value,
                    macAddress = _macAddress.value,
                    description = _description.value
                )

                try {

                    repository.updateItem(
                        title = _title.value,
                        category = _category.value,
                        userName = _userName.value,
                        password = _password.value,
                        macAddress = _macAddress.value,
                        description = _description.value,
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


    private fun getAllFavoriteOthersItems() {
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

    private fun getAllOthersItems() {
        viewModelScope.launch {
            repository.getAllOthersItems()
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

    fun getItemById(itemId: Int): LiveData<OthersItems> {
        return repository.getItemById(itemId = itemId).asLiveData()
    }

}