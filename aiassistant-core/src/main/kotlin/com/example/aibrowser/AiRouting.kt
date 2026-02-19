package com.example.aibrowser

import kotlin.math.max

/**
 * Provider ordering + failover policy for AI requests.
 *
 * Policy:
 * - Providers are attempted in [priorityOrder] sequence.
 * - Each provider gets at most [maxAttemptsPerProvider] attempts.
 * - Each attempt is expected to use request timeout [requestTimeoutSeconds].
 * - If all attempts fail for a provider, it is marked as skipped and the next provider is selected.
 */
class AiFailoverPlanner(
    private val maxAttemptsPerProvider: Int = 2,
    private val requestTimeoutSeconds: Int = 10
) {
    init {
        require(maxAttemptsPerProvider > 0) { "maxAttemptsPerProvider must be > 0" }
        require(requestTimeoutSeconds > 0) { "requestTimeoutSeconds must be > 0" }
    }

    fun buildPlan(priorityOrder: List<String>): List<ProviderAttempt> {
        return priorityOrder.flatMap { providerId ->
            (1..maxAttemptsPerProvider).map { attempt ->
                ProviderAttempt(providerId = providerId, attempt = attempt, timeoutSeconds = requestTimeoutSeconds)
            }
        }
    }

    fun nextProvider(currentPriorityOrder: List<String>, failedProviderId: String): List<String> {
        return currentPriorityOrder.filterNot { it == failedProviderId }
    }
}

data class ProviderAttempt(
    val providerId: String,
    val attempt: Int,
    val timeoutSeconds: Int
)

/**
 * In-memory state that can be mirrored by Settings screen:
 * - user-defined provider order
 * - runtime disabled providers after repeated failures
 */
data class ProviderRuntimeState(
    val orderedProviderIds: List<String> = emptyList(),
    val disabledProviderIds: Set<String> = emptySet()
) {
    fun activeOrderedProviders(): List<String> = orderedProviderIds.filterNot { disabledProviderIds.contains(it) }

    fun markDisabled(providerId: String): ProviderRuntimeState {
        if (!orderedProviderIds.contains(providerId)) return this
        return copy(disabledProviderIds = disabledProviderIds + providerId)
    }

    fun withReorderedProviders(newOrder: List<String>): ProviderRuntimeState {
        return copy(orderedProviderIds = newOrder)
    }

    fun withProviderReenabled(providerId: String): ProviderRuntimeState {
        return copy(disabledProviderIds = disabledProviderIds - providerId)
    }
}

/**
 * Optional prompt selection model: user may choose one prompt or send no prompt.
 */
sealed class PromptSelection {
    data object None : PromptSelection()
    data class Template(val promptId: String) : PromptSelection()
}

fun resolvePromptText(selection: PromptSelection, templates: List<PromptTemplate>): String? {
    return when (selection) {
        PromptSelection.None -> null
        is PromptSelection.Template -> templates.firstOrNull { it.id == selection.promptId }?.prompt
    }
}

/**
 * Lightweight extraction for multiple-choice style responses.
 * It scans for an integer in [1..maxOption] and returns the first match.
 */
fun extractMcqOptionNumber(rawAnswer: String, maxOption: Int = 4): Int? {
    if (rawAnswer.isBlank()) return null
    val normalizedMax = max(1, maxOption)
    val regex = Regex("""\b([1-9][0-9]?)\b""")
    return regex.findAll(rawAnswer)
        .mapNotNull { it.groupValues.getOrNull(1)?.toIntOrNull() }
        .firstOrNull { it in 1..normalizedMax }
}
