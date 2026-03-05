package com.example.litlens.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litlens.domain.usecases.DescribeBookUseCase
import com.example.litlens.domain.usecases.SuggestSimilarBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val describeBookUseCase: DescribeBookUseCase,
    private val suggestSimilarBooksUseCase: SuggestSimilarBooksUseCase,
) : ViewModel() {

    private val _response = MutableStateFlow("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _describeResponse = MutableStateFlow("")
    val describeResponse: StateFlow<String> = _describeResponse

    private val _similarResponse = MutableStateFlow("")
    val similarResponse: StateFlow<String> = _similarResponse

    fun describeBook(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            _isLoading.value = true
            _response.value = "Thinking..."
            try {
                val result = describeBookUseCase(title)
                _response.value = result
                _describeResponse.value = result
            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
                _describeResponse.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun findSimilarBooks(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            _isLoading.value = true
            _response.value = "Thinking..."
            try {
                val result = suggestSimilarBooksUseCase(title)
                _similarResponse.value = result
                _response.value = result
            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
                _similarResponse.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}