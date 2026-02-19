package com.example.aibrowser

object AnswerSignalExtractor {
    private val optionRegex = Regex("""(?i)(option|گزینه)\s*[:\-]?\s*([1-4])""")
    private val isolatedDigitRegex = Regex("""(?<!\d)([1-4])(?!\d)""")

    /**
     * Extracts a likely multiple-choice answer index (1..4) from model text.
     * This is designed for explicit UI display (non-hidden).
     */
    fun extractChoiceNumber(text: String): Int? {
        optionRegex.find(text)?.groupValues?.getOrNull(2)?.toIntOrNull()?.let { return it }
        return isolatedDigitRegex.find(text)?.groupValues?.getOrNull(1)?.toIntOrNull()
    }
}
