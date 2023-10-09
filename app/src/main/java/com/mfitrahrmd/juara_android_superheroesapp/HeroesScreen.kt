package com.mfitrahrmd.juara_android_superheroesapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfitrahrmd.juara_android_superheroesapp.model.Hero
import com.mfitrahrmd.juara_android_superheroesapp.ui.theme.Juara_AndroidSuperheroesAppTheme

@Composable
fun HeroItem(hero: Hero) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            HeroInformation(hero.nameRes, hero.descriptionRes)
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            HeroImage(hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(@StringRes nameRes: Int, @StringRes descriptionRes: Int) {
    Column {
        Text(text = stringResource(id = nameRes), style = MaterialTheme.typography.displaySmall)
        Text(text = stringResource(id = descriptionRes), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun HeroImage(@DrawableRes imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .size(72.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun HeroItemList(heroes: List<Hero>, contentPadding: PaddingValues) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(items = heroes) { hero -> 
            HeroItem(hero)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen() {
    val heroes = listOf(
        Hero(
            R.string.hero1,
            R.string.description1,
            R.drawable.witch_0
        ),
        Hero(
            R.string.hero2,
            R.string.description2,
            R.drawable.priestess_0
        )
    )
    Scaffold(
        topBar = {
            SuperheroesTopAppBar(R.string.superheroes)
        }
    ) { pv ->
        HeroItemList(heroes, pv)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesTopAppBar(@StringRes title: Int, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
                Text(text = stringResource(id = title), style = MaterialTheme.typography.displayLarge)
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeroItemPreview() {
    Juara_AndroidSuperheroesAppTheme {
        HeroesScreen()
    }
}