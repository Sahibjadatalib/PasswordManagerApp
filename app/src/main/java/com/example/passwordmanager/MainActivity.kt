package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.AppNavGraph
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import com.example.passwordmanager.ui.viewModel.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerApp()
        }
    }
}

@Composable
fun PasswordManagerApp() {


    PasswordManagerTheme {

        val mainViewModel: MainViewModel = hiltViewModel()
        val navController = rememberNavController()

        val systemUiController = rememberSystemUiController()
        val colorForStatusBar = mainViewModel.colorForStatusBar
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = colorForStatusBar.value
            )
        }


        AppNavGraph(
            mainViewModel = mainViewModel,
            navController = navController
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PasswordManagerTheme {
        //PasswordManagerApp()
    }
}