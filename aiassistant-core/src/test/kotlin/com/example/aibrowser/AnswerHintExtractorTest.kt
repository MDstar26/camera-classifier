package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals

class AnswerHintExtractorTest {
    @Test
    fun `extracts persian گزینه pattern`() {
        assertEquals(3, AnswerHintExtractor.extractOptionNumberOrNull("پاسخ نهایی: گزینه 3"))
    }

    @Test
    fun `extracts english option pattern`() {
        assertEquals(2, AnswerHintExtractor.extractOptionNumberOrNull("The best option is 2."))
    }

    @Test
    fun `returns null when no mcq number exists`() {
        assertEquals(null, AnswerHintExtractor.extractOptionNumberOrNull("Detailed explanation without final option."))
    }
}
