package com.example.litlens.data.remote.koog

import ai.koog.prompt.executor.llms.all.simpleOpenAIExecutor

object KoogExecutorProvider {
    fun provide(apiKey: String) = simpleOpenAIExecutor(apiKey)
}