package com.jeluchu.rickandmorty.features.locations.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.ui.theme.TypographyRicky
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen
import com.jeluchu.rickandmorty.features.locations.viewmodel.LocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationsView(
    safePaddingValues: PaddingValues,
    vm: LocationViewModel = koinViewModel(),
    listState: LazyListState = rememberLazyListState()
) {
    val state by vm.state.collectAsStateWithLifecycle()

    val shouldLoadMore by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItems - 7) &&
                    state.hasMorePages &&
                    !state.isLoadingMore &&
                    totalItems > 0
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) vm.loadMoreLocations()
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            end = 15.dp,
            start = 15.dp,
            bottom = safePaddingValues.calculateBottomPadding(),
            top = safePaddingValues.calculateTopPadding()
        )
    ) {
        items(state.locations, key = { it.id }) { location ->
            LocationCard(
                location = location,
                onResidentClick = { }
            )
        }

        if (state.isLoadingMore) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                    content =  { CircularProgressIndicator() }
                )
            }
        }
    }

    if (state.isLoading && state.locations.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            content =  { CircularProgressIndicator() }
        )
    }

    state.error?.let { error ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(safePaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                color = darkGreen,
                style = TypographyRicky.bodyLarge,
                text = stringResource(R.string.no_locations)
            )
        }
    }
}