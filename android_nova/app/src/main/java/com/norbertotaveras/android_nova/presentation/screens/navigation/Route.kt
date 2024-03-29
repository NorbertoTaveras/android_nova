package com.norbertotaveras.android_nova.presentation.screens.navigation

sealed class Route(
    val route: String
) {
    data object OnboardingScreen : Route(route = "onboardingScreen")
    data object HomeScreen : Route(route = "homeScreen")
    data object SourceScreen : Route(route = "sourceScreen")
    data object SearchScreen : Route(route = "searchScreen")
    data object BookmarkScreen : Route(route = "bookmarkScreen")
    data object WeatherScreen : Route(route = "weatherScreen")
    data object DetailScreen : Route(route = "detailScreen")
    data object NewsCategoryScreen : Route("newsCategoryScreen/{category}") {
        fun createRoute(category: String) = "newsCategoryScreen/$category"
    }
    data object NewsSourceScreen : Route("newsSourceScreen/{name}/{id}") {
        fun createRoute(name: String, id: String) = "newsSourceScreen/$name/$id"
    }
    data object AppStartNavigation : Route(route = "appStartNavigation")
    data object NewsNavigation : Route(route = "newsNavigation")
    data object NewsNavigatorScreen : Route(route = "newsNavigator")
}