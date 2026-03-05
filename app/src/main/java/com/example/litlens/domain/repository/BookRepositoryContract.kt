package com.example.litlens.domain.repository

interface BookRepositoryContract {
    suspend fun describeBook(title: String): String
    suspend fun suggestSimilarBooks(title: String): String
}