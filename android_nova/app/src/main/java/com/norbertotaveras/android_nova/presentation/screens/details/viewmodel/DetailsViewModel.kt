package com.norbertotaveras.android_nova.presentation.screens.details.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norbertotaveras.android_nova.data.remote.dto.articles.Article
import com.norbertotaveras.android_nova.domain.usecase.news.NewsUseCases
import com.norbertotaveras.android_nova.presentation.screens.details.event.DetailsEvent
import com.norbertotaveras.android_nova.utils.state.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    private val _articleSaved = MutableStateFlow<Boolean>(false)
    val articleSaved: StateFlow<Boolean> = _articleSaved.asStateFlow()

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticleUseCase(url = event.article.url) != null
                    if (!article){
                        upsertArticle(article = event.article)
                        _articleSaved.value = true
                        onEvent(DetailsEvent.CheckArticleSaved(event.article.url))
                    } else {
                        deleteArticle(article = event.article)
                        _articleSaved.value = false
                        onEvent(DetailsEvent.CheckArticleSaved(event.article.url))
                    }
                }
            }
            is DetailsEvent.CheckArticleSaved -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticleUseCase(event.url)
                    _articleSaved.value = (article != null)
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticleUseCase(article = article)
        sideEffect = UIComponent.Toast("Article Deleted")
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticleUseCase(article = article)
        sideEffect = UIComponent.Toast("Article Saved")
    }
}