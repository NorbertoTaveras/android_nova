package com.norbertotaveras.android_nova.presentation.screens.search.event

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}