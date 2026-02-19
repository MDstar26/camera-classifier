package com.example.aibrowser

object AiRequestComposer {
    fun resolvePrompt(settings: AiAssistantSettings): String? {
        if (!settings.sendPromptWithImage) return null
        val promptId = settings.activePromptId ?: return null
        return settings.prompts.firstOrNull { it.id == promptId }?.prompt
    }
}
