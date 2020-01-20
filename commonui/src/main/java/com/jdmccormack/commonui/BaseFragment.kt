package com.jdmccormack.commonui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.jdmccormack.commonui.navigation.*

abstract class BaseFragment : Fragment() {

    /**
     * The [layoutResourceId] of the resource file to be inflated in the fragment.
     */
    protected abstract val layoutResourceId: Int

    /**
     * Helper method that overrides onCreateView to automatically inflate the layout provided
     * within [layoutResourceId]
     */
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    /**
     * Fragment extension function for obtaining an [instance] of the view model through ViewModelProviders
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified V : ViewModel> Fragment.obtainViewModel(crossinline instance: () -> V): V {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return instance() as T
            }
        }
        return ViewModelProvider(this, factory).get(V::class.java)
    }

    /**
     * Sets up the fragment to listen for navigation events from a particular [viewModel]
     */
    protected fun attachNavigationListener(viewModel: NavigationViewModel) {
        configureNavigationListener(viewModel.navigationLiveDataField)
    }

    /**
     * Sets up the fragment to listen for navigation events from [navigationLiveDataField]
     */
    private fun configureNavigationListener(navigationLiveDataField: LiveNavigationField<NavigationEvent>) {
        navigationLiveDataField.observe(viewLifecycleOwner, Observer { navigate(it) })
    }

    private fun navigate(event: NavigationEvent) {
        when (event) {
            is NavigationIdEvent -> findNavController().navigate(event.navId, event.navigationArguments)
            is NavigationDeepLinkEvent -> {
                val pendingIntent = NavDeepLinkBuilder(requireContext())
                    .setGraph(findNavController().graph)
                    .setDestination(event.navId)
                    .createPendingIntent()
                pendingIntent.send()
            }
            is NavigationBackEvent -> {
                findNavController().navigate(event.navId, event.navigationArguments)
                findNavController().popBackStack(event.navId, false)
            }

        }
    }

}
