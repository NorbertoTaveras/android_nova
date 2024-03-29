package com.norbertotaveras.android_nova.presentation.screens.weather.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.norbertotaveras.android_nova.domain.model.source.SourceDataModel
import com.norbertotaveras.android_nova.presentation.composables.shared.BoxWithLoading
import com.norbertotaveras.android_nova.presentation.screens.home.screen.AppSwitchToolbar
import com.norbertotaveras.android_nova.presentation.screens.sources.composables.NewsSourceItem
import com.norbertotaveras.android_nova.presentation.screens.sources.state.SourcesViewStateHolder
import com.norbertotaveras.android_nova.presentation.screens.weather.composables.CurrentWeatherSection
import com.norbertotaveras.android_nova.presentation.screens.weather.composables.DailyWeatherSection
import com.norbertotaveras.android_nova.presentation.screens.weather.composables.HourlyWeatherSection
import com.norbertotaveras.android_nova.presentation.screens.weather.composables.LocationHeader
import com.norbertotaveras.android_nova.presentation.screens.weather.state.WeatherViewStateHolder
import com.norbertotaveras.android_nova.presentation.theme.AppColors

@Composable
fun WeatherScreen(
    state: WeatherViewStateHolder
) {
    Scaffold(topBar = { AppSwitchToolbar() }) { padding ->
        BoxWithLoading(
            modifier = Modifier
                .background(color = AppColors.parent)
                .padding(top = 64.dp)
                .fillMaxSize()
                .systemBarsPadding(),
            state = state
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                state.current?.let { weather ->
                    state.location?.let { location ->
                        state.forecast?.let { forecast ->
                            LocationHeader(
                                location = location
                            )
                            CurrentWeatherSection(
                                weather = weather,
                                location = location
                            )
                            HourlyWeatherSection(
                                forecast = forecast
                            )
                            DailyWeatherSection(
                                forecast = forecast
                            )
                        }
                    }
                }
            }
        }
    }
}