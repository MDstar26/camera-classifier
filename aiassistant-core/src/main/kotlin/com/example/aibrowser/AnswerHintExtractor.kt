package com.example.aibrowser

/**
 * Extracts an explicit numeric hint (1..4) from AI text for MCQ-like answers.
 * This is for visible UI display (not hidden UI behavior).
 */
object AnswerHintExtractor {
    private val patterns = listOf(
        Regex("گزینه\\s*([1-4])"),
        Regex("option\\s*([1-4])", RegexOption.IGNORE_CASE),
        Regex("answer\\s*[:：-]?\\s*([1-4])", RegexOption.IGNORE_CASE),
        Regex("^\\s*([1-4])\\s*$")
    )

    fun extractOptionNumberOrNull(text: String): Int? {
        for (pattern in patterns) {
            val match = pattern.find(text) ?: continue
            val value = match.groupValues.getOrNull(1)?.toIntOrNull()
            if (value != null && value in 1..4) return value
        }
        return null
    }
}
