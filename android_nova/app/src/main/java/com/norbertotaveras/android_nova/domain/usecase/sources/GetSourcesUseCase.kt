package com.norbertotaveras.android_nova.domain.usecase.sources

import com.norbertotaveras.android_nova.domain.model.source.SourceResponseDataModel
import com.norbertotaveras.android_nova.domain.repository.news.NewsFwkRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val repository: NewsFwkRepository
) {
    suspend operator fun invoke(): Result<SourceResponseDataModel> {
        return repository.runCatching {
            getSources(country = "us")
        }.mapCatching {
            Result.success(it.toSourceResponseDateModel())
        }.getOrElse {
            Result.failure(it)
        }
    }
}