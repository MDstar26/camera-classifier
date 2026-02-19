package com.example.aibrowser

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ProviderConfig(
    val id: String,
    val displayName: String,
    val baseUrl: String,
    val endpoint: String,
    val model: String,
    val authType: String = "bearer",
    val extraHeaders: Map<String, String> = emptyMap()
)

@Serializable
data class PromptTemplate(
    val id: String,
    val name: String,
    val prompt: String
)

@Serializable
data class CaptureSettings(
    val marginDp: Int = 64,
    val autoCaptureSeconds: Int? = null,
    val jpegQuality: Int = 80,
    val doNotSaveImages: Boolean = true
)

@Serializable
data class AiAssistantSettings(
    val providers: List<ProviderConfig> = emptyList(),
    val activeProviderId: String? = null,
    val providerPriorityOrder: List<String> = emptyList(),
    val prompts: List<PromptTemplate> = emptyList(),
    val activePromptId: String? = null,
    val allowNoPrompt: Boolean = true,
    val capture: CaptureSettings = CaptureSettings()
)

object AiSettingsCodec {
    private val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    fun encode(settings: AiAssistantSettings): String = json.encodeToString(settings)

    fun decode(serialized: String): AiAssistantSettings = json.decodeFromString(serialized)
}
