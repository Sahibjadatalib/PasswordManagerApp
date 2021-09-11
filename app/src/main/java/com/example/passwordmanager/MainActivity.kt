package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.AppNavGraph
import com.example.passwordmanager.presentation.common_components.DefaultSnackbar
import com.example.passwordmanager.presentation.theme.Theme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            PasswordManagerApp()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun PasswordManagerApp() {


    val mainViewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    //val navController = rememberAnimatedNavController()
    val systemUiController = rememberSystemUiController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Theme(darkTheme = mainViewModel.storedAppTheme.value) {

        val colorForStatusBar = Theme.colors.primaryVariant

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = colorForStatusBar
            )
        }

        ProvideWindowInsets {

            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = { scaffoldState.snackbarHostState },
            ) {

                Box(modifier = Modifier) {

                    AppNavGraph(
                        mainViewModel = mainViewModel,
                        navController = navController,
                        scaffoldState = scaffoldState
                    )

                    DefaultSnackbar(
                        snackbarHostState = scaffoldState.snackbarHostState,
                        onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )

                }


            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Theme {
        //PasswordManagerApp()
    }
}