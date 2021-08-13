package com.example.passwordmanager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {

    val topAppBarTitle = mutableStateOf("")

    val colorForStatusBar = mutableStateOf(Color(0xFF0039cb))

    fun setColorForStatusBar(color:Color){
        colorForStatusBar.value = color
    }


    //val currentScreen: MutableState<Screen> = mutableStateOf(Screen.HomeScreens.LoginsScreen)


//    fun setCurrentScreen(currScreen: Screen){
//        currentScreen.value = currScreen
//    }


}