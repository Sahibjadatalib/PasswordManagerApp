package com.stalib.passwordmanager.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.stalib.passwordmanager.presentation.theme.*

data class Settings(
    val index: Int,
    val icon: ImageVector,
    val title: String,
    val subtitle: String? = null,
    val iconColor: Color = Color.LightGray
)

val generalSettings = listOf(
    Settings(
        0,
        Icons.Default.DarkMode,
        "Change Theme",
        iconColor = Color.Gray
    )
)

val helpsAndAboutSettings = listOf(
    Settings(
        0,
        Icons.Default.Mail,
        "Contact us",
        iconColor = PinkA200
    ),
    Settings(
        1,
        Icons.Default.Lightbulb,
        "Send Suggestion",
        iconColor = YellowA700
    ),
    Settings(
        2,
        Icons.Default.BugReport,
        "Report Bug",
        iconColor = Color.Red
    ),
    Settings(
        3,
        Icons.Default.Security,
        "Privacy Policy",
        iconColor = GreenA700
    ),
    Settings(
        4,
        Icons.Default.StarOutline,
        "Rate App",
        iconColor = AmberA700
    ),
)

val securitySettings = listOf(
    Settings(0,
        Icons.Default.VpnKey,
        "Change master password",
        iconColor = Blue700Dark
    ),
    Settings(
        1,
        Icons.Default.HelpOutline,
        "Change hint for master password",
        iconColor = GreenA700
    )
)

val dangerZoneSettings = listOf(
    Settings(
        0,
        Icons.Default.DeleteForever,
        "Delete data",
        "Delete all logins, cards and others data. This will not delete your settings or master password",
        iconColor = Color.Red

    ),
    Settings(
        1,
        Icons.Default.RestartAlt,
        "Reset app",
        "Delete of data, settings, master password and reset the app",
        iconColor = Color.Red
    )
)


