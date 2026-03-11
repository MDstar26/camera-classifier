package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AnswerSignalExtractorTest {
    @Test
    fun `extracts persian option format`() {
        assertEquals(3, AnswerSignalExtractor.extractChoiceNumber("پاسخ نهایی: گزینه 3"))
    }

    @Test
    fun `extracts english option format`() {
        assertEquals(2, AnswerSignalExtractor.extractChoiceNumber("Final answer: option 2"))
    }

    @Test
    fun `returns null when no choice found`() {
        assertNull(AnswerSignalExtractor.extractChoiceNumber("the result is force equals ma"))
    }
}
