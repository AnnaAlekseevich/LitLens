package com.example.litlens.domain.usecases

import com.example.litlens.domain.repository.BookRepositoryContract
import javax.inject.Inject

class SuggestSimilarBooksUseCase @Inject constructor(private val repo: BookRepositoryContract) {
    suspend operator fun invoke(title: String): String = repo.suggestSimilarBooks(title)
}