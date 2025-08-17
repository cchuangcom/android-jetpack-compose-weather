package com.example.weatherforecastdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherforecastdemo.data.WeatherForecastItem
import com.example.weatherforecastdemo.ui.theme.WeatherForecastDemoTheme
import com.example.weatherforecastdemo.viewmodel.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastDemoTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    val viewModel: WeatherViewModel = viewModel()
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: WeatherViewModel) {

    val apiKey = stringResource(R.string.api_key)
    val cityList = mutableListOf(
        "Taipei",
        "Tokyo",
        "Shanghai",
        "New York",
        "London",
        "Los Angeles",
    )
    val isCityClick = rememberSaveable { mutableStateOf(false) }
    val citySelect= rememberSaveable { mutableStateOf("Taipei") }

    val weatherList = mutableListOf(
        stringResource(R.string.current_day),
        stringResource(R.string.week_forecast),
    )
    val isWeatherClick = rememberSaveable { mutableStateOf(false) }
    val weatherSelect = rememberSaveable { mutableStateOf("Current Day") }

    // Collect the StateFlow as a Compose State
    val weather by viewModel.weather.collectAsState()
    val forecast by viewModel.forecast.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeatherData(apiKey, citySelect.value, weatherSelect.value)
    }

    Column(modifier = Modifier.fillMaxWidth().padding(50.dp)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row() {
                Box(
                    Modifier
                        .weight(0.4f)
                        .padding(10.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { isCityClick.value = !isCityClick.value },
                        content = {
                            Text(text = citySelect.value)
                        },
                    )
                    DropdownMenu(
                        expanded = isCityClick.value,
                        onDismissRequest = { isCityClick.value = false }) {
                        cityList.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = { Text(s) },
                                onClick = {
                                    isCityClick.value = false
                                    citySelect.value = s
                                    //weatherApi.getWeatherFromApi(apiKey, s, weatherSelect.value)
                                    viewModel.fetchWeatherData(apiKey, s, weatherSelect.value)
                                }
                            )
                        }
                    }
                }
                Box(
                    Modifier
                        .weight(0.4f)
                        .padding(10.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { isWeatherClick.value = !isWeatherClick.value },
                        content = {
                            Text(text = weatherSelect.value)
                        },
                    )
                    DropdownMenu(
                        expanded = isWeatherClick.value,
                        onDismissRequest = { isWeatherClick.value = false }) {
                        weatherList.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                text = { Text(s) },
                                onClick = {
                                    isWeatherClick.value = false
                                    weatherSelect.value = s
                                    //weatherApi.getWeatherFromApi(apiKey, citySelect.value, s)
                                    viewModel.fetchWeatherData(apiKey, citySelect.value, s)
                                }
                            )
                        }
                    }
                }
            }

        }

        if (weatherSelect.value == stringResource(R.string.current_day)) {
            weather?.let {
                FetchCurrentDayWeatherCard(
                    it.name,
                    it.weather.get(0).description,
                    it.main.temp.toString(),
                    getWeatherConditionIcon(it.weather.get(0).icon)
                )
            }
        } else {
            forecast?.let {
                FetchForecastList(it.list)
            }
        }
/*      // Test Weather API Data
        val weatherApi = WeatherApi()
        if (weatherSelect.value == stringResource(R.string.current_day)) {
            val weather = weatherApi.weatherParser()
            if (weather != null) {
                FetchCurrentDayWeatherCard(
                    weather.name,
                    weather.weather.get(0).description,
                    weather.main.temp.toString(),
                    getWeatherConditionIcon(weather.weather.get(0).icon)
                )
            }
        } else {
            val forecast = weatherApi.weatherForecastParser()
            if (forecast != null) {
                val list = forecast.list
                FetchForecastList(list)
            }
        }
 */
    }
}

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
fun FetchForecastList(list: List<WeatherForecastItem>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(list.size) { index ->
            FetchForecastWeatherCard(
                list.get(index).dt_txt,
                list.get(index).weather.get(0).description,
                list.get(index).main.temp.toString(),
                getWeatherConditionIcon(list.get(index).weather.get(0).icon)
            )
        }
    }
/*  // Test Weather API Data
    val item = list.get(0)
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            repeat(10) {
                FetchForecastWeatherCard(
                    item.dt_txt,
                    item.weather.get(0).description,
                    item.main.temp.toString(),
                    getWeatherConditionIcon(item.weather.get(0).icon)
                )
            }
        }
    }
*/
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

fun getWeatherConditionIcon(icon: String) : Int {
    if (icon == "01d") return R.drawable.clearsky
    if (icon == "02d") return R.drawable.fewclouds
    if (icon == "03d") return R.drawable.scatteredclouds
    if (icon == "04d") return R.drawable.brokenclouds
    if (icon == "09d") return R.drawable.showerrain
    if (icon == "10d") return R.drawable.rain
    if (icon == "11d") return R.drawable.thunderstorm
    if (icon == "13d") return R.drawable.snow
    if (icon == "50d") return R.drawable.mist
    return R.drawable.clearsky
}



