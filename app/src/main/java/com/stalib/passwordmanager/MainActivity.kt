package com.stalib.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.stalib.passwordmanager.presentation.common_components.DefaultSnackbar
import com.stalib.passwordmanager.presentation.theme.Theme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

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