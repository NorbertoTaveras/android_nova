package com.norbertotaveras.android_nova.presentation.screens.navigator.screen

import NewsBottomNavigation
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.presentation.screens.bookmark.screen.BookmarkScreen
import com.norbertotaveras.android_nova.presentation.screens.bookmark.viewmodel.BookmarkViewModel
import com.norbertotaveras.android_nova.presentation.screens.category.screen.CategoryScreen
import com.norbertotaveras.android_nova.presentation.screens.category.viewmodel.CategoryViewModel
import com.norbertotaveras.android_nova.presentation.screens.details.screen.DetailsScreen
import com.norbertotaveras.android_nova.presentation.screens.details.viewmodel.DetailsViewModel
import com.norbertotaveras.android_nova.presentation.screens.home.screen.HomeScreen
import com.norbertotaveras.android_nova.presentation.screens.home.viewmodel.HomeViewModel
import com.norbertotaveras.android_nova.presentation.screens.navigation.Route
import com.norbertotaveras.android_nova.presentation.screens.navigator.composables.BottomNavigationItem
import com.norbertotaveras.android_nova.presentation.screens.search.screen.SearchScreen
import com.norbertotaveras.android_nova.presentation.screens.search.viewmodel.SearchViewModel
import com.norbertotaveras.android_nova.presentation.screens.source.screen.SourceArticleScreen
import com.norbertotaveras.android_nova.presentation.screens.sources.screen.SourceScreen
import com.norbertotaveras.android_nova.presentation.screens.sources.viewmodel.SourcesViewModel
import com.norbertotaveras.android_nova.presentation.screens.weather.screen.WeatherScreen
import com.norbertotaveras.android_nova.presentation.screens.weather.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Rounded.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Rounded.List, text = "Sources"),
            BottomNavigationItem(icon = Icons.Rounded.Search, text = "Search"),
            BottomNavigationItem(icon = Icons.Rounded.Bookmarks, text = "Bookmark"),
            BottomNavigationItem(icon = Icons.Rounded.Cloud, text = "Weather")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SourceScreen.route -> 1
        Route.SearchScreen.route -> 2
        Route.BookmarkScreen.route -> 3
        Route.WeatherScreen.route -> 4
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SourceScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route ||
                backStackState?.destination?.route == Route.WeatherScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )
                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SourceScreen.route
                        )
                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                        4 -> navigateToTab(
                            navController = navController,
                            route = Route.WeatherScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                val state = remember { viewModel.viewStateHolder }
                HomeScreen(
                    state = state,
                    articles = articles,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    },
                    navigateToNewsCategory = { category ->
                        navigateToNewsCategory(
                            navController = navController,
                            category = category
                        )
                    }
                )
            }
            composable(route = Route.SourceScreen.route) {
                val viewModel: SourcesViewModel = hiltViewModel()
                val state = remember { viewModel.viewStateHolder }
                val sources = state.sources
                SourceScreen(
                    state = state,
                    sources = sources,
                    navigateToNewsSource = { name, id ->
                        navigateToNewsSource(
                            navController = navController,
                            name = name,
                            id = id
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
                    }
            }
            composable(
                route = Route.NewsCategoryScreen.route,
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) { backStackEntry ->
                CategoryScreen(
                    category = backStackEntry.arguments?.getString("category") ?: "",
                    navigateUp = { navController.navigateUp() },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(
                route = Route.NewsSourceScreen.route,
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("id") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                SourceArticleScreen(
                    id = backStackEntry.arguments?.getString("id") ?: "",
                    name = backStackEntry.arguments?.getString("name") ?: "",
                    navigateUp = { navController.navigateUp() },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
            composable(route = Route.WeatherScreen.route) {
                val viewModel: WeatherViewModel = hiltViewModel()
                val state = viewModel.viewStateHolder
                OnBackClickStateSaver(navController = navController)
                WeatherScreen(state = state)
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}

private fun navigateToNewsCategory(
    navController: NavController,
    category: String
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("category", category)
    navController.navigate(
        route = Route.NewsCategoryScreen.createRoute(category)
    )
}

private fun navigateToNewsSource(
    navController: NavController,
    name: String,
    id: String
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("name", name)
    navController.currentBackStackEntry?.savedStateHandle?.set("id", id)
    navController.navigate(
        route = Route.NewsSourceScreen.createRoute(name, id)
    )
}