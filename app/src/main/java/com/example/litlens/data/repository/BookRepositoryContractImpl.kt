package com.example.litlens.data.repository

import ai.koog.agents.core.agent.AIAgent
import ai.koog.prompt.executor.clients.openai.OpenAIModels
import com.example.litlens.data.remote.google.GoogleBooksApi
import com.example.litlens.data.remote.koog.KoogExecutorProvider
import com.example.litlens.domain.repository.BookRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class BookRepositoryContractImpl @Inject constructor(
    private val googleApi: GoogleBooksApi,
    @Named("OPENAI_API_KEY") private val openAiApiKey: String
) : BookRepositoryContract {

    private fun createAgent(): AIAgent<String, String> {
        return AIAgent(
            promptExecutor = KoogExecutorProvider.provide(openAiApiKey),
            llmModel = OpenAIModels.Chat.GPT5,
            systemPrompt = "You are an intelligent literary assistant. Provide concise descriptions and recommend similar books."
        )
    }

    override suspend fun describeBook(title: String): String {

        val googleRes = googleApi.searchVolumes(title)

        val meta = googleRes.items?.firstOrNull()?.volumeInfo?.description ?: ""
        val prompt =
            "Describe the book titled \"$title\".\nIf you know metadata: $meta\nKeep answer concise (2-4 sentences)."

        val agent = createAgent()
        return agent.run(prompt)
    }

    override suspend fun suggestSimilarBooks(title: String): String = withContext(Dispatchers.IO) {
        val prompt =
            "Suggest 3–5 books similar to \"$title\" and explain briefly why each is similar. Provide titles and 1-line reasons."
        val agent = createAgent()
        agent.run(prompt)
    }

}