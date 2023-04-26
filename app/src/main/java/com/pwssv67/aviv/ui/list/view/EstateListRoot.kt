package com.pwssv67.aviv.ui.list.view

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pwssv67.aviv.R
import com.pwssv67.aviv.model.data.EstateList
import com.pwssv67.aviv.ui.base.nav.Navigation
import com.pwssv67.aviv.ui.common.view.Error
import com.pwssv67.aviv.ui.common.view.EstateItem
import com.pwssv67.aviv.ui.common.view.Loading
import com.pwssv67.aviv.ui.info.view.EstateInfoScreen
import com.pwssv67.aviv.ui.list.state.EstateListViewState
import com.pwssv67.aviv.ui.list.viewmodel.EstateListViewModel
import com.pwssv67.aviv.utils.preview.EstateGenerator
import com.pwssv67.aviv.utils.preview.PreviewTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun EstateListRoot(navigation: Navigation, viewModel: EstateListViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        //delay(3.seconds) //let users see beautiful loading animation
        viewModel.load()
    }

    when (val stateSnapshot = state) {
        is EstateListViewState.Loading -> Loading()
        is EstateListViewState.Error -> Error(
            stateSnapshot.message ?: stringResource(R.string.unknown_error),
            onRetry = viewModel::load
        )

        is EstateListViewState.Success -> EstateListView(stateSnapshot.data) { id ->
            val estateInfoScreen = EstateInfoScreen(id)
            navigation.navigateToScreen(estateInfoScreen)
        }
    }
}

@VisibleForTesting
const val ESTATE_LIST = "estate_list"
@Composable
@VisibleForTesting
fun EstateListView(data: EstateList, openEstateItem: (Int) -> Unit) {

    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier.semantics { testTag = ESTATE_LIST },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data.estates) {
            EstateItem(it, Modifier.clickable { openEstateItem(it.id) })
        }
    }
}

@Preview
@Composable
private fun EstateListPreview() {
    val data = EstateGenerator.getEstateList()

    PreviewTheme {
        EstateListView(data = data) {}
    }
}
