package com.jeluchu.rickandmorty.features.characters.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen

@Composable
fun SimpleTextCard(
    text: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(darkGreen.copy(.7f))
            .padding(
                vertical = 2.dp,
                horizontal = 8.dp
            )
    ) {
        Text(
            text = text,
            fontSize = 13.sp
        )
    }
}