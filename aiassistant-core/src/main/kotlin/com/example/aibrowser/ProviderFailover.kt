package com.example.aibrowser

/**
 * Explicit provider failover pipeline:
 * - providers are ordered by priority
 * - each provider gets a fixed number of attempts
 * - failed providers can be marked unavailable and skipped
 */
class ProviderFailoverEngine {

    fun executionPlan(
        providers: List<ProviderConfig>,
        unavailableProviderIds: Set<String>,
        retryPolicy: RetryPolicySettings
    ): List<ProviderAttempt> {
        val ordered = providers
            .filterNot { unavailableProviderIds.contains(it.id) }
            .sortedWith(compareByDescending<ProviderConfig> { it.priority }.thenBy { it.displayName })

        return ordered.flatMap { provider ->
            (1..retryPolicy.maxAttemptsPerProvider).map { attempt ->
                ProviderAttempt(providerId = provider.id, displayName = provider.displayName, attempt = attempt)
            }
        }
    }
}

data class ProviderAttempt(
    val providerId: String,
    val displayName: String,
    val attempt: Int
)

class ProviderHealthRegistry {
    private val unavailableProviderIds = linkedSetOf<String>()

    fun markUnavailable(providerId: String) {
        unavailableProviderIds.add(providerId)
    }

    fun clearUnavailable(providerId: String) {
        unavailableProviderIds.remove(providerId)
    }

    fun snapshot(): Set<String> = unavailableProviderIds.toSet()
}
