package com.pwssv67.aviv.ui.info.view

import androidx.compose.runtime.Composable
import com.pwssv67.aviv.ui.base.nav.Navigation
import com.pwssv67.aviv.ui.base.nav.Screen

class EstateInfoScreen(val id: Int) : Screen {
    @Composable
    override fun Render(navigation: Navigation) {
        EstateInfoView(id, navigation)
    }
}