package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AiRequestComposerTest {
    @Test
    fun `returns active prompt when enabled`() {
        val settings = AiAssistantSettings(
            prompts = listOf(PromptTemplate("p1", "default", "solve step by step")),
            activePromptId = "p1",
            sendPromptWithImage = true
        )

        assertEquals("solve step by step", AiRequestComposer.resolvePrompt(settings))
    }

    @Test
    fun `returns null when prompt sending disabled`() {
        val settings = AiAssistantSettings(
            prompts = listOf(PromptTemplate("p1", "default", "solve step by step")),
            activePromptId = "p1",
            sendPromptWithImage = false
        )

        assertNull(AiRequestComposer.resolvePrompt(settings))
    }
}
