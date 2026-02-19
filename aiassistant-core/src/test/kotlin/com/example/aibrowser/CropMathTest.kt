package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals

class CropMathTest {
    @Test
    fun `adds margin to the selected bounds`() {
        val points = listOf(PointF(100f, 80f), PointF(140f, 100f), PointF(180f, 140f))

        val rect = CropMath.rectFromStroke(points, surfaceWidth = 500, surfaceHeight = 500, marginPx = 20)

        assertEquals(IntRect(80, 60, 200, 160), rect)
        assertEquals(120, rect.width)
        assertEquals(100, rect.height)
    }

    @Test
    fun `clamps margins to viewport bounds`() {
        val points = listOf(PointF(4f, 3f), PointF(30f, 21f), PointF(45f, 28f))

        val rect = CropMath.rectFromStroke(points, surfaceWidth = 50, surfaceHeight = 40, marginPx = 100)

        assertEquals(IntRect(0, 0, 50, 40), rect)
    }
}
