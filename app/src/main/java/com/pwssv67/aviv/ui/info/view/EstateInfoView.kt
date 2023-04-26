package com.pwssv67.aviv.ui.info.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pwssv67.aviv.R
import com.pwssv67.aviv.model.data.Estate
import com.pwssv67.aviv.ui.base.nav.Navigation
import com.pwssv67.aviv.ui.common.view.Error
import com.pwssv67.aviv.ui.common.view.EstateItem
import com.pwssv67.aviv.ui.common.view.Loading
import com.pwssv67.aviv.ui.info.state.EstateInfoViewState
import com.pwssv67.aviv.ui.info.viewmodel.EstateInfoViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EstateInfoView(
    id: Int,
    navigation: Navigation,
    viewModel: EstateInfoViewModel = koinViewModel { parametersOf(id) }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BackHandler { navigation.goBack() }

    when (val stateSnapshot = state) {
        is EstateInfoViewState.Error -> Error(
            message = stateSnapshot.message ?: stringResource(id = R.string.unknown_error)
        ) { viewModel.load() }

        EstateInfoViewState.Loading -> Loading()
        is EstateInfoViewState.Success -> WrapEstateItem(item = stateSnapshot.data.estate, goBack = navigation::goBack)
    }
}

@Composable
fun WrapEstateItem(item: Estate, goBack: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .size(32.dp)
                .clickable { goBack() }
                .semantics { testTag = "back" }
                .align(Alignment.Start)
        )

        EstateItem(it = item)

        Text("""
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Nulla euismod, nisl eget aliquam ultricies, nunc nisl ultrices nunc, quis ultrices nisl nisl nec nisl.
            
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Nulla euismod, nisl eget aliquam ultricies, nunc nisl ultrices nunc, quis ultrices nisl nisl nec nisl.
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Nulla euismod, nisl eget aliquam ultricies, nunc nisl ultrices nunc, quis ultrices nisl nisl nec nisl.
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Nulla euismod, nisl eget aliquam ultricies, nunc nisl ultrices nunc, quis ultrices nisl nisl nec nisl.
            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Nulla euismod, nisl eget aliquam ultricies, nunc nisl ultrices nunc, quis ultrices nisl nisl nec nisl.
        """.trimIndent(),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}