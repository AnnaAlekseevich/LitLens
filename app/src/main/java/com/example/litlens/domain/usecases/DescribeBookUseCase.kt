package com.example.litlens.domain.usecases

import com.example.litlens.domain.repository.BookRepositoryContract
import javax.inject.Inject

class DescribeBookUseCase @Inject constructor(private val repo: BookRepositoryContract) {
    suspend operator fun invoke(title: String): String = repo.describeBook(title)
}