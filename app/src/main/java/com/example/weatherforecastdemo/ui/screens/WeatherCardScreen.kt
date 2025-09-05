package com.example.weatherforecastdemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherforecastdemo.R

@Composable
fun FetchCurrentDayWeatherCard(city: String, weather: String, temperature: String, icon: Int) {
    Column {
        Card(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit,
                )
                Column(Modifier.padding(4.dp)) {
                    Text(text = stringResource(R.string.location) + ": $city")
                    Text(text = stringResource(R.string.weather) + ": $weather")
                    Text(text = stringResource(R.string.temperature) + ": $temperature")
                }
            }
        }
    }
}

@Composable
fun FetchForecastWeatherCard(dt: String, weather: String, temperature: String, icon: Int) {
    Column {
        Card(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit,
                )
                Column(Modifier.padding(4.dp)) {
                    Text(text = "$dt ")
                    Text(text = stringResource(R.string.weather) + ": $weather")
                    Text(text = stringResource(R.string.temperature) + ": $temperature")
                }
            }
        }
    }
}
