package com.norbertotaveras.android_nova.presentation.screens.onboarding.sealed.event

sealed class OnboardingEvent {
    data object SaveAppEntry: OnboardingEvent()
}