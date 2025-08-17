package com.example.weatherforecastdemo

import com.example.weatherforecastdemo.data.WeatherForecastResponse
import com.example.weatherforecastdemo.data.WeatherResponse
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class WeatherApi {
    private final val WeatherCurrentDayApi = "https://api.openweathermap.org/data/2.5/weather"
    private final val WeatherForecastApi   = "https://api.openweathermap.org/data/2.5/forecast"
    private var onResponseListener: IWeatherApiListener? = null

    fun getWeatherFromApi(apiKey: String, city: String, type: String) {
        println("[getWeatherFromApi]...............................{$city} {$type}")
        if (type == "Current Day") {
            //weatherParser()
            val weatherCurrentServerApi = "$WeatherCurrentDayApi?q=$city&appid=$apiKey";
            asynchronousGetRequest(weatherCurrentServerApi, 0)
        } else {
            //weatherForecastParser()
            val weatherForecastServerApi = "$WeatherForecastApi?q=$city&appid=$apiKey";
            asynchronousGetRequest(weatherForecastServerApi, 1)
        }
    }

    fun asynchronousGetRequest(url: String, api: Int) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle success
                val result = response.body?.string() ?: ""
                // Process the response data
                println(result)
                try {
                    if (api == 0) {
                        val weatherCurrent = Json.decodeFromString<WeatherResponse>(result)
                        println("JSON: ${weatherCurrent.cod}")
                        onResponseListener?.onSuccessWeatherResponse(weatherCurrent)
                    } else {
                        val forecast = Json.decodeFromString<WeatherForecastResponse>(result)
                        println("JSON: ${forecast.cod}")
                        onResponseListener?.onSuccessForecastResponse(forecast)
                    }
                } catch (e: Exception) {
                    println("Error parsing JSON: ${e.message}")
                }
            }
        })
    }

    fun setOnResponseListener(Listener: IWeatherApiListener) {
        this.onResponseListener = Listener
    }

    interface IWeatherApiListener {
        fun onSuccessWeatherResponse(weather: WeatherResponse)
        fun onSuccessForecastResponse(forecast: WeatherForecastResponse)
    }

    /* Test Weather API Data */
    fun weatherParser() : WeatherResponse? {
        val openWeatherJson = "{\n" +
                "  \"coord\" : {\n" +
                "    \"lon\" : -0.1257,\n" +
                "    \"lat\" : 51.5085\n" +
                "  },\n" +
                "  \"weather\" : [ {\n" +
                "    \"id\" : 800,\n" +
                "    \"main\" : \"Clear\",\n" +
                "    \"description\" : \"clear sky\",\n" +
                "    \"icon\" : \"01n\"\n" +
                "  } ],\n" +
                "  \"base\" : \"stations\",\n" +
                "  \"main\" : {\n" +
                "    \"temp\" : 290.25,\n" +
                "    \"feels_like\" : 290.16,\n" +
                "    \"temp_min\" : 290.25,\n" +
                "    \"temp_max\" : 290.25,\n" +
                "    \"pressure\" : 1027,\n" +
                "    \"humidity\" : 82,\n" +
                "    \"sea_level\" : 1027,\n" +
                "    \"grnd_level\" : 1023\n" +
                "  },\n" +
                "  \"visibility\" : 10000,\n" +
                "  \"wind\" : {\n" +
                "    \"speed\" : 3.81,\n" +
                "    \"deg\" : 53,\n" +
                "    \"gust\" : 6.37\n" +
                "  },\n" +
                "  \"clouds\" : {\n" +
                "    \"all\" : 8\n" +
                "  },\n" +
                "  \"dt\" : 1755308622,\n" +
                "  \"sys\" : {\n" +
                "    \"country\" : \"GB\",\n" +
                "    \"sunrise\" : 1755319669,\n" +
                "    \"sunset\" : 1755372120\n" +
                "  },\n" +
                "  \"timezone\" : 3600,\n" +
                "  \"id\" : 2643743,\n" +
                "  \"name\" : \"London\",\n" +
                "  \"cod\" : 200\n" +
                "}"
        try {
            val weather = Json.decodeFromString<WeatherResponse>(openWeatherJson)
            return weather
        } catch (e: Exception) {
            println("Error parsing JSON: ${e.message}")
        }
        return null
    }

    fun weatherForecastParser() : WeatherForecastResponse? {
        val openWeatherJson = "{\n" +
                "    \"cod\": \"200\",\n" +
                "    \"message\": 0,\n" +
                "    \"cnt\": 40,\n" +
                "    \"list\": [\n" +
                "    {\n" +
                "        \"dt\": 1755313200,\n" +
                "        \"main\": {\n" +
                "                 \"temp\": 289.87,\n" +
                "                 \"feels_like\": 289.76,\n" +
                "                 \"temp_min\": 289.87,\n" +
                "                 \"temp_max\": 289.87,\n" +
                "                 \"pressure\": 1027,\n" +
                "                 \"sea_level\": 1027,\n" +
                "                 \"grnd_level\": 1023,\n" +
                "                 \"humidity\": 83,\n" +
                "                 \"temp_kf\": 0\n" +
                "                 },\n" +
                "        \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 800,\n" +
                "            \"main\": \"Clear\",\n" +
                "            \"description\": \"clear sky\",\n" +
                "            \"icon\": \"01n\"\n" +
                "        }\n" +
                "        ],\n" +
                "        \"clouds\": {\"all\": 9},\n" +
                "        \"wind\": {\n" +
                "                  \"speed\": 3.98,\n" +
                "                  \"deg\": 53,\n" +
                "                  \"gust\": 6.85\n" +
                "                  },\n" +
                "        \"visibility\": 10000,\n" +
                "        \"pop\": 0,\n" +
                "        \"sys\": {\"pod\": \"n\"},\n" +
                "        \"dt_txt\": \"2025-08-16 03:00:00\"\n" +
                "    }],\n" +
                "    \"city\": {\n" +
                "             \"id\": 2643743,\n" +
                "             \"name\": \"London\",\n" +
                "             \"coord\": {\n" +
                "                       \"lat\": 51.5085,\n" +
                "                       \"lon\": -0.1257\n" +
                "             },\n" +
                "    \"country\": \"GB\",\n" +
                "    \"population\": 1000000,\n" +
                "    \"timezone\": 3600,\n" +
                "    \"sunrise\": 1755319669,\n" +
                "    \"sunset\": 1755372120\n" +
                "    }\n" +
                "}"
        try {
            val forecast = Json.decodeFromString<WeatherForecastResponse>(openWeatherJson)
            return forecast
        } catch (e: Exception) {
            println("Error parsing JSON: ${e.message}")
        }
        return null
    }

}