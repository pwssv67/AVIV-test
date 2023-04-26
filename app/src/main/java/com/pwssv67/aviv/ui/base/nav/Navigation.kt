package com.pwssv67.aviv.ui.base.nav

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface Navigation {
    fun goBack()

    fun navigateToScreen(screen: Screen)
}

/**
* That's just a simple version of navigation, since I really don't like string parameters in Jetpack Navigation for Compose,
* and more complex libraries would be an overkill for this case.
 *
* It doesn't use any persistent storage, so it's not suitable for complex navigation and large apps.
* For more complex cases I would use something like [Odyssey](https://github.com/AlexGladkov/Odyssey)
**/
class SimpleNavigation(initScreen: Screen) : Navigation {
    private var screens: MutableList<Screen?> = mutableListOf(initScreen)
    private val _currentScreen: MutableStateFlow<Screen?> = MutableStateFlow(initScreen)
    val currentScreen = _currentScreen.asStateFlow()

    @MainThread
    override fun goBack() {
        if (screens.size <= 1) {
            _currentScreen.value = null
            return
        }
        screens.removeLast()
        _currentScreen.value = screens.last()
    }

    @MainThread
    override fun navigateToScreen(screen: Screen) {
        screens.add(screen)
        _currentScreen.value = screen
    }
}