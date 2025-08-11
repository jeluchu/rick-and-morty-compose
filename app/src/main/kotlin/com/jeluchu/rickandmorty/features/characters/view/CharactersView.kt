package com.jeluchu.rickandmorty.features.characters.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.characters.viewmodel.CharactersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersView(
    safePaddingValues: PaddingValues,
    vm: CharactersViewModel = koinViewModel(),
    onCharacterDetailsClick: (Character) -> Unit = {},
    listState: LazyListState = rememberLazyListState(),
    onSaveCheck: (CharactersViewModel, Int) -> Boolean = { _, _ -> false }
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
        if (shouldLoadMore) vm.loadMoreCharacters()
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            vertical = safePaddingValues.calculateTopPadding(),
            horizontal = 15.dp
        )
    ) {
        items(state.characters, key = { it.id }) { character ->
            CharacterCard(
                character = character,
                isFavorite = onSaveCheck(vm, character.id),
                onClick = { onCharacterDetailsClick(character) },
                onFavoriteClick = { vm.saveOrDelete(it) }
            )
        }

        if (state.isLoadingMore) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    if (state.isLoading && state.characters.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            content = { CircularProgressIndicator() }
        )
    }

    state.error?.let { error ->

    }
}