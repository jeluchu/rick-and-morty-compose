package com.jeluchu.rickandmorty.features.home.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.extensions.times
import com.jeluchu.rickandmorty.core.extensions.transform
import com.jeluchu.rickandmorty.core.ui.composables.AnimatedFab
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen
import com.jeluchu.rickandmorty.core.ui.theme.lightGreen
import com.jeluchu.rickandmorty.core.ui.theme.turquoise
import com.jeluchu.rickandmorty.core.utils.BottomNavOptions
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun PickleRickBottomNavigation(
    optionSelected: String,
    modifier: Modifier = Modifier,
    onGitHubClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onDarkModeClick: () -> Unit = {},
    onBottomClick: (String) -> Unit = {}
) {
    val animationValue = sin(PI * 0.5f).toFloat()
    val isMenuExtended = remember { mutableStateOf(false) }
    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(80.dp)
                .paint(
                    painter = painterResource(R.drawable.ic_bottom_navigation),
                    contentScale = ContentScale.FillHeight
                )
                .padding(horizontal = 40.dp)
        ) {
            listOf(
                BottomNavOptions.CHARACTERS to ImageVector.vectorResource(R.drawable.ic_deco_character),
                BottomNavOptions.LOCATIONS to ImageVector.vectorResource(R.drawable.ic_deco_location)
            ).map { (option, icon) ->
                IconButton(onClick = { onBottomClick(option) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White.copy(
                            if (optionSelected == option) 1f else 0.5f
                        ),
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(44.dp)
                .size(56.dp)
                .scale(2 - animationValue)
                .background(
                    shape = CircleShape,
                    color = lightGreen.copy(alpha = lightGreen.alpha * animationValue)
                )
        )

        Box(
            Modifier
                .fillMaxSize()
                .graphicsLayer { this.renderEffect = renderEffect }
                .padding(bottom = 44.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedFab(
                contentColor = darkGreen,
                onClick = onDarkModeClick,
                containerColor = Color.White,
                icon = ImageVector.vectorResource(R.drawable.ic_deco_day_night),
                opacity = LinearEasing.transform(0.2f, 0.7f, fabAnimationProgress),
                modifier = Modifier
                    .padding(
                        PaddingValues(
                            bottom = 72.dp,
                            end = 210.dp
                        ) * FastOutSlowInEasing.transform(0f, 0.8f, fabAnimationProgress)
                    )
            )

            AnimatedFab(
                onClick = onFavoriteClick,
                contentColor = darkGreen,
                containerColor = Color.White,
                icon = ImageVector.vectorResource(R.drawable.ic_deco_filled_heart),
                opacity = LinearEasing.transform(0.3f, 0.8f, fabAnimationProgress),
                modifier = Modifier.padding(
                    PaddingValues(
                        bottom = 88.dp,
                    ) * FastOutSlowInEasing.transform(0.1f, 0.9f, fabAnimationProgress)
                )
            )

            AnimatedFab(
                onClick = onGitHubClick,
                contentColor = darkGreen,
                containerColor = Color.White,
                opacity = LinearEasing.transform(0.4f, 0.9f, fabAnimationProgress),
                icon = ImageVector.vectorResource(R.drawable.ic_deco_github),
                modifier = Modifier.padding(
                    PaddingValues(
                        bottom = 72.dp,
                        start = 210.dp
                    ) * FastOutSlowInEasing.transform(0.2f, 1.0f, fabAnimationProgress)
                )
            )

            AnimatedFab(
                modifier = Modifier
                    .scale(1f - LinearEasing.transform(0.5f, 0.85f, fabAnimationProgress)),
                contentColor = turquoise,
                containerColor = Color.White,
            )

            AnimatedFab(
                icon = ImageVector.vectorResource(R.drawable.ic_deco_add),
                modifier = Modifier
                    .rotate(
                        225 * FastOutSlowInEasing
                            .transform(0.35f, 0.65f, fabAnimationProgress)
                    ),
                contentColor = darkGreen,
                containerColor = Color.Transparent,
                onClick = { isMenuExtended.value = isMenuExtended.value.not() }
            )
        }
    }
}

@Composable
@Preview(device = "id:pixel_4a", showBackground = true, backgroundColor = 0xFFECFADC)
private fun MainScreenPreview() {
    PickleRickBottomNavigation(optionSelected = BottomNavOptions.CHARACTERS)
}