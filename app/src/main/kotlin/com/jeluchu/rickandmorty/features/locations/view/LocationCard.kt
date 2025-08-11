package com.jeluchu.rickandmorty.features.locations.view

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeluchu.rickandmorty.R
import com.jeluchu.rickandmorty.core.ui.theme.darkGreen
import com.jeluchu.rickandmorty.features.locations.models.Location

@Composable
fun LocationCard(
    location: Location,
    onResidentClick: () -> Unit
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable { onResidentClick() },
    colors = CardDefaults.cardColors(
        containerColor = darkGreen.copy(.3f)
    ),
    shape = RoundedCornerShape(10.dp)
) {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint = darkGreen,
                        contentDescription = "Location",
                        modifier = Modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_deco_location),
                    )

                    Text(
                        maxLines = 1,
                        color = darkGreen,
                        text = location.name,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .basicMarquee(iterations = Int.MAX_VALUE)
                    )
                }

                Surface(
                    color = darkGreen.copy(.2f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        color = darkGreen,
                        text = location.type,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(
                            vertical = 2.dp,
                            horizontal = 10.dp
                        )
                    )
                }
            }

            if (location.residents.isNotEmpty()) {
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
                        text = stringResource(R.string.residents),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
                    ) {
                        items(location.residents, key = { it }) { resident ->
                            val id = Regex("""\d+$""").find(resident)?.value?.toIntOrNull()

                            Text(
                                text = "Resident #$id",
                                color = darkGreen,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(darkGreen.copy(.2f))
                                    .clickable { onResidentClick() }
                                    .padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
