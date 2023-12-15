package ru.woyfit.core.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import ru.woyfit.core.di.WoyfitApp
import ru.woyfit.core.di.provider.CoreProvider

abstract class BaseFragment(
    @LayoutRes
    layoutId: Int = 0
): Fragment(layoutId) {
    protected var currentBackStackEntry: NavBackStackEntry? = null

    val coreComponent: CoreProvider by lazy {
        (requireActivity().applicationContext as WoyfitApp).getApplicationProvider()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try{
            currentBackStackEntry = findNavController().currentBackStackEntry
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
    abstract fun injectDependencies()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

}