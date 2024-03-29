package com.norbertotaveras.android_nova.domain.usecase.app

import com.norbertotaveras.android_nova.domain.manager.LocalUserManager

class SaveAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}