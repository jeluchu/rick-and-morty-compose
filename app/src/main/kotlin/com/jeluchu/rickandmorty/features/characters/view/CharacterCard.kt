package com.jeluchu.rickandmorty.features.characters.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen
import com.jeluchu.rickandmorty.core.ui.theme.lightGreen
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity

@Composable
fun CharacterCard(
    isFavorite: Boolean,
    character: Character,
    onClick: () -> Unit,
    onFavoriteClick: (Character) -> Unit
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() },
    colors = CardDefaults.cardColors(
        containerColor = darkGreen.copy(.3f)
    ),
    shape = RoundedCornerShape(10.dp)
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, darkGreen, RoundedCornerShape(8.dp))
                .background(darkGreen.copy(.3f))
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = ImageVector.vectorResource(
                    if (character.gender == "Female") R.drawable.ic_deco_female
                    else R.drawable.ic_deco_male
                ),
                contentDescription = null,
                tint = lightGreen,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(topStart = 10.dp))
                    .background(darkGreen)
                    .padding(8.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                fontSize = 18.sp,
                color = darkGreen,
                text = "# ${character.id} · ${character.name}",
                fontWeight = FontWeight.Black
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                if (character.status != "unknown") SimpleTextCard(text = character.status)
                SimpleTextCard(text = character.species)
            }
        }

        IconButton(onClick = { onFavoriteClick(character) }) {
            Icon(
                tint = darkGreen,
                modifier = Modifier.padding(5.dp),
                imageVector = ImageVector.vectorResource(
                    if (isFavorite) R.drawable.ic_deco_filled_heart
                    else R.drawable.ic_deco_outlined_heart
                ),
                contentDescription = null
            )
        }
    }
}

@Composable
fun CharacterCard(
    character: CharacterEntity
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clip(RoundedCornerShape(10.dp)),
    colors = CardDefaults.cardColors(
        containerColor = darkGreen.copy(.3f)
    ),
    shape = RoundedCornerShape(10.dp)
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(2.dp, darkGreen, RoundedCornerShape(8.dp))
                .background(darkGreen.copy(.3f))
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = ImageVector.vectorResource(
                    if (character.gender == "Female") R.drawable.ic_deco_female
                    else R.drawable.ic_deco_male
                ),
                contentDescription = null,
                tint = lightGreen,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(topStart = 10.dp))
                    .background(darkGreen)
                    .padding(8.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                fontSize = 18.sp,
                color = darkGreen,
                text = "# ${character.id} · ${character.name}",
                fontWeight = FontWeight.Black
            )

            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                if (character.status != "unknown") character.status?.let { SimpleTextCard(text = it) }
                character.species?.let { SimpleTextCard(text = it) }
            }
        }
    }
}
