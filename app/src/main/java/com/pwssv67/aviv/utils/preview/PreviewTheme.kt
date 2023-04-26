package com.pwssv67.aviv.utils.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pwssv67.aviv.core.ui.theme.AVIVTheme

@Composable
fun PreviewTheme(content: @Composable () -> Unit) {
    AVIVTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            content()
        }
    }
}