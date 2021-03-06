package com.stalib.passwordmanager

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stalib.passwordmanager.presentation.navigation.MainActions
import com.stalib.passwordmanager.presentation.navigation.addCardsGraph
import com.stalib.passwordmanager.presentation.navigation.addSettingsGraph
import com.stalib.passwordmanager.presentation.navigation.addWelcomeGraph
import com.stalib.passwordmanager.ui.screens.loginsScreen.addLoginsGraph
import com.stalib.passwordmanager.ui.screens.othersScreen.addOthersGraph

sealed class Screen(
    val route: String
) {

    object WelcomeScreenRoot: Screen("welcome")
    object LoginsScreenRoot : Screen("logins")
    object CardsScreenRoot : Screen("cards")
    object OthersScreenRoot : Screen("others")
    object SettingsScreenRoot: Screen("settings")

}

sealed class WelcomeScreen(
    val route: String,
    val label: String
){
    object SignUp: WelcomeScreen("sign_up", "SignUp")
    object SignIn: WelcomeScreen("sign_in", "SignIn")
}

sealed class LoginsScreen(
    val route: String,
    val label: String,
    val icon: ImageVector? = null
){

    object AllLogins : LoginsScreen("logins_screen", "Logins", Icons.Default.Language)

    object NewLoginsItem : LoginsScreen("new_logins_item", "New Item")

    object LoginsDetails: LoginsScreen("logins_details_screen/{itemId}", "Details"){
        fun createRoute(itemId: String) = "logins_details_screen/${itemId}"
    }

    object EditLoginsDetails: LoginsScreen("logins_edit_screen/{itemId}", "Edit Item"){
        fun createRoute(itemId: String) = "logins_edit_screen/${itemId}"
    }

}

sealed class CardsScreen(
    val route: String,
    val label: String,
    val icon: ImageVector? = null
){

    object AllCards : CardsScreen("cards_screen", "Cards", Icons.Default.CreditCard)

    object NewCardsItem : CardsScreen("new_cards_item", "New Item")

    object CardsDetails: CardsScreen("cards_details_screen/{itemId}", "Details"){
        fun createRoute(itemId: String) = "cards_details_screen/${itemId}"
    }
    object EditCardsDetails: CardsScreen("cards_edit_screen/{itemId}", "Edit Item"){
        fun createRoute(itemId: String) = "cards_edit_screen/${itemId}"
    }


}

sealed class OthersScreen(
    val route: String,
    val label: String,
    val icon: ImageVector? = null
){

    object AllOthers : OthersScreen("others_screen", "Others", Icons.Default.LinearScale)

    object NewOthersItem : OthersScreen("new_others_item", "New Item")

    object OthersDetails: OthersScreen("others_details_screen/{itemId}", "Details"){
        fun createRoute(itemId: String) = "others_details_screen/${itemId}"
    }

    object EditOthersDetails: CardsScreen("others_edit_screen/{itemId}", "Edit Item"){
        fun createRoute(itemId: String) = "others_edit_screen/${itemId}"
    }

}

sealed class SettingsScreen(
    val route: String,
    val label: String,
){
    object Settings: SettingsScreen("setting_screen", "Settings")
}





@ExperimentalAnimationApi
@Composable
fun AppNavGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {

    val coroutineScope = rememberCoroutineScope()
    val actions = remember(navController){ MainActions(navController,scaffoldState,coroutineScope) }

    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreenRoot.route
    ) {


        addWelcomeGraph(mainViewModel,scaffoldState,actions)

        addLoginsGraph(mainViewModel,scaffoldState,actions)
        addCardsGraph(mainViewModel,scaffoldState,actions)
        addOthersGraph(mainViewModel,scaffoldState,actions)

        addSettingsGraph(mainViewModel,scaffoldState,actions)


    }

}





