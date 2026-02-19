package com.example.aibrowser

/**
 * Transparent provider failover for AI requests.
 *
 * Behavior:
 * - Providers are ordered by user priority.
 * - Each provider gets maxAttemptsPerProvider attempts.
 * - If all attempts fail, provider is marked temporarily unavailable and router moves to next provider.
 */
class ProviderFailoverRouter(
    providerIdsInPriorityOrder: List<String>,
    private val maxAttemptsPerProvider: Int = 2
) {
    private val providers: MutableList<String> = providerIdsInPriorityOrder.toMutableList()
    private val failureCount: MutableMap<String, Int> = providers.associateWith { 0 }.toMutableMap()
    private val suspendedProviders: MutableSet<String> = mutableSetOf()

    init {
        require(maxAttemptsPerProvider > 0) { "maxAttemptsPerProvider must be > 0" }
    }

    fun currentProviderOrNull(): String? = providers.firstOrNull { it !in suspendedProviders }

    fun reportSuccess(providerId: String) {
        require(providerId in providers) { "Unknown provider: $providerId" }
        failureCount[providerId] = 0
    }

    /**
     * Report a failed attempt for a provider.
     * Returns true if provider became suspended after this call.
     */
    fun reportFailure(providerId: String): Boolean {
        require(providerId in providers) { "Unknown provider: $providerId" }

        val newCount = (failureCount[providerId] ?: 0) + 1
        failureCount[providerId] = newCount

        if (newCount >= maxAttemptsPerProvider) {
            suspendedProviders += providerId
            return true
        }
        return false
    }

    fun suspendedProviderIds(): List<String> = providers.filter { it in suspendedProviders }

    fun activeProviderIds(): List<String> = providers.filter { it !in suspendedProviders }

    fun resetProvider(providerId: String) {
        require(providerId in providers) { "Unknown provider: $providerId" }
        failureCount[providerId] = 0
        suspendedProviders -= providerId
    }

    fun reorderProviders(newOrder: List<String>) {
        require(newOrder.toSet() == providers.toSet()) {
            "newOrder must contain exactly the same provider ids"
        }
        providers.clear()
        providers.addAll(newOrder)
    }
}
