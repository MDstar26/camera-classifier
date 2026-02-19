package com.example.aibrowser

import kotlin.math.max
import kotlin.math.min

/**
 * Bounding box calculation for free-form selection overlays.
 */
object CropMath {
    fun rectFromStroke(points: List<PointF>, surfaceWidth: Int, surfaceHeight: Int, marginPx: Int): IntRect {
        require(points.isNotEmpty()) { "points must not be empty" }
        require(surfaceWidth > 0 && surfaceHeight > 0) { "surface bounds must be positive" }

        val minX = points.minOf { it.x }
        val minY = points.minOf { it.y }
        val maxX = points.maxOf { it.x }
        val maxY = points.maxOf { it.y }

        val left = max(0, (minX - marginPx).toInt())
        val top = max(0, (minY - marginPx).toInt())
        val right = min(surfaceWidth, (maxX + marginPx).toInt())
        val bottom = min(surfaceHeight, (maxY + marginPx).toInt())

        return IntRect(left, top, right, bottom)
    }
}

data class PointF(val x: Float, val y: Float)

data class IntRect(val left: Int, val top: Int, val right: Int, val bottom: Int) {
    val width: Int = max(0, right - left)
    val height: Int = max(0, bottom - top)
}
