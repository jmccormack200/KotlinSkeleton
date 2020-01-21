package com.jdmccormack.commonui.navigation

import android.os.Bundle

/**
 * A standard navigation destination for navigating from one fragment to another.
 */
open class NavigationDestination(
    open val id: Int,
    open val navigationArguments: Bundle? = null
) {
    /**
     * Generates a navigation event from the [id] and [navigationArguments] of the
     * [NavigationDestination] class.
     */
    open fun buildEvent(): NavigationEvent = NavigationIdEvent(id, navigationArguments)
}

/**
 * A destination specifically used for navigating to a Deep Link.
 */
class NavigationDeepLinkDestination(override val id: Int) : NavigationDestination(id) {
    override fun buildEvent(): NavigationEvent = NavigationDeepLinkEvent(id)
}

/**
 * A Destination for navigating backwards manually instead of using the backstack.
 */
class NavigationBackDestination(override val id: Int, override val navigationArguments: Bundle?) :
    NavigationDestination(id, navigationArguments) {
    override fun buildEvent(): NavigationEvent = NavigationBackEvent(id, navigationArguments)
}

/**
 * Extension function for returning a [NavigationDeepLinkDestination] from a [NavigationDestination]
 */
fun NavigationDestination.asDeepLink(): NavigationDeepLinkDestination {
    return NavigationDeepLinkDestination(id)
}

/**
 * Extension function for returning a [NavigationBackDestination] from a [NavigationDestination]
 */
fun NavigationDestination.asBackwardsNavigation(): NavigationBackDestination {
    return NavigationBackDestination(id, navigationArguments)
}
