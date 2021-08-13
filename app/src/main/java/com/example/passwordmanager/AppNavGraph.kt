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

sealed class Screen(
    val route: String
) {

    object LoginsScreenRoot : Screen("logins_root")
    object CardsScreenRoot : Screen("cards_root")
    object OthersScreenRoot : Screen("others_root")

}

sealed class LeafScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {

    object AllLogins : LeafScreen(
        "logins_screen",
        "Logins",
        Icons.Default.Language
    )

    object NewLoginsItem : LeafScreen(
        "new_logins_item",
        "New Item", Icons.Default.Language
    )

    object LoginsIconsLibrary : LeafScreen(
        "icons_library_logins",
        "Icons Library", Icons.Default.Language
    )

    object AllCards : LeafScreen(
        "cards_screen", "Cards",
        Icons.Default.CreditCard
    )

    object NewCardsItem : LeafScreen(
        "new_cards_item", "New Item",
        Icons.Default.CreditCard
    )

    object AllOthers : LeafScreen(
        "others_screen", "Others",
        Icons.Default.LinearScale
    )

    object NewOthersItem : LeafScreen(
        "new_others_item", "New Item",
        Icons.Default.LinearScale
    )

    object OthersIconsLibrary : LeafScreen(
        "icons_library_others",
        "Icons Library",
        Icons.Default.LinearScale
    )

}

val screensForBottomBar = listOf(
    LeafScreen.AllLogins,
    LeafScreen.AllCards,
    LeafScreen.AllOthers
)


@Composable
fun AppNavGraph(
    mainViewModel: MainViewModel,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.LoginsScreenRoot.route
    ) {

        addLoginsGraph(mainViewModel, navController)
        addCardsGraph(mainViewModel, navController)
        addOthersGraph(mainViewModel, navController)

    }

}



