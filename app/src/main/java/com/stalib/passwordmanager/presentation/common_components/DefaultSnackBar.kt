package com.stalib.passwordmanager.presentation.common_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stalib.passwordmanager.presentation.theme.Theme

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit?
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(8.dp),
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(
                            onClick = {
                                onDismiss()
                            }
                        ) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.body2,
                                color = Theme.colors.primary
                            )
                        }
                    }
                }
            ) {

                Text(
                    text = data.message,
                    style = MaterialTheme.typography.body2,
                    color = Theme.colors.onPrimary
                )
            }
        },
        modifier = modifier
    )
}


