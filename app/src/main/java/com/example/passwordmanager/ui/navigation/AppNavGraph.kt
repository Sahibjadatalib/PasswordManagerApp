package com.example.passwordmanager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.passwordmanager.ui.screens.cardsScreen.addCardsGraph
import com.example.passwordmanager.ui.screens.loginsScreen.addLoginsGraph
import com.example.passwordmanager.ui.screens.othersScreen.addOthersGraph
import com.example.passwordmanager.ui.viewModel.MainViewModel

sealed class Screen(
    val route: String
) {

    object LoginsScreenRoot : Screen("logins")
    object CardsScreenRoot : Screen("cards")
    object OthersScreenRoot : Screen("others")

}

sealed class LoginsScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
){

    object AllLogins : LoginsScreen(
        "logins_screen",
        "Logins",
        Icons.Default.Language
    )

    object NewLoginsItem : LoginsScreen(
        "new_logins_item",
        "New Item", Icons.Default.Language
    )

    object LoginsDetails: LoginsScreen(
        "logins_details_screen/{itemId}",
        "Details",
        Icons.Default.Details
    ){
        fun createRoute(itemId: String) = "logins_details_screen/${itemId}"
    }

    object EditLoginsDetails: LoginsScreen(
        "logins_edit_screen/{itemId}",
        "Edit Item",
        Icons.Default.Details
    ){
        fun createRoute(itemId: String) = "logins_edit_screen/${itemId}"
    }

}

sealed class CardsScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
){

    object AllCards : CardsScreen(
        "cards_screen", "Cards",
        Icons.Default.CreditCard
    )

    object NewCardsItem : CardsScreen(
        "new_cards_item", "New Item",
        Icons.Default.CreditCard
    )

    object CardsDetails: CardsScreen(
        "cards_details_screen/{itemId}",
        "Details",
        Icons.Default.Details
    ){
        fun createRoute(itemId: String) = "cards_details_screen/${itemId}"
    }

    object EditCardsDetails: CardsScreen(
        "cards_edit_screen/{itemId}",
        "Edit Item",
        Icons.Default.Details
    ){
        fun createRoute(itemId: String) = "cards_edit_screen/${itemId}"
    }


}

sealed class OthersScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
){

    object AllOthers : OthersScreen(
        "others_screen", "Others",
        Icons.Default.LinearScale
    )

    object NewOthersItem : OthersScreen(
        "new_others_item", "New Item",
        Icons.Default.LinearScale
    )

    object OthersDetails: OthersScreen(
        "others_details_screen/{itemId}",
        "Details",
        Icons.Default.Details
    ){
        fun createRoute(itemId: String) = "others_details_screen/${itemId}"
    }

    object EditOthersDetails: CardsScreen(
        "others_edit_screen/{itemId}",
        "Edit Item",
        Icons.Default.Details
    ){
        fun createRoute(itemId: String) = "others_edit_screen/${itemId}"
    }

}




@Composable
fun AppNavGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.CardsScreenRoot.route
    ) {

        addLoginsGraph(mainViewModel, navController)
        addCardsGraph(mainViewModel, navController)
        addOthersGraph(mainViewModel, navController)


    }

}



