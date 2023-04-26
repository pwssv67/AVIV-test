package com.pwssv67.aviv.ui.base.nav

import androidx.compose.runtime.Composable

interface Screen {
    @Composable
    fun Render(navigation: Navigation)
}