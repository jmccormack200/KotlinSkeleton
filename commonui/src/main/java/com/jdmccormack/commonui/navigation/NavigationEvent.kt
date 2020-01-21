package com.jdmccormack.commonui.navigation

import android.os.Bundle
import androidx.annotation.IdRes

/**
 * Sealed Class to wrap the navigation ID and appropriate arguments that need to be in the bundle while switching between fragments.
 *
 * @param navId = the ID to navigate too
 * @param navigationArguments: Bundle of values of various parameters that need to be passed into the next screen.
 */
sealed class NavigationEvent
data class NavigationIdEvent(@IdRes val navId: Int, val navigationArguments: Bundle? = null): NavigationEvent()
data class NavigationDeepLinkEvent(val navId: Int): NavigationEvent()
data class NavigationBackEvent(val navId: Int, val navigationArguments: Bundle? = null): NavigationEvent()
