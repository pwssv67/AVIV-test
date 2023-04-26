package com.pwssv67.aviv.ui.base.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pwssv67.aviv.utils.views.activity

@Composable
fun NavigationView(initScreen: Screen) {
    val navigation = remember { SimpleNavigation(initScreen) }

    val screen = navigation.currentScreen.collectAsStateWithLifecycle()

    screen.value?.Render(navigation) ?: run { LocalContext.current.activity?.finish() }
}