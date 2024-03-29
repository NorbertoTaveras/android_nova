package com.norbertotaveras.android_nova.presentation.screens.weather.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.norbertotaveras.android_nova.domain.model.forecast.DayDataModel
import com.norbertotaveras.android_nova.domain.model.forecast.ForecastDataModel
import com.norbertotaveras.android_nova.domain.model.forecast.HourDataModel
import com.norbertotaveras.android_nova.domain.model.weather.CurrentDataModel
import com.norbertotaveras.android_nova.domain.model.weather.LocationDataModel
import com.norbertotaveras.android_nova.extensions.toDayOfWeek
import com.norbertotaveras.android_nova.extensions.toLocalDateTime
import com.norbertotaveras.android_nova.extensions.toLocalTime
import com.norbertotaveras.android_nova.presentation.theme.AppColors
import com.norbertotaveras.android_nova.utils.dimens.Dimens
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LocationHeader(location: LocationDataModel) {
    Text(
        text = location.name,
        style = MaterialTheme.typography.displaySmall.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp)
    )
}

@Composable
fun CurrentWeatherSection(
    weather: CurrentDataModel,
    location: LocationDataModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Current",
            style = MaterialTheme.typography.labelLarge.copy(color = AppColors.gray),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, bottom = 10.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = rememberAsyncImagePainter(weather.condition.iconUrl),
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(48.dp)
                )
                Column {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = String.format("%.1f°F", weather.temp_f),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = weather.condition.text,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
    }
}

@Composable
fun HourlyWeatherSection(forecast: ForecastDataModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = "Hourly",
            style = MaterialTheme.typography.labelLarge.copy(color = AppColors.gray),
            modifier = Modifier
                .padding(start = 20.dp, top = 24.dp, bottom = 8.dp)
                .align(Alignment.Start)
        )
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 2.dp, bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(vertical = 8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val hours = forecast.forecastDay.flatMap { it.hour }.take(12)
                items(hours.size) { index ->
                    val hour = hours[index]
                    HourlyWeatherItem(hour)
                }
            }
        }
    }
}

@Composable
fun HourlyWeatherItem(hour: HourDataModel) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            hour.time_epoch.toLocalTime(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
        Image(
            painter = rememberAsyncImagePainter(hour.condition.iconUrl),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(60.dp)
                .padding(top = 8.dp, bottom = 8.dp)
        )
        Text(
            text = "${hour.temp_f}°F",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun DailyWeatherSection(forecast: ForecastDataModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = "Daily",
            style = MaterialTheme.typography.labelLarge.copy(color = AppColors.gray),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 4.dp, bottom = 0.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            val dayPairs = forecast.forecastDay.map { it.date to it.day }
            LazyColumn {
                items(dayPairs.size) { index ->
                    if (index > 0)
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 0.dp),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.DividerAlpha)
                        )
                    val (date, day) = dayPairs[index]
                    DailyWeatherItem(date, day)
                }
            }
        }
    }
}

@Composable
fun DailyWeatherItem(
    date: String,
    day: DayDataModel,
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date.toDayOfWeek(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(model = day.condition.iconUrl),
                contentDescription = "Daily Weather Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and temperatures
            Text(
                text = "${day.maxtemp_f}°F",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}