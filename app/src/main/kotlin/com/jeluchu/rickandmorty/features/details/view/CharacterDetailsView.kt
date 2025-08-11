package com.jeluchu.rickandmorty.features.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.ui.theme.TypographyRicky
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen
import com.jeluchu.rickandmorty.features.characters.models.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsView(
    character: Character?,
    onBackClick: () -> Unit = {}
) = Scaffold(
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
                    text = character?.name.orEmpty()
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.secondary,
                navigationIconContentColor = MaterialTheme.colorScheme.secondary
            )
        )
    },
    containerColor = MaterialTheme.colorScheme.background,
    content = { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = padding.calculateTopPadding())
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, darkGreen, RoundedCornerShape(8.dp))
                    .background(darkGreen.copy(.3f))
            ) {
                AsyncImage(
                    model = character?.image,
                    contentDescription = character?.name,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .background(
                            MaterialTheme.colorScheme.secondary,
                            RoundedCornerShape(bottomEnd = 12.dp)
                        )
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        fontWeight = FontWeight.Black,
                        text = character?.status.orEmpty(),
                        style = TypographyRicky.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = darkGreen.copy(.4f),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Text(
                    color = darkGreen,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    text = stringResource(R.string.properties),
                    modifier = Modifier.fillMaxWidth()
                )

                CharacterInfoCard(
                    name = stringResource(R.string.gender),
                    value = character?.gender.orEmpty()
                )

                CharacterInfoCard(
                    name = stringResource(R.string.species),
                    value = character?.species.orEmpty()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = darkGreen.copy(.4f),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
            ) {
                Text(
                    color = darkGreen,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    text = stringResource(R.string.whereabouts),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                CharacterInfoCard(
                    name = stringResource(R.string.location),
                    value = character?.location?.name.orEmpty()
                )

                CharacterInfoCard(
                    name = stringResource(R.string.origin),
                    value = character?.origin?.name.orEmpty()
                )
            }
        }
    }
)

@Composable
fun CharacterInfoCard(
    name: String,
    value: String
) = Row(
    modifier = Modifier
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
) {
    Text(
        text = name,
        color = darkGreen,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Black,
        style = TypographyRicky.bodyLarge,
        modifier = Modifier
            .weight(.5f)
            .background(
                color = darkGreen.copy(.25f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    )

    Text(
        text = value,
        color = darkGreen,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Black,
        style = TypographyRicky.bodyLarge,
        modifier = Modifier
            .weight(1f)
            .background(
                color = darkGreen.copy(.15f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    )
}