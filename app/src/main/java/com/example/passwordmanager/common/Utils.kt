package com.example.passwordmanager.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TransformedText

@Composable
fun transformToCardNumber(text:String): String {

    val trimmed = if(text.length >= 16) text.substring(0..15) else text
    var output = ""

    for(i in trimmed.indices){
        output += trimmed[i]

        if(i%4==3 && i != 15) output += "  "
    }

    return output

}

@Composable
fun transformToStarredText(text:String): String{

    val trimmed = if(text.length>=16) text.substring(0..15) else text
    var output = ""

    for(i in text.indices){
        if(i<=7){
            output += trimmed[i]
            if(i%4==3) output += " "
        }else if(i<=11){
            output += "*"
        }
    }

    return output

}