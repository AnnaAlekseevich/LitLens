package com.example.litlens.domain.model

data class BookQuery(
    val id: Long = 0,
    val title: String,
    val timestamp: Long = System.currentTimeMillis(),
    val response: String? = null
)