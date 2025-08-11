package com.jeluchu.rickandmorty.features.home.view

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.ui.theme.TypographyRicky
import com.jeluchu.rickandmorty.core.utils.BottomNavOptions
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.characters.view.CharactersView
import com.jeluchu.rickandmorty.features.characters.viewmodel.CharactersViewModel
import com.jeluchu.rickandmorty.features.home.viewmodel.CustomizationViewModel
import com.jeluchu.rickandmorty.features.locations.view.LocationsView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(
    onFavoriteClick: () -> Unit = {},
    onGitHubClick: (Context) -> Unit = {},
    context: Context = LocalContext.current,
    vm: CustomizationViewModel = koinViewModel(),
    onCharacterDetailsClick: (Character) -> Unit = {},
    onDarkModeClick: (CustomizationViewModel) -> Unit = {},
    onSaveCheck: (CharactersViewModel, Int) -> Boolean = { _, _ -> false }
) {
    var selectedScreen by remember { mutableStateOf(BottomNavOptions.CHARACTERS) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        fontSize = 25.sp,
                        style = TypographyRicky.titleLarge,
                        text = stringResource(R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                )
            )
        },
        bottomBar = {
            PickleRickBottomNavigation(
                optionSelected = selectedScreen,
                onFavoriteClick = onFavoriteClick,
                onDarkModeClick = { onDarkModeClick(vm) },
                onGitHubClick = { onGitHubClick(context) },
                modifier = Modifier.navigationBarsPadding(),
                onBottomClick = { option -> selectedScreen = option }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        content = { padding ->
            AnimatedContent(selectedScreen) { selected ->
                when (selected) {
                    BottomNavOptions.CHARACTERS -> CharactersView(
                        onSaveCheck = onSaveCheck,
                        safePaddingValues = padding,
                        onCharacterDetailsClick = onCharacterDetailsClick
                    )

                    BottomNavOptions.LOCATIONS -> LocationsView(
                        safePaddingValues = padding
                    )
                }
            }
        }
    )
}