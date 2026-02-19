package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AiSettingsCodecTest {
    @Test
    fun `serializes and deserializes settings`() {
        val settings = AiAssistantSettings(
            providers = listOf(
                ProviderConfig(
                    id = "openai",
                    displayName = "OpenAI",
                    baseUrl = "https://api.openai.com",
                    endpoint = "/v1/responses",
                    model = "gpt-4.1-mini"
                )
            ),
            activeProviderId = "openai",
            prompts = listOf(
                PromptTemplate(
                    id = "study",
                    name = "Study Helper",
                    prompt = "Analyze the question and answer step by step."
                )
            ),
            activePromptId = "study",
            capture = CaptureSettings(marginDp = 64, autoCaptureSeconds = 3, jpegQuality = 85)
        )

        val encoded = AiSettingsCodec.encode(settings)
        val decoded = AiSettingsCodec.decode(encoded)

        assertEquals(settings, decoded)
        assertTrue(encoded.contains("OpenAI"))
    }
}
