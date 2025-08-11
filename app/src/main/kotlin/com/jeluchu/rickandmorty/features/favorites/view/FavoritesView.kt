package com.jeluchu.rickandmorty.features.favorites.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.util.TableInfo
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.ui.theme.TypographyRicky
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen
import com.jeluchu.rickandmorty.features.characters.view.CharacterCard
import com.jeluchu.rickandmorty.features.favorites.viewmodel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesView(
    onBackClick: () -> Unit = {},
    vm: FavoritesViewModel = koinViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_deco_arrow_back),
                            contentDescription = "stringResource(R.string.back)"
                        )
                    }
                },
                title = {
                    Text(
                        fontSize = 25.sp,
                        style = TypographyRicky.titleLarge,
                        text = stringResource(R.string.favorites)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = MaterialTheme.colorScheme.secondary
                )
            )
        },
        content = { padding ->
            if (state.favorites.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = darkGreen,
                        style = TypographyRicky.bodyLarge,
                        text = stringResource(R.string.no_favorites),
                        modifier = Modifier.padding(padding.calculateBottomPadding())
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        end = 15.dp,
                        start = 15.dp,
                        top = padding.calculateTopPadding()
                    )
                ) {
                    items(state.favorites) { character ->
                        CharacterCard(character = character)
                    }
                }
            }
        }
    )
}