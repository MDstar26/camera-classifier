package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AiRoutingTest {
    @Test
    fun `builds provider attempt plan with 2 retries and 10 second timeout`() {
        val planner = AiFailoverPlanner(maxAttemptsPerProvider = 2, requestTimeoutSeconds = 10)

        val plan = planner.buildPlan(listOf("chatgpt", "gemini"))

        assertEquals(
            listOf(
                ProviderAttempt("chatgpt", 1, 10),
                ProviderAttempt("chatgpt", 2, 10),
                ProviderAttempt("gemini", 1, 10),
                ProviderAttempt("gemini", 2, 10)
            ),
            plan
        )
    }

    @Test
    fun `disables provider after repeated failure and keeps order for remaining providers`() {
        val state = ProviderRuntimeState(orderedProviderIds = listOf("chatgpt", "gemini", "proxy"))

        val disabled = state.markDisabled("chatgpt")

        assertEquals(listOf("gemini", "proxy"), disabled.activeOrderedProviders())
    }

    @Test
    fun `resolves selected prompt or allows none`() {
        val templates = listOf(
            PromptTemplate("t1", "حل مرحله ای", "قدم به قدم جواب بده"),
            PromptTemplate("t2", "خلاصه", "خلاصه کن")
        )

        assertEquals("قدم به قدم جواب بده", resolvePromptText(PromptSelection.Template("t1"), templates))
        assertNull(resolvePromptText(PromptSelection.None, templates))
    }

    @Test
    fun `extracts option number for mcq answers`() {
        assertEquals(3, extractMcqOptionNumber("گزینه صحیح 3 است"))
        assertEquals(1, extractMcqOptionNumber("Answer: 1"))
        assertNull(extractMcqOptionNumber("پاسخ مشخص نیست"))
    }
}
