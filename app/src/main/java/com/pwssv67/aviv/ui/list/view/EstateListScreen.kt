package com.pwssv67.aviv.ui.list.view

import androidx.compose.runtime.Composable
import com.pwssv67.aviv.ui.base.nav.Navigation
import com.pwssv67.aviv.ui.base.nav.Screen

class EstateListScreen : Screen {
    @Composable
    override fun Render(navigation: Navigation) {
        EstateListRoot(navigation)
    }
}