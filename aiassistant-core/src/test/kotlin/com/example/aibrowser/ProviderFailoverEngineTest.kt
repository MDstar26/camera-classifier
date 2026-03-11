package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals

class ProviderFailoverEngineTest {
    private val engine = ProviderFailoverEngine()

    @Test
    fun `builds attempts ordered by priority and retries twice`() {
        val providers = listOf(
            ProviderConfig("gemini", "Gemini", "https://x", "/v1", "gemini-1.5", priority = 10),
            ProviderConfig("openai", "OpenAI", "https://y", "/v1", "gpt-4o", priority = 20)
        )

        val plan = engine.executionPlan(
            providers = providers,
            unavailableProviderIds = emptySet(),
            retryPolicy = RetryPolicySettings(timeoutSeconds = 10, maxAttemptsPerProvider = 2)
        )

        assertEquals(
            listOf("openai#1", "openai#2", "gemini#1", "gemini#2"),
            plan.map { "${it.providerId}#${it.attempt}" }
        )
    }

    @Test
    fun `skips unavailable providers`() {
        val providers = listOf(
            ProviderConfig("gemini", "Gemini", "https://x", "/v1", "gemini-1.5", priority = 10),
            ProviderConfig("openai", "OpenAI", "https://y", "/v1", "gpt-4o", priority = 20)
        )

        val plan = engine.executionPlan(
            providers = providers,
            unavailableProviderIds = setOf("openai"),
            retryPolicy = RetryPolicySettings(maxAttemptsPerProvider = 2)
        )

        assertEquals(listOf("gemini#1", "gemini#2"), plan.map { "${it.providerId}#${it.attempt}" })
    }
}
