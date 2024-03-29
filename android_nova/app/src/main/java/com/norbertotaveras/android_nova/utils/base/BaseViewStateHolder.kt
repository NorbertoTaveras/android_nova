package com.norbertotaveras.android_nova.utils.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class BaseViewStateHolder(isLoading: Boolean) {
    var isLoading by mutableStateOf(isLoading)
}