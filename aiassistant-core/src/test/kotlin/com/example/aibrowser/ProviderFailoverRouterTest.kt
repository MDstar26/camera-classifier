package com.example.aibrowser

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ProviderFailoverRouterTest {
    @Test
    fun `suspends provider after two failures and moves to next`() {
        val router = ProviderFailoverRouter(listOf("chatgpt", "gemini", "proxy"), maxAttemptsPerProvider = 2)

        assertEquals("chatgpt", router.currentProviderOrNull())
        assertEquals(false, router.reportFailure("chatgpt"))
        assertEquals(true, router.reportFailure("chatgpt"))

        assertEquals(listOf("chatgpt"), router.suspendedProviderIds())
        assertEquals("gemini", router.currentProviderOrNull())
    }

    @Test
    fun `supports provider reordering`() {
        val router = ProviderFailoverRouter(listOf("chatgpt", "gemini", "proxy"))
        router.reorderProviders(listOf("proxy", "gemini", "chatgpt"))

        assertEquals(listOf("proxy", "gemini", "chatgpt"), router.activeProviderIds())
    }

    @Test
    fun `reset makes suspended provider active again`() {
        val router = ProviderFailoverRouter(listOf("chatgpt", "gemini"), maxAttemptsPerProvider = 2)

        router.reportFailure("chatgpt")
        router.reportFailure("chatgpt")
        assertTrue("chatgpt" in router.suspendedProviderIds())

        router.resetProvider("chatgpt")
        assertTrue("chatgpt" in router.activeProviderIds())
    }
}
