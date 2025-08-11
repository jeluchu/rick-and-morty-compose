package com.jeluchu.rickandmorty.core.ui.composables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: ImageVector? = null,
    opacity: Float = 1f,
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.secondary,
    onClick: () -> Unit = {}
) = FloatingActionButton(
    onClick = onClick,
    shape = CircleShape,
    elevation = FloatingActionButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        focusedElevation = 0.dp,
        hoveredElevation = 0.dp
    ),
    containerColor = containerColor,
    modifier = modifier.scale(1.25f)
) {
    icon?.let {
        Icon(
            imageVector = it,
            contentDescription = null,
            tint = contentColor.copy(alpha = opacity)
        )
    }
}