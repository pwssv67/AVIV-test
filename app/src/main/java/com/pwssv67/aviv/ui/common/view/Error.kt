package com.pwssv67.aviv.ui.common.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwssv67.aviv.R
import com.pwssv67.aviv.utils.preview.PreviewTheme

@Composable
fun Error(message: String, modifier: Modifier = Modifier, onRetry: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = message, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.reload))
        }
    }
}

@Preview
@Composable
private fun EstateListErrorPreview() {
    val error = "lorem ipsum dolor sit amet"
    PreviewTheme {
        Error(error, onRetry = {})
    }
}