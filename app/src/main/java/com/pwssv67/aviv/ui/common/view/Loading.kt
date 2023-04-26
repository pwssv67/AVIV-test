package com.pwssv67.aviv.ui.common.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
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
import com.pwssv67.aviv.utils.views.Loader

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Loader(Modifier.size(64.dp))
        Text(text = stringResource(R.string.loading), style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
private fun EstateListLoadingPreview() {
    PreviewTheme {
        Loading()
    }
}