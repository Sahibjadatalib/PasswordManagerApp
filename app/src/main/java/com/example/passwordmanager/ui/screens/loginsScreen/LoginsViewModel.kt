package com.example.passwordmanager.ui.screens.loginsScreen

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.mutableStateOf
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.passwordmanager.LeafScreen
import com.example.passwordmanager.R
import com.example.passwordmanager.Screen
import com.example.passwordmanager.repository.RoomsRepository
import com.example.passwordmanager.room.entity.LoginsItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginsViewModel @Inject constructor(
    private val repository: RoomsRepository,
    @SuppressLint("StaticFieldLeak") private val context: Context
) : ViewModel() {

    init {
        getAllLoginsItems()
    }

    //input states
    val title = mutableStateOf("")
    val category = mutableStateOf("Personal")
    val userName = mutableStateOf("")
    val password = mutableStateOf("")
    val website = mutableStateOf("")
    val email = mutableStateOf("")
    val number = mutableStateOf("")
    val pinNumber = mutableStateOf("")
    val dateDDMMYYYY = mutableStateOf("")
    val dateMMYY = mutableStateOf("")
    val text = mutableStateOf("")
    val multiLineText = mutableStateOf("")



    val openDialog = mutableStateOf(false)
    fun setOpenDialog() {
        openDialog.value = true
    }
    fun resetOpenDialog() {

        openDialog.value = false
    }

    fun deleteLoginsItem(loginsItems: LoginsItems){
        viewModelScope.launch {
            try {
                repository.deleteLoginsItem(loginsItems)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun insertLoginsItem(
        navigateToAllLogins: ()->Unit,
        navController: NavController,
        showSnackBar: (String,String)->Unit
    ) {
        viewModelScope.launch {

            val resources = context.resources
            val icon = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+
            "://"+
            resources.getResourcePackageName(R.drawable.flower)+
            "/"+
            resources.getResourceTypeName(R.drawable.flower)+
            "/"+
            resources.getResourceEntryName(R.drawable.flower))

            Log.e("room","insertion started")
            try {
                repository.insertLoginsItem(
                    LoginsItems(
                        title = title.value,
                        itemIcon = icon.toString(),
                        category = category.value,
                        userName = userName.value,
                        passWord = password.value
                    )
                )

                Log.e("room","insertion ended")

                showSnackBar("saved","dismiss")
                navigateToAllLogins()

            } catch (e: Exception) {
                showSnackBar(e.message.toString(),"dismiss")

                Log.e("room","insertion failed")
            }

            Log.e("room","insertion failed again")
        }
    }

    private val _results = MutableStateFlow<List<LoginsItems>>(emptyList())
    val results: StateFlow<List<LoginsItems>> = _results

    fun getAllLoginsItems() {
        viewModelScope.launch {
            repository.getAllLoginsItems
                .catch { exception ->

                }
                .collect {
                    _results.value = it
                }
        }
    }

}